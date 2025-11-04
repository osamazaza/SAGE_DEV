package com.sage.base.Report;




import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.ExtentTest;


public class ReportAttributes {

	//squad testSuiteName testCaseID test





	//public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	//public static Squad squad = null;
	//public static ExtentTest test = null;
	//public static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
	public  int numberOfScreenshotsAttached = 0;
	//public  String testSuiteCollectionName = "";
	public  String testSuiteName = "";

	public  String testCaseID = "";
	//public  String htmlFilePath = "";
	//public  String jsonreportData = "";
	//public  String imageCapturesSource = "";
	//public  String uniqueTestID = "";

	public  boolean skipCurrentTestCase = false;
	public  String blockerIssue = "";
	//public static boolean isReportLoggerStopped = false;

	public int timeout;
	public  List <String> testCaseErrorsDescription = new ArrayList <String>();
	public  List <String> testCaseErrorsSummary = new ArrayList <String>();
	public  List <String> testCaseAutomationErrors = new ArrayList <String>();

	public  List <String> testCaseSteps = new ArrayList <String>();
	public  List <String> pageSource = new ArrayList <String>();



	public void setNumberOfScreenshotsAttached(int numberOfScreenshotsAttached) {
		this.numberOfScreenshotsAttached = numberOfScreenshotsAttached;
	}



	public void setTestSuiteName(String testSuiteName) {
		this.testSuiteName = testSuiteName;
	}

	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}


	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setSkipCurrentTestCase(boolean skipCurrentTestCase) {
		this.skipCurrentTestCase = skipCurrentTestCase;
	}

	public void setBlockerIssue(String blockerIssue) {
		this.blockerIssue = blockerIssue;
	}

	public void setTestCaseErrorsDescription(List<String> testCaseErrorsDescription) {
		this.testCaseErrorsDescription = testCaseErrorsDescription;
	}

	public void setTestCaseErrorsSummary(List<String> testCaseErrorsSummary) {
		this.testCaseErrorsSummary = testCaseErrorsSummary;
	}

	public void setTestCaseAutomationErrors(List<String> testCaseAutomationErrors) {
		this.testCaseAutomationErrors = testCaseAutomationErrors;
	}

	public void setTestCaseSteps(List<String> testCaseSteps) {
		this.testCaseSteps = testCaseSteps;
	}

	public void setPageSource(List<String> pageSource) {
		this.pageSource = pageSource;
	}


	public int getNumberOfScreenshotsAttached() {
		return numberOfScreenshotsAttached;
	}



	public String getTestSuiteName() {
		return testSuiteName;
	}

	public String getTestCaseID() {
		return testCaseID;
	}


	public boolean isSkipCurrentTestCase() {
		return skipCurrentTestCase;
	}

	public String getBlockerIssue() {
		return blockerIssue;
	}

	public List<String> getTestCaseErrorsDescription() {
		return testCaseErrorsDescription;
	}

	public List<String> getTestCaseErrorsSummary() {
		return testCaseErrorsSummary;
	}

	public List<String> getTestCaseAutomationErrors() {
		return testCaseAutomationErrors;
	}

	public List<String> getTestCaseSteps() {
		return testCaseSteps;
	}

	public List<String> getPageSource() {
		return pageSource;
	}

	public static String convertTextToHyperLink(String jiraLink, String text) {
		// Return the text as a clickable hyperlink in HTML format
		return "<a href='" + jiraLink + "'>" + text + "</a>";
	}


// functions will be moved to other class.
	public static String appendUnderList(String symbol, String... errors) {
		StringBuilder html = new StringBuilder();
		html.append("<div>");
		html.append("<ul>");

		for (String error : errors) {
			html.append("<li>").append(symbol).append(" ").append(error).append("</li>");
		}

		html.append("</ul>");
		html.append("</div>");

		return html.toString();
	}

	public static void logErrors(ExtentTest test, String symbol, String... errors) {
		String collapsibleHtml = appendUnderList(symbol, errors);
		// Log the HTML directly
		test.fail(collapsibleHtml);
	}




}
