package com.sage.base.validators.backend;





import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sage.base.TestBase;


import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class APIConfiguration {

    private String endPoint;
    private String requestMethod;
    private List<Map<String, String>> headers;
    private List<Map<String, String>> restParameters;
    private String body;
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public APIConfiguration(String endPoint, String requestMethod, List<Map<String, String>> headers, List<Map<String, String>> restParameters, String body) {
        this.endPoint = endPoint;
        this.requestMethod = requestMethod;
        this.headers = headers;
        this.restParameters = restParameters;
        this.body = body;
    }

    public static void main(String[] args) {
        // ====== Test 1: GET Request to JSONPlaceholder ======
        APIConfiguration getRequest = new APIConfiguration(
                "https://jsonplaceholder.typicode.com/users",
                "GET",
                null,  // No headers
                null,  // No query params
                null   // No body
        );

        System.out.println("----- GET Request -----");
        Response getResponse = getRequest.pushRequest(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
        System.out.println("GET Response Code: " + getResponse.getStatusCode());
        System.out.println("GET Response Body:\n" + getResponse.getBody().asPrettyString());

        // ====== Test 2: POST Request to ReqRes ======
        String postBody = "{ \"name\": \"John Doe\", \"job\": \"QA Tester\" }";

        APIConfiguration postRequest = new APIConfiguration(
                "https://reqres.in/api/users",
                "POST",
                null,  // No headers
                null,  // No query params
                postBody
        );

        System.out.println("\n----- POST Request -----");
        Response postResponse = postRequest.pushRequest(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
        System.out.println("POST Response Code: " + postResponse.getStatusCode());
        System.out.println("POST Response Body:\n" + postResponse.getBody().asPrettyString());
    }


    public Response pushRequest(FailureHandler.FailureHandling failureHandling) {
        Response response = null;

        if (!TestBase.getReportAttributes().isSkipCurrentTestCase()) {
            RequestSpecification request = RestAssured.given();

            // Add headers
            if (headers != null) {
                for (Map<String, String> header : headers) {
                    header.forEach(request::header);
                }
            }

            // Add query parameters
            if (restParameters != null) {
                for (Map<String, String> param : restParameters) {
                    param.forEach(request::queryParam);
                }
            }

            // Add body
            if (body != null && !body.isEmpty()) {
                request.body(body);
            }

            try {
                // Capture time before execution
                String apiCallTime = java.time.LocalDateTime.now().toString();

                // Execute request
                switch (requestMethod.toUpperCase()) {
                    case "GET":
                        response = request.get(endPoint);
                        break;
                    case "POST":
                        response = request.post(endPoint);
                        break;
                    case "PUT":
                        response = request.put(endPoint);
                        break;
                    case "DELETE":
                        response = request.delete(endPoint);
                        break;
                    case "PATCH":
                        response = request.patch(endPoint);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid request method: " + requestMethod);
                }

                // Log using SmartUIValidator
                SmartUIValidator.addStep(
                        "API Request Details:\n" +
                        "-----------------------\n" +
                        "Time            : " + apiCallTime + "\n" +
                        "Endpoint        : " + endPoint + "\n" +
                        "Request Method  : " + requestMethod + "\n" +
                        "Headers         : " + headers + "\n" +
                        "Parameters      : " + restParameters + "\n" +
                        "Body Content    : " + body + "\n\n" +
                        "Response Details:\n" +
                        "-----------------------\n" +
                        "Response Code   : " + response.getStatusCode() + "\n" +
                        "Response Body   : " + response.getBody().asString() + "\n"
                );

            } catch (Exception e) {
                // Attempt to include response details even in failure
                String responseStatusCode = "N/A";
                String responseBody = "N/A";

                if (response != null) {
                    try {
                        responseStatusCode = String.valueOf(response.getStatusCode());
                        responseBody = response.getBody().asString();
                    } catch (Exception respEx) {
                        responseBody = "Unable to extract response body due to: " + respEx.getMessage();
                    }
                }

                String defectSummary = "API Exception issue";
                String defectDescription =
                        "Expected Result: API with Endpoint " + endPoint + " should work properly\n" +
                        "Actual Result: API Exception Error: " + e.getMessage();

                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String stackTrace = sw.toString();

                String fullDetailsTech =
                        "API Exception Technical Details:\n" +
                        "----------------------------------\n" +
                        "Time of Error    : " + java.time.LocalDateTime.now() + "\n" +
                        "Endpoint         : " + endPoint + "\n" +
                        "Request Method   : " + requestMethod + "\n" +
                        "Headers          : " + headers + "\n" +
                        "Parameters       : " + restParameters + "\n" +
                        "Body Content     : " + body + "\n\n" +
                        "Response Code    : " + responseStatusCode + "\n" +
                        "Response Body    : " + responseBody + "\n\n" +
                        "Exception Message: " + e.getMessage() + "\n" +
                        "Stack Trace      :\n" + stackTrace;

                SmartUIValidator.handleSeleniumException(
                        e,
                        failureHandling,
                        "validate end point: " + endPoint,
                        null, "",
                        defectSummary,
                        defectDescription,
                        fullDetailsTech
                );
            }

        } else {
            // Skipped due to prior blocker
            if (TestBase.getReportAttributes().getTestCaseErrorsSummary().isEmpty()) {
                SmartUIValidator.handleSeleniumException(null, FailureHandler.FailureHandling.STOP_ON_FAILURE, "validate end point: " + endPoint, null, "","", TestBase.getReportAttributes().getBlockerIssue(), "");
            } else {
                SmartUIValidator.handleSeleniumException(null, FailureHandler.FailureHandling.STOP_ON_FAILURE, "validate end point: " + endPoint, null, "", "", "", "");
            }


        }

        return response;
    }

    // Getters and Setters omitted for brevity
}
