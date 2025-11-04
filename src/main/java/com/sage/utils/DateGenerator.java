package com.sage.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sage.base.validators.FailureHandler.FailureHandling;
import com.sage.base.validators.*;

import java.util.Calendar;

public class DateGenerator {


	public static String getCurrentDate(String separator) {
		try {
			// Validate the separator
			if (separator == null || separator.isEmpty()) {
				separator = "."; // Default separator
			}

			// Define the date format with the given separator
			String dateFormat = "dd" + separator + "MM" + separator + "yyyy";

			// Create a SimpleDateFormat instance with the desired format
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

			// Get the current date
			Date now = new Date();

			// Format the date and return it as a string
			return sdf.format(now);
		} catch (Exception exception) {
			
			SmartUIValidator.handleSeleniumException(exception, FailureHandling.CONTINUE_ON_FAILURE, "Get Today's Date", null, "", "Automation Error: while getting the current date", "Expected Resut: today date should be returned <br> Actual Result: current Date is not generated as error occur", null);

		}

		return "";
	}



	public static String getAdjustedDate(String separator, int days, boolean isNextDays) {
		try {
			// Validate the separator
			if (separator == null || separator.isEmpty()) {
				separator = "."; // Default separator
			}

			// Define the date format with the given separator
			String dateFormat = "dd" + separator + "MM" + separator + "yyyy";

			// Create a Calendar instance to get the current date
			Calendar calendar = Calendar.getInstance();

			// Adjust the date based on the isNextDays flag
			if (isNextDays) {
				calendar.add(Calendar.DAY_OF_MONTH, days); // Add days
			} else {
				calendar.add(Calendar.DAY_OF_MONTH, -days); // Subtract days
			}

			// Create a SimpleDateFormat instance with the desired format
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

			// Get the adjusted date
			Date adjustedDate = calendar.getTime();

			// Format the date and return it as a string
			return sdf.format(adjustedDate);
		} catch (Exception exception) {
			//SmartUIValidator.addStepFailure(FailureHandling.CONTINUE_ON_FAILURE, "Automation Error: while adjusting the date", exception.getMessage());
		}

		return "";
	}
}
