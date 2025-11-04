package com.sage.utils;

import java.util.regex.Pattern;

public class StringProcessor {

	
	

	
    public static boolean isValidNumber(String input) {
        String NUMBER_REGEX = "^-?\\d+(\\.\\d+)?([eE][-+]?\\d+)?$";
        Pattern pattern = Pattern.compile(NUMBER_REGEX);

        if (input == null || input.isEmpty()) {
            return false;
        }
        return pattern.matcher(input).matches();
    }

    public static String trimFromRight(String input, int length) {
        if (input == null || length < 0) {
            throw new IllegalArgumentException("Input cannot be null, and length cannot be negative.");
        }

        if (length >= input.length()) {
            return input;
        }

        return input.substring(0, input.length() - length);
    }

    public static String trimFromLeft(String input, int length) {
        if (input == null || length < 0) {
            throw new IllegalArgumentException("Input cannot be null, and length cannot be negative.");
        }

        if (length >= input.length()) {
            return input;
        }

        return input.substring(length);
    }

    public static String checkFullTextContainsPartText(String fullText, String partText) {
        if (fullText == null || partText == null) {
            throw new IllegalArgumentException("Neither fullText nor partText can be null.");
        }

        if (fullText.toLowerCase().contains(partText.toLowerCase())) {
            return partText;
        } else {
            return fullText;
        }
    }

    public static String convertDoubleToSimplifiedString(double number) {
        if (number == (long) number) {
            return String.valueOf((long) number);
        } else {
            return String.valueOf(number);
        }
    }
}
