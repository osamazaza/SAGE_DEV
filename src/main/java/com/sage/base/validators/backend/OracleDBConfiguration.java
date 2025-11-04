package com.sage.base.validators.backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

import com.sage.base.validators.FailureHandler;
import com.sage.base.TestBase;
import oracle.jdbc.driver.OracleDriver;

import java.sql.Statement;

import com.sage.base.validators.SmartUIValidator;
import com.sage.base.TestBase;


// this class is used to connect to oracle database.
public class OracleDBConfiguration {

	private static  String URL = "";
	private static  String USERNAME = "";// case sensitive
	private static  String PASSWORD = "";// case sensitive
	private static Connection theConnection = null;
	private static Statement statement = null;


	public static String getURL() {
		return URL;
	}

	public static String getUSERNAME() {
		return USERNAME;
	}

	public static String getPASSWORD() {
		return PASSWORD;
	}

	public static void setURL(String URL) {
		OracleDBConfiguration.URL = URL;
	}

	public static void setUSERNAME(String USERNAME) {
		OracleDBConfiguration.USERNAME = USERNAME;
	}

	public static void setPASSWORD(String PASSWORD) {
		OracleDBConfiguration.PASSWORD = PASSWORD;
	}

	public static void openOracleConnection(FailureHandler.FailureHandling failureHandling) {


		if(!TestBase.getReportAttributes().isSkipCurrentTestCase()) {

			SmartUIValidator.addStep(
					"Database Connection Details:\n" +
					"-------------------------------\n" +
					"Database URL      : " + URL + "\n" +
					"Username          : " + USERNAME + "\n" +
					"Password          : " + PASSWORD + "\n" +  // Don't log sensitive information
					"Connection Status : Attempting to connect to the database..."
					);

			boolean isThereExeception = false;

			try {
				DriverManager.registerDriver(new OracleDriver());

				theConnection =  DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (SQLException e) {

				String defectSummary = "Failed to Open Oracle Connection";
				String defectDescription =  "Expected Result: Oracle DB connection should be established. \n Actual Result: " + "Execution Status: Failed to establish DB oracle Connection ";
				
				SmartUIValidator.handleSeleniumException(null, failureHandling, "Open Oracle Connection", null, "",defectSummary, defectDescription, "DB Oracle Connection issue: " + e.getMessage() );
				

			}



			if (theConnection == null) {
				System.out.println("Connection is null");
			}
			else {
				System.out.println("Connection is passed");
			}
		} // end of if statement


		else {

			if(TestBase.getReportAttributes().getTestCaseErrorsSummary().isEmpty()) {
				// this is mean that we are in new test case (not in sub test case), so we need to stop it's execution + attach blocker issue
				
				
				SmartUIValidator.handleSeleniumException(null, failureHandling, "Open Oracle Connection", null, "","", TestBase.getReportAttributes().getBlockerIssue(), "" );

				
			}

			else {
				// this means we are in sub test case, and we just need to stop execution since blocker issue has been raisen
				
				
				SmartUIValidator.handleSeleniumException(null, failureHandling, "Open Oracle Connection", null, "", "", "", "" );

				
			}
		}
	} // end of openOracleConnection

	public static Statement createStatement(FailureHandler.FailureHandling  failureHandling) {
		if (!TestBase.getReportAttributes().isSkipCurrentTestCase() && theConnection != null) {
			try {
				return statement = theConnection.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				String defectSummary = "Failed to create oracle statement";
				String defectDescription =  "Expected Result: query statement should be created sucessfully. \n Actual Result: " + "Execution Status: query oracle statement is not created sucessfully";
				
				SmartUIValidator.handleSeleniumException(null, failureHandling, "Oracle Create Statement", null, "",defectSummary, defectDescription, "DB Oracle Connection issue: " + e.getMessage() );
				
			}
		}

		else {

			if(TestBase.getReportAttributes().getTestCaseErrorsSummary().isEmpty()) {
				// this is mean that we are in new test case (not in sub test case), so we need to stop it's execution + attach blocker issue
				
				
				SmartUIValidator.handleSeleniumException(null, failureHandling, "Oracle Create Statement", null, "", "", TestBase.getReportAttributes().getBlockerIssue(), "" );

				
			}

			else {
				// this means we are in sub test case, and we just need to stop execution since blocker issue has been raisen
				
				
				SmartUIValidator.handleSeleniumException(null, failureHandling, "Oracle Create Statement", null, "", "", "", "" );

				
			}
		}

		return null;
	}



	public static int executeUpdate(String sqlUpdateQuery, FailureHandler.FailureHandling  failureHandling) {

		if(!TestBase.getReportAttributes().isSkipCurrentTestCase() && statement != null) {

			int rowsAffected = -1;
			try {
				rowsAffected =  statement.executeUpdate(sqlUpdateQuery);


				SmartUIValidator.addStep(
						"Executing SQL Update Query:\n" +
						"-------------------------------\n" +
						"SQL Query: " + sqlUpdateQuery + "\n" +
						"Rows Effected:  " + rowsAffected + " Rows."
						);
			} catch(SQLException e) {

				String defectSummary = "Failed to Execute Update Query";
				String defectDescription =  "Expected Result: The update query "+sqlUpdateQuery+" should work. \n Actual Result: " + "Execution Status: Failed to execute query. ";
				SmartUIValidator.handleSeleniumException(e, failureHandling, "Open Execute Update", null, "", defectSummary, defectDescription, "Failed to execute update: " + e.getMessage()  );
			}



			return rowsAffected;
		}

		else {

			if(TestBase.getReportAttributes().getTestCaseErrorsSummary().isEmpty()) {
				// this is mean that we are in new test case (not in sub test case), so we need to stop it's execution + attach blocker issue
				
				
				SmartUIValidator.handleSeleniumException(null, failureHandling, "Oracle execute Update", null, "", "", TestBase.getReportAttributes().getBlockerIssue(), "" );

				
			}

			else {
				// this means we are in sub test case, and we just need to stop execution since blocker issue has been raisen
				
				
				SmartUIValidator.handleSeleniumException(null, failureHandling, "Oracle execute Update", null, "","", "", "" );

				
			}
		}
		return -1;
	}

	
	
	
	public static ResultSet executeQuery(String sqlQuery, FailureHandler.FailureHandling  failureHandling) {
		if (!TestBase.getReportAttributes().isSkipCurrentTestCase() && statement != null) {
			try {
				SmartUIValidator.addStep(
						"Executing SQL Query:\n" +
						"-------------------------------\n" +
						"SQL Query: " + sqlQuery + "\n" +
						"Execution Status: Attempting to execute query..."
						);

				// Execute the query and return the ResultSet
				return statement.executeQuery(sqlQuery);
			} catch (Exception e) {

				String defectSummary = "Failed to Execute SQL Query";
				String defectDescription =  "Expected Result: The update query "+sqlQuery+" should work. \n Actual Result: " + "Execution Status: Failed to execute query. ";
				
				
				SmartUIValidator.handleSeleniumException(e, failureHandling, "Open Execute Query", null, "", defectSummary, defectDescription, "Failed to execute Query: " + e.getMessage()  );

				
			}
		}

		else {

			if(TestBase.getReportAttributes().getTestCaseErrorsSummary().isEmpty()) {
				// this is mean that we are in new test case (not in sub test case), so we need to stop it's execution + attach blocker issue
				
				
				SmartUIValidator.handleSeleniumException(null, failureHandling, "Oracle execute Query", null, "","", TestBase.getReportAttributes().getBlockerIssue(), "" );

				
			}

			else {
				// this means we are in sub test case, and we just need to stop execution since blocker issue has been raisen
				
				
				SmartUIValidator.handleSeleniumException(null, failureHandling, "Oracle execute Query", null, "", "", "", "" );

				
			}
		}
		// Return null or handle skipped test case appropriately
		return null;
	}

	
	public static void closeConnection(FailureHandler.FailureHandling  failureHandling) {
		if (!TestBase.getReportAttributes().isSkipCurrentTestCase()) {
			SmartUIValidator.addStep("Close SIT Oracle DB Connection");
			try {
				theConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block

				String defectSummary = "Failed to Close SQL Connection";
				String defectDescription =  "Expected Result: The Oracle connection should be terminated sucessfully <br> Actual Result: oracle connection has not been closed";
				
				
				SmartUIValidator.handleSeleniumException(e, failureHandling, "Open Execute Query", null,  "",defectSummary, defectDescription, "Failed to execute Query: " + e.getMessage()  );

				
				
				
			}
		}

		else {

			if(TestBase.getReportAttributes().getTestCaseErrorsSummary().isEmpty()) {
				// this is mean that we are in new test case (not in sub test case), so we need to stop it's execution + attach blocker issue
				
				
				SmartUIValidator.handleSeleniumException(null, failureHandling, "Oracle execute Query", null, "", "", TestBase.getReportAttributes().getBlockerIssue(), "" );

				
			}

			else {
				// this means we are in sub test case, and we just need to stop execution since blocker issue has been raisen
				
				
				SmartUIValidator.handleSeleniumException(null, failureHandling, "Oracle execute Query", null, "", "", "", "" );

				
			}
		}
	}
}
