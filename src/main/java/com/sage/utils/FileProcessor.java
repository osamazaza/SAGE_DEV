package com.sage.utils;


import java.io.File;
import java.util.ArrayList;

// add file priocess to find files
// use excel sheet

public class FileProcessor {

    /**
     * Scans a given folder for files matching the provided format.
     *
     * @param folderPath          The path to the folder to search.
     * @param fileFormat          The file extension to look for (e.g., ".txt", ".xlsx").
     * @param returnWithFileFormat If true, returns files with extensions; if false, without extensions.
     * @return An ArrayList of file names (based on user preference).
     */
    public static ArrayList<String> getFilesListByFormat(String folderPath, String fileFormat, boolean returnWithFileFormat) {
        ArrayList<String> matchingFiles = new ArrayList<>();

        File folder = new File(folderPath);

        // Validate folder path
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path: " + folderPath);
            return matchingFiles; // return empty list
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(fileFormat.toLowerCase())) {

                    String fileName = file.getName();

                    // Remove extension if user requested so
                    if (!returnWithFileFormat) {
                        int lastDotIndex = fileName.lastIndexOf('.');
                        if (lastDotIndex > 0) {
                            fileName = fileName.substring(0, lastDotIndex);
                        }
                    }

                    matchingFiles.add(fileName);
                }
            }
        }

        return matchingFiles;
    }

    // Example test
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\user\\Desktop\\MyDataUntil2025\\Tutorials\\Cources\\Docker\\Projects\\sdbProjectLive\\src\\main\\java\\com\\sdb\\testdata\\dataDriven\\ExternalFiles\\TestSuiteCollectionDependency";
        String fileFormat = ".xlsx";

        ArrayList<String> files = getFilesListByFormat(folderPath, fileFormat, true);

        if (files.isEmpty()) {
            System.out.println("No files found with format " + fileFormat);
        } else {
            System.out.println("Files found:");
            for (String file : files) {
                System.out.println(file);
            }

            System.out.println("Number of excel sheet files is: " + files.size());

        }
    }
}
