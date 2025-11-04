package com.sage.pages.systems.CrmDynamics;


import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainCRMPage extends TestBase {

    public static final String SIT_CRM_LINK = "https://sitsdbcrm.sdb.gov.sa";


    By userIDField = By.xpath("//input[@id='userNameInput']");
    By passwordField = By.xpath("//input[@id='passwordInput']");
    By signInButton = By.xpath("//span[@id='submitButton']");


    By closePopupIframe = By.xpath("//iframe[@id='InlineDialog1_Iframe' and @name='InlineDialog1_Iframe']");
    By exitPopupIframe = By.xpath("//iframe[@id='InlineDialog_Iframe' and @name='InlineDialog_Iframe']");

    By closePopupButton = By.xpath("//button[@id='butBegin']");
    By exitPopupButton = By.xpath("//div[@id='navTourCloseButtonImage' and contains(@class, 'navTourButtonImage')]");

    // Service drown items

    By servicearrowDropDownButton = By.xpath("//span[@class='navTabButtonArrowDown' and @aria-hidden='true']");


    // values of services
    public static final String SERVICE_SERVICE_ITEM = "Service";


    // values of subServices
    public static final String SUBSERVICE_INDIVIDUALS_ITEM = "Individuals";




    public String FinanceBrokersManagementSystemLink = "https://sitsdbcrm.sdb.gov.sa/main.aspx?appid=850ea415-23e5-ec11-b822-00505692a571&pagetype=entitylist&etn=ntw_licensecancellationrequest&viewid=a5709212-14c9-eb11-baeb-00155d003c17&viewType=1039";




    public void loginToCRM(String userID, String password, FailureHandler.FailureHandling failureHandling){

        SmartUIValidator.navigateToUrl(SIT_CRM_LINK, failureHandling);
        SmartUIValidator.sendKeys(userIDField, failureHandling, "User ID Field", userID);
        SmartUIValidator.sendKeys(passwordField, failureHandling, "Password ID Field", password);

        SmartUIValidator.click(signInButton, failureHandling, "Sign in Button");

    }


    public void hideStartUpPopups(FailureHandler.FailureHandling failureHandling){


        SmartUIValidator.switchToIframe(closePopupIframe, failureHandling, "Close Popup iframe");

        boolean isClosedPopupButtonDisplayed = SmartUIValidator.isDisplayed(closePopupButton, FailureHandler.FailureHandling.OPTIONAL, "Close Popup Button");

        if(isClosedPopupButtonDisplayed){
            SmartUIValidator.click(closePopupButton, failureHandling, "Close Popup Button");
            SmartUIValidator.switchToDefaultContent(FailureHandler.FailureHandling.STOP_ON_FAILURE);
        }

SmartUIValidator.switchToDefaultContent(FailureHandler.FailureHandling.STOP_ON_FAILURE);
        SmartUIValidator.switchToIframe(exitPopupIframe, failureHandling, "Close Popup iframe");



        boolean isExitPopupButtonDisplayed = SmartUIValidator.isDisplayed(exitPopupButton, FailureHandler.FailureHandling.OPTIONAL, "Exit Popup Button");

        if(isExitPopupButtonDisplayed){
            SmartUIValidator.click(exitPopupButton, failureHandling, "Exit Popup Button");
        }

        SmartUIValidator.switchToDefaultContent(FailureHandler.FailureHandling.STOP_ON_FAILURE);


    }


        public void selectServiceAndSubserviceFromServiceDashboard(String serviceTitle, String subServiceTitle, FailureHandler.FailureHandling failureHandling){

        SmartUIValidator.click(servicearrowDropDownButton, failureHandling, "service arrow DropDownButton");

        String xpathOfService = "(//a[contains(@class,'navActionButton') and @title='" + serviceTitle + "']//span[@class='navActionButtonIcon' or @class='navActionButtonLabel'])[1]";

        By serviceItem = By.xpath(xpathOfService);

        SmartUIValidator.click(serviceItem, failureHandling,  " "+serviceTitle+" Service Menu Item");



            String xpathOfSebservice = "//span[@class='nav-rowLabel' and normalize-space(text())='"+subServiceTitle+"']";

            By subServiceItem = By.xpath(xpathOfSebservice);

        SmartUIValidator.click(subServiceItem, failureHandling,  " "+serviceTitle+" Sub-service Item");




    }






     //





    public void navigateToFinanceBrokersManagementSystemPage(FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.navigateToUrl(FinanceBrokersManagementSystemLink, failureHandling);
    }





    //https://sitsdbcrm.sdb.gov.sa/main.aspx?appid=850ea415-23e5-ec11-b822-00505692a571&pagetype=entitylist&etn=ntw_licensecancellationrequest&viewid=a5709212-14c9-eb11-baeb-00155d003c17&viewType=1039
}
