package com.sage.testdata.dataDriven.RunTimeAttributes;

import com.sage.model.LocalBeneficiary;

public class SquadTestData2 {

    // Thread-safe fields for parallel execution
    private static final ThreadLocal<String> userIDNumber = new ThreadLocal<>();
    private static final ThreadLocal<String> userFullName = new ThreadLocal<>();
    private static final ThreadLocal<String> userMobileNumber = new ThreadLocal<>();
    private static final ThreadLocal<String> userBirthOfDate = new ThreadLocal<>();
    private static final ThreadLocal<String> userGender = new ThreadLocal<>();

    // --- Getters ---
    public static String getUserIDNumber() { return userIDNumber.get(); }
    public static String getUserFullName() { return userFullName.get(); }
    public static String getUserMobileNumber() { return userMobileNumber.get(); }
    public static String getUserBirthOfDate() { return userBirthOfDate.get(); }
    public static String getUserGender() { return userGender.get(); }

    // --- Setters ---
    public static void setUserIDNumber(String value) { userIDNumber.set(value); }
    public static void setUserFullName(String value) { userFullName.set(value); }
    public static void setUserMobileNumber(String value) { userMobileNumber.set(value); }
    public static void setUserBirthOfDate(String value) { userBirthOfDate.set(value); }
    public static void setUserGender(String value) { userGender.set(value); }

    // --- Cleanup after each test thread ---
    public static void clear() {
        userIDNumber.remove();
        userFullName.remove();
        userMobileNumber.remove();
        userBirthOfDate.remove();
        userGender.remove();
    }
}
