package com.sage.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sage.base.validators.SmartUIValidator;
import com.sage.base.validators.FailureHandler.FailureHandling;

public class DataSorter {



	public static List<String> sortStringList(List<String> list, boolean descending, FailureHandling failureHandling) {
		try {
			// Check if the current test case is skipped
				if (list == null) {
					throw new IllegalArgumentException("List cannot be null");
				}

				// Define the comparator
				Comparator<String> comparator = Comparator.naturalOrder();

				// Apply the comparator and sorting order
				if (descending) {
					comparator = comparator.reversed();
				}

				// Sort the list using the comparator
				Collections.sort(list, comparator);

				System.out.println("Sorted string list: " + list);
				return list;


		}
		
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			String defectSummary = "Failed to sort list ";
			String defectDescription =  "Expected Result: the String list "+list.toString()+" should be sorted sucessfully. <br> Actual Result: facing issue while sorting the list. ";
			
			SmartUIValidator.handleSeleniumException(null, failureHandling, "Sort List", null, "", defectSummary, defectDescription, "Faced issue while sorting the list: " + e.getMessage() );
			
		}

		// Return an empty list if skipped or an error occurs
		return new ArrayList<>();
	}

	public static List<Double> sortList(List<Double> list, boolean descending, FailureHandling failureHandling) {
		List<Double> sortedList = new ArrayList<>();
		try {
			// Check if the current test case is skipped
				if (list == null || list.isEmpty()) {
					System.out.println("Provided list is null or empty. Returning an empty list.");
					return sortedList; // Return empty list
				}

				sortedList.addAll(list); // Create a copy of the input list
				if (descending) {
					Collections.sort(sortedList, Collections.reverseOrder());
				} else {
					Collections.sort(sortedList);
				}

				System.out.println("Sorted numeric list: " + sortedList);

			
			
		} 
		

		catch (Exception e) {
			// TODO Auto-generated catch block
			String defectSummary = "Failed to sort double list ";
			String defectDescription =  "Expected Result: the numeric list "+list.toString()+" should be sorted sucessfully. <br> Actual Result: facing issue while sorting the list. ";
			
			SmartUIValidator.handleSeleniumException(null, failureHandling, "Sort List", null, "",defectSummary, defectDescription, "Faced issue while sorting the list: " + e.getMessage() );

		}



		return sortedList; // Return sorted list or empty list
	}
}
