package com.sage.tests;


import com.sage.base.Runners.JavaDebugRunner;
import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import com.sage.testdata.dataDriven.External.ExternalDataDrivenTestUtil;
import com.sage.testdata.dataDriven.RunTimeAttributes.SharedTestData;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.util.Map;


public class JustForTest extends TestBase {

@DataProvider
public Object[][] formDataProvider(){

    String excelSheetPath = "C:\\Users\\user\\Desktop\\MyDataUntil2025\\Tutorials\\Cources\\Docker\\Projects\\MeetingTutorialsAndDocumentations\\formTestData.xlsx";
    String testDataSheet = "FromTestDataSheet";


    ExternalDataDrivenTestUtil externalDataDrivenTestUtil = new ExternalDataDrivenTestUtil();
    Object[][] data =   externalDataDrivenTestUtil.readDataFromExcelSheetFile(excelSheetPath, testDataSheet, true, -1);

return data;


}



    @Test(priority = 1 )
    public void NavigateToGoogleTest(){

try{
SmartUIValidator.navigateToUrl("https://www.google.com/", FailureHandler.FailureHandling.STOP_ON_FAILURE);


        }

        finally {
            SmartUIValidator.assertAll();

        }


    }



/*
    @Test(priority = 2, dataProvider = "formDataProvider")
public void ValidateDate(String username, String password, String email, String gender){



    String formPageUrl = "C:\\Users\\user\\Desktop\\MyDataUntil2025\\Tutorials\\Cources\\Docker\\Projects\\MeetingTutorialsAndDocumentations\\page.html";




    SmartUIValidator.navigateToUrl(formPageUrl, FailureHandler.FailureHandling.STOP_ON_FAILURE);

    System.out.println("Otp is " + JavaDebugRunner.getLatestOtp("1721080081", FailureHandler.FailureHandling.STOP_ON_FAILURE));



    try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }

        try {

            By userNameLocator = By.xpath("//*[@id= 'input_username']");
            By passwordLocator = By.xpath("//*[@id= 'input_password']");
            By emailLocator = By.xpath("//*[@id= 'input_email']");
            By maleGenderLocator = By.xpath("//*[@id= 'radio_male']");
            By femaleGenderLocator = By.xpath("//*[@id= 'radio_female']");
            By sendButton = By.xpath("//*[@id='btn_send']");




            SmartUIValidator.sendKeys(userNameLocator, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE, "userName Field", username);
            SmartUIValidator.sendKeys(passwordLocator, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE, "password Field", password);
            SmartUIValidator.sendKeys(emailLocator, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE, "Email Field", email);

            if(gender.equalsIgnoreCase("Male")){
                SmartUIValidator.sendKeys(maleGenderLocator, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE, "Male Radio button", gender);

            }
            else{
                SmartUIValidator.sendKeys(femaleGenderLocator, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE, "Female radio button", gender);

            }

            SmartUIValidator.click(sendButton, FailureHandler.FailureHandling.STOP_ON_FAILURE, "Send Button");






        }

        finally {
            SmartUIValidator.assertAll();

        }


    }



*/





}
