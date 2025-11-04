package com.sage.base.dependency;

import com.sage.utils.MyTestClass;
import com.sage.testdata.dataDriven.External.ExternalDataDrivenTestUtil;
import java.util.*;

public class TestCaseDependencyLogger {


    private final MyTestClass myTestClass = new MyTestClass();

    public Map<String, ArrayList<String>> getSuiteDependency(
            String excelSheetSuiteCollectionPath,
            String testSuiteSheetName) {



        Map<String, ArrayList<String>> testCasesDep = new HashMap<>();
        Map<String, String> testCaseIdMapper = new HashMap<>();

        // Read the full data from the sheet
        ExternalDataDrivenTestUtil externalDataDrivenTestUtil = new ExternalDataDrivenTestUtil();
        Object[][] sheetData = externalDataDrivenTestUtil.readDataFromExcelSheetFile(
                excelSheetSuiteCollectionPath,
                testSuiteSheetName,
                true,
                -1);

        if (sheetData == null || sheetData.length == 0) {
            System.out.println("No data found in sheet: " + testSuiteSheetName);
            return testCasesDep;
        }

        // --- STAGE 1: Create a MAPPING of all available Test Case IDs ---
        // We iterate through all rows to find all unique Test Case/Class pairs and their Dependent Case/Class pairs.

        // Define column indices based on your file structure:
        // Index 0: Test Case (Method Name)
        // Index 1: Test Case Class (Class Name)
        // Index 2: Dependent Test Case (Dependent Method Name)
        // Index 3: Dependent Test Class (Dependent Class Name) <-- ASSUMED NEW COLUMN
        final int COL_TEST_CASE = 0;
        final int COL_TEST_CLASS = 1;
        final int COL_DEP_TEST_CASE = 2;
        final int COL_DEP_TEST_CLASS = 3; // Assuming this new column is present

        for (Object[] row : sheetData) {
            if (row.length < 4) {
                System.err.println("Skipping row: Expected 4 columns for dependency mapping (TestCase, TestClass, DepTestCase, DepTestClass).");
                continue;
            }

            String testMethod = (String) row[COL_TEST_CASE];
            String testClass = (String) row[COL_TEST_CLASS];
            String depTestMethod = (String) row[COL_DEP_TEST_CASE];
            String depTestClass = (String) row[COL_DEP_TEST_CLASS];

            // Generate ID for the CURRENT Test Case
            if (!testMethod.isEmpty() && !testClass.isEmpty()) {
                String key = testClass + "_" + testMethod;
                if (!testCaseIdMapper.containsKey(key)) {
                    // *** NOTE: Class.forName() required for myTestClass.getUniqueIdentifier to work ***
                    // We must assume a simple String concatenation here unless you can resolve the Class object.
                    // If myTestClass.getUniqueIdentifier requires Class<?>, you MUST implement Class resolution.
                    // Example using simple concatenation as a fallback:
                    String uniqueId = testClass + "." + testMethod;
                    testCaseIdMapper.put(key, uniqueId);
                }
            }

            // Generate ID for the DEPENDENT Test Case
            if (!depTestMethod.isEmpty() && !depTestClass.isEmpty()) {
                String key = depTestClass + "_" + depTestMethod;
                if (!testCaseIdMapper.containsKey(key)) {
                    String uniqueId = depTestClass + "." + depTestMethod;
                    testCaseIdMapper.put(key, uniqueId);
                }
            }
        }

        // --- STAGE 2: BIND Test Case IDs with Dependent Test Case IDs ---
        for (Object[] row : sheetData) {
            if (row.length < 4) {
                continue; // Already logged in Stage 1
            }

            String testMethod = (String) row[COL_TEST_CASE];
            String testClass = (String) row[COL_TEST_CLASS];
            String depTestMethod = (String) row[COL_DEP_TEST_CASE];
            String depTestClass = (String) row[COL_DEP_TEST_CLASS];

            if (testMethod.isEmpty() || testClass.isEmpty() || depTestMethod.isEmpty() || depTestClass.isEmpty()) {
                continue; // Skip rows that don't define a dependency fully
            }

            // Get the Unique ID for the current test case (the KEY)
            String currentTestKey = testClass + "_" + testMethod;
            String currentTestCaseID = testCaseIdMapper.get(currentTestKey);

            // Get the Unique ID for the dependent test case (the VALUE)
            String depTestKey = depTestClass + "_" + depTestMethod;
            String dependentTestCaseID = testCaseIdMapper.get(depTestKey);

            if (currentTestCaseID == null || dependentTestCaseID == null) {
                System.err.println("Could not resolve ID for dependency in row: " + Arrays.toString(row));
                continue;
            }

            // Map the dependency: Handle multiple dependencies across rows
            testCasesDep.computeIfAbsent(currentTestCaseID, k -> new ArrayList<>())
                    .add(dependentTestCaseID);
        }

        return testCasesDep;
    }

    // NOTE: This placeholder shows where your actual unique ID generation would go
    /* private String resolveUniqueIdentifier(String className, String methodName) {
        try {
            Class<?> testClass = Class.forName(className);
            return myTestClass.getUniqueIdentifier(testClass, methodName);
        } catch (ClassNotFoundException e) {
            // Log error or throw exception if the class can't be found
            System.err.println("Class not found: " + className);
            return className + "." + methodName; // Fallback
        }
    }
    */















    public Map<String, ArrayList <String>> RequestForLoanRequestSuiteDependencyLogger(){

        ExternalDataDrivenTestUtil dataUtil = new ExternalDataDrivenTestUtil();


        Map<String, ArrayList<String>> testCasesDepList = getSuiteDependency("C:\\Users\\user\\Desktop\\MyDataUntil2025\\Tutorials\\Cources\\Docker\\Projects\\sdbProjectLive\\src\\main\\java\\com\\sage\\testdata\\dataDriven\\External\\DataDrivenTemplete.xlsx", "createLoanRequestSuite");

        for (Map.Entry<String, ArrayList<String>> entry : testCasesDepList.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> values = entry.getValue();

            System.out.println("Key: " + key);
            System.out.println("Values: " + values); // prints list directly
            // Or iterate through values individually
            for (String value : values) {
                System.out.println(" - " + value);
            }
        }
        return testCasesDepList;


    }













    public void JustForDebugging(){

        ExternalDataDrivenTestUtil dataUtil = new ExternalDataDrivenTestUtil();


        Map<String, ArrayList<String>> list = getSuiteDependency("C:\\Users\\user\\Desktop\\MyDataUntil2025\\Tutorials\\Cources\\Docker\\Projects\\sdbProjectLive\\src\\main\\java\\com\\sage\\testdata\\dataDriven\\External\\DataDrivenTemplete.xlsx", "createLoanRequest");

        for (Map.Entry<String, ArrayList<String>> entry : list.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> values = entry.getValue();

            System.out.println("Key: " + key);
            System.out.println("Values: " + values); // prints list directly
            // Or iterate through values individually
            for (String value : values) {
                System.out.println(" - " + value);
            }
        }
    }




}

