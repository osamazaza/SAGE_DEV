package com.sage.pages.systems.ProductiveFamiliesPlatform.LoginPage;


import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;

public class LoginPage extends TestBase {


    String loginPageURL = "https://tstproductivefamilyportal.sdb.gov.sa/customlogin";
    By userNameTextField = By.xpath("//input[@formcontrolname='username']");
    By passwordTextField = By.xpath("//input[@formcontrolname='password']");
    By loginButton = By.xpath("//a[normalize-space(text())='تسجيل الدخول']");



    public void navigateToLoginPage(FailureHandler.FailureHandling failureHandling){
     SmartUIValidator.navigateToUrl(loginPageURL, failureHandling);
    }

    public void insertUserName(String userNameData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(userNameTextField, failureHandling, "UserName TextField");
        SmartUIValidator.sendKeys(userNameTextField, failureHandling, "UserName TextField", userNameData);

    }


    public void insertPassword(String passwordData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(passwordTextField, failureHandling, "password text field");
        SmartUIValidator.sendKeys(passwordTextField, failureHandling, "password text field", passwordData);

    }


    public void clickOnLoginButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(loginButton, failureHandling, "Login Button");

    }




}
