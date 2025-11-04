package com.sage.pages.systems.CrmDynamics;

import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class IndividualDashboard extends TestBase {

By dashboardListArrowButton = By.xpath("//a[@id='crmGrid_SavedNewQuerySelector']");
By dashboardSearchTextField = By.xpath("//input[@title='Search for records' or contains(@class,'ms-crm-Dialog-Lookup-QuickFind')]");
By individualDashboardIframe0 = By.xpath("//iframe[@id='contentIFrame0' and @title='Content Area']");
    By individualDashboardIframe2 = By.xpath("//iframe[@id='contentIFrame2' and @title='Content Area']");


// dasboard options


    public static final String ALL_INDIVIDUALS_DASHBOARD_OPTION = "All Individuals";


    public void selectOptionFromDashboardList(String option, FailureHandler.FailureHandling failureHandling){



       SmartUIValidator.switchToIframe(individualDashboardIframe0,failureHandling, "individual Dashboard Iframe");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SmartUIValidator.click(dashboardListArrowButton, failureHandling, "dashboardListArrow Button");
        String dashboardOptionXpath = "//li[.//span[normalize-space(@title)='"+option+"']]";
        By dashboardOptionItem = By.xpath(dashboardOptionXpath);
        SmartUIValidator.click(dashboardOptionItem, failureHandling, "Dashboard \"+option+\"  Option");
        SmartUIValidator.switchToDefaultContent(failureHandling);
    }



    public void searchIntoDashboard(String text, FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.switchToIframe(individualDashboardIframe0,failureHandling, "individual Dashboard Iframe");

        SmartUIValidator.clearText(dashboardSearchTextField, failureHandling, "DashboardSearch TextField");

        SmartUIValidator.sendKeys(dashboardSearchTextField, failureHandling, "DashboardSearch TextField", text);
        SmartUIValidator.sendKeys(dashboardSearchTextField, failureHandling, "DashboardSearch TextField", Keys.ENTER);
        SmartUIValidator.switchToDefaultContent(failureHandling);


    }


    public void clickOnIndividualFromDashboard(String individualID, FailureHandler.FailureHandling failureHandling) {
        // XPath targeting a div with id containing the pattern "cell-{rowIndex}-{columnIndex}"
        SmartUIValidator.switchToIframe(individualDashboardIframe0,failureHandling, "individual Dashboard Iframe0");



        By individualIDLocator = By.xpath("(//table[@id='gridBodyTable']//*[@title='"+individualID+"']/ancestor::tr)[1]");


/*
        Actions actions = new Actions(getDriver());
        actions.moveToElement(view).clickAndHold(view).build().perform();

*/



        SmartUIValidator.doubleClick(individualIDLocator, failureHandling, "Individual "+individualID+" Row View", 60);
        SmartUIValidator.switchToDefaultContent(failureHandling);


    }









}
