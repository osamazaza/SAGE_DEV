package com.sage.utils;

import com.sage.base.validators.SmartUIValidator;
import com.sage.base.validators.*;



public class Calculator {

    double VAT = 0.15;





	/*
	 Updates:
	 Merge SIT branches
	 Enhance the time exection while lanhcning app application and during test cases of test suite
	 new Calculator keywords: calculateUnitsToReachValue,roundToNearestDecimal,stabilizeDouble, getIntegerAndFirstFractionDigit.
	 new shared test cases: ToValidateLoginForRemoteDevicesOnly
	 Fixes:
	 Adapt frameowork for remote parallel exection
	 Fix report generation for parallel exection
	 Fixes Remote devices configuration
	 Update Digtial Market place keywords with validation attributes
	 */



    public static int calculateUnitsToReachValue(double productPrice, double maxValue) {
        if (productPrice <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero.");
        }
        if (maxValue <= 0) {
            throw new IllegalArgumentException("Maximum value must be greater than zero.");
        }

        if (maxValue % productPrice != 0) {
            return -1; // Not possible to reach exact value
        }

        return (int) (maxValue / productPrice);
    }






    public static double extractNumbersFromText(String text) {
        double extractedNumber = -1;


            try {

                // Remove all characters except digits and dots, including Arabic numerals
                String numbersExtractedFromTextAsString = text.replaceAll("[^0-9.\\u0660-\\u0669]", "");

                // If no numbers are found, return -1
                if (numbersExtractedFromTextAsString.isEmpty()) {
                    return -1;
                }

                // Handle cases where there might be multiple dots due to non-numeric characters between numbers
                // Split the string by dot and keep only valid parts to avoid invalid number format
                String[] parts = numbersExtractedFromTextAsString.split("\\.");
                StringBuilder result = new StringBuilder();

                for (int i = 0; i < parts.length; i++) {
                    if (!parts[i].isEmpty()) {
                        if (i > 0) {
                            result.append(".");
                        }
                        result.append(parts[i]);
                    }
                }

                // Convert the cleaned numeric string to double
                extractedNumber = Double.parseDouble(result.toString());
            }
            catch(Exception e) {

                SmartUIValidator.handleSeleniumException(e, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE, "Extract Numbers From Text: "+text+" ", null, "", "number/s is not fetched from text", "Expected Result: number/s should be fetched from text <br> Actual Result: Number is not fetched ", null);

            }



        return extractedNumber;
    }

    public static double calculateMultipleItem(double singleAmount, int numberOfItems) {
        double temp = -1;

        try {

                return singleAmount * numberOfItems;



        } catch (Exception exception) {

            SmartUIValidator.handleSeleniumException(
                    exception,
                    FailureHandler.FailureHandling.CONTINUE_ON_FAILURE,
                    "calculate totals for amount price " + singleAmount + " and number of items: " + numberOfItems,
                    null, "",
                    "Total Amount is not calculated properly",
                    "Expected Result: Total amount related for amount price " +
                            singleAmount +
                            " and number of items:  " + numberOfItems +
                            " Should be calculated properly. <br> Actual Result: Facing issue while calculating the total amount",
                    null);

        }

        return temp;
    }


    public double calculateTotalAmountWithVAT(double amount) {
        double temp = -1;

        try {
                return amount + (amount * VAT);

        } catch (Exception exception) {


            SmartUIValidator.handleSeleniumException(null, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE,
                    "calculate Total Amount With VAT " + amount, null, "", // or a By locator if applicable
                    "Error while calculating Total Amount including VAT", "Expected Reslt: Total Amount including VAT should be calculated <br> Actual Result: Total Amount is not calculated", "");

        }

        return temp;
    }






    public static double roundToNearestDecimal(double value, int fractionIndex) {
        String valueStr = Double.toString(value);
        int decimalIndex = valueStr.indexOf('.');

        if (decimalIndex == -1) {
            return value;
        }

        int decimalPlaces = valueStr.length() - decimalIndex - 1;

        if (fractionIndex > decimalPlaces || fractionIndex <= 0) {
            return value;
        }

        int roundingIndex = decimalIndex + fractionIndex;
        char roundingChar = valueStr.charAt(roundingIndex);
        int roundingDigit = Character.getNumericValue(roundingChar);

        StringBuilder newValueStr = new StringBuilder(valueStr.substring(0, roundingIndex));

        if (roundingDigit >= 5) {
            // Round up
            int indexToIncrement = roundingIndex - 1;
            while (indexToIncrement >= decimalIndex + 1) {
                char charToIncrement = newValueStr.charAt(indexToIncrement);
                int digitToIncrement = Character.getNumericValue(charToIncrement);
                if (digitToIncrement < 9) {
                    newValueStr.setCharAt(indexToIncrement, Character.forDigit(digitToIncrement + 1, 10));
                    return Double.parseDouble(newValueStr.toString());
                } else {
                    newValueStr.setCharAt(indexToIncrement, '0');
                    indexToIncrement--;
                }
            }

            // Carry over to integer part
            if (indexToIncrement == decimalIndex) {
                indexToIncrement--;
                while (indexToIncrement >= 0) {
                    char charToIncrement = newValueStr.charAt(indexToIncrement);
                    if (charToIncrement == '.') {
                        indexToIncrement--;
                        continue;
                    }
                    int digitToIncrement = Character.getNumericValue(charToIncrement);
                    if (digitToIncrement < 9) {
                        newValueStr.setCharAt(indexToIncrement, Character.forDigit(digitToIncrement + 1, 10));
                        return Double.parseDouble(newValueStr.toString());
                    } else {
                        newValueStr.setCharAt(indexToIncrement, '0');
                        indexToIncrement--;
                    }
                }

                // If all digits were 9, add a leading 1
                return Double.parseDouble("1" + newValueStr.toString());
            }
        } else {
            // Round down (truncate)
            return Double.parseDouble(newValueStr.toString());
        }

        return 0; // Should never reach here
    }






    public static double stabilizeDouble(double value, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("Decimal places must be non-negative.");
        }

        double scale = Math.pow(10, decimalPlaces); // Calculate the scaling factor.
        return Math.round(value * scale) / scale; // Round to the desired precision and scale back.
    }




    public static double getIntegerAndFirstFractionDigit(double number) {
        try {
            // Extract the integer part
            int integerPart = (int) number;

            // Get the fractional part
            double fractionalPart = number - integerPart;

            // Get the first digit of the fractional part
            int firstFractionDigit = (int) (fractionalPart * 10) % 10;

            // Combine the integer part and the first fraction digit
            return integerPart + firstFractionDigit * 0.1; // Multiply by 0.1 to position it correctly
        } catch (Exception exception) {



            SmartUIValidator.handleSeleniumException(exception, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE, "get Integer And First Fraction Digit "+number+" ", null, "","The whole number including integer and first fraction part is not fetched properly", "Expected Result: the whole number should be fetched properly <br> Actual Result: the whole number is not fetched properly", "");

        }

        return -1;
    }
}
