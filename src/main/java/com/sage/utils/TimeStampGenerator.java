package com.sage.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampGenerator {



	public static String generateUniqueTimestamp(String fileFormat, String testCaseName) {
		// Create a SimpleDateFormat object to format the timestamp
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		// Get the current date and time
		Date currentDate = new Date();

		// Format the current date and time using the specified format
		String timestamp = sdf.format(currentDate);

		// Return the formatted timestamp
		timestamp = "//" + testCaseName + "__" + timestamp + "." + fileFormat;

		System.out.println("Time Stamp is : " + timestamp);
		return timestamp;
	}



	public static String generateUniqueTimestamp() {
		// Create a SimpleDateFormat object to format the timestamp
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		// Get the current date and time
		Date currentDate = new Date();

		// Format the current date and time using the specified format
		String timestamp = sdf.format(currentDate);

		// Return the formatted timestamp
		return timestamp;
	}
}
