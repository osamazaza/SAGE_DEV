//https://chatgpt.com/share/686fc149-c228-800d-9942-5849228a6c91


package com.sage.base.validators;
import java.util.function.BiFunction;
import java.util.function.Function;

import java.net.URL;
import java.sql.Time;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.net.URI;
import java.util.function.Predicate;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.sage.base.Report.SmartImageAttacher;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v137.network.Network;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import com.sage.base.validators.FailureHandler.FailureHandling;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sage.base.*;

import java.util.function.Supplier;
import java.util.stream.Collectors;


public class SmartUIValidator extends TestBase {










    public static String selectDropdownOption(By locator, String visibleText, String value, int index, boolean isRandomSelection, FailureHandling failureHandling, String viewName, int ... timeout) {

        String step = "Select option from dropdown::" + viewName;
        AtomicReference<String> selectedOptionText = new AtomicReference<>("");


            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);
            Runnable selectDropdownOptionCode =  () ->{
                //start
                WebElement dropdownElement = getDriver().findElement(locator);
                Select dropdown = new Select(dropdownElement);

                if (isRandomSelection) {
                    List<WebElement> allOptions = dropdown.getOptions();

                    List<WebElement> validOptions = allOptions.stream()
                            .filter(opt -> opt.getText() != null && !opt.getText().trim().isEmpty())
                            .collect(Collectors.toList());

                    if (validOptions.isEmpty()) {
                        //throw new Exception("No valid options found in dropdown for random selection.");
                    }

                    Random rand = new Random();
                    int randomIndex = rand.nextInt(validOptions.size());
                    WebElement randomOption = validOptions.get(randomIndex);
                    dropdown.selectByVisibleText(randomOption.getText());
                    selectedOptionText.set(randomOption.getText());
                    System.out.println("Randomly selected option: " + randomOption.getText());

                } else if (visibleText != null) {
                    dropdown.selectByVisibleText(visibleText);
                    selectedOptionText.set(visibleText);
                    System.out.println("Selected by visible text: " + selectedOptionText.get());

                } else if (value != null) {
                    dropdown.selectByValue(value);
                    selectedOptionText.set(dropdown.getFirstSelectedOption().getText());
                    System.out.println("Selected by value: " + selectedOptionText.get());

                } else if (index >= 0) {
                    dropdown.selectByIndex(index);
                    selectedOptionText.set(dropdown.getFirstSelectedOption().getText());
                    System.out.println("Selected by index: " + selectedOptionText.get());

                    //end
                }

                else {
                    handleSeleniumException(new Exception("Must provide at least one selection method: visibleText, value, or index."), failureHandling, step, locator, viewName, "", "", "");
                }

            };




            try {
                getFluentWait().until(driver -> {

                    selectDropdownOptionCode.run();
                    return true;
                });

            } catch (TimeoutException e) {

                if(!e.getMessage().equalsIgnoreCase("Must provide at least one selection method: visibleText, value, or index.")){
                    try {

                        resetWaitWithTimeoutNoIgnores();
                        selectDropdownOptionCode.run();

                    } catch (Exception e2) {

                        handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                    }

                }

                else{
                    handleSeleniumException(e, failureHandling, step, locator, viewName, "", "", "");

                }


            }



        return selectedOptionText.get();
    }




    public static boolean waitForPageLoad(FailureHandling failureHandling,
                                          int durationInSeconds) {

        String stepName = "Wait for page to fully load :: ";
            addStep(stepName);
            System.out.println(stepName);

            AtomicBoolean loaded = new AtomicBoolean(false);

            try {
                FluentWait<?> wait = new FluentWait<>(getDriver())
                        .withTimeout(Duration.ofSeconds(durationInSeconds))
                        .pollingEvery(Duration.ofMillis(TestBase.pollying))
                        .ignoring(Exception.class);

                wait.until(new Function<Object, Boolean>() {
                    @Override
                    public Boolean apply(Object driver) {
                        String readyState = (String) ((JavascriptExecutor) getDriver())
                                .executeScript("return document.readyState");
                        if ("complete".equals(readyState)) {
                            loaded.set(true);
                            System.out.println("✅ Page loaded successfully");
                            return true;
                        }
                        return false;
                    }
                });

                return loaded.get();

            } catch (TimeoutException te) {
                System.out.println("⚠️ Page did not load within " + durationInSeconds + " seconds");

                handleSeleniumException(
                        null,
                        failureHandling,
                        stepName,
                        null, // no locator
                        null,
                        "Page not loaded",
                        "🔍 **Expected:** Page should be fully loaded\n"
                                + "❗ **Actual:** Page is not loaded after " + durationInSeconds + " seconds\n"
                                + "🧩 **Issue Trigger:** " + stepName,
                        null
                );
            }



        return false;
    }






    public static Map<String, String> getAPIRequestAndResponse(String APIURL,
                                                               FailureHandling failureHandling,
                                                               int timeoutInSeconds) {
        String step = "Getting API Request and Response for URL: " + APIURL;
        Map<String, String> result = new HashMap<>();
        addStep(step);
        System.out.println(step);

        JsonParser parser = new JsonParser();
        AtomicReference<String> requestIdRef = new AtomicReference<>();
        AtomicReference<JsonObject> requestDataRef = new AtomicReference<>();
        AtomicReference<JsonObject> responseDataRef = new AtomicReference<>();

        FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NullPointerException.class);

        try {
            // ================================
            // 1️⃣ Wait for API Request
            // ================================
            String requestId;
            try {
                requestId = wait.until(driver -> {
                    LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);

                    for (LogEntry entry : logs) {
                        JsonObject json = parser.parse(entry.getMessage()).getAsJsonObject();
                        JsonObject messageObj = json.getAsJsonObject("message");

                        if (messageObj != null && "Network.requestWillBeSent".equals(messageObj.get("method").getAsString())) {
                            JsonObject params = messageObj.getAsJsonObject("params");
                            String url = params.getAsJsonObject("request").get("url").getAsString();

                            if (url.contains(APIURL)) {
                                String foundRequestId = params.get("requestId").getAsString();
                                System.out.println("✅ Request found with ID: " + foundRequestId);

                                JsonObject request = params.getAsJsonObject("request");
                                JsonObject requestData = new JsonObject();
                                requestData.addProperty("url", url);
                                requestData.addProperty("method", request.get("method").getAsString());
                                requestData.add("fullRequest", request);

                                requestDataRef.set(requestData);
                                requestIdRef.set(foundRequestId);
                                result.put("requestKey", requestData.toString());

                                return foundRequestId; // stop waiting
                            }
                        }
                    }
                    return null; // keep waiting
                });
            } catch (TimeoutException te) {
                String errorMessage = "Timeout: API request not found for URL: " + APIURL;
                System.err.println("❌ " + errorMessage);
                handleSeleniumException(
                        te,
                        failureHandling,
                        step,
                        null,
                        "Network Logs",
                        "API Request Timeout Error",
                        errorMessage,
                        errorMessage
                );
                return null;
            }

            // Optional small delay to allow response to appear in logs

            // ================================
            // 2️⃣ Wait for API Response
            // ================================
            Boolean responseFound;
            try {
                responseFound = wait.until(driver -> {
                    LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);

                    for (LogEntry entry : logs) {
                        JsonObject json = parser.parse(entry.getMessage()).getAsJsonObject();
                        JsonObject messageObj = json.getAsJsonObject("message");

                        if (messageObj != null && "Network.responseReceived".equals(messageObj.get("method").getAsString())) {
                            JsonObject params = messageObj.getAsJsonObject("params");
                            String currentId = params.get("requestId").getAsString();

                            if (currentId.equals(requestIdRef.get())) {
                                JsonObject response = params.getAsJsonObject("response");
                                int statusCode = response.get("status").getAsInt();
                                String url = response.get("url").getAsString();

                                System.out.println("✅ Response found for URL: " + url + " | Status: " + statusCode);
                                System.out.println("Full Response Data: " + response.toString());

                                JsonObject responseData = new JsonObject();
                                responseData.addProperty("statusCode", String.valueOf(statusCode));
                                responseData.addProperty("statusText", response.get("statusText").getAsString());
                                responseData.addProperty("responseURL", url);
                                responseData.add("fullResponse", response);

                                responseDataRef.set(responseData);
                                result.put("responseKey", responseData.toString());
                                return true; // stop waiting
                            }
                        }
                    }
                    return false; // keep waiting
                });
            } catch (TimeoutException te) {
                String errorMessage = "Timeout: API response not found for URL: " + APIURL
                        + " (Request ID: " + requestId + ")";
                System.err.println("❌ " + errorMessage);
                handleSeleniumException(
                        te,
                        failureHandling,
                        step,
                        null,
                        "Network Logs",
                        "API Response Timeout Error",
                        errorMessage,
                        errorMessage
                );
                return null;
            }

            return result;

        } catch (Exception e) {
            handleSeleniumException(
                    e,
                    failureHandling,
                    step,
                    null,
                    "Network Logs",
                    "General Error accessing logs.",
                    "",
                    ""
            );
            return null;
        }
    }



    public static boolean refreshAndWaitForAPIRequest(
            String APIURL,
            FailureHandling failureHandling,
            String apiName,
            int counterOfMaxTries,
            int durationInSeconds
    ) {
        String step = "Refresh page and wait for API request: " + apiName;
        addStep(step);
        System.out.println(step);

        AtomicBoolean found = new AtomicBoolean(false);

        // Loop for maxTries
        for (int attempt = 1; attempt <= counterOfMaxTries; attempt++) {
            final int currentAttempt = attempt; // Effectively final for lambdas/logs
            System.out.println("🔄 Attempt " + currentAttempt + " of " + counterOfMaxTries + " to find API: " + apiName);

            // Refresh the page on every attempt AFTER the first one
            if (attempt != 1) {
                refreshPage(failureHandling);
            }

            // Call your API function with built-in FluentWait
            Map<String, String> apiData = getAPIRequestAndResponse(
                    APIURL,
                    FailureHandling.OPTIONAL, // keep it optional so the wait function handles timeout itself
                    durationInSeconds         // pass timeout per attempt
            );

            if (apiData != null && apiData.containsKey("requestKey")) {
                found.set(true);
                System.out.println("✅ API request '" + apiName + "' found successfully on attempt " + currentAttempt);
                return true; // Success
            } else {
                System.out.println("⚠️ API request not found in attempt " + currentAttempt + ", retrying...");
            }
        }

        // --- After all tries fail ---
        String expected = "API Request for URL '" + APIURL + "' should be found in network logs.";
        String actual = "API Request for '" + apiName + "' is still not found after " + counterOfMaxTries + " refresh attempts.";

        // Handle failure using the original failureHandling
        handleSeleniumException(
                null,
                failureHandling,
                step,
                null,
                apiName,
                "API Request not found for " + apiName,
                "🔍 **Expected:** " + expected + "\n"
                        + "❗ **Actual:** " + actual + "\n"
                        + "🧩 **Issue Trigger:** " + step,
                null
        );

        return false;
    }






    public static List<String> extractAttributeFromMyRequestModels(String responseJson, String attributeName) {
        List<String> values = new ArrayList<>();
        try {
            // Parse response string into JsonObject
            JsonObject responseObj = JsonParser.parseString(responseJson).getAsJsonObject();

            // Navigate to "Data" -> "MyRequestModels"
            JsonObject dataObj = responseObj.getAsJsonObject("Data");
            JsonArray myRequestModels = dataObj.getAsJsonArray("MyRequestModels");

            // Loop through the array and extract the requested attribute
            for (JsonElement element : myRequestModels) {
                JsonObject requestObj = element.getAsJsonObject();
                if (requestObj.has(attributeName)) {
                    values.add(requestObj.get(attributeName).getAsString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values; // returns list, empty if nothing found
    }





    public static String getText(By locator, FailureHandling failureHandling, String viewName, int ... timeout) {
        String step = "Get Text From::" + viewName;

            AtomicReference<String> text = new AtomicReference<>();

            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);

            Runnable getTextCode =  () ->

            {WebElement element = getDriver().findElement(locator);
                String visibleText = element.getText();

                // If getText() is empty, try getting the 'value' attribute
                if (visibleText == null || visibleText.trim().isEmpty()) {
                    visibleText = element.getAttribute("value");
                }

                text.set(visibleText);
            };
            //start
            try {

                getFluentWait().until(d -> {
                    getTextCode.run();
                    return true;
                });


                return text.get();
            } catch (TimeoutException e) {
                try {

                    resetWaitWithTimeoutNoIgnores();
                    getTextCode.run();

                } catch (Exception e2) {

                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                }



            }

        return null;
    }


    public static void checkNetworkLogsForApiResponse(FailureHandling failureHandling, Set<Integer> expectedStatusCodes) {
        String step = "Checking Network Logs for API Responses";

            addStep(step);
            System.out.println(step);

            //strat
            try {
                LogEntries logs = getDriver().manage().logs().get(LogType.PERFORMANCE);
                boolean apiResponseFound = false;
                StringBuilder errorMessages = new StringBuilder();
                JsonParser parser = new JsonParser();

                for (LogEntry entry : logs) {
                    String message = entry.getMessage();

                    JsonObject json = parser.parse(message).getAsJsonObject();
                    JsonObject messageObj = json.getAsJsonObject("message");

                    if (messageObj != null && "Network.responseReceived".equals(messageObj.get("method").getAsString())) {
                        JsonObject params = messageObj.getAsJsonObject("params");
                        JsonObject response = params.getAsJsonObject("response");
                        int statusCode = response.get("status").getAsInt();

                        System.out.println("Status code is " + statusCode);
                        apiResponseFound = true;

                        if (expectedStatusCodes.contains(statusCode)) {
                            System.out.println("✅ Expected status received: " + statusCode + " - URL: " + response.get("url").getAsString());
                        } else {
                            String errorMsg = String.format("❌ Unexpected status code %d received for URL: %s", statusCode, response.get("url").getAsString());
                            System.out.println(errorMsg);
                            errorMessages.append(errorMsg).append("\n");


                        }
                    }
                }


                if (errorMessages.length() > 0) {
                    System.out.println("dlksajdsalkdjsa");
                    handleSeleniumException(
                            new Exception("Unexpected API status codes detected"),
                            failureHandling,
                            step,
                            null,
                            null,
                            "API Error: Unexpected HTTP status codes found in network logs.",
                            errorMessages.toString(),
                            errorMessages.toString()
                    );
                }

                //end
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, null, "Network Logs", "", "", "");
            }

    }






    public static void click(By locator, FailureHandling failureHandling, String viewName, int ... timeout) {


     // documentation for the function

        /*
        getFluentWait() starts waiting.

On each polling cycle (every few seconds, depending on your FluentWait config), it executes the lambda function.

Inside the lambda: clickCode.run() tries to click the element.

If the click succeeds (no exception thrown), it returns true, which immediately ends the wait successfully → ✅ Pass.

If the click fails (e.g. NoSuchElementException, ElementClickInterceptedException), the FluentWait catches it and retries until the timeout is reached.

⚙️ 2. When it’s considered a “pass”

FluentWait treats the operation as passed if:

The lambda (until(...)) returns a non-null and non-false value before the timeout.

In your case, return true; → means success.

So, as soon as one click succeeds, until() returns, and the function continues normally.
➡️ Your “click passed” — no exceptions are thrown.

❌ 3. When it’s considered a “fail”

FluentWait treats it as failed if:

The timeout expires without ever returning true (meaning every attempt threw exceptions).

Then it throws a TimeoutException,
         */




        String step = "Click on::" + viewName;
        Runnable clickCode =  () ->  getDriver().findElement(locator).click();

            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);

            try {
                getFluentWait().until(d -> {
                    clickCode.run();
                    return true;
                });


            } catch (TimeoutException e) {

                System.out.println("Catching the first exception for the click" + e.getMessage());
                try {

                    resetWaitWithTimeoutNoIgnores();
                    clickCode.run();

                } catch (Exception e2) {

                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                }

            }


    }

    public static void refreshPage(FailureHandling failureHandling) {
        String step = "Refresh Page";


            addStep(step);
            System.out.println(step);

            try {
                getDriver().navigate().refresh();
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, null, "", null, null, null);
            }



    }








    public static void navigateWithBasicAuth(String url, String userName, String password) {
        try {


            // Insert credentials in URL format
            // Example: https://username:password@yourhost.com/page
            String authUrl = url.replace("https://",
                    "https://" + userName + ":" + password + "@");

            getDriver().navigate().to(authUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // only works for chrome and edge browsers.
    public static void navigateToUrlWithCredentials(String url, FailureHandling failureHandling, String userNameOrEmail, String password, String host) {


        String step = "Navigate To URL::  "+url+" With the user credentials: \n UserNameOrEmail: "+userNameOrEmail+"  And Password:  "+password+" " ;
            addStep(step);

            System.out.println(step);
            // start
            try {

                WebDriver webDriver = getDriver();

                Predicate<URI> uriPredicate = uri -> uri.getHost().contains(host);

                // Cast driver to HasAuthentication to register authentication
                HasAuthentication drAuth = (HasAuthentication) webDriver;


                // Register the URI predicate and the credentials
                drAuth.register(()-> new UsernameAndPassword(userNameOrEmail, password));

                webDriver.navigate().to(url);
                // end

            } catch (Exception e) {
                //handleSeleniumException(e, failureHandling, step, null, null, null, null);

                handleSeleniumException(e, failureHandling, step, null, "", "", "", "");
            }



    }


    public static void navigateToUrl(String url, FailureHandling failureHandling) {
        String step = "Navigate To URL::" + url;
            addStep(step);

            System.out.println(step);
            try {
                getDriver().navigate().to(url);
            } catch (Exception e) {
                //handleSeleniumException(e, failureHandling, step, null, null, null, null);

                handleSeleniumException(e, failureHandling, step, null, "", "", "", "");
            }



    }

    public static void uploadAttachments(By locator, String filePath, FailureHandling failureHandling,
                                         String viewName, int ... timeout) {
        String step = "Upload file to::" + viewName;


            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);
            Runnable uploadAttachmentsCode =  () -> getDriver().findElement(locator).sendKeys(filePath);

            try {
                getFluentWait().until(d -> {
                    uploadAttachmentsCode.run();
                    return true;
                });
            } catch (TimeoutException e) {


                try {

                    resetWaitWithTimeoutNoIgnores();
                    uploadAttachmentsCode.run();

                } catch (Exception e2) {

                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                }

            }


    }

    public static void clearText(By locator, FailureHandling failureHandling, String viewName, int ... timeout) {

        String step = "Clear Text from the view: " + viewName;


            System.out.println("Casted Keys is : " + step);

            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);

            Runnable clearTextCode =  () -> getDriver().findElement(locator).clear();

            try {
                getFluentWait().until(d -> {
                    clearTextCode.run();
                    return true;
                });
            } catch (TimeoutException e) {

                try {

                    resetWaitWithTimeoutNoIgnores();
                    clearTextCode.run();

                } catch (Exception e2) {

                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                } // end of try


                // handleSeleniumException(e, failureHandling, step, locator, null, null);
            }


    }

    public static void sendKeys(By locator, FailureHandling failureHandling, String viewName, CharSequence keys, int ... timeout) {



        String castedKeys = KeyConverter.convertCharSequenceToString(keys);
        String step = "Send Text: " + castedKeys + " to the view: " + viewName;


            System.out.println("Casted Keys is : " + castedKeys);

            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);
            Runnable sendKeysCode =  () -> getDriver().findElement(locator).sendKeys(keys);
            try {
                getFluentWait().until(d -> {
                    sendKeysCode.run();
                    return true;
                });
            } catch (TimeoutException e) {

                try {

                    resetWaitWithTimeoutNoIgnores();
                    sendKeysCode.run();

                } catch (Exception e2) {

                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                }
            }


    }


    public static boolean actionAndWaitForViewCondition(
            Runnable action,                                // the action (refresh, click, etc.)
            Supplier<Boolean> condition,                    // the condition (isDisplayed, isEnabled, etc.)
            FailureHandling failureHandling,
            String viewName,
            String actionDescription,                       // NEW - human-readable description of action
            String conditionDescription,                    // NEW - human-readable description of condition
            int counterOfMaxTries,
            int durationInSeconds) {

        String step = "Perform action [" + actionDescription + "] and wait for condition [" + conditionDescription + "] on view: " + viewName;

            addStep(step);
            System.out.println("📌 Step: " + step);

            AtomicBoolean found = new AtomicBoolean(false);

            for (int attempt = 1; attempt <= counterOfMaxTries; attempt++) {
                final int currentAttempt = attempt;
                System.out.println("🔄 Attempt " + currentAttempt + " of " + counterOfMaxTries);

                if (attempt != 1 && action != null) {
                    try {
                        System.out.println("👉 Executing action: " + actionDescription);
                        action.run(); // perform custom action
                    } catch (Exception e) {
                        System.out.println("⚠️ Action failed on attempt " + currentAttempt + ": " + e.getMessage());
                    }
                }

                try {
                    getFluentWait()
                            .ignoring(ElementNotInteractableException.class)
                            .ignoring(StaleElementReferenceException.class)
                            .ignoring(NoSuchElementException.class)
                            .ignoring(NotFoundException.class)
                            .ignoring(InvalidElementStateException.class)
                            .withTimeout(Duration.ofSeconds(durationInSeconds))
                            .pollingEvery(Duration.ofMillis(TestBase.pollying))
                            .until(driver -> {
                                if (condition.get()) {
                                    found.set(true);
                                    System.out.println("✅ Condition [" + conditionDescription + "] met successfully for view '" + viewName + "' on attempt " + currentAttempt);
                                    return true;
                                }
                                return false;
                            });

                    if (found.get()) {
                        return true; // success
                    }
                } catch (TimeoutException te) {
                    System.out.println("⚠️ Condition [" + conditionDescription + "] not met in attempt " + currentAttempt + ", retrying...");
                }
            }

            // Fail after all retries
            String expected = "After performing action [" + actionDescription + "], view '" + viewName + "' should meet condition [" + conditionDescription + "].";
            String actual = "Even after " + counterOfMaxTries + " attempt(s) of performing [" + actionDescription + "], condition [" + conditionDescription + "] was NOT met for view '" + viewName + "'.";

            handleSeleniumException(
                    null,
                    failureHandling,
                    step,
                    null,
                    viewName,
                    "Condition not met for " + viewName,
                    "🔍 **Expected:** " + expected + "\n"
                            + "❗ **Actual:** " + actual + "\n"
                            + "🧩 **Issue Trigger:** " + step,
                    null
            );



        return false;
    }




    public static boolean refreshAndWaitForView(By locator, FailureHandling failureHandling, String viewName, int counterOfMaxTries, int durationInSeconds) {
        String step = "Refresh page and wait for view::" + viewName;
            addStep(step);
            System.out.println(step);


                AtomicBoolean found = new AtomicBoolean(false);

                // Loop for maxTries
                for (int attempt = 1; attempt <= counterOfMaxTries; attempt++) {
                    final int currentAttempt = attempt; // ✅ effectively final for lambda
                    System.out.println("🔄 Attempt " + currentAttempt + " of " + counterOfMaxTries);

                    if(attempt != 1){
                        refreshPage(failureHandling);
                    }


                    try {
                        // Configure FluentWait for each attempt
                        //setDefaultSettingsToFluentWait();
                        getFluentWait()

                                .ignoring(ElementNotInteractableException.class) // If element is not ready for interaction
                                .ignoring(StaleElementReferenceException.class) // DOM has changed
                                .ignoring(NoSuchElementException.class) // Element not found yet
                                .ignoring(NotFoundException.class) // WebDriver-level "not found"
                                .ignoring(InvalidElementStateException.class) // WebDriver-level "not found"

                                .withTimeout(Duration.ofSeconds(durationInSeconds))
                                .pollingEvery(Duration.ofMillis(TestBase.pollying))
                                .until(driver -> {

                                        if (isDisplayed(locator, FailureHandling.OPTIONAL, viewName)) {
                                            found.set(true);
                                            //System.out.println("✅ View '" + viewName + "' displayed successfully on attempt " + currentAttempt);
                                            return true;
                                        }
                                        return false;

                                });

                        if (found.get()) {
                            return true; // success
                        }
                    } catch (TimeoutException te) {
                        // If not found in this attempt, log and retry
                        System.out.println("⚠️ View not found in attempt " + currentAttempt + ", retrying...");
                    }
                }

                // After all tries fail
                String expected = "View '" + viewName + "' should be displayed after refreshing.";
                String actual = "View '" + viewName + "' is still not displayed after " + counterOfMaxTries + " refresh attempts.";
                handleSeleniumException(
                       null,
                        failureHandling,
                        step,
                        locator,
                        viewName,
                        "View not displayed for " + viewName,
                        "🔍 **Expected:** " + expected + "\n"
                                + "❗ **Actual:** " + actual + "\n"
                                + "🧩 **Issue Trigger:** " + step,
                        null
                );





        return false;
    }




    /*

     this function make strick checinng to wait
      until item is not displayed or not existed in
      dom and in that case will retrun true other wise
      return false and exception will occur
     */

    public static boolean isNotVisibleOrAbsent(By locator, FailureHandling failureHandling, String viewName, int ... timeout) {

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String step = "wait until " + viewName + " disappear";
            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);

            String defectSummary = "❌ View Still Displayed";
            String defectDescription = "🔍 **Expected:** The element '" + viewName + "' should disappear. " +
                    "❗ **Actual:** The element is still displayed after waiting " + timeout + " seconds! " +
                    "🧩 **Step:** " + step;
            String errorDetails = "Locator: " + locator.toString();

            try {
                return getFluentWait().until(d -> {
                    try {
                        // This will throw a NoSuchElementException if the element is not in the DOM.
                        // This is handled by the outer try-catch.
                        boolean isDisplayed = d.findElement(locator).isDisplayed();
                        //System.out.println("View is displayed? " + isDisplayed);
                        // The condition for the wait to finish is that the element is NOT displayed.
                        return !isDisplayed;
                    } catch (NoSuchElementException | StaleElementReferenceException e) {
                        // The element is not in the DOM, so the condition is met.
                        return true;
                    }
                });
            } catch (TimeoutException e) {
                System.out.println("TimeoutException occurred. Element is still visible.");
                handleSeleniumException(e, failureHandling, step, locator, viewName, defectSummary, defectDescription, errorDetails);
                return false;
            }

    }



    public static boolean isDisplayed(By locator, FailureHandling failureHandling, String viewName, int ... timeout) {
        String step = "Check if Displayed::" + viewName;
        AtomicReference<Boolean> result = new AtomicReference<>(false);

            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);
            Runnable isDisplayedCode =  () -> result.set(getDriver().findElement(locator).isDisplayed());
            try {
                getFluentWait().until(d -> {
                    isDisplayedCode.run();
                    return true;
                });
                return result.get();
            }

            catch (TimeoutException e) {

                System.out.println("Catching the first exception for the click" + e.getMessage());
                try {

                    resetWaitWithTimeoutNoIgnores();
                    isDisplayedCode.run();

                    return result.get();


                }
                catch(Exception e2){
                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                }


            }




        return false;

    }

    public static boolean verifyElementEnabled(By locator, FailureHandling failureHandling, String viewName, int ... timeout) {
        boolean isThereIssue = false;
        String step = "Verify Element Enabled::" + viewName;

            AtomicReference<Boolean> isEnabledResult = new AtomicReference<>(false);

            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);


            Runnable verifyElementEnabledCode = () ->
                    isEnabledResult.set(getDriver().findElement(locator).isEnabled());


            try {
                getFluentWait().until(d -> {
                    verifyElementEnabledCode.run();
                    //System.out.println("Result of enable: " + isEnabledResult.get());
                    return  isEnabledResult.get();
                    //return true;
                });
                return isEnabledResult.get();
            }

           catch (TimeoutException e) {

                System.out.println("TimeoutException");
                try {

                    resetWaitWithTimeoutNoIgnores();
                    verifyElementEnabledCode.run();


                    //  return isEnabledResult.get();




                } catch (Exception e2) {
                    isThereIssue = true;
                    System.out.println("e2 Exception + ");

                    System.out.println("e2 Exception + " + e2.getMessage());
                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                } // end of try
               finally{

                    System.out.println("Finally");
                    if(!isThereIssue){
                        System.out.println("Finally there is no issue");
                        Exception exception =  new RuntimeException("Element is not enabled" );

                        handleSeleniumException(exception, failureHandling, step, locator, viewName, "Element is not enabled", "Expected Result: "+viewName+" should be enabled so it can be interactable <br> Actual Result: "+viewName+" is not enabled ", "");

                    }
                    // I make here exception because there no specified exception in seleniun in case when the itme is not enabled, as for example for item display validation there will be run time exception thrown directly in case of failure so that our framework can catch it using catch;.

                    //  return isEnabledResult.get();
                }

                return false;
            }



    }


    // verifyElementSelected
    public static boolean verifyElementSelected(By locator, FailureHandling failureHandling, String viewName, int ... timeout) {
        boolean isThereIssue = false;
        String step = "Verify Element Selected::" + viewName;

            AtomicReference<Boolean> isSelectedResult = new AtomicReference<>(false);

            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);

            Runnable verifyElementSelectedCode = () ->
                    isSelectedResult.set(getDriver().findElement(locator).isSelected());

            try {
                getFluentWait().until(d -> {
                    verifyElementSelectedCode.run();
                   // System.out.println("Result of selected: " + isSelectedResult.get());
                    return isSelectedResult.get();
                });
                return isSelectedResult.get();
            } catch (TimeoutException e) {
                System.out.println("TimeoutException");
                try {
                    resetWaitWithTimeoutNoIgnores();
                    verifyElementSelectedCode.run();
                } catch (Exception e2) {
                    isThereIssue = true;
                    System.out.println("e2 Exception: " + e2.getMessage());
                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                } finally {
                    System.out.println("Finally");
                    if (!isThereIssue) {
                        System.out.println("Finally there is no issue");
                        Exception exception = new RuntimeException("Element is not selected");

                        handleSeleniumException(exception, failureHandling, step, locator, viewName,
                                "Element is not selected",
                                "Expected Result: " + viewName + " should be selected <br> Actual Result: " + viewName + " is not selected",
                                "");
                    }
                }
                return false;
            }


    }




    public static boolean verifyEqual(Object expectedResult, Object actualResult, FailureHandling failureHandling) {


        String step = "Check if Expected Result: " + expectedResult + " Equals Actual Result: " + actualResult;

         System.out.println("Entering block verifyEqual");

            System.out.println(
                    "No blocker.... \n Expected Result: " + expectedResult + " , Actaul Result: " + actualResult);

            if (expectedResult instanceof String && actualResult instanceof String) {
                expectedResult = ((String) expectedResult).trim().toLowerCase();
                actualResult = ((String) actualResult).trim().toLowerCase();
            }

            addStep(step);
            System.out.println(step);
            if (!expectedResult.equals(actualResult)) {

                System.out.println("Expected Result !=== Actual Result");

                handleSeleniumException(
                        new Exception("The Expected Result is: " + expectedResult + " but found: " + actualResult),
                        failureHandling, step, null, "", "Validation Failure", "The Expected Result is: " + expectedResult + " but found: " + actualResult, null);
            } else {
                return true;

            }


        return false;

    }

    public static boolean verifyNotEqual(Object expectedResult, Object actualResult, FailureHandling failureHandling) {
        String step = "Check if Expected Result: " + expectedResult + " not Equals Actual Result: " + actualResult;


            if (expectedResult instanceof String && actualResult instanceof String) {
                expectedResult = ((String) actualResult).trim().toLowerCase();
                actualResult = ((String) expectedResult).trim().toLowerCase();
            }

            addStep(step);
            System.out.println(step);
            if (!expectedResult.equals(actualResult)) {
                handleSeleniumException(
                        new Exception("The Expected Result is: " + expectedResult + " but found: " + actualResult),
                        failureHandling, step, null, "", null, null, null);
            } else {
                return true;
            }


        return false;

    }


    public static void back(FailureHandling failureHandling) {



        String step = "Navigate Back";
            addStep(step);
            System.out.println(step);
            try {
                getDriver().navigate().back();
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, null, "", null, null, null);
            }

    }


    public static void forward(FailureHandling failureHandling) {
        String step = "Navigate Forward";

            addStep(step);
            System.out.println(step);
            try {
                getDriver().navigate().forward();
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, null, "", null, null, null);
            }

    }


    public static void close(boolean closeCurrentWindow, FailureHandling failureHandling) {
        String step = "Close Current Window";

            addStep(step);
            System.out.println(step);
            try {
                getDriver().close();
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, null, "", null, null, null);
            }

    }


    public static ArrayList<String> getWindowHandles(FailureHandling failureHandling) {
        String step = "Get All Window Handles(Windows IDs)";

            addStep(step);
            System.out.println(step);
            try {
                Set<String> handlesSet = getDriver().getWindowHandles();

                return new ArrayList<>(handlesSet);
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, null, "Window Handles", "", "", "");
                return null;
            }

    }


    public static void switchToWindow(String windowHandle, FailureHandling failureHandling) {
        String step = "Switching to window: " + windowHandle;
            addStep(step);
            System.out.println(step);
            try {
                // Get all window handles
                //from
                ArrayList<String> windowHandles = getWindowHandles(failureHandling);
                boolean found = false;
                for (String handle : windowHandles) {
                    // Try switching by handle, then by title, then by URL containing the handle string
                    try {
                        getDriver().switchTo().window(handle);
                        if (handle.equals(windowHandle) || getDriver().getTitle().equals(windowHandle) || getDriver().getCurrentUrl().contains(windowHandle)) {
                            found = true;
                            break;
                        }
                    } catch (NoSuchWindowException e) {
                        // This window handle might be stale, continue to next
                        System.out.println("Window handle " + handle + " is stale or invalid. Trying next.");
                    }
                }
                if (!found) {
                    handleSeleniumException(new NoSuchWindowException("Window with handle or title/URL containing '" + windowHandle + "' not found."),
                            failureHandling, step, null, "", null, null, null);
                }
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, null, "", null, null, null);
            }

    }


    public static void switchToIframe(By locator, FailureHandling failureHandling, String viewName, int ... timeout) {
        String step = "Switching to iframe: " + viewName;

            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);

            Runnable switchToIframeCode = () -> {
                WebElement iframeElement = getFluentWait().until(d -> getDriver().findElement(locator));
                getDriver().switchTo().frame(iframeElement);
            };

            try {
                switchToIframeCode.run();
            } catch (TimeoutException e) {
                try {
                    resetWaitWithTimeoutNoIgnores();
                    switchToIframeCode.run();
                } catch (Exception e2) {
                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                }
            }


    }

    public static void switchToDefaultContent(FailureHandling failureHandling) {
        String step = "Switching to Default Content";

            addStep(step);
            System.out.println(step);
            try {
                getDriver().switchTo().defaultContent();
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, null, "", null, null, null);
            }

    }


    public static String getPropertyValue(By locator, String attributeName, FailureHandling failureHandling, String viewName, int ... timeout) {
        boolean isThereIssue = false;
        String step = "Get Property '" + attributeName + "' From::" + viewName;

            AtomicReference<String> value = new AtomicReference<>();

            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);

            Runnable getPropertyCode = () ->
                    value.set(getDriver().findElement(locator).getAttribute(attributeName));

            try {
                getFluentWait().until(d -> {
                    getPropertyCode.run();
                    //System.out.println("Property value: " + value.get());
                    return value.get() != null; // Wait until property is non-null
                });
                return value.get();
            } catch (TimeoutException e) {
                System.out.println("TimeoutException while getting property");
                try {
                    resetWaitWithTimeoutNoIgnores();
                    getPropertyCode.run();
                } catch (Exception e2) {
                    isThereIssue = true;
                    System.out.println("e2 Exception: " + e2.getMessage());
                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                } finally {
                    System.out.println("Finally");
                    if (!isThereIssue && (value.get() == null || value.get().isEmpty())) {
                        System.out.println("Finally: property value could not be retrieved");
                        Exception exception = new RuntimeException("Property '" + attributeName + "' could not be retrieved");

                        handleSeleniumException(exception, failureHandling, step, locator, viewName,
                                "Property value is null or empty",
                                "Expected Result: " + viewName + " should have attribute '" + attributeName + "' <br> Actual Result: value is null or empty",
                                "");
                    }
                }
                return value.get();
            }


    }










    public static void handleSeleniumException(Exception e, FailureHandling handling, String contextMessage, By locator,
                                               String viewName, String optionalSummary, String optionalDescription, String optionalErrorDetails) {

        String fullMessage;
        String detailedMessage = "";
        String defectSummary = "";
        String defectDescription = "";
        String automationErrorDetails = (e != null) ? e.getMessage() : "";





            // log details in case no blocker issue has been faced before
            boolean isSeleniumRelated =
                    e instanceof NoSuchElementException ||
                            e instanceof StaleElementReferenceException ||
                            e instanceof ElementNotInteractableException ||
                            e instanceof InvalidElementStateException ||
                            e instanceof TimeoutException ||
                            e instanceof ElementClickInterceptedException ||
                            e instanceof WebDriverException || e instanceof JavascriptException;

            if (e != null && isSeleniumRelated) {
                boolean locatorInvolved = locator != null;

                fullMessage = contextMessage + (locatorInvolved ? " - Exception while handling locator: " + locator.toString() : "");

                // Locator-independent exception handling (e.g., browser setup issues)
                if (!locatorInvolved) {
                    if (e instanceof WebDriverException && e.getMessage().contains("session not created")) {
                        detailedMessage = "🚧 SessionNotCreatedException: Failed to create a browser session. <br>" + e.getMessage();
                        defectSummary = "🚧 WebDriver Session Failure";
                        defectDescription = "🔍 **Expected:** A browser session should start normally.<br>" +
                                "❗ **Actual:** WebDriver could not start a session.<br>" +
                                "🧩 **Issue Trigger** " + contextMessage;
                    } else if (e instanceof WebDriverException) {
                        System.out.println("I am here");
                        System.out.println("I am not here" + e.getMessage());
                        detailedMessage = "🚨 WebDriverException: General WebDriver error. <br>" + e.getMessage();
                        defectSummary = "🚨 WebDriver Error";
                        defectDescription = "🔍 **Expected:** Action should complete successfully.<br>" +
                                "❗ **Actual:** A WebDriver exception occurred.<br>" +
                                "🧩 **Issue Trigger** " + contextMessage;
                    } else {
                        detailedMessage = "❓ Unknown Selenium Exception occurred during execution.<br>Please refer to the logs for more details.";
                        defectSummary = "❓ Unknown Selenium Error";
                        defectDescription = "🔍 **Expected:** Operation should succeed.<br>" +
                                "❗ **Actual:** An unexpected error occurred during test execution.<br>" +
                                "🧩 **Issue Trigger** " + contextMessage;
                    }
                } else {
                    // Locator-related exception handling (preserve original logic)
                    if (e instanceof NoSuchElementException) {
                        detailedMessage = "❌ NoSuchElementException: The element was not found on the page. " + fullMessage;
                        defectSummary = "❌ Element Not Found";
                        defectDescription = "🔍 **Expected:** The element '" + viewName + "' should be visible and present on the page.<br>" +
                                "❗ **Actual:** The element '" + viewName + "' could not be found.<br>" +
                                "🧩 **Issue Trigger** " + contextMessage;
                    } else if (e instanceof StaleElementReferenceException) {
                        detailedMessage = "♻️ StaleElementReferenceException: The element is no longer attached to the DOM. " + fullMessage;
                        defectSummary = "♻️ Stale Element Reference";
                        defectDescription = "🔍 **Expected:** The element '" + viewName + "' should remain attached to the page.<br>" +
                                "❗ **Actual:** The element '" + viewName + "' became detached or stale.<br>" +
                                "🧩 **Issue Trigger** " + contextMessage;
                    } else if (e instanceof ElementNotInteractableException) {
                        detailedMessage = "🚫 ElementNotInteractableException: Element is present but not interactable. " + fullMessage;
                        defectSummary = "🚫 Element Not Interactable";
                        defectDescription = "🔍 **Expected:** The element '" + viewName + "' should be interactable (clickable, fillable).<br>" +
                                "❗ **Actual:** The element '" + viewName + "' is not currently interactable.<br>" +
                                "🧩 **Issue Trigger** " + contextMessage;
                    } else if (e instanceof InvalidElementStateException) {
                        detailedMessage = "⚠️ InvalidElementStateException: The element is in an invalid state. " + fullMessage;
                        defectSummary = "⚠️ Invalid Element State";
                        defectDescription = "🔍 **Expected:** The element '" + viewName + "' should be ready for interaction.<br>" +
                                "❗ **Actual:** The element is disabled or read-only.<br>" +
                                "🧩 **Issue Trigger** " + contextMessage;
                    } else if (e instanceof TimeoutException) {
                        detailedMessage = "⏰ TimeoutException: Waiting for element timed out. " + fullMessage;
                        defectSummary = "⏰ Timeout";
                        defectDescription = "🔍 **Expected:** The element '" + viewName + "' should appear within the timeout.<br>" +
                                "❗ **Actual:** The element '" + viewName + "' did not appear in time.<br>" +
                                "🧩 **Issue Trigger** " + contextMessage;
                    } else if (e instanceof ElementClickInterceptedException) {
                        detailedMessage = "🛑 ElementClickInterceptedException: Click action was intercepted. " + fullMessage;
                        defectSummary = "🛑 Click Intercepted";
                        defectDescription = "🔍 **Expected:** The element '" + viewName + "' should be clickable without obstruction.<br>" +
                                "❗ **Actual:** Another element blocked the click on '" + viewName + "'.<br>" +
                                "🧩 **Issue Trigger** " + contextMessage;
                    } else if (e instanceof WebDriverException && e.getMessage().contains("move target out of bounds")) {
                        detailedMessage = "📍 MoveTargetOutOfBoundsException: Cannot move to the target element—it is out of view. " + fullMessage;
                        defectSummary = "📍 Element Out of View";
                        defectDescription = "🔍 **Expected:** The element '" + viewName + "' should be within visible bounds.<br>" +
                                "❗ **Actual:** The element is outside the screen or scrollable area.<br>" +
                                "🧩 **Issue Trigger** " + contextMessage;
                    } else if (e instanceof JavascriptException) {
                        detailedMessage = "💥 JavascriptException: JavaScript execution failed. " + fullMessage;
                        defectSummary = "💥 JavaScript Error";
                        defectDescription = "🔍 **Expected:** JavaScript should execute successfully on '" + viewName + "'.<br>" +
                                "❗ **Actual:** JavaScript error occurred during execution.<br>" +
                                "🧩 **Issue Trigger** " + contextMessage;
                    } else {
                        detailedMessage = "❓ Unknown Selenium Exception: " + e.getClass().getSimpleName() + ". " + fullMessage;
                        defectSummary = "❓ Unknown Selenium Error";
                        defectDescription = "🔍 **Expected:** The element '" + viewName + "' should behave correctly.<br>" +
                                "❗ **Actual:** Unexpected exception: `" + e.getClass().getSimpleName() + "`.<br>" +
                                "🧩 **Issue Trigger** " + contextMessage;
                    }
                }
            } else {
                // Not a Selenium exception
                detailedMessage = "❌ Exception while validating: " + contextMessage + "<br>" + automationErrorDetails + "<br><br>";
                defectSummary = "Validation Failure";
                defectDescription = "Exception occurred while validating precondition/context: " + contextMessage;
            }


        // end of new issue collector block


        // in case user want to make custom validations.
        // Override defaults if optional parameters are provided
        if (optionalSummary != null && !optionalSummary.trim().isEmpty()) {
            defectSummary = optionalSummary.trim();
        }

        if (optionalDescription != null && !optionalDescription.trim().isEmpty()) {
            defectDescription = optionalDescription.trim();
        }

        if (optionalErrorDetails != null && !optionalErrorDetails.trim().isEmpty()) {
            automationErrorDetails = optionalErrorDetails.trim();
        }


        String finalDetailedMessage = detailedMessage;
        String finalDefectSummary = defectSummary;
        String finalDefectDescription = defectDescription;

        try {
            throw new RuntimeException("Detailed Error: " + finalDetailedMessage);
        } catch (Throwable ex) {
            String msg = "[" + handling.name() + "] " + ex.getMessage();


            if (!getReportAttributes().isSkipCurrentTestCase()) {
                switch (handling) {
                    case CONTINUE_ON_FAILURE:


                        getReportAttributes().getTestCaseErrorsSummary().add("Issue Summary: 🐞:<br> " + defectSummary);
                        getReportAttributes().getTestCaseErrorsDescription().add("Issue Description 📝:<br>"
                                + finalDefectDescription + ", <br><br> Steps to Produce: " + getCurrentTestCaseSteps());

                        getReportAttributes().getTestCaseAutomationErrors().add("Technical Issue Details:💻 <br> " + finalDetailedMessage);


                        if (getReportAttributes().getNumberOfScreenshotsAttached() < 2) {
                            SmartImageAttacher.attachScreenshotToCustomReport();
                            getReportAttributes().setNumberOfScreenshotsAttached(getReportAttributes().getNumberOfScreenshotsAttached() + 1);

                        }

                        System.err.println("⚠️ " + msg);
                        Reporter.log("⚠️ " + msg, true);
                        getSoftAssert().fail(msg);
                        break;

                    case STOP_ON_FAILURE:
                        //getReportAttributes().isSkipCurrentTestCase() = true;
                        System.out.println("❌error__: " + msg);

                        Reporter.log("❌ " + msg, true);
                        getReportAttributes().setSkipCurrentTestCase(true);
                        SmartImageAttacher.attachScreenshotToCustomReport();
                        getReportAttributes().setNumberOfScreenshotsAttached(getReportAttributes().getNumberOfScreenshotsAttached() + 1);
                        getReportAttributes().setBlockerIssue("Impacted By Blocker Issue: " + finalDefectDescription);


                        getReportAttributes().getTestCaseErrorsDescription().add("Issue Description 📝:<br>"
                                + getReportAttributes().getBlockerIssue() + ", <br><br> Steps to Produce: " + getCurrentTestCaseSteps());


                        getReportAttributes().getTestCaseErrorsSummary().add("");

                        getReportAttributes().getTestCaseAutomationErrors().add("");


                        throw new RuntimeException(msg, ex);


                    case OPTIONAL:
                        System.out.println("🔸" + msg);
                        Reporter.log("🔸" + msg, true);
                        break;
                }

            } else {




                boolean isTestCaseContainsBlockerIssue = false;
                for (int i = 0; i < getReportAttributes().getTestCaseErrorsDescription().size(); i++) {
                    if (getReportAttributes().getTestCaseErrorsDescription().get(i).contains(TestBase.getReportAttributes().getBlockerIssue())) {
                        isTestCaseContainsBlockerIssue = true;
                        break;
                    }


                }

                if (!isTestCaseContainsBlockerIssue) {



                    System.out.println("Add the blocker issue now: " +    getReportAttributes().getBlockerIssue());
                    getReportAttributes().getTestCaseErrorsDescription().add("Issue Description 📝:<br>"
                            +    getReportAttributes().getBlockerIssue() + ", <br><br> Steps to Produce: " + getCurrentTestCaseSteps());


                    getReportAttributes().getTestCaseErrorsSummary().add("");

                    getReportAttributes().getTestCaseAutomationErrors().add("");
                    getSoftAssert().fail(msg);
                    throw new RuntimeException(msg, ex);



                }







            }


            //SmartUIValidator.assertAll();

            // This will re-trigger a failure for TestNG


        }
    }

    public static void addStep(String stepTitle) {

        // getReportAttributes().getTestCaseSteps().add(stepTitle)

        int size = getReportAttributes().getTestCaseSteps().size();
        int newIndex = size + 1;
        getReportAttributes().getTestCaseSteps().add("<br>" + newIndex + "- : " + stepTitle);
    }

    public static String getCurrentTestCaseSteps() {

        String testCaseStep = "";
        for (int i = 0; i < getReportAttributes().getTestCaseSteps().size(); i++) {

            testCaseStep = testCaseStep + "<br>" + getReportAttributes().getTestCaseSteps().get(i);
            System.out.println("Steps was: " + testCaseStep);

        }

        //System.out.println("Steps was: " + testCaseStep);
        return testCaseStep;
    }

    public static void assertAll() {
        SoftAssert softAssert = FailureHandler.softAssertMap.get((int) Thread.currentThread().getId());
        if (softAssert != null) {
            try {
                softAssert.assertAll();
            } finally {
                // Clear after assertion to prevent memory leaks
                FailureHandler.softAssertMap.remove((int) Thread.currentThread().getId());
            }
        }
    }

    public static void stopIfFailed(FailureHandling handling) {
        if (handling == FailureHandling.STOP_ON_FAILURE) {
            assertAll(); // Will throw AssertionError if any collected failures exist
        }
    }


    public static SoftAssert getSoftAssert() {
        return FailureHandler.softAssertMap.computeIfAbsent(
                (int) Thread.currentThread().getId(),
                k -> new SoftAssert()
        );
    }


    // Custom exception for when a window is not found
    private static class NoSuchWindowException extends RuntimeException {
        public NoSuchWindowException(String message) {
            super(message);
        }
    }

    // new not tested functions
    public static String getPageTitle(FailureHandling failureHandling) {
        String step = "Get Page Title";
            addStep(step);
            System.out.println(step);
            try {
                String pageTitle = getDriver().getTitle();
                System.out.println("Page Title: " + pageTitle);
                return pageTitle;
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, null, "Page Title", "", "", "");
                return null;
            }

    }


    public static String getCurrentUrl(FailureHandling failureHandling) {
        String step = "Get Current URL";
            addStep(step);
            System.out.println(step);
            try {
                String currentUrl = getDriver().getCurrentUrl();
                System.out.println("Current URL: " + currentUrl);
                return currentUrl;
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, null, "Current URL", "", "", "");
                return null;
            }

    }

    public static void dragAndDrop(By sourceLocator, By targetLocator, FailureHandling failureHandling,

                                   String sourceViewName, String targetViewName, int ... timeout) {

        String step = "Drag '" + sourceViewName + "' and drop onto '" + targetViewName + "'";


            addStep(step);
            setDefaultSettingsToFluentWait(timeout);

            System.out.println(step);

            Runnable dragAndDropCode = () -> {

                WebElement sourceElement = getDriver().findElement(sourceLocator);

                WebElement targetElement = getDriver().findElement(targetLocator);

                new Actions(getDriver()).dragAndDrop(sourceElement, targetElement).perform();

            };

            try {

                getFluentWait().until(d -> {

                    dragAndDropCode.run();

                    return true;

                });

            } catch (TimeoutException e) {

                try {

                    resetWaitWithTimeoutNoIgnores();

                    dragAndDropCode.run();

                } catch (Exception e2) {

                    handleSeleniumException(e2, failureHandling, step, sourceLocator,

                            "Source: " + sourceViewName + ", Target: " + targetViewName, "", "", "");

                }

            }



    }




    public static void rightClick(By locator, FailureHandling failureHandling, String viewName, int ... timeout) {

        String step = "Right-click on::" + viewName;


            addStep(step);
            setDefaultSettingsToFluentWait(timeout);

            System.out.println(step);

            Runnable rightClickCode = () -> {

                WebElement element = getDriver().findElement(locator);

                new Actions(getDriver()).contextClick(element).perform();

            };

            try {

                getFluentWait().until(d -> {

                    rightClickCode.run();

                    return true;

                });

            } catch (TimeoutException e) {

                try {

                    resetWaitWithTimeoutNoIgnores();

                    rightClickCode.run();

                } catch (Exception e2) {

                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");

                }

            }



    }




    public static void doubleClick(By locator, FailureHandling failureHandling, String viewName, int ... timeout) {
        String step = "Double-click on::" + viewName;

            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);

            Runnable doubleClickCode = () -> {
                WebElement element = getDriver().findElement(locator);
                new Actions(getDriver()).doubleClick(element).perform();
            };

            try {
                getFluentWait().until(d -> {
                    doubleClickCode.run();
                    return true;
                });
            } catch (TimeoutException e) {
                try {
                    resetWaitWithTimeoutNoIgnores();
                    doubleClickCode.run();
                } catch (Exception e2) {
                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                }
            }


    }


    // new functions to test:

    public static boolean verifyElementAttributeValue(By locator, String attributeName, String expectedValue, FailureHandling failureHandling, String viewName, int ... timeout) {
        boolean isThereIssue = false;
        String step = "Verify '" + attributeName + "' attribute of " + viewName + " equals '" + expectedValue + "'";

            AtomicReference<String> actualValue = new AtomicReference<>("");

            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);

            Runnable getAttributeCode = () ->
                    actualValue.set(getDriver().findElement(locator).getAttribute(attributeName));

            try {
                Boolean until = getFluentWait().until(d -> {
                    getAttributeCode.run();
                   // System.out.println("Current value of '" + attributeName + "': " + actualValue.get());
                    return expectedValue.equals(actualValue.get());
                });
                System.out.println("Validation successful: '" + attributeName + "' of " + viewName + " is '" + actualValue.get() + "'.");
                return true;
            } catch (TimeoutException e) {
                System.out.println("TimeoutException");
                try {
                    resetWaitWithTimeoutNoIgnores();
                    getAttributeCode.run();
                } catch (Exception e2) {
                    isThereIssue = true;
                    System.out.println("e2 Exception: " + e2.getMessage());
                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                } finally {
                    System.out.println("Finally");
                    if (!isThereIssue) {
                        System.out.println("Finally there is no issue");
                        Exception exception = new RuntimeException("Attribute value mismatch");

                        handleSeleniumException(exception, failureHandling, step, locator, viewName,
                                "Attribute Value Mismatch for " + viewName,
                                "🔍 **Expected:** The '" + attributeName + "' attribute of element '" + viewName + "' should be '" + expectedValue + "'.<br>" +
                                        "❗ **Actual:** The '" + attributeName + "' attribute is '" + actualValue.get() + "'.<br>" +
                                        "🧩 **Issue Trigger** " + step,
                                null);
                    }
                }
                return false;
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, locator, viewName, "", "", "");
                return false;
            }


    }



    public static boolean verifyElementCssValue(By locator, String propertyName, String expectedValue, FailureHandling failureHandling, String viewName, int ... timeout) {
        boolean isThereIssue = false;
        String step = "Verify CSS property '" + propertyName + "' of " + viewName + " equals '" + expectedValue + "'";

            AtomicReference<String> actualValue = new AtomicReference<>("");

            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);

            Runnable getCssValueCode = () ->
                    actualValue.set(getDriver().findElement(locator).getCssValue(propertyName));

            try {
                getFluentWait().until(d -> {
                    getCssValueCode.run();
                    //System.out.println("Current CSS value of '" + propertyName + "': " + actualValue.get());
                    return expectedValue.equals(actualValue.get());
                });
                System.out.println("Validation successful: CSS '" + propertyName + "' of " + viewName + " is '" + actualValue.get() + "'.");
                return true;
            } catch (TimeoutException e) {
                System.out.println("TimeoutException");
                try {
                    resetWaitWithTimeoutNoIgnores();
                    getCssValueCode.run();
                } catch (Exception e2) {
                    isThereIssue = true;
                    System.out.println("e2 Exception: " + e2.getMessage());
                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                } finally {
                    System.out.println("Finally");
                    if (!isThereIssue) {
                        System.out.println("Finally there is no issue");
                        Exception exception = new RuntimeException("CSS property value mismatch");

                        handleSeleniumException(exception, failureHandling, step, locator, viewName,
                                "CSS Property Value Mismatch for " + viewName,
                                "🔍 **Expected:** The CSS property '" + propertyName + "' of element '" + viewName + "' should be '" + expectedValue + "'.<br>" +
                                        "❗ **Actual:** The CSS property '" + propertyName + "' is '" + actualValue.get() + "'.<br>" +
                                        "🧩 **Issue Trigger** " + step,
                                null);
                    }
                }
                return false;
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, locator, viewName, "", "", "");
                return false;
            }


    }



    public static boolean verifyElementHasText(By locator, String expectedText, FailureHandling failureHandling, String viewName, int ... timeout) {
        String step = "Verify text of " + viewName + " contains '" + expectedText + "'";
            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);
            try {
                AtomicReference<String> actualText = new AtomicReference<>();
                getFluentWait().until(d -> {
                    actualText.set(getDriver().findElement(locator).getText());
                    return true;
                });

                if (actualText.get() != null && actualText.get().contains(expectedText)) {
                    System.out.println("Validation successful: Text of " + viewName + " contains '" + expectedText + "'. Actual text: '" + actualText.get() + "'.");
                    return true;
                } else {
                    handleSeleniumException(
                            new Exception("Text mismatch. Expected text to contain '" + expectedText + "' but found '" + actualText.get() + "' in element " + viewName + "."),
                            failureHandling,
                            step,
                            locator,
                            viewName,
                            "Element Text Mismatch for " + viewName,
                            "🔍 **Expected:** The element '" + viewName + "' should contain the text '" + expectedText + "'.<br>" +
                                    "❗ **Actual:** The element's text is '" + actualText.get() + "'.<br>" +
                                    "🧩 **Issue Trigger** " + step,
                            null
                    );
                    return false;
                }
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, locator, viewName, "", "", "");
                return false;
            }

    }


    public static boolean verifyElementCount(By locator, int expectedCount, FailureHandling failureHandling, String viewName, int ... timeout) {
        boolean isThereIssue = false;
        String step = "Verify " + viewName + " count is " + expectedCount;

            AtomicReference<Integer> actualCount = new AtomicReference<>(0);

            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);

            Runnable getCountCode = () ->
                    actualCount.set(getDriver().findElements(locator).size());

            try {
                getFluentWait().until(d -> {
                    getCountCode.run();
                    //System.out.println("Current count of " + viewName + ": " + actualCount.get());
                    return actualCount.get() == expectedCount;
                });
                System.out.println("Validation successful: " + viewName + " count is " + actualCount.get() + ".");
                return true;
            } catch (TimeoutException e) {
                System.out.println("TimeoutException");
                try {
                    resetWaitWithTimeoutNoIgnores();
                    getCountCode.run();
                } catch (Exception e2) {
                    isThereIssue = true;
                    System.out.println("e2 Exception: " + e2.getMessage());
                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                } finally {
                    System.out.println("Finally");
                    if (!isThereIssue) {
                        System.out.println("Finally there is no issue");
                        Exception exception = new RuntimeException("Element count mismatch");

                        handleSeleniumException(exception, failureHandling, step, locator, viewName,
                                "Element Count Mismatch for " + viewName,
                                "🔍 **Expected:** To find " + expectedCount + " elements for '" + viewName + "'.<br>" +
                                        "❗ **Actual:** Found " + actualCount.get() + " elements for '" + viewName + "'.<br>" +
                                        "🧩 **Issue Trigger** " + step,
                                null);
                    }
                }
                return false;
            } catch (Exception e) {
                handleSeleniumException(e, failureHandling, step, locator, viewName, "", "", "");
                return false;
            }


    }








    public static void scrollToElement2(By locator, FailureHandling failureHandling, String viewName, int ... timeout) {
        String step = "Scroll to element::" + viewName;
            addStep(step);
            setDefaultSettingsToFluentWait(timeout);
            System.out.println(step);
            Runnable scrollCode = () -> {
                WebElement element = getDriver().findElement(locator);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            };
            try {
                getFluentWait().until(d -> {
                    scrollCode.run();
                    return true;
                });
            } catch (TimeoutException e) {
                try {
                    resetWaitWithTimeoutNoIgnores();
                    scrollCode.run();
                } catch (Exception e2) {
                    handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
                }
            }


    }

    public static Object executeJavaScript(String script, FailureHandling failureHandling, String scriptName) {
        String step = "Execute JavaScript: " + scriptName;

            addStep(step);
            System.out.println(step);
            try {
                JavascriptExecutor js = (JavascriptExecutor) getDriver();
                // You might want to add a fluent wait here if the script relies on elements that might not be immediately present
                Object result = js.executeScript(script);
                return result;
            } catch (Exception e) {
				/*
				handleSeleniumException(e, failureHandling, step, null, null,
						"JavaScript Execution Failed", "Failed to execute the JavaScript: " + script + ""),
						e.getMessage(), "");
*/


                return null;
            }

    }




    // newer functions






    private static String getCurrentFramePath(int depth, int frameIndex) {
        StringBuilder path = new StringBuilder("defaultContent");
        for (int i = 0; i <= depth; i++) {
            path.append(" > iframe[").append(frameIndex + 1).append("]");
        }
        return path.toString();
    }



private static void setDefaultSettingsToFluentWait(int ... timeout){





        getFluentWait()
            .pollingEvery(Duration.ofMillis(TestBase.pollying)) // Retry interval
            .ignoring(ElementNotInteractableException.class) // If element is not ready for interaction
            .ignoring(StaleElementReferenceException.class) // DOM has changed
            .ignoring(NoSuchElementException.class) // Element not found yet
            .ignoring(NotFoundException.class) // WebDriver-level "not found"
            .ignoring(InvalidElementStateException.class); // WebDriver-level "not found"

    if(timeout.length > 0){
        System.out.println("time is set from function validator and it's " + timeout[0]);
        getFluentWait().withTimeout(Duration.ofSeconds(timeout[0])); // Total wait time

    }
    else{
        System.out.println("time is set from suite level and it's " + getReportAttributes().timeout);

        getFluentWait().withTimeout(Duration.ofSeconds(getReportAttributes().timeout)); // Total wait time

    }



}

    private static void resetWaitWithTimeoutNoIgnores(){

        FluentWait fluentWait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(10)) // Total wait time
                .pollingEvery(Duration.ofMillis(TestBase.pollying)); // Retry interval


        setFluentWait(fluentWait);
    }




    // --- New Constants for Gestures/Scrolling (Adapted from MobileGestures) ---
    public static final String UP_DIRECTION = "UP";
    public static final String DOWN_DIRECTION = "DOWN";
    public static final String LEFT_DIRECTION = "LEFT";
    public static final String RIGHT_DIRECTION = "RIGHT";

    public static final String QUARTER_SCROLLING = "QUARTER";
    public static final String HALF_SCROLLING = "HALF";





    public static boolean swipePageUntilElementIsVisible(By locator, String direction,
                                                         FailureHandling failureHandling, String viewName,
                                                         int repeatEvery, int scrollDistance,
                                                         By scrollViewLocator, int... timeout) {


        String directionEmoji = switch (direction.toLowerCase()) {
            case "down" -> "⬇️";
            case "up" -> "⬆️";
            case "left" -> "⬅️";
            case "right" -> "➡️";
            default -> "↕️";
        };

        String step = "\uD83D\uDDB1\uFE0F Swiping " + directionEmoji + " until element visible & clickable :: " + viewName;

        addStep(step);
        setDefaultSettingsToFluentWait(timeout);
        getFluentWait().pollingEvery(Duration.ofSeconds(repeatEvery));
        System.out.println(step);

        WebDriver driver = getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        AtomicBoolean ready = new AtomicBoolean(false);

        // Determine scroll container
        WebElement scrollContainer = null;
        if (scrollViewLocator != null) {
            try {
                scrollContainer = driver.findElement(scrollViewLocator);
            } catch (Exception e) {
                System.out.println("⚠️ Scroll view locator not found, falling back to window scrolling.");
            }
        }

        final WebElement scrollElement = scrollContainer;
        final int scrollAmount = scrollDistance;
        final String swipeDirection = direction.toLowerCase();

        // Helper: scroll one step in given direction
        Runnable scrollInDirection = () -> {
            if (scrollElement != null) {
                switch (swipeDirection) {
                    case "down" -> js.executeScript("arguments[0].scrollBy(0, arguments[1]);", scrollElement, scrollAmount);
                    case "up" -> js.executeScript("arguments[0].scrollBy(0, arguments[1]);", scrollElement, -scrollAmount);
                    case "left" -> js.executeScript("arguments[0].scrollBy(arguments[1], 0);", scrollElement, -scrollAmount);
                    case "right" -> js.executeScript("arguments[0].scrollBy(arguments[1], 0);", scrollElement, scrollAmount);
                    default -> throw new IllegalArgumentException("Invalid direction: " + direction);
                }
            } else {
                switch (swipeDirection) {
                    case "down" -> js.executeScript("window.scrollBy(0, arguments[0]);", scrollAmount);
                    case "up" -> js.executeScript("window.scrollBy(0, arguments[0]);", -scrollAmount);
                    case "left" -> js.executeScript("window.scrollBy(arguments[0], 0);", -scrollAmount);
                    case "right" -> js.executeScript("window.scrollBy(arguments[0], 0);", scrollAmount);
                    default -> throw new IllegalArgumentException("Invalid direction: " + direction);
                }
            }
        };

        // Helper: check if element is actually visible inside scroll container or window
        BiFunction<WebElement, WebElement, Boolean> isVisiblyInViewport = (container, target) -> {
            try {
                if (container != null) {
                    return (Boolean) js.executeScript(
                            "const elem = arguments[1]; const container = arguments[0];" +
                                    "const elemRect = elem.getBoundingClientRect();" +
                                    "const contRect = container.getBoundingClientRect();" +
                                    "return (elemRect.left >= contRect.left && elemRect.right <= contRect.right && " +
                                    "elemRect.top >= contRect.top && elemRect.bottom <= contRect.bottom);",
                            container, target
                    );
                } else {
                    return (Boolean) js.executeScript(
                            "const elem = arguments[0];" +
                                    "const r = elem.getBoundingClientRect();" +
                                    "return (r.right > 0 && r.left < window.innerWidth && r.bottom > 0 && r.top < window.innerHeight);",
                            target
                    );
                }
            } catch (Exception e) {
                return false;
            }
        };

        try {
            // FluentWait until element is visible, enabled, and actually in viewport
            getFluentWait().until(d -> {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    WebElement target = elements.get(0);
                    boolean visuallyVisible = isVisiblyInViewport.apply(scrollElement, target);
                    if (target.isDisplayed() && target.isEnabled() && visuallyVisible) {
                        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", target);
                        System.out.println("✅ Element visible & clickable: " + viewName);
                        ready.set(true);
                        return true;
                    }
                }
                scrollInDirection.run();
                return false;
            });

        } catch (TimeoutException e) {
            System.out.println("⚠️ Timeout reached — reversing direction to restore view.");

            final String oppositeDirection = switch (swipeDirection) {
                case "down" -> "up";
                case "up" -> "down";
                case "left" -> "right";
                case "right" -> "left";
                default -> "down";
            };

            Runnable reverseScroll = () -> {
                if (scrollElement != null) {
                    switch (oppositeDirection) {
                        case "down" -> js.executeScript("arguments[0].scrollBy(0, arguments[1]);", scrollElement, scrollAmount);
                        case "up" -> js.executeScript("arguments[0].scrollBy(0, arguments[1]);", scrollElement, -scrollAmount);
                        case "left" -> js.executeScript("arguments[0].scrollBy(arguments[1], 0);", scrollElement, -scrollAmount);
                        case "right" -> js.executeScript("arguments[0].scrollBy(arguments[1], 0);", scrollElement, scrollAmount);
                    }
                } else {
                    switch (oppositeDirection) {
                        case "down" -> js.executeScript("window.scrollBy(0, arguments[0]);", scrollAmount);
                        case "up" -> js.executeScript("window.scrollBy(0, arguments[0]);", -scrollAmount);
                        case "left" -> js.executeScript("window.scrollBy(arguments[0], 0);", -scrollAmount);
                        case "right" -> js.executeScript("window.scrollBy(arguments[0], 0);", scrollAmount);
                    }
                }
            };

            try {
                resetWaitWithTimeoutNoIgnores();
                getFluentWait().until(d -> {
                    List<WebElement> elements = driver.findElements(locator);
                    if (!elements.isEmpty()) {
                        WebElement target = elements.get(0);
                        boolean visuallyVisible = isVisiblyInViewport.apply(scrollElement, target);
                        if (target.isDisplayed() && target.isEnabled() && visuallyVisible) {
                            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", target);
                            System.out.println("✅ Element visible & clickable after reverse scroll: " + viewName);
                            ready.set(true);
                            return true;
                        }
                    }
                    reverseScroll.run();
                    return false;
                });
            } catch (Exception e2) {
                System.out.println("e2 Exception:::: " + e2.getMessage());
                handleSeleniumException(e2, failureHandling, step, locator, viewName, "", "", "");
            }
        }

        if (!ready.get()) {
            System.out.println("❌ Element not visible or clickable after all attempts: " + viewName);
        }

        return ready.get();
    }









}
