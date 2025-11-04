package com.sage.base.validators;

import org.openqa.selenium.Keys;

public class KeyConverter {

    /**
     * Converts CharSequence input into a human-readable string.
     * - Normal strings are returned as-is.
     * - Keys like SHIFT, ENTER are returned as "KEY_NAME Keyboard Key".
     */
    public static String convertCharSequenceToString(CharSequence... keys) {
        StringBuilder result = new StringBuilder();
        for (CharSequence key : keys) {
            if (key instanceof Keys) {
                result.append(((Keys) key).name()).append(" Keyboard Key");
            } else {
                result.append(key);
            }
        }
        
       
        return result.toString();
    }

    // Example usage:
    public static void main(String[] args) {
        // Case 1: Normal string
        System.out.println(convertCharSequenceToString("john")); 
        // Output: john

        // Case 2: Special key
        System.out.println(convertCharSequenceToString(Keys.ENTER)); 
        // Output: ENTER Keyboard Key

        // Case 3: Mix of both
        System.out.println(convertCharSequenceToString( Keys.TAB)); 
        // Output: helloTAB Keyboard Keyworld
    }
}
