package com.sage.integrations;

//https://sageframework.atlassian.net/jira/software/projects/DP/boards/1



import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;


import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JiraDefector {


// 7f1f8322-3f8b-4923-80cd-a6e547b14d17 orgnization id
    // ATCTT3xFfGN0rwxfUrz0Ogjn1ShyjkUQaxQZcRSNeFkVQ2kxbgY_xX2NN9c7EGUeEnRHd2EBD5UP34we-m0FOA07-OKhgWUKmd3GyEFschGYmaZGMLMaNdYXKDQxurWsrWHY33PAv-_uU6dV0n6y2619YNHN-HVmGy5hbdD23F4eqRFL1G6VHKU=17ADC228 token
    static String userName = "osamazaza200@gmail.com";
    static String token = System.getenv("JIRA_API_TOKEN");



    // Global priority values for easy access
    public static final String PRIORITY_HIGHEST = "Highest";
    public static final String PRIORITY_HIGH = "High";
    public static final String PRIORITY_MEDIUM = "Medium";
    public static final String PRIORITY_LOW = "Low";
    public static final String PRIORITY_LOWEST = "Lowest";
   // public static final String PRIORITY_VIP = "VIP";

/*    public static final String WMV_SQUAD = "Wallet & VAS";
    public static final String REMITTANCE_SQUAD = "Remittance Services";
    public static final String PAYMENTSANDCARDS_SQUAD = "Payments & Card";
    public static final String DMPANDFOODPICKUP_SQUAD = "Marketplace & Food pickup";
    public static final String ITENHANCEMENT_SQUAD = "IT Enhancement";
    public static  String SELECTED_SQUAD = "";*/


    public static final String OSAMA_ZAZA_PROFILE = "6128ca71129802006a01e49f"; // OSAMA ZAZA

    public static final String WMV_SQUAD_LEADER_PROFILE = "6385cf629e48f2b9a61462b9"; // mahmoud elsayed
    public static final String REMITTANCE_SQUAD_LEADER_PROFILE = "712020:74e40860-7aec-4441-b128-d8fce8231277"; // assad
    public static final String PAYMENTSANDCARDS_SQUAD_LEADER_PROFILE = "712020:4608c669-8881-41ad-ba60-9bf1c6ec2816"; // adel
    public static final String DMPANDFOODPICKUP_SQUAD_LEADER_PROFILE = "6416c700f1b529dfa98cf88c"; // ahamad salah
    public static final String ITENHANCEMENT_SQUAD_LEADER_PROFILE = "63d8c131c3eb74ad8e95055f"; // rana
    public static  String SELECTED_SQUAD_LEADER_PROFILE = ""; //



    public static void x(){
       // createJiraDefect("DP", "Summary defect", "Summary description" , )
    }


    // Enhanced function to handle multiple labels
    public static String createJiraDefect(String projectKey, String issueType, String summary, String description, String priority, String attachmentPath, String accountId, String label) {
System.out.println("The token is " + token);
        System.out.println("Create Jira Defect is executed");
        String issueKey = ""; // Initialize the variable

        // NOTE: 'userName' and 'password' are assumed to be public static fields or variables accessible here.
        // NOTE: 'attachFileToIssue', 'SmartUIValidator', and 'FailureHandling' are assumed to be accessible utility methods/classes.

        try {
            String jiraUrl = "https://sageframework.atlassian.net/rest/api/3/issue";


            // Escape newline characters in description and steps
            description = description.replace("\n", "\\n");
            //steps = steps.replace("\n", "\\n");
            description = description.replace("<br>", "\\n");
           // steps = steps.replace("<br>", "\\n");

            // Trim the summary to a maximum of 255 characters
            if (summary.length() > 255) {
                summary = summary.substring(0, 255);
            }

            // Split the label string by commas and trim any extra spaces
            // Groovy's 'def labelsList = label.split(',').collect { it.trim() }' is replaced with standard Java
            String[] labelArray = label.split(",");
            java.util.List<String> labelsList = new java.util.ArrayList<>();
            for (String singleLabel : labelArray) {
                labelsList.add(singleLabel.trim());
            }

            // Set up basic authentication (assumes RestAssured imports)
            io.restassured.authentication.PreemptiveBasicAuthScheme basicAuth = new io.restassured.authentication.PreemptiveBasicAuthScheme();
            // NOTE: userName and password are assumed to be defined elsewhere or passed in
            basicAuth.setUserName(userName);
            basicAuth.setPassword(token);
            io.restassured.RestAssured.authentication = basicAuth;

            // Create dynamic JSON payload with multiple labels
            StringBuilder labelsJson = new StringBuilder("[");
            for (int i = 0; i < labelsList.size(); i++) {
                labelsJson.append("\"").append(labelsList.get(i)).append("\"");
                if (i < labelsList.size() - 1) {
                    labelsJson.append(", ");
                }
            }
            labelsJson.append("]");

            // Update the payload
            String jiraPayload = "{\n" +
                    "  \"fields\": {\n" +
                    "    \"project\": {\n" +
                    "      \"key\": \"" + projectKey + "\"\n" +
                    "    },\n" +
                    "    \"summary\": \"" + summary + "\",\n" +
                    "    \"issuetype\": {\n" +
                    "      \"name\": \"" + issueType + "\"\n" +
                    "    },\n" +
                    "    \"description\": {\n" +
                    "      \"type\": \"doc\",\n" +
                    "      \"version\": 1,\n" +
                    "      \"content\": [\n" +
                    "        {\n" +
                    "          \"type\": \"paragraph\",\n" +
                    "          \"content\": [\n" +
                    "            {\n" +
                    "              \"text\": \"" + description.replace("\"", "\\\"") + "\",\n" +
                    "              \"type\": \"text\"\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    \"priority\": { \"name\": \"" + priority + "\" },\n" +
                    "    \"assignee\": { \"id\": \"" + accountId + "\" },\n" + // <-- Use "id" (not "accountId")
                    "    \"labels\": " + labelsJson.toString() + "\n" +
                    "  }\n" +
                    "}";

            // Send POST request (assumes RestAssured imports)
            io.restassured.response.Response response = io.restassured.RestAssured.given()
                    .contentType(io.restassured.http.ContentType.JSON)
                    .body(jiraPayload)
                    .post(jiraUrl);

            // Get response details
            int statusCode = response.getStatusCode();
            String responseBody = response.getBody().asPrettyString();
            issueKey = response.jsonPath().getString("key");

            // Print response information
            System.out.println("Response Status Code: " + statusCode);
            System.out.println("Response Body: " + responseBody);

            // Step 2: Attach a file to the created issue if attachmentPath is provided
            if (attachmentPath != null && !attachmentPath.isEmpty() && issueKey != null && !issueKey.isEmpty()) {
                attachFileToIssue(issueKey, attachmentPath);
            }
        }
        // Groovy's 'catch(e)' is replaced with standard Java 'catch (Exception e)'
        catch (Exception e) {
            // Groovy-style string interpolation is replaced with standard Java string concatenation
            String defectSummary = "Automation Error: Not able to raise defect to the Jira";
            String defectDescription = "Expected Result: Defect [" + description + " ] should be raized into jira. \n Actual Result: Defect is not raised. " + e.getMessage();

            // Assumes SmartUIValidator and FailureHandling are accessible classes/enums
           // SmartUIValidator.addStepFailure(FailureHandling.CONTINUE_ON_FAILURE, defectSummary, defectDescription);
        }

        // Return the issueKey
        return issueKey;
    }

    private static void attachFileToIssue(String issueKey, String attachmentPath) {

        try {

            String attachmentUrl = "https://sageframework.atlassian.net/rest/api/3/issue/" + issueKey + "/attachments";

            System.out.println("Attachment URL is " + attachmentUrl);
            // Add file to attach
            File file = new File(attachmentPath);

            // Ensure file exists
            if (!file.exists()) {
                System.out.println("Attachment file not found: " + attachmentPath);
                return;
            }

            // Send POST request to attach the file
            Response attachResponse = RestAssured.given()
                    .header("X-Atlassian-Token", "no-check") // Required for attachment uploads
                    .multiPart("file", file)
                    .post(attachmentUrl);

            // Get response details
            int attachStatusCode = attachResponse.getStatusCode();
            String attachResponseBody = attachResponse.getBody().asPrettyString();

            // Print attachment response information
            System.out.println("Attachment Response Status Code: " + attachStatusCode);
            System.out.println("Attachment Response Body: " + attachResponseBody);
        }
        catch(Exception e) {
            String defectSummary = "Automation Error: Not able to raise attachment to jira defect " + issueKey;
            String defectDescription = "Expected Result: Attachment should be uploaded for jira defect ["+issueKey+" ]  \n Actual Result: attachment's Defect is not uploaded. " + e.getMessage();
            //SmartUIValidator.addStepFailure(FailureHandling.CONTINUE_ON_FAILURE, defectSummary, defectDescription)
        }
    }
}
