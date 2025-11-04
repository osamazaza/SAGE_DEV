package com.sage.utils;

import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;

import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;

import java.util.regex.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class SmartDateFormatter {

	
	
	
	
	
    public static boolean isFirstDateLargerOrEqual(String firstDate, String secondDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        try {
            Date date1 = dateFormat.parse(firstDate);
            Date date2 = dateFormat.parse(secondDate);
            return !date1.before(date2);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd-MM-yyyy.");
        }
    }

    public static String reverseDateOrder(String inputDateToReverse) {
        if (inputDateToReverse == null || inputDateToReverse.trim().isEmpty()) {
            throw new IllegalArgumentException("Input date cannot be null or empty.");
        }

        String regex1 = "^(\\d{4})([-./])(\\d{1,2})\\2(\\d{1,2})$";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(inputDateToReverse);

        if (matcher1.matches()) {
            String year = matcher1.group(1);
            String separator = matcher1.group(2);
            String month = matcher1.group(3);
            String day = matcher1.group(4);
            return day + separator + month + separator + year;
        }

        String regex2 = "^(\\d{1,2})([-./])(\\d{1,2})\\2(\\d{4})$";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(inputDateToReverse);

        if (matcher2.matches()) {
            String day = matcher2.group(1);
            String separator = matcher2.group(2);
            String month = matcher2.group(3);
            String year = matcher2.group(4);
            return year + separator + month + separator + day;
        }

        throw new IllegalArgumentException("Input date format is not supported. Please use a valid date format.");
    }

    public static String gregorianToHijriString(int gregorianYear, int gregorianMonth, int gregorianDay) {
        try {
            LocalDate gregorianDate = LocalDate.of(gregorianYear, gregorianMonth, gregorianDay);
            HijrahChronology hijriChronology = HijrahChronology.INSTANCE;
            HijrahDate hijriDate = HijrahDate.from(gregorianDate);

            return removeDatePrefix(hijriDate.toString());
        } catch (Exception exception) {
        	
        	
        
        	SmartUIValidator.handleSeleniumException(exception, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE
        			, "Reverse gregorian Date to Hijri:<br> Year: "+gregorianYear+" Month: "+gregorianMonth+"  Day: "+gregorianDay+"  ", null, "","Error while converting Gregorian to Hijri", null, null);

        	/*
            SmartUIValidator.addStepFailure(FailureHandling.CONTINUE_ON_FAILURE,
                    "Automation Error: while converting Gregorian to Hijri",
                    exception.getMessage());
                    */
        }
        return "";
    }

    public static String hijriToGregorianString(int hijriYear, int hijriMonth, int hijriDay) {
        try {
            HijrahChronology hijriChronology = HijrahChronology.INSTANCE;
            HijrahDate hijriDate = HijrahDate.of(hijriYear, hijriMonth, hijriDay);
            LocalDate gregorianDate = LocalDate.from(hijriDate);
            return gregorianDate.toString();
        } catch (Exception exception) {
            
        	
        	SmartUIValidator.handleSeleniumException(exception, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE
        			, "Reverse gregorian Date to Hijri:<br> Year: "+hijriYear+" Month: "+hijriMonth+"  Day: "+hijriDay+"  ", null, "","Error while converting hijri to Gregorian", null, null);
        	
        
        }
        return "";
    }

    public static String replaceDateSeparator(String dateString, String newSeparator) {
        try {
            String[] parts = dateString.split("/");
            return String.join(newSeparator, parts);
        } catch (Exception exception) {


        	SmartUIValidator.handleSeleniumException(exception, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE
        			, "Replace Date's seperator "+dateString+" to seperator "+newSeparator+" ", null,  "","Error while replace date seperator",null, null);
        	
   
            
            
        }
        return "";
    }

    private static String removeDatePrefix(String input) {
        try {
            String prefixToRemove = "Hijrah-umalqura AH ";
            return input.replace(prefixToRemove, "");
        } catch (Exception exception) {

        	SmartUIValidator.handleSeleniumException(exception, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE
        			, "Remove Date Prefix "+input+" ", null, "", "Error while remove Date Prefix",null, null);
        	
        }
        return input;
    }
    
    
    
    
    public static void main(String[] args) {
        // Test isFirstDateLargerOrEqual
        System.out.println("isFirstDateLargerOrEqual:");
        System.out.println("15-06-2023 >= 14-06-2023: " + isFirstDateLargerOrEqual("15-06-2023", "14-06-2023")); // true
        System.out.println("14-06-2023 >= 15-06-2023: " + isFirstDateLargerOrEqual("14-06-2023", "15-06-2023")); // false

        // Test reverseDateOrder
        System.out.println("\nreverseDateOrder:");
        System.out.println("2023-06-15 -> " + reverseDateOrder("2023-06-15")); // 15-06-2023
        System.out.println("15/06/2023 -> " + reverseDateOrder("15/06/2023")); // 2023/06/15

        // Test gregorianToHijriString
        System.out.println("\ngregorianToHijriString:");
        String hijriDate = gregorianToHijriString(2023, 6, 15);
        System.out.println("Gregorian 2023-06-15 -> Hijri: " + hijriDate);

        // Test hijriToGregorianString
        System.out.println("\nhijriToGregorianString:");
        String gregorianDate = hijriToGregorianString(1444, 11, 26); // Example Hijri date
        System.out.println("Hijri 1444-11-26 -> Gregorian: " + gregorianDate);

        // Test replaceDateSeparator
        System.out.println("\nreplaceDateSeparator:");
        System.out.println("15/06/2023 to - : " + replaceDateSeparator("15/06/2023", "-")); // 15-06-2023
    }
    
    
    
    
    
    
    
}
