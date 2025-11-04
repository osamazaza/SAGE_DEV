
package com.sage.base.Runners;

import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import com.sage.base.validators.backend.OracleDBConfiguration;
import com.sage.integrations.JiraDefector;
import com.sage.model.LocalBeneficiary;
import com.sage.model.UserIdentity;
import com.sage.testdata.dataDriven.External.ExternalDataDrivenTestUtil;
import com.sage.testdata.generators.IDGenerator;
import com.sage.testdata.generators.LocalBeneficiaryGenerator;

import java.sql.ResultSet;

public class JavaDebugRunner {

    public static void main(String[] args) {




        // example to read data from external file

        LocalBeneficiary localBeneficiary =  LocalBeneficiaryGenerator.generateRandomLocalBeneficiary();

        System.out.println("Random Local beneficiary generated is " + localBeneficiary.toString());

      String saudiCitizenID = IDGenerator.getIdNumber(1); // for saudi
        String residentCitizenID = IDGenerator.getIdNumber(2); // for resident
        String mobilePhone = IDGenerator.getSaudiPhoneNumberStartWith52();

        System.out.println("Saudi Cititzen ID " + saudiCitizenID);
        System.out.println("resident  ID " + residentCitizenID);
        System.out.println("mobile phone  " + mobilePhone);



// use JiraDefector


//        JiraDefector.createJiraDefect("DP", "Bug", "This is the summary of the issue", "This is description", JiraDefector.PRIORITY_HIGH,"C:\\Users\\user\\Documents\\a.png", JiraDefector.OSAMA_ZAZA_PROFILE, "Automation_label");




}


    public static String getLatestOtp(String nationalId, FailureHandler.FailureHandling failureHandling) {


        String otp = null;
        OracleDBConfiguration.setURL("jdbc:sqlserver://t-sdb-db:1433;databaseName=NotificationDb");
        OracleDBConfiguration.setUSERNAME("sa");
        OracleDBConfiguration.setPASSWORD("123456");

        // 1️⃣ Open connection and create statement using existing methods
        OracleDBConfiguration.openOracleConnection(failureHandling);
        OracleDBConfiguration.createStatement(failureHandling);

        // 2️⃣ Build Oracle-compatible SQL query
        String sqlQuery =
                "SELECT OtpCode " +
                        "FROM NotificationDb.App.OtpIssues " +
                        "WHERE NationalId = '" + nationalId + "' " +
                        "ORDER BY OtpCode DESC FETCH FIRST 1 ROWS ONLY";

        // 3️⃣ Execute query
        ResultSet rs = OracleDBConfiguration.executeQuery(sqlQuery, failureHandling);

        // 4️⃣ Read the OTP from ResultSet
        if (rs != null) {
            try {
                if (rs.next()) {
                    otp = rs.getString("OtpCode");
                  /*  SmartUIValidator.addStep("✅ Latest OTP fetched successfully for NationalId: "
                            + nationalId + " | OTP: " + otp);*/
                } else {
/*
                    SmartUIValidator.addStep("⚠️ No OTP record found for NationalId: " + nationalId);
*/
                }
            } catch (Exception ignored) {
                // No explicit exception handling — framework handles it
            }
        }

        // 5️⃣ Close the connection
        OracleDBConfiguration.closeConnection(failureHandling);

        return otp;
    }









    //

}


