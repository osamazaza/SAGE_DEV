package com.sage.pages.systems.LenPortal;

import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;

public class IdentitySSOLoginPage extends TestBase {

//1088401540
    private static final String LEN_PORTAL_LINK = "https://tstlen2.sdb.gov.sa/web-17871/login/unified/individuals";


    // locators

    By termsConfirmCheckBox = By.xpath("//span[@class='border-primary']");

    By nafathLoginButton = By.xpath("//button[contains(@class, 'btn-primary') and contains(normalize-space(text()), 'بوابة النفاذ')]");

    By idTextField = By.xpath("//input[@id='txtIdentityNumber']");
    By userThisDeviceButton = By.xpath("(//input[@type='submit' or @value='استخدام هذا الجهاز'])[1]");
    By loginButton = By.xpath("//input[@id='btnLogin']");





public void navigateToLen(FailureHandler.FailureHandling failureHandling){

    SmartUIValidator.navigateToUrl(LEN_PORTAL_LINK, failureHandling);

}

    public void loginByNafath(String idNumber, FailureHandler.FailureHandling failureHandling){



        SmartUIValidator.click(termsConfirmCheckBox, failureHandling, "Terms Condition Check box");
        SmartUIValidator.click(nafathLoginButton, failureHandling, "Nafath Login Button");


        SmartUIValidator.sendKeys(idTextField, failureHandling, "ID TextField", idNumber);
    SmartUIValidator.click(loginButton, failureHandling, "Login Button");






}

public void acceptToUseCurrentDevice(FailureHandler.FailureHandling failureHandling){
    boolean isUseThisDeviceButtonDisplayed  = SmartUIValidator.isDisplayed(userThisDeviceButton, FailureHandler.FailureHandling.OPTIONAL, "Use this device Button");
    if(isUseThisDeviceButtonDisplayed){
        SmartUIValidator.click(userThisDeviceButton, failureHandling, "Use this device Button");
    }
}







}
