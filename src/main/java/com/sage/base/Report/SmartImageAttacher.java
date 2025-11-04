package com.sage.base.Report;

import com.sage.base.TestBase;
import com.sage.utils.SmartEncoder;
import com.sage.utils.TimeStampGenerator;
import com.sage.base.validators.SmartUIValidator;
import org.testng.annotations.Test;


public class SmartImageAttacher extends TestBase {


	public static String attachScreenshotToCustomReport(){


		try {

			SmartEncoder smartEncoder = new SmartEncoder();
			TimeStampGenerator timeStampGenerator = new TimeStampGenerator();


			//squad testSuiteName testCaseID test


			System.out.println("Attaching screen shoot to your test case...");



			String screenShootSource = TestBase.squad.getSquadImageScreenCaptureSource() + "\\Test Suites" + timeStampGenerator.generateUniqueTimestamp(".png", TestBase.getReportAttributes().testSuiteName +  "\\Test Cases" + "\\" + TestBase.getReportAttributes().getTestCaseID());



               ScreenshotUtil.captureScreenshot(getDriver(), screenShootSource);



			/// convert png to jpg
			//String newJpjImageSource = smartEncoder.convertPngToJpg(screenShootSource, 0.7f)

			String encodedImage = smartEncoder.convertFileToBase64(screenShootSource);



			getTest().addScreenCaptureFromBase64String(encodedImage);

			// test object to be global variable in uspace listener
			//ReportAttributes.test.pass("", MediaEntityBuilder.createScreenCaptureFromBase64String(encodedImage).build())

			System.out.println("Done from attaching screen shoot to your test case.");

			return screenShootSource;
		}

		catch(Exception e) {
			//SmartUIValidator.addStepFailure(FailureHandling.CONTINUE_ON_FAILURE, "Automation Error: while attach Screenshot To CustomReport ", "Expected Result: The framework should attach screenshot to report.\n Actual Result: Get Error message " + e.getMessage())
		}




		return null;
	}
}
