
package com.sage.pages.systems.ProductiveFamiliesPlatform.HomePage;

import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;

public class MainHomePage extends TestBase {

    String homePageUrl = "https://tstproductivefamilyportal.sdb.gov.sa/home";



    // 3 buttons
    By updateDataServiceCardButton = By.xpath("//div[contains(@class, 'subtitle') and contains(text(), 'تحديث البيانات')]");
    By productiveFamilyCertificateServiceCardButton = By.xpath("//div[contains(@class, 'subtitle') and contains(text(), 'شهادة أسر منتجة')]");
    By LoanRequestServiceCardButton = By.xpath("//div[contains(@class, 'subtitle') and contains(text(), 'طلب قرض')]");
    By progressPar = By.xpath("//app-loading-spinner//div[contains(@class,'page-preloader')]");



    public boolean waitUntilProgressParFinishLoading(FailureHandler.FailureHandling failureHandling){

       return SmartUIValidator.isNotVisibleOrAbsent(progressPar, failureHandling, "Progress Bar", 60);
    }



// 3 texts
    By updateDataCompletedServiceText =


        By.xpath("//div[contains(@class, 'subtitle') and contains(text(), 'تحديث البيانات')]/following::span[.//b[contains(text(), '100')] or contains(text(), 'مكتمل')][1]");
    By productiveFamilyCertificateCompletedServiceText =  By.xpath("//div[contains(@class, 'subtitle') and contains(text(), 'شهادة أسر منتجة')]/following::span[normalize-space(text())='مكتمل'][1]");
    By LoanRequestServiceUnderStudyText =  By.xpath("//div[contains(@class, 'subtitle') and contains(text(), 'طلب قرض')]/following::span[normalize-space(text())='قيد الدراسة'][1]");






    public void navigateToHomePage(FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.navigateToUrl(homePageUrl, failureHandling);
    }


// buttons



    public void clickOnUpdateDataServiceCardButton(FailureHandler.FailureHandling failureHandling) {

      boolean isDisplayed = SmartUIValidator.refreshAndWaitForView(updateDataServiceCardButton, failureHandling, "Update Data Service Card Button", 2, 20);

      if(isDisplayed){
          SmartUIValidator.click(updateDataServiceCardButton, failureHandling, "Update Data Service Card Button");

      }

    }


    public void clickOnProductiveFamilyCertificateServiceCardButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(productiveFamilyCertificateServiceCardButton, failureHandling, "productive Family Certificate Service Card Button");

    }

    public void clickOnLoanRequestServiceCardButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(LoanRequestServiceCardButton, failureHandling, "Loan Request Service Card Button");

    }



// texts

    public void verifyUpdateDataServiceTaskIsCompleted(FailureHandler.FailureHandling failureHandling){
        boolean isDisplayed = SmartUIValidator.refreshAndWaitForView(updateDataCompletedServiceText, failureHandling, "Completed Text for Update Data Task", 3, 25);





    }



    public void verifyProductiveFamilyCertificationTaskIsCompleted(FailureHandler.FailureHandling failureHandling){

        boolean isDisplayed = SmartUIValidator.refreshAndWaitForView(productiveFamilyCertificateCompletedServiceText, failureHandling, "Completed Text for Productive Family Certification Task", 3, 20);


        SmartUIValidator.isDisplayed(productiveFamilyCertificateCompletedServiceText, failureHandling, "Completed Text for Productive Family Certification Task");
    }




    public void verifyLoanRequestTaskIsUnderStudy(FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.isDisplayed(LoanRequestServiceUnderStudyText, failureHandling, "Completed Text for Load Request Task");
    }







    public void retryUntilLoanRequestDataCachedIsCleared(FailureHandler.FailureHandling failureHandling) {

        boolean isDisplayed =  SmartUIValidator.refreshAndWaitForView(LoanRequestServiceUnderStudyText, failureHandling, "Loan Request Service Pending textView", 25, 3);


/*

        clickOnLoanRequestServiceCardButton(failureHandling);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SmartUIValidator.refreshPage(failureHandling);
        navigateToHomePage(FailureHandler.FailureHandling.STOP_ON_FAILURE);

        // First check before entering retry loop
        boolean isDisplayed = SmartUIValidator.isDisplayed(
                LoanRequestServiceUnderStudyText,
                FailureHandler.FailureHandling.OPTIONAL,
                "Loan Request Service Pending textView"
        );

        if (isDisplayed) {
            return; // Already visible, no need to retry
        }

        // Retry up to 5 times with OPTIONAL handling
        int retries = 5;
        boolean updated = false;

        for (int i = 0; i < retries; i++) {
            try {
                Thread.sleep(6000); // small delay before retry
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            isDisplayed = SmartUIValidator.isDisplayed(
                    LoanRequestServiceUnderStudyText,
                    FailureHandler.FailureHandling.OPTIONAL,
                    "Loan Request Service Pending textView"
            );

            if (isDisplayed) {
                updated = true;
                break;
            }

            // Refresh the page on each failed retry
            SmartUIValidator.refreshPage(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
        }

        // Final attempt with actual failure handling if still not updated
        if (!updated) {
            SmartUIValidator.isDisplayed(
                    LoanRequestServiceUnderStudyText,
                    failureHandling, // Use passed-in failure handling on last try
                    "Final Attempt: Loan Request Service Completed TextView"
            );
        }


      */

    }



    public void retryUntilFamilyCertificationDataCachedIsCleared(FailureHandler.FailureHandling failureHandling) {

       boolean isDisplayed =  SmartUIValidator.refreshAndWaitForView(productiveFamilyCertificateCompletedServiceText, failureHandling, "Completed Text for Productive Family Certification Task", 3, 20);


/*
        isDisplayed = SmartUIValidator.isDisplayed(
                productiveFamilyCertificateCompletedServiceText,
                FailureHandler.FailureHandling.OPTIONAL,
                "Completed Text for Productive Family Certification Task"
        );


     clickOnProductiveFamilyCertificateServiceCardButton(failureHandling);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SmartUIValidator.refreshPage(failureHandling);
        navigateToHomePage(FailureHandler.FailureHandling.STOP_ON_FAILURE);

        // First check before entering retry loop
        boolean isDisplayed = SmartUIValidator.isDisplayed(
                productiveFamilyCertificateCompletedServiceText,
                FailureHandler.FailureHandling.OPTIONAL,
                "Completed Text for Productive Family Certification Task"
        );

        if (isDisplayed) {
            return; // Already visible, no need to retry
        }

        // Retry up to 5 times with OPTIONAL handling
        int retries = 5;
        boolean updated = false;

        for (int i = 0; i < retries; i++) {
            try {
                Thread.sleep(6000); // small delay before retry
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            isDisplayed = SmartUIValidator.isDisplayed(
                    productiveFamilyCertificateCompletedServiceText,
                    FailureHandler.FailureHandling.OPTIONAL,
                    "Completed Text for Productive Family Certification Task"
            );

            if (isDisplayed) {
                updated = true;
                break;
            }

            // Refresh the page on each failed retry
            SmartUIValidator.refreshPage(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
        }

        // Final attempt with actual failure handling if still not updated
        if (!updated) {
            SmartUIValidator.isDisplayed(
                    productiveFamilyCertificateCompletedServiceText,
                    failureHandling, // Use passed-in failure handling on last try
                    "Final Attempt: Completed Text for Productive Family Certification Task"
            );
        }


 */
    }








}
