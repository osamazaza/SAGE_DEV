package com.sage.testdata.dataDriven.External;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExternalDataDrivenTestUtil {

    // Helper to format cell data as a String, safely handling various types
    private final DataFormatter dataFormatter = new DataFormatter();

    public Object[][] readDataFromExcelSheetFile(String excelSheetPath, String sheetName, boolean readWhole, int rowIndex) {
        Object[][] sheetDataAsArr = null;

        try (FileInputStream fileInputStream = new FileInputStream(new File(excelSheetPath));
             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheetData = workbook.getSheet(sheetName);

            if (sheetData == null) {
                throw new IllegalArgumentException("Sheet " + sheetName + " not found in " + excelSheetPath);
            }

            // Decide whether to read the whole sheet or a specific row
            if (readWhole) {
                sheetDataAsArr = readWholeDataFromExcelSheet(sheetData);
            } else {
                sheetDataAsArr = readSpecificDataFromSheet(sheetData, rowIndex);
            }

        } catch (FileNotFoundException e) {
            System.err.println("The Excel file is not found: " + excelSheetPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sheetDataAsArr;
    }

    private Object[][] readWholeDataFromExcelSheet(Sheet sheetData) {
        Row headerRow = sheetData.getRow(0);

        if (headerRow == null) {
            return new Object[0][0]; // Handle empty sheet
        }

        int columns = headerRow.getLastCellNum();
        int lastRowIndex = sheetData.getLastRowNum();

        List<Object[]> dataList = new ArrayList<>();

        // r=1 to skip header row (Excel row 2)
        for (int r = 1; r <= lastRowIndex; r++) {
            Row row = sheetData.getRow(r);

            // Check for a null row (an empty row index)
            if (row == null) {
                // If a row is null, we can often stop, assuming no more meaningful data follows.
                break;
            }

            Object[] rowData = new Object[columns];
            boolean hasData = false;

            for (int c = 0; c < columns; c++) {
                // Use the helper function to get cell value, handling merged regions
                String cellValue = getCellValueConsideringMergedRegions(sheetData, r, c);

                rowData[c] = cellValue;

                // Track if this row contains any data
                if (cellValue != null && !cellValue.trim().isEmpty()) {
                    hasData = true;
                }
            }

            // Only add the row to the list if it contained data (to exclude trailing empty rows)
            if (hasData) {
                dataList.add(rowData);
            }
        }

        // Convert the List of Object arrays back to a 2D Object array
        return dataList.toArray(new Object[0][0]);
    }

    private Object[][] readSpecificDataFromSheet(Sheet sheetData, int rowIndex) {
        Row headerRow = sheetData.getRow(0);
        if (headerRow == null) {
            return new Object[0][0];
        }
        int columns = headerRow.getLastCellNum();
        Object[][] data = new Object[1][columns];

        Row row = sheetData.getRow(rowIndex);

        if (row == null) {
            // Return an array of empty strings for the row if it doesn't exist
            Arrays.fill(data[0], "");
            return data;
        }

        for (int c = 0; c < columns; c++) {
            // Use the helper function to get cell value, handling merged regions
            data[0][c] = getCellValueConsideringMergedRegions(sheetData, rowIndex, c);
        }

        return data;
    }

    /**
     * Finds the cell value, considering if the cell is part of a merged region.
     * If part of a merge, it returns the value of the top-left cell of the region.
     * If the cell is null, it returns an empty string.
     */
    private String getCellValueConsideringMergedRegions(Sheet sheetData, int rowIdx, int colIdx) {
        // Iterate over all merged regions in the sheet
        for (int i = 0; i < sheetData.getNumMergedRegions(); i++) {
            CellRangeAddress range = sheetData.getMergedRegion(i);

            // Check if the current cell (rowIdx, colIdx) is within the merged region
            if (range.isInRange(rowIdx, colIdx)) {
                // If it is, get the value from the top-left cell (first row and first column of the range)
                Row row = sheetData.getRow(range.getFirstRow());
                if (row != null) {
                    Cell cell = row.getCell(range.getFirstColumn());
                    if (cell != null) {
                        // Use DataFormatter to correctly handle different cell types
                        return dataFormatter.formatCellValue(cell);
                    }
                }
                // Return empty string for merged but empty top-left cell
                return "";
            }
        }

        // If the cell is not part of any merged region, get its value directly
        Row row = sheetData.getRow(rowIdx);
        if (row != null) {
            Cell cell = row.getCell(colIdx);
            if (cell != null) {
                // Use DataFormatter for standard cell formatting
                return dataFormatter.formatCellValue(cell);
            }
        }

        // Return empty string for a non-merged null cell or null row
        return "";
    }
}