package com.sage.pages.systems.LenPortal;

import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;

public class UserProfilePage extends TestBase {

By fullName = By.xpath("//span[contains(@class, 'font-weight-bold') and contains(@class, 'font-size-sm') and contains(@class, 'text-white')]");
By id = By.xpath("(//div[@class='text-white'])[1]");
By mobileNumber = By.xpath("(//p[contains(@class, 'main-color') and contains(@class, 'mb-0')])[1]");
By birthOfDate = By.xpath("(//p[contains(@class, 'main-color') and contains(@class, 'mb-0')])[2]");
By gender = By.xpath("(//p[contains(@class, 'main-color') and contains(@class, 'mb-0')])[3]");

By editMobileNumberButton = By.xpath("(//div[a[contains(@href, 'change-phone-number')]])[1]");
    By changeMobileNumberTextField = By.xpath("//input[@type='text' and @placeholder=\"don't remove it\" and contains(@class,'form-control')]");
By confirmChangeMobileNumberButton = By.xpath("//button[@type='button'  and normalize-space(text())='تأكيد']");


    By changeOTPNumberTextField =  By.xpath("//input[@type='text' and @placeholder=\"don't remove it\" and contains(@class,'form-control')]");

    By confirmInsertOTPNumberButton = By.xpath("//button[@type='button'  and normalize-space(text())='تأكيد']");

    By closeChangeMobileNumberAlertButton = By.xpath("//button[@type='button' and normalize-space(text())='إغلاق']");
    By closeChangeMobileNumberAlertButton2 = By.xpath("(//button[@type='button' and @aria-label='Close' and contains(@class,'btn-close')])[2]");

//






//button[@type='button' and @aria-label='Close' and contains(@class,'btn-close')]


    public void changeMobileNumber(String updatedMobileNumber, FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.sendKeys(changeMobileNumberTextField, failureHandling, "Change Mobile Number Text Field", updatedMobileNumber);
    }

    public void insertOTPNumber(String otpNumber, FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.sendKeys(changeOTPNumberTextField, failureHandling, "Change OTP Number Text Field", otpNumber);
    }



public void ClickOnCloseChangeMobileNumberAlertButton(FailureHandler.FailureHandling failureHandling){
    SmartUIValidator.click(closeChangeMobileNumberAlertButton, failureHandling, "close Change Mobile Number Alert Button ");
    SmartUIValidator.click(closeChangeMobileNumberAlertButton2, failureHandling, "close Change Mobile Number Alert Button 2 ");

}




    public void clickOnConfirmChangeMobileNumberButton(FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.click(confirmChangeMobileNumberButton, failureHandling, "Change Mobile Number Button");
    }

    public void clickOnConfirmOTPNumberButton(FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.click(confirmInsertOTPNumberButton, failureHandling, "confirm Change OTP Number Button");
    }

public void clickOnEditMobileNumberButton(FailureHandler.FailureHandling failureHandling){
    SmartUIValidator.click(editMobileNumberButton, failureHandling, "Edit Mobile Number Button");
}

public String validateFullName(String expectedData, FailureHandler.FailureHandling failureHandling){

    String actualData = SmartUIValidator.getText(fullName, failureHandling, "Full Name Text Field");
    SmartUIValidator.verifyEqual(expectedData,actualData, failureHandling);

    return actualData;

}



    public String validateID(String expectedData, FailureHandler.FailureHandling failureHandling){

        String actualData = SmartUIValidator.getText(id, failureHandling, "ID Text Field");
        SmartUIValidator.verifyEqual(expectedData,actualData, failureHandling);

        return actualData;

    }


    public String validateMobileNumber(String expectedData, FailureHandler.FailureHandling failureHandling){

        String actualData = SmartUIValidator.getText(mobileNumber, failureHandling, "Mobile Number Text Field");
        SmartUIValidator.verifyEqual(expectedData,actualData, failureHandling);

        return actualData;

    }



    public String validateBirthOfDate(String expectedData, FailureHandler.FailureHandling failureHandling){

        String actualData = SmartUIValidator.getText(birthOfDate, failureHandling, "Birth of date Text Field");
        SmartUIValidator.verifyEqual(expectedData,actualData, failureHandling);

        return actualData;

    }


    public String validateGender(String expectedData, FailureHandler.FailureHandling failureHandling){

        String actualData = SmartUIValidator.getText(gender, failureHandling, "Gender Text Field");
        SmartUIValidator.verifyEqual(expectedData,actualData, failureHandling);

        return actualData;

    }







}
