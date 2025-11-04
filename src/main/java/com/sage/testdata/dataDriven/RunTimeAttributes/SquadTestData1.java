package com.sage.testdata.dataDriven.RunTimeAttributes;

import com.sage.model.LocalBeneficiary;

public class SquadTestData1 {

    /*
        Example IDs:
        1067523561
        1086027594 (not working)
        1060880455 (not working)
        1068584810 (already existed)
    */

    // Thread-safe fields for parallel execution
    private static final ThreadLocal<String> userIDNumber = new ThreadLocal<>();
    private static final ThreadLocal<String> userName = new ThreadLocal<>();
    private static final ThreadLocal<String> userPassword = ThreadLocal.withInitial(() -> "Test@1234");
    private static final ThreadLocal<String> referenceNumber = new ThreadLocal<>();
    private static final ThreadLocal<String> crmUserId = new ThreadLocal<>();
    private static final ThreadLocal<String> crmUserPassword = new ThreadLocal<>();
    private static final ThreadLocal<String> mobilePhoneNumber = new ThreadLocal<>();
    private static final ThreadLocal<LocalBeneficiary> localBeneficiary = new ThreadLocal<>();

    // --- Getters ---
    public static String getUserIDNumber() { return userIDNumber.get(); }
    public static String getUserName() { return userName.get(); }
    public static String getUserPassword() { return userPassword.get(); }
    public static String getReferenceNumber() { return referenceNumber.get(); }
    public static String getCrmUserId() { return crmUserId.get(); }
    public static String getCrmUserPassword() { return crmUserPassword.get(); }
    public static String getMobilePhoneNumber() { return mobilePhoneNumber.get(); }
    public static LocalBeneficiary getLocalBeneficiary() { return localBeneficiary.get(); }

    // --- Setters ---
    public static void setUserIDNumber(String value) { userIDNumber.set(value); }
    public static void setUserName(String value) { userName.set(value); }
    public static void setUserPassword(String value) { userPassword.set(value); }
    public static void setReferenceNumber(String value) { referenceNumber.set(value); }
    public static void setCrmUserId(String value) { crmUserId.set(value); }
    public static void setCrmUserPassword(String value) { crmUserPassword.set(value); }
    public static void setMobilePhoneNumber(String value) { mobilePhoneNumber.set(value); }
    public static void setLocalBeneficiary(LocalBeneficiary value) { localBeneficiary.set(value); }

    // --- Cleanup after each test thread ---
    public static void clear() {
        userIDNumber.remove();
        userName.remove();
        userPassword.remove();
        referenceNumber.remove();
        crmUserId.remove();
        crmUserPassword.remove();
        mobilePhoneNumber.remove();
        localBeneficiary.remove();
    }
}
