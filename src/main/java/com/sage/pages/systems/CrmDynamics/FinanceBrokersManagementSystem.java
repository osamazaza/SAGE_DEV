package com.sage.pages.systems.CrmDynamics;

import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;


// outside dasbhaord (navigator)
public class FinanceBrokersManagementSystem extends TestBase {
// this class is used to have the page object model for all staff related for navigation to the other pages for dashboard




    public static final String FinanceBrokersManagementSystemLINK = "https://sitsdbcrm.sdb.gov.sa/main.aspx?appid=850ea415-23e5-ec11-b822-00505692a571&pagetype=entitylist&etn=ntw_licensecancellationrequest&viewid=a5709212-14c9-eb11-baeb-00155d003c17&viewType=1039";






    By refreshButton = By.xpath("//button[@aria-label='Refresh' and @title='Refresh']");

    By assignButton = By.xpath("//button[.//span[normalize-space(text())='Assign']]");
    By assignPopupButton = By.xpath("//span[contains(@id, 'dialogButtonText_id-') and normalize-space(.)='Assign']");
    By approveButton = By.xpath("//input[@type='button' and @value='Approve']");
    By saveAndCloseButton = By.xpath("//button[normalize-space(@aria-label)='Save & Close']");
    By saveAndContinueButton = By.xpath("//button[@id='confirmButton' and @aria-label='Save and continue' and @type='button']");




    By moreOptionsButton = By.xpath("//button[@data-lp-id='Form:ntw_productivefamilyloanrequest-OverflowButton']");



    By authFrame = By.xpath("//iframe[@id='authFrame']"); // this frame covers view switcher options


    // clickOnNavigationSidebar

    // does not work for home, recenent, pinned(they have different xpath) could I late on add then in same function to locaate different xpath, in which the user only only write option to the function, and the function will now which xpath to use



    // Options:
    public static final String LOAN_REQUEST_SIDEBAR_OPTION = "Loan Request";




    public void selectOptionFromNavigationSideBar(String option, FailureHandler.FailureHandling failureHandling){
        String optionXpath = "";
        optionXpath = "//li[@role='treeitem' and @title='"+option+"' and contains(@data-id, 'sitemap-entity-NewSubArea_')]";

        By optionItemButton = By.xpath(optionXpath);


        SmartUIValidator.click(optionItemButton, failureHandling, optionXpath + " Navigation Bar Option");


    }



    public void saveAndClose(FailureHandler.FailureHandling failureHandling){

       //SmartUIValidator.refreshAndWaitForView(saveAndContinueButton, failureHandling, "Save And Close Button", 3, 20);
        SmartUIValidator.click(saveAndCloseButton, failureHandling, "Save And Close Button");


        //button[@id='confirmButton' and @aria-label='Save and continue' and @type='button']



        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //SmartUIValidator.refreshPage(FailureHandler.FailureHandling.STOP_ON_FAILURE);


    }


    public void clickOnRefreshButton(FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.click(refreshButton, failureHandling, "Refresh Button");



        boolean isSaveAndContinueDisplayed = SmartUIValidator.isDisplayed(saveAndContinueButton, FailureHandler.FailureHandling.OPTIONAL, "Save And continue Button");

        if(isSaveAndContinueDisplayed){
            SmartUIValidator.click(saveAndContinueButton, failureHandling, "Save And Continue Button");

        }





    }





    public void assignTicketToMe(FailureHandler.FailureHandling failureHandling){

       SmartUIValidator.click(moreOptionsButton, FailureHandler.FailureHandling.OPTIONAL, "more Options button");
        SmartUIValidator.click(assignButton, failureHandling, "Assign Button");
        SmartUIValidator.click(assignPopupButton, failureHandling, "assignPopup Button");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }





    By viewSwitcherButton = By.xpath("//button[@id='areaSwitcherId']");

    public static final String PRODUTIVE_FAMILIES_OPTION = "Productive Families";
    public static final String LOAN_MANAGEMENT_OPTION = "Loan Management";
    public static final String FUNDING_WALLET_MANAGEMENT_OPTION = "Funding Wallet Management";



    // shared dashboardViews



    By dashboardSearchTextField = By.xpath("//*[@placeholder='Search this view']");
    By dashboardListArrowButton = By.xpath("//span[@class='symbolFont ChevronDownMed-symbol ']");

    







    public void searchIntoDashboard(String text, FailureHandler.FailureHandling failureHandling){






       SmartUIValidator.clearText(dashboardSearchTextField, failureHandling, "DashboardSearch TextField");

        SmartUIValidator.sendKeys(dashboardSearchTextField, failureHandling, "DashboardSearch TextField", text);
       SmartUIValidator.sendKeys(dashboardSearchTextField, failureHandling, "DashboardSearch TextField", Keys.ENTER);




    }


    public void selectOptionFromDashboardList(String option, FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.click(dashboardListArrowButton, failureHandling, "dashboardListArrow Button");
        String dashboardOptionXpath = "//div[contains(@id, 'ViewSelector') or contains(@class, 'view')]//span[normalize-space(text())='"+option+"']";
        By dashboardOptionItem = By.xpath(dashboardOptionXpath);
        SmartUIValidator.click(dashboardOptionItem, failureHandling, "Dashboard \"+option+\"  Option");
    }




    public boolean checkRequestItemDisplayedInDashboard(int requestItemIndex, String requestNumber, FailureHandler.FailureHandling failureHandling){
        String requestItemXpath = generateRequestItemXpath(requestItemIndex);
        By requestItem = By.xpath(requestItemXpath);


        boolean isRequestNumberExisted =  SmartUIValidator.isDisplayed(requestItem, FailureHandler.FailureHandling.OPTIONAL, "Request Item number "+requestItemIndex+"  ");

        System.out.println("Is request number displayed at dashboard " + isRequestNumberExisted);
        if(!isRequestNumberExisted){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SmartUIValidator.refreshPage(failureHandling);
           selectOptionFromDashboardList("Loan Applications", failureHandling);
            searchIntoDashboard(requestNumber, failureHandling);

             return isRequestNumberExisted =  SmartUIValidator.isDisplayed(requestItem, failureHandling, "Request Item number "+requestItemIndex+"  ");

        }
        else{
            return isRequestNumberExisted;
        }



    }


    public void doubleClickOnRequestItemFromDashboard(int requestItemIndex, FailureHandler.FailureHandling failureHandling) {
        // XPath targeting a div with id containing the pattern "cell-{rowIndex}-{columnIndex}"

       String requestItemXpath = generateRequestItemXpath(requestItemIndex);
       By requestItem = By.xpath(requestItemXpath);


        SmartUIValidator.click(requestItem, failureHandling, "Request Item number "+requestItemIndex+"  ");


    }

    private  String generateRequestItemXpath(int requestItemIndex) {
        // XPath targeting a div with id containing the pattern "cell-{rowIndex}-{columnIndex}"
        String requestItemXpath =  String.format("//div[contains(@id, 'cell-%d-%d') and contains(@class, 'wj-cell')]", requestItemIndex, 2);
        System.out.println("Request ITEM XPATH: "  + requestItemXpath);


        return requestItemXpath;
    }







    /*

    dashboardSearchTextField
"//*[@placeholder='Search this view']"

dashboardListArrowButton
//span[@class='symbolFont ChevronDownMed-symbol ']

value of dashboardList
//div[contains(@id, 'ViewSelector') or contains(@class, 'view')]//span[normalize-space(text())='Additional Loan Requests']
     */














    public void selectViewFromViewSwitcher(String option, FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.click(viewSwitcherButton, failureHandling, "View Switcher Button");
        String optionXpath = "";

        //SmartUIValidator.switchToIframe(authFrame, failureHandling, "auth iframe");

            optionXpath = "//li[@role='menuitemradio']//span[contains(@title, '"+option+"')]/ancestor::li";
            By optionItemButton = By.xpath(optionXpath);


        SmartUIValidator.click(optionItemButton, failureHandling, optionXpath + " Button");


        //SmartUIValidator.switchToDefaultContent(FailureHandler.FailureHandling.STOP_ON_FAILURE);





    }







}
