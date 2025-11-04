package com.sage.base.dependency;

import com.sage.base.TestBase;
import com.sage.base.Report.ReportAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestCaseDependencyManager extends TestBase {

	// ------------------------ ThreadLocals ------------------------
	protected static ThreadLocal<List<String>> dependentTestCasesThread = new ThreadLocal<>();
	protected static ThreadLocal<List<String>> failedStopOnFailureTestsThread = new ThreadLocal<>();

	public static final String STOP_ON_FAILURE = "STOP_ON_FAILURE";

	public TestCaseDependencyManager() {
		// Default constructor
	}

	// ------------------------ Dependent Test Cases ------------------------
	public static void setDependentTestCases(List<String> data) {
		dependentTestCasesThread.set(data);
	}

	public static List<String> getDependentTestCases() {
		if (dependentTestCasesThread.get() == null) {
			dependentTestCasesThread.set(new ArrayList<>());
		}
		return dependentTestCasesThread.get();
	}

	// ------------------------ Failed STOP_ON_FAILURE Tests ------------------------
	public static void setFailedStopOnFailureTests(List<String> data) {
		failedStopOnFailureTestsThread.set(data);
	}

	public static List<String> getFailedStopOnFailureTests() {
		if (failedStopOnFailureTestsThread.get() == null) {
			failedStopOnFailureTestsThread.set(new ArrayList<>());
		}
		return failedStopOnFailureTestsThread.get();
	}

	// ------------------------ Fill Dependent Test Cases ------------------------
	public static void fillDependentTestCases(List<String> testcases) {

		if(getDependentTestCases() != null && !getDependentTestCases().isEmpty()){
			getDependentTestCases().clear();
			setDependentTestCases(new ArrayList<>());
			//dependentTestCasesThread.set(new ArrayList<>());
		}


		if (testcases == null || testcases.isEmpty()) return;

		for (String testString : testcases) {
			if ("*p".equals(testString)) {
				// Add all previously failed STOP_ON_FAILURE tests
				getDependentTestCases().addAll(getFailedStopOnFailureTests());
			} else {
				// Add specific test case name as dependency
				System.out.println("Test case [" + testString + "] is added to dependent list");
				getDependentTestCases().add(testString);
			}
		}
	}

	// ------------------------ Validate Failed Dependencies ------------------------
	public boolean validateFailedDep() {
		List<String> impactedTestCases = new ArrayList<>();

		System.out.println("Dependent cases for current test case: " + getDependentTestCases());
		for (String depTestCase : getDependentTestCases()) {
			if (getFailedStopOnFailureTests().contains(depTestCase)) {
				System.out.println("Current test is impacted by failed dependency: " + depTestCase);
				impactedTestCases.add(depTestCase);
			}
		}

		if (!impactedTestCases.isEmpty()) {
			String impactedMessage = String.join(", ", impactedTestCases);
			getReportAttributes().setSkipCurrentTestCase(true);
			getReportAttributes().setBlockerIssue("Impacted By Blocker Issues for test cases: " + impactedMessage);

			System.out.println("Impacted By Blocker Issue: " + getReportAttributes().getBlockerIssue());
			return true;
		}

		getReportAttributes().setSkipCurrentTestCase(false);
		return false;
	}

	// ------------------------ Add Failed Test Case ------------------------
	public static void addTestCaseDepFailure(String testCaseMethodID, String status) {
		if (STOP_ON_FAILURE.equals(status) && !getFailedStopOnFailureTests().contains(testCaseMethodID)) {
			getFailedStopOnFailureTests().add(testCaseMethodID);
			System.out.println("#_# Test Case " + testCaseMethodID + " added to failedStopOnFailureTests");
		}
	}

	// ------------------------ Clear ThreadLocals ------------------------
	public static void clearFailedTests() {
		getFailedStopOnFailureTests().clear();
	}

	public static void clearDependentTests() {
		getDependentTestCases().clear();
	}

}
