// stackover flow question: https://stackoverflow.com/questions/79733051/selenium-grid-4-dynamically-naming-video-files-before-test-start
package com.sage.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.aventstack.extentreports.reporter.JsonFormatter;
import com.sage.base.Report.ReportAttributes;
import com.sage.base.Report.WebVideoRecorder;
import com.sage.base.dependency.TestCaseDependencyLogger;
import com.sage.base.dependency.TestCaseDependencyManager;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import com.sage.integrations.EmailSender;
import com.sage.integrations.JiraDefector;
import com.sage.model.Squad;
import com.sage.testdata.dataDriven.RunTimeAttributes.SquadTestData2;
import com.sage.testdata.dataDriven.RunTimeAttributes.SquadTestData3;
import com.sage.utils.FileProcessor;
import com.sage.utils.MyTestClass;
import com.sage.utils.TimeStampGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sage.base.SquadDirector.*;
import com.sage.testdata.dataDriven.RunTimeAttributes.SharedTestData;
import com.sage.testdata.dataDriven.RunTimeAttributes.SquadTestData1;



public class TestBase {

	// public static ThreadLocal<ExtentReports> extentThread = new ThreadLocal<>();
	// // before test suite collection


	public static SquadTestData1 squadTestData1;
	public static SquadTestData2 squadTestData2;
	public static SquadTestData3 squadTestData3;

	//WebVideoRecorder webVideoRecorder;


	// was provided before in report Attributes class
	public static Squad squad = null;
	public  String imageCapturesSource = "";
	public  String uniqueTestID = "";
	public  static String htmlFilePath = "";
	public  static String jsonreportData = "";
	ChromeOptions ch;

static 	int i = 1;




	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	// private static final ExtentReports EXTENT = getInstance();
	private static ExtentReports extent;














	public static ReportAttributes getReportAttributes() {
	//System.out.println("ReportAttributesThread " + reportAttributesThread.toString());
		return reportAttributesThread.get();
	}



	public static void setReportAttributes(ReportAttributes reportAttributes) {
		reportAttributesThread.set(reportAttributes);

	}













	// public static ThreadLocal<ExtentTest> ReportAttributes.testThread = new
	// ThreadLocal<>();

	// Static instance of ExtentReports to ensure it's created only once

	SquadDataInitializer squadDataInitializer;
	static TimeStampGenerator timeStampGenerator;
	public  static String testSuiteCollectionName = "";

	// validations on test levels:

	// before test case existed(method)
	// after test case existed (method)

	// before test suite existed (test) test in xml
	// after test suite existed (test) test in xml

	// suiteCollection existed(suite in xml)

	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	protected static ThreadLocal<ReportAttributes> reportAttributesThread = new ThreadLocal<>();
	protected static ThreadLocal<FluentWait<WebDriver>> fluentWaitThread = new ThreadLocal<>();




	//public static int timeout = 40; //40
	public	static int pollying = 250;


	public static void setFluentWait(FluentWait<WebDriver> fluentWait) {
		fluentWaitThread.set(fluentWait);

	}

	public static FluentWait<WebDriver> getFluentWait() {

		return fluentWaitThread.get();
	}
	// protected WebDriver webDriver;

	public static WebDriver getDriver() {
		return driver.get();
	}

	@Parameters({"browser", "suiteCollectionName", "timeout"}) // Multiple parameters listed
	@BeforeTest(alwaysRun = true)
	public void setUp(String browser, String suiteCollectionName, String timeout) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("#1##1## Before Test");



		System.out.println("Setting up browser " + browser + " on thread: " + Thread.currentThread().getId());

		// 4.9.0

//        WebDriverListener listener = new CustomWebDriverListener();
		WebDriver webDriver = null;
		// WebDriver decoratedDriver = null;




		//String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");
		String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");







		if (browser.equalsIgnoreCase("Chrome")) {
			//String downloadPath = System.getProperty("user.dir") + "/downloads";

// will test chrome setup firstly if it working remotely, then solution will be propagated to other browsers


			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--incognito");
			options.addArguments("--disable-geolocation");






			options.setAcceptInsecureCerts(true); // Ignore certificate errors


			options.setCapability("se:name", suiteCollectionName); // Pass your methodID here

			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.PERFORMANCE, Level.ALL); // Enable performance logging
			options.setCapability("goog:loggingPrefs", logPrefs);
			//options.addArguments("--incognito");


			if ((remoteUrl == null || remoteUrl.isEmpty())) {
				System.out.println("Initilizaing chrome locally");

				// initlize locally browser

				webDriver = new ChromeDriver(options);
			}

			else{
				// complete setup for remote browser
				options.setCapability("browserName", "chrome"); // maybe be requrired for grid to recognize the browser type selected.

				try {
					webDriver = new RemoteWebDriver(new URL(remoteUrl), options);
				} catch (MalformedURLException e) {
					throw new RuntimeException(e);
				}
			}




		} else if (browser.equalsIgnoreCase("edge")) {






			EdgeOptions options = new EdgeOptions();

			options.setAcceptInsecureCerts(true); // Ignore certificate errors

			options.addArguments("--incognito");


			if ((remoteUrl == null || remoteUrl.isEmpty())) {

				// initlize locally browser
				webDriver = new EdgeDriver(options);
			}

			else{
				// complete setup for remote browser
				options.setCapability("browserName", "edge"); // maybe be requrired for grid to recognize the browser type selected.

				try {
					webDriver = new RemoteWebDriver(new URL(remoteUrl), options);
				} catch (MalformedURLException e) {
					throw new RuntimeException(e);
				}
			}





		} else if (browser.equalsIgnoreCase("firefox")) {

			//String downloadPath = System.getProperty("user.dir") + "/downloads";

// will test chrome setup firstly if it working remotely, then solution will be propagated to other browsers

			FirefoxOptions options = new FirefoxOptions();
			options.setAcceptInsecureCerts(true); // Ignore certificate errors

			options.addArguments("-private");


			if ((remoteUrl == null || remoteUrl.isEmpty())) {

				// initlize locally browser
				webDriver = new FirefoxDriver(options);
			}

			else{
				// complete setup for remote browser
				options.setCapability("browserName", "firefox"); // maybe be requrired for grid to recognize the browser type selected.

				try {
					webDriver = new RemoteWebDriver(new URL(remoteUrl), options);
				} catch (MalformedURLException e) {
					throw new RuntimeException(e);
				}
			}




		}

		driver.set(webDriver); // Set the WebDriver instance for the current thread


		// webVideoRecorder = new WebVideoRecorder();


		if (getDriver() != null) {
			System.out.println(browser + " WebDriver is initialized on thread: " + Thread.currentThread().getId());
		} else {
			System.err.println("WebDriver is not properly initialized on thread: " + Thread.currentThread().getId());
		}








		// getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		getDriver().manage().window().maximize();


		ReportAttributes reportAttributes = new ReportAttributes();
		setReportAttributes(reportAttributes);

		if(getReportAttributes() != null){
			System.out.println("get report attributes is not null");

			System.out.println("Timeout is: " + Integer.parseInt(timeout));

			getReportAttributes().setTimeout(Integer.parseInt(timeout));

		}
		else{
			System.out.println("Reprt attributes is null");
		}

		//setUpReport();
	}

	// @Parameters({ "selectedSquad", "suiteName" })




	@Parameters({ "squad", "suiteCollectionName" })
	@BeforeSuite
	public void beforeSuite(String squad, String suiteCollectionName) {
		System.out.println("#1##1## beforeSuite");

		System.out.println("selected squad " + squad);

		SharedTestData.projectPath = System.getProperty("user.dir");

		testSuiteCollectionName = suiteCollectionName;

		System.out.println("Before test suite");

		String selectedSquad = squad;




		// setup the squad info
		squadDataInitializer = new SquadDataInitializer();

		System.out.println("selectedSquad.toLowerCase().trim() " + selectedSquad.toLowerCase().trim());

		if (selectedSquad.toLowerCase().trim().equalsIgnoreCase("squad1")) {
			this.squad = squadDataInitializer.getSquad1Data();
			System.out.println("Squad 1 is inititilized");

			squadTestData1 = new SquadTestData1();


		}

		else if (selectedSquad.toLowerCase().trim().equalsIgnoreCase("squad2")) {

			this.squad  = squadDataInitializer.getSquad2Data();
			squadTestData2 = new SquadTestData2();

		}

		else if (selectedSquad.toLowerCase().trim().equalsIgnoreCase("squad3")) {

			this.squad  = squadDataInitializer.getSquad3Data();
			squadTestData3 = new SquadTestData3();

		}

		imageCapturesSource = SharedTestData.projectPath + "//Reports//CustomReport//ImageCaptures";

		System.out.println("Entering Squad: " + selectedSquad);
		System.out.println("Squad data " + this.squad);

		timeStampGenerator = timeStampGenerator;


		uniqueTestID = timeStampGenerator.generateUniqueTimestamp();

		System.out.println("Unique test id: " + uniqueTestID);

		createInstance();

		// setUpReport();

	}

	@AfterSuite(alwaysRun = true)
	public void sampleAfterTestSuite() {


		flushReport();

		System.out.println("#1##1## sampleAfterTestSuite");

		System.out.println("After suite....");

		// flushReport();
		// ReportAttributes.testThread.remove();

		if (SharedTestData.isMailReportingEnabled) {
			EmailSender emailSender = new EmailSender();


			System.out.println("Source of html document that will be sent is :" + htmlFilePath);

			String modifiedHTMLString = replaceBackslashes(htmlFilePath);






			String from = "sdbframeworkautomation@gmail.com";
			String password = "hrjv ijuw cnxq uzcu";

			String to = "osamazaza200@gmail.com,ozaza@testcrew.com, kbashir@testcrew.com";
			String cc = "usernormal18@gmail.com,Mahmoudmadboly625@yahoo.com";

			String file = "C:\\Install.txt";

			String emailTitle = "Automated Testing Report via Vega";
			String emailBody = "Please Find the automation report attachment for CI/CD testing. This automated report is generated by SAGE Framework :) ";

			// Sending two attachments (same file repeated here)
			//String attachments = file + "," + file;

			// Call the function



            emailSender.sendEmailWithAttachment(from, password, to, cc, emailTitle, emailBody, htmlFilePath);






		}

		// extentThread.remove();
		// ReportAttributes.testThread.remove();

		/*
		 * if (driver.get() != null) { // driver.get().quit(); driver.remove(); //
		 * Important to remove the ThreadLocal variable
		 *
		 * getDriver().quit();
		 *
		 * }
		 *
		 */

	}

	/*@BeforeTest
	public void beforeTest(ITestContext context) {
		String testNameFromXML = context.getName(); // This gets the <test name="..."> from testng.xml
		// ReportAttributes.testSuiteName = testNameFromXML;
		//ReportAttributes.testSuiteName = testNameFromXML;
		// System.out.println("Test Suite (test in xml) " +
		// ReportAttributes.testSuiteName);
		setUpReport();

		// issue1: one test printe two times(last test)
		System.out.println("Before Starting Test : " + ReportAttributes.testSuiteName);

	}*/

	@AfterTest
	public void afterTest(ITestContext context) {

		System.out.println("#1##1## afterTest");


		//flushReport();

		getDriver().quit();

	}

	// flushReport();

	@BeforeMethod(alwaysRun = true)
	public void beforeTestCase(ITestResult result, ITestContext iTestContext) {


 /*     if(i == 1){
		  i++;
		  throw new RuntimeException("Skipping all tests in this class");
	  }
*/




		FluentWait fluentWait = new FluentWait<>(getDriver());
		setFluentWait(fluentWait);






		System.out.println("#1##1## beforeTestCase");

		/*
		 * MyTestClass myTestClass = new MyTestClass(); String methodID =
		 * myTestClass.getUniqueIdentifier(result.getMethod().getClass(),
		 * result.getMethod().getMethodName());
		 *
		 * System.out.println("Method is :" + methodID);
		 */

		// first limitation need to address

		System.out.println("Test Suite Name::: " + iTestContext.getName());
		//ReportAttributes.testSuiteName = iTestContext.getName();
		String testSuiteName = iTestContext.getName();
		System.out.println("Test Suite Name is:-:: " + testSuiteName);


		//ReportAttributes.testSuiteName	=




		String methodName = result.getMethod().getMethodName();
		String className = result.getMethod().getTestClass().getName();
		String simpleClassName = result.getMethod().getTestClass().getRealClass().getSimpleName(); // just class name

		System.out.println("Method Name is : " + methodName);

		System.out.println("simpleClassName " + simpleClassName);

		String methodID = simpleClassName + "." + methodName;



		System.out.println("Method ID is  :" + methodID);

		getReportAttributes().setTestCaseID(methodID);
		getReportAttributes().setTestSuiteName(testSuiteName);
		setUpTestCase(methodName, testSuiteName);

		// validate test case dependency

		// get sheet dependancy document

		String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");
		String depdendencyExternalFilesPath = null;


		if ((remoteUrl == null || remoteUrl.isEmpty())) {


		//
			 depdendencyExternalFilesPath = SharedTestData.projectPath + "\\" + "src\\main\\java\\com\\sage\\testdata\\dataDriven\\External\\ExternalFiles\\TestSuiteCollectionDependency";

		}
		else{
			System.out.println("Reading doucment xlsx from container");
			depdendencyExternalFilesPath = "/app/src/main/java/com/sage/testdata/dataDriven/External/ExternalFiles/TestSuiteCollectionDependency";



		}

			ArrayList<String> depdendencyExternalFailesList = FileProcessor.getFilesListByFormat(depdendencyExternalFilesPath, "xlsx", false);

		TestCaseDependencyManager testCaseDependencyManager  = new TestCaseDependencyManager();

		System.out.println("TestCase Dep manager ID: " + testCaseDependencyManager.toString());


		Map<String, ArrayList<String>> testMapping = null;

		TestCaseDependencyLogger testCaseDependencyLogger = new TestCaseDependencyLogger();


		for(int i = 0; i<depdendencyExternalFailesList.size(); i++){

			System.out.println("depdendencyExternalFailesList.get(i).trim() " +  depdendencyExternalFailesList.get(i).trim());
			if(depdendencyExternalFailesList.get(i).trim().equalsIgnoreCase(testSuiteCollectionName)){


				depdendencyExternalFilesPath = depdendencyExternalFilesPath + "/" + testSuiteCollectionName +".xlsx";




				System.out.println("Test Suite Name ::: " + getReportAttributes().getTestSuiteName());
                System.out.println("Path of test suite collection is " + depdendencyExternalFilesPath);

				testMapping = testCaseDependencyLogger.getSuiteDependency(depdendencyExternalFilesPath, getReportAttributes().getTestSuiteName());
				if(testMapping == null){
					System.out.println("testMapping is null");
				}
				else{
					System.out.println("testMapping is NOT null and it's data is " + testMapping.toString());
				}

                System.out.println("Test Suite Collection dep file is " + depdendencyExternalFailesList.get(i));
				System.out.println("Dependent Test Case/s  for test " + methodName + " is/are: "   + testMapping.get(methodID));
				System.out.println("Method ID2 is  :" + methodID);

				testCaseDependencyManager.fillDependentTestCases(testMapping.get(methodID));

                 break;
			}
		}


			boolean failedDep = testCaseDependencyManager.validateFailedDep();



        if(failedDep){



				getReportAttributes().getTestCaseErrorsDescription().add("Issue Description 📝:<br>"
						+    getReportAttributes().getBlockerIssue());


				getReportAttributes().getTestCaseErrorsSummary().add("");

				getReportAttributes().getTestCaseAutomationErrors().add("");
				SmartUIValidator.getSoftAssert().fail(getReportAttributes().getBlockerIssue());


				TestCaseDependencyManager.addTestCaseDepFailure(methodID, "STOP_ON_FAILURE");
				System.out.println("Is skip current test cases inside update ? " + getReportAttributes().isSkipCurrentTestCase());

				//SmartUIValidator.handleSeleniumException(null, FailureHandler.FailureHandling.STOP_ON_FAILURE, "", null, null, null, null, null);


				System.out.println("The size of summary errors is: " + getReportAttributes().getTestCaseErrorsSummary().size());
				System.out.println("The size of description errors is: " + getReportAttributes().getTestCaseErrorsDescription().size());
				System.out.println("The size of description errors is: " + getReportAttributes().getTestCaseAutomationErrors().size());

				for (int i = 0; i < getReportAttributes().getTestCaseErrorsDescription().size(); i++) {

					System.out.println("Adding data result to test case ");


					System.out.println("Error is::::: " + 	getReportAttributes().getTestCaseErrorsDescription().get(i) );
//+ "Issue Description:\uD83D\uDDD2\uFE0F " +
					//	+ "Technical Issue Details:\uD83D\uDCBB "
					getTest().fail("<br>" + getReportAttributes().getTestCaseErrorsSummary().get(i) + "<br>" + "<br>" +
							getReportAttributes().getTestCaseErrorsDescription().get(i) + "<br>" +
							"<br>" + getReportAttributes().getTestCaseAutomationErrors().get(i));


					if(SharedTestData.isJiraEnabled){
						if ((remoteUrl == null || remoteUrl.isEmpty())) {
							String jiraTicketNumber = JiraDefector.createJiraDefect("DP", "Bug", getReportAttributes().getTestCaseErrorsSummary().get(i), getReportAttributes().getTestCaseErrorsDescription().get(i) + "<br>" + "<br>" + getReportAttributes().getTestCaseAutomationErrors().get(i), JiraDefector.PRIORITY_HIGH,htmlFilePath, JiraDefector.OSAMA_ZAZA_PROFILE, "Automation_label");
							System.out.println("Jira Ticket Number " + jiraTicketNumber);
							getTest().info("Jira Ticket: " +  jiraTicketNumber);

						}
					}

					// ReportAttributes.test.fail("<br>" +
					// getReportAttributes().getTestCaseErrorsSummary().get(i) +"<br>" + "<br>" +
					// getReportAttributes().getTestCaseErrorsDescription().get(i))

				}



				if (!FailureHandler.softAssertMap.isEmpty()) {
					FailureHandler.softAssertMap.clear();
					System.out.println("softAssertMap has been cleared");
				}

				if (!getReportAttributes().getTestCaseErrorsSummary().isEmpty()) {
					getReportAttributes().getTestCaseErrorsSummary().clear();
					System.out.println("testCaseErrorsSummary has been cleared");
				}

				if (!getReportAttributes().getTestCaseErrorsDescription().isEmpty()) {
					getReportAttributes().getTestCaseErrorsDescription().clear();
					System.out.println("testCaseErrorsDescription has been cleared");
				}

				if (!getReportAttributes().getTestCaseAutomationErrors().isEmpty()) {
					getReportAttributes().getTestCaseAutomationErrors().clear();
					System.out.println("testCaseAutomationErrors has been cleared");
				}


				if (!getReportAttributes().getTestCaseSteps().isEmpty()) {

					getReportAttributes().getTestCaseSteps().clear();
					System.out.println("test Case testCaseSteps for test case has been cleared");
				}



				throw new RuntimeException(getReportAttributes().getBlockerIssue());
			}












		if (!getReportAttributes().isSkipCurrentTestCase()) {


if(SharedTestData.isVideoRecordingEnabled)
{

	System.out.println("Start Recording end-to-end test By USpace, Have a good time :)");

			try {


				 remoteUrl = System.getenv("SELENIUM_REMOTE_URL");

				if (!(remoteUrl == null || remoteUrl.isEmpty())) {
					SharedTestData.isVideoRecordingEnabled = false;
				}


				//WebVideoRecorder.initializeRecorder(methodName);
				WebVideoRecorder.startRecord(methodName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Facing issue while start recording the video: " + e.getMessage());
			}

		}

		}
		uniqueTestID = timeStampGenerator.generateUniqueTimestamp();

	}

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized ExtentTest createTest(String testName, String suiteName, String author) {

		ExtentTest test = getInstance().createTest(testName).assignCategory(suiteName);
		test.assignAuthor(author);


		Capabilities caps = ((RemoteWebDriver) getDriver()).getCapabilities();

		// Get browser name and version
		String browserName = caps.getBrowserName();
		String browserVersion = caps.getBrowserVersion();

		System.out.println("Browser Name: " + browserName);
		System.out.println("Browser Version: " + browserVersion);
		test.assignDevice(browserName + "-" + " Version: " + browserVersion);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

		return test;
	}

	@AfterMethod
	public void afterEachTest(ITestResult result) {
		System.out.println("#1##1## afterEachTest");

		// ExtentTest testInstance = ReportAttributes.testThread.get();

		// Extract test method and class
		String methodName = result.getMethod().getMethodName();
		Class<?> testClass = result.getMethod().getRealClass();

		// Get unique identifier
		MyTestClass myTestClass = new MyTestClass();
		String testCaseUniqueId = myTestClass.getUniqueIdentifier(testClass, methodName);

		//System.out.println("Errror bucket: " + TestCaseDependencyManager.failedStopOnFailureTests.toString());

		//
if(SharedTestData.isVideoRecordingEnabled){
	try {
		WebVideoRecorder.stopRecord();
	} catch (Exception e) {
		// throw new RuntimeException(e);
		System.out.println("Issue for video recording (stopping)" + e.getMessage());
	}
}


		// Get test status
		int status = result.getStatus();
		String statusString;

		// This is crucial for Soft Assertions to actually fail the TestNG test
		// if it hasn't failed already by a hard assertion or an uncaught exception.


		String className = result.getMethod().getTestClass().getName();
		String simpleClassName = result.getMethod().getTestClass().getRealClass().getSimpleName(); // just class name

		System.out.println("Method Name is : " + methodName);

		System.out.println("simpleClassName " + simpleClassName);

		String methodID = simpleClassName + "." + methodName;


		if (status == ITestResult.SUCCESS) {
			statusString = "PASSED";
			TestCaseDependencyManager.addTestCaseDepFailure(methodID, "PASSED");
			getTest().pass("Passed");
			getTest().info("Test Case Steps: \uD83D\uDD22 <br> " + SmartUIValidator.getCurrentTestCaseSteps());
		}


		else if (status == ITestResult.FAILURE) {
			statusString = "FAILED";


			if (	getReportAttributes().isSkipCurrentTestCase()) {

				TestCaseDependencyManager.addTestCaseDepFailure(methodID, "STOP_ON_FAILURE");
			} else {
				TestCaseDependencyManager.addTestCaseDepFailure(methodID, "CONTINUE_ON_FAILURE");
			}


			System.out.println("The size of summary errors is: " + getReportAttributes().getTestCaseErrorsSummary().size());
			System.out.println("The size of description errors is: " + getReportAttributes().getTestCaseErrorsDescription().size());
			System.out.println("The size of description errors is: " + getReportAttributes().getTestCaseAutomationErrors().size());

			for (int i = 0; i < getReportAttributes().getTestCaseErrorsDescription().size(); i++) {

				System.out.println("Adding data result to test case ");

//+ "Issue Description:\uD83D\uDDD2\uFE0F " +
				//	+ "Technical Issue Details:\uD83D\uDCBB "
				getTest().fail("<br>" + getReportAttributes().getTestCaseErrorsSummary().get(i) + "<br>" + "<br>" +
						getReportAttributes().getTestCaseErrorsDescription().get(i) + "<br>" +
						"<br>" + getReportAttributes().getTestCaseAutomationErrors().get(i));


				String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");


				if(SharedTestData.isJiraEnabled){
					if ((remoteUrl == null || remoteUrl.isEmpty())) {

						String jiraTicketNumber = JiraDefector.createJiraDefect("DP", "Bug", getReportAttributes().getTestCaseErrorsSummary().get(i), getReportAttributes().getTestCaseErrorsDescription().get(i) + "<br>" + "<br>" + getReportAttributes().getTestCaseAutomationErrors().get(i), JiraDefector.PRIORITY_HIGH,htmlFilePath, JiraDefector.OSAMA_ZAZA_PROFILE, "Automation_label");
						System.out.println("Jira Ticket Number " + jiraTicketNumber);

						getTest().info("Jira Ticket: " +  jiraTicketNumber);

					}
				}




				// ReportAttributes.test.fail("<br>" +
				// getReportAttributes().getTestCaseErrorsSummary().get(i) +"<br>" + "<br>" +
				// getReportAttributes().getTestCaseErrorsDescription().get(i))

			}
			//System.out.println("Page Source:::: " + getDriver().getPageSource());
			// getTest().fail(MarkupHelper.createLabel(getDriver().getPageSource().toString(),
			// ExtentColor.RED));

		}




		if (!getReportAttributes().getTestCaseErrorsSummary().isEmpty()) {
			getReportAttributes().getTestCaseErrorsSummary().clear();
			System.out.println("testCaseErrorsSummary has been cleared");
		}

		if (!getReportAttributes().getTestCaseErrorsDescription().isEmpty()) {
			getReportAttributes().getTestCaseErrorsDescription().clear();
			System.out.println("testCaseErrorsDescription has been cleared");
		}

		if (!getReportAttributes().getTestCaseAutomationErrors().isEmpty()) {
			getReportAttributes().getTestCaseAutomationErrors().clear();
			System.out.println("testCaseAutomationErrors has been cleared");
		}

		if (!FailureHandler.softAssertMap.isEmpty()) {
			FailureHandler.softAssertMap.clear();
			System.out.println("softAssertMap has been cleared");
		}

		// add error new paramter
		// check adding for summary and description


		//System.out.println("Steps of test case " + getReportAttributes().getTestCaseSteps());


		if (!getReportAttributes().getTestCaseSteps().isEmpty()) {

			getReportAttributes().getTestCaseSteps().clear();
			System.out.println("test Case testCaseSteps for test case has been cleared");
		}

		// ReportAttributes.testThread.remove();

	}



	private synchronized void flushReport() { // Not synchronized
		if (getInstance() != null) {
			getInstance().flush();
			System.out.println("Flush report result now");

		}
	}

	public synchronized static void drawRectangle(String text, String symbol) {
		/*
		int length = text.length() + 4; // Padding of 2 on each side
		String horizontalLine = symbol.repeat(length);

		System.out.println(horizontalLine);
		System.out.println(symbol + " " + text + " " + symbol);
		System.out.println(horizontalLine);

		 */
	}

	synchronized static String replaceBackslashes(String inputString) {
		// Check if the input string is not null
		if (inputString != null) {
			// Replace backslashes with double forward slashes
			return inputString.replaceAll("\\\\", "//");
		} else {
			// If input string is null, return null
			return null;
		}
	}

	private void setUpTestCase(String testCaseID, String testSuiteName) {
		System.out.println("#1##1## setUpTestCase");

		getReportAttributes().setNumberOfScreenshotsAttached(0);

		drawRectangle("Start Executing test case: " + testCaseID, "*");
		System.out.println("ReportAttributes.testSuiteName: " + testSuiteCollectionName);

		// ReportAttributes.testCaseID = testCaseID;
		createTest(testCaseID, testSuiteName, "Automation team");

		if (getTest() != null) {

			System.out.println("Test is not nulll!!!");

//getDriver().

/*
			Capabilities caps = ((RemoteWebDriver) getDriver()).getCapabilities();



			String browserName = caps.getBrowserName();
			String browserVersion = caps.getCapability("browserVersion").toString();

			System.out.println(browserName + "-" + "V::::/ " + browserVersion);

			getTest().assignDevice(browserName + "-" + "V: " + browserVersion);
			// test.assignDevice(browserName + "-" + "V: " + browserVersion);
*/




		}

		// ReportAttributes.testThread.set(test);

	}

	public synchronized static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}

		return extent;
	}

	public synchronized static ExtentReports createInstance() {
		String reportTimeStamp = timeStampGenerator.generateUniqueTimestamp();
		String xx = squad.getSquadMainSource() + File.separator;

		System.out.println("reportPath xx: " + xx);
		String reportPath = "";

		String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");

		if ((remoteUrl == null || remoteUrl.isEmpty())) {

			// browser running in local then use normal path
			reportPath = squad.getSquadMainSource() + File.separator;


		}
		else{
			// otherwise use docker path
			reportPath = "/app/Reports/CustomReport/";

		}





		/// //




		htmlFilePath = reportPath + "//" + "AutomationReport_" + reportTimeStamp
				+ testSuiteCollectionName + ".html"; // ReportAttributes.testSuiteName
		jsonreportData = reportPath + "//" + "AutomationReport_" + reportTimeStamp
				+ testSuiteCollectionName + ".json";// ReportAttributes.testSuiteName

		System.out.println("HTML FILE: " + htmlFilePath);

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(htmlFilePath); // The
		// sparkReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		// sparkReporter.config().setChartVisibilityOnOpen(true);
		sparkReporter.config().setTheme(Theme.STANDARD);
		// sparkReporter.config().setDocumentTitle(fileName);
		sparkReporter.config().setEncoding("utf-8");

		extent = new ExtentReports();




//
		System.out.println("Setup Report...");

		drawRectangle("Start Generating Custom Report for your test :)", "📊");

		sparkReporter.config().thumbnailForBase64(true);
		// sparkReporter.config().setCss("body { font-size: 14px; }")
		JsonFormatter json = new JsonFormatter(jsonreportData);
		try {
			extent.createDomainFromJsonArchive(jsonreportData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extent.attachReporter(json, sparkReporter);

		// sparkReporter.config().(Theme.DARK)
		sparkReporter.config().setTimelineEnabled(true);
		sparkReporter.config().setDocumentTitle("UI Automation Report");

		String logoPath = SharedTestData.projectPath + "/sage.jpg";
		sparkReporter.config().setReportName(
				"<span class='report-logo'><img src=' " + logoPath + "' width='300' height='100'/></span>");

		sparkReporter.config().setTheme(Theme.DARK);

		extent.setAnalysisStrategy(AnalysisStrategy.TEST);
		extent.attachReporter(sparkReporter);

		return extent;
	}
/*
	@AfterClass
	public void afterClass() {
		//getDriver().quit();

	}*/

	public static String toHyperlink(String text, String url) {
		if (text == null || url == null) {
			return "";
		}
		return "<a href=\"" + url + "\">" + text + "</a>";
	}


}
