package com.sage.pages.systems.LenPortal;

import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;

public class RegisterAccountPage extends TestBase {

    // for auto fill validation
    By idTextField = By.xpath("//input[@id='txtIdentityNumber']");
    By fullNameTextField = By.xpath("//input[@id='txtArabicName']");
    By birthOfDateTextField = By.xpath("//input[@id='txtBirthDate']");


    // for entering
    By mobileNumberTextField = By.xpath("//input[@id='txtMobileNumber']");
    By emailTextField = By.xpath("//input[@id='txtEmail']");


    // DDLs
    By idIssurancePlaceDDL = By.xpath("//select[@id='ddlIdentityIssuePlace']");
    By placeOfBirthTextDDL = By.xpath("//select[@id='ddlBirthPlace' or @name='ddlBirthPlace']");

// check box
    By acceptDataEnteringCheckBox = By.xpath("//input[@id='chkPledge' and @type='checkbox']");


    // button

    By confirmButton = By.xpath("//button[@id='btnConfirm']");


    By otpCodeTextField = By.xpath("//input[@name='txtOtpCode']");
    By verifyOTPButton = By.xpath("//button[@id='btnVerifyOtp']");







// auto fill functions validations
    public String validateAutoFillID(String expectedID, FailureHandler.FailureHandling failureHandling){
        String actualData = SmartUIValidator.getText(idTextField, failureHandling, "ID Text Field");
        SmartUIValidator.verifyEqual(expectedID,actualData, failureHandling);
        return actualData;
    }

    public String validateAutoFillFullName(String expectedFullName, FailureHandler.FailureHandling failureHandling){
        String actualData = SmartUIValidator.getText(fullNameTextField, failureHandling, "Full Name Text Field");
        SmartUIValidator.verifyEqual(expectedFullName,actualData, failureHandling);
        return actualData;
    }


    public String validateAutoBirthOfDate(String expectedFillName, FailureHandler.FailureHandling failureHandling){
        String actualData = SmartUIValidator.getText(birthOfDateTextField, failureHandling, "Birth of date Text Field");
        SmartUIValidator.verifyEqual(expectedFillName,actualData, failureHandling);
        return actualData;
    }



    // insertion validation functions

    public void insertMobileNumber(String mobileNumber, FailureHandler.FailureHandling failureHandling){

        SmartUIValidator.sendKeys(mobileNumberTextField, failureHandling, "Mobile Number Text Field", mobileNumber);
    }

    public void insertEmailTextField(String email, FailureHandler.FailureHandling failureHandling){

        SmartUIValidator.sendKeys(emailTextField, failureHandling, "Email Text Field", email);
    }


    public String selectRandomIDIssurancePlaceDDL(FailureHandler.FailureHandling failureHandling){

       return SmartUIValidator.selectDropdownOption(idIssurancePlaceDDL,null,null,-1,true,failureHandling,"ID Issurance DDL");
    }


    public String selectRandomPlaceOfBirthDDL(FailureHandler.FailureHandling failureHandling){

        return SmartUIValidator.selectDropdownOption(placeOfBirthTextDDL,null,null,-1,true,failureHandling,"Place of birth DDL");
    }


    public void clickOnAcceptDataEntering(FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.swipePageUntilElementIsVisible(acceptDataEnteringCheckBox, SmartUIValidator.DOWN_DIRECTION, failureHandling, "acceptDataEnteringCheckBox", 3, 300, null, 30);

        SmartUIValidator.click(acceptDataEnteringCheckBox, failureHandling, "acceptDataEnteringCheckBox");
    }



    public void clickOnConfirmButtonButton(FailureHandler.FailureHandling failureHandling){

        SmartUIValidator.swipePageUntilElementIsVisible(confirmButton, SmartUIValidator.DOWN_DIRECTION, failureHandling, "Confirm Button", 3, 300, null, 30);
        SmartUIValidator.click(confirmButton, failureHandling, "Confirm Button");
    }



    public void insertOTP(String otpCode,FailureHandler.FailureHandling failureHandling ){

        SmartUIValidator.click(otpCodeTextField, failureHandling, "OTP Text Field");
        SmartUIValidator.clearText(otpCodeTextField, failureHandling, "OTP Text Field");
        SmartUIValidator.sendKeys(otpCodeTextField, failureHandling, "OTP Text Field", otpCode);

    }

    public void clickOnVerifyOTPButton(FailureHandler.FailureHandling failureHandling ){

        SmartUIValidator.click(verifyOTPButton, failureHandling, "Verify OTP Button");
    }



}
