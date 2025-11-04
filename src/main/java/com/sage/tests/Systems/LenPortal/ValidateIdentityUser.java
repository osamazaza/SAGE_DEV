package com.sage.tests.Systems.LenPortal;

import com.sage.base.Runners.JavaDebugRunner;
import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import com.sage.pages.systems.CrmDynamics.*;
import com.sage.pages.systems.LenPortal.HomePage;
import com.sage.pages.systems.LenPortal.IdentitySSOLoginPage;
import com.sage.pages.systems.LenPortal.RegisterAccountPage;
import com.sage.pages.systems.LenPortal.UserProfilePage;
import com.sage.testdata.dataDriven.RunTimeAttributes.SquadTestData1;
import com.sage.testdata.dataDriven.RunTimeAttributes.SquadTestData2;
import com.sage.testdata.generators.GenericGenerator;
import com.sage.testdata.generators.IDGenerator;
import com.sage.utils.StringProcessor;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v139.network.Network;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import com.sdb.pages.systems.CrmDynamics
public class ValidateIdentityUser extends TestBase {
    MainCRMPage mainCRMPage;
       IdentitySSOLoginPage identitySSOLoginPage;
HomePage homePage;
IndividualDashboard individualDashboard;
UserProfilePage userProfilePage;
RegisterAccountPage registerAccountPage;



    @BeforeClass
    public void initPage() {

        mainCRMPage = PageFactory.initElements(getDriver(), MainCRMPage.class);
        identitySSOLoginPage = PageFactory.initElements(getDriver(), IdentitySSOLoginPage.class);
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        individualDashboard = PageFactory.initElements(getDriver(), IndividualDashboard.class);
        userProfilePage = PageFactory.initElements(getDriver(), UserProfilePage.class);
        registerAccountPage = PageFactory.initElements(getDriver(), RegisterAccountPage.class);

    }




    @Test(priority = 1)
    public void setupTestDataForNewUser(){
        try {




            SquadTestData2.setUserIDNumber(IDGenerator.getIdNumber(1));
            SquadTestData2.setUserGender("ذكر");
            SquadTestData2.setUserFullName("اختبار1 اختبار2 اختبار3 اختبار4");
            SquadTestData2.setUserBirthOfDate("22/09/1990");
            SquadTestData2.setUserMobileNumber(StringProcessor.trimFromLeft(IDGenerator.getSaudiPhoneNumberStartWith52(), 1));



            SmartUIValidator.addStep("Test Data ↓↓↓↓");
            SmartUIValidator.addStep("USER ID Number " + SquadTestData2.getUserIDNumber());
            SmartUIValidator.addStep("USER Gender " + SquadTestData2.getUserGender());
            SmartUIValidator.addStep("User Full Name " + SquadTestData2.getUserFullName());
            SmartUIValidator.addStep("USER Birth Date " + SquadTestData2.getUserBirthOfDate());
            SmartUIValidator.addStep("USER Mobile Number " + SquadTestData2.getUserMobileNumber());


        }
        finally {
            SmartUIValidator.assertAll();
        }

    }

    @Test(priority = 2)
    public void
    validateRegisterNewIdentity() {

        // 9 15
        try {




            identitySSOLoginPage.navigateToLen(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            identitySSOLoginPage.loginByNafath(SquadTestData2.getUserIDNumber(), FailureHandler.FailureHandling.STOP_ON_FAILURE);

             // register
            registerAccountPage.validateAutoFillID(SquadTestData2.getUserIDNumber(), FailureHandler.FailureHandling.STOP_ON_FAILURE);
            registerAccountPage.validateAutoFillFullName(SquadTestData2.getUserFullName(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            registerAccountPage.validateAutoBirthOfDate(SquadTestData2.getUserBirthOfDate(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);

            // random value selection from DDls
            registerAccountPage.selectRandomIDIssurancePlaceDDL(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            registerAccountPage.selectRandomPlaceOfBirthDDL(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);


            //data insertion
            // entering mobile phone number



            String updatedMobileNumber = "0"+ StringProcessor.trimFromLeft(SquadTestData2.getUserMobileNumber(), 3);

            registerAccountPage.insertMobileNumber(updatedMobileNumber, FailureHandler.FailureHandling.STOP_ON_FAILURE);

            //registerAccountPage.insertMobileNumber("0" + StringProcessor.trimFromLeft(SquadTestData2.getUserMobileNumber(), 3), FailureHandler.FailureHandling.STOP_ON_FAILURE);
            GenericGenerator genericGenerator = new GenericGenerator();
            String randomDigits = genericGenerator.generateRandomDigit(6);
            registerAccountPage.insertEmailTextField("test" + randomDigits +"@gmail.com", FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);



            System.out.println("The id generated isThe id generated is " + SquadTestData2.getUserIDNumber());


            registerAccountPage.clickOnAcceptDataEntering(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            registerAccountPage.clickOnConfirmButtonButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            SmartUIValidator.waitForPageLoad(FailureHandler.FailureHandling.STOP_ON_FAILURE, 30);


            registerAccountPage.insertOTP("123", FailureHandler.FailureHandling.STOP_ON_FAILURE);

            try {
                Thread.sleep(9000 * 6);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            //System.out.println("Otp is :" +       JavaDebugRunner.getLatestOtp("1068370608", FailureHandler.FailureHandling.STOP_ON_FAILURE));


            registerAccountPage.clickOnVerifyOTPButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);



          homePage.waitUntilLoadingViewDisappear(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);


            homePage.navigateToUserProfilePage(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            SmartUIValidator.waitForPageLoad(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE, 30);
            homePage.waitUntilLoadingViewDisappear(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);

            userProfilePage.validateID(SquadTestData2.getUserIDNumber(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            userProfilePage.validateMobileNumber(SquadTestData2.getUserMobileNumber(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            userProfilePage.validateGender(SquadTestData2.getUserGender() , FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            userProfilePage.validateFullName(SquadTestData2.getUserFullName(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            userProfilePage.validateBirthOfDate(SquadTestData2.getUserBirthOfDate(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);







        }

        finally {
            SmartUIValidator.assertAll();

        }






    }







    @Test(priority = 3)
    public void setupTestDataForUpdateRegisteredUser(){
        try {

            SmartUIValidator.addStep("Test Data ↓↓↓↓");
            SmartUIValidator.addStep("Previous USER Mobile Number " + SquadTestData2.getUserMobileNumber());

            SquadTestData2.setUserMobileNumber(StringProcessor.trimFromLeft(IDGenerator.getSaudiPhoneNumberStartWith52(), 1));
            SmartUIValidator.addStep("Newer USER Mobile Number " + SquadTestData2.getUserMobileNumber());
            SmartUIValidator.addStep("USER ID Number " + SquadTestData2.getUserIDNumber());
            SmartUIValidator.addStep("USER Gender " + SquadTestData2.getUserGender());
            SmartUIValidator.addStep("User Full Name " + SquadTestData2.getUserFullName());
            SmartUIValidator.addStep("USER Birth Date " + SquadTestData2.getUserBirthOfDate());


        }
        finally {
            SmartUIValidator.assertAll();
        }

    }

    @Test(priority = 4)
    public void
    validateUpdateRegisteredUser() {

        // 9 15
        try {

            userProfilePage.clickOnEditMobileNumberButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            String updatedMobileNumber = "0"+ StringProcessor.trimFromLeft(SquadTestData2.getUserMobileNumber(), 3);
            userProfilePage.changeMobileNumber(updatedMobileNumber, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            userProfilePage.clickOnConfirmChangeMobileNumberButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
// update otp


            homePage.waitUntilLoadingViewDisappear(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);

            userProfilePage.insertOTPNumber("123", FailureHandler.FailureHandling.STOP_ON_FAILURE);

            try {
                Thread.sleep(9000 * 6);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            userProfilePage.clickOnConfirmOTPNumberButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            userProfilePage.ClickOnCloseChangeMobileNumberAlertButton(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);

            homePage.waitUntilLoadingViewDisappear(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            homePage.navigateToUserProfilePage(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            SmartUIValidator.waitForPageLoad(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE, 30);
            homePage.waitUntilLoadingViewDisappear(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);

            userProfilePage.validateID(SquadTestData2.getUserIDNumber(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            userProfilePage.validateMobileNumber(SquadTestData2.getUserMobileNumber(), FailureHandler.FailureHandling.STOP_ON_FAILURE);
            userProfilePage.validateGender(SquadTestData2.getUserGender() , FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            userProfilePage.validateFullName(SquadTestData2.getUserFullName(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            userProfilePage.validateBirthOfDate(SquadTestData2.getUserBirthOfDate(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);





        }

        finally {
            SmartUIValidator.assertAll();

        }



    }




        @Test(priority = 5)
    public void setupTestDataForAuthroizedUser(){
        try {



    /*    mainCRMPage.loginToCRM("ozaza.c@sdb.gov.sa", "Neoleap@1971o", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            SmartUIValidator.waitForPageLoad(FailureHandler.FailureHandling.STOP_ON_FAILURE, 30);
            mainCRMPage.hideStartUpPopups(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            mainCRMPage.selectServiceAndSubserviceFromServiceDashboard(MainCRMPage.SERVICE_SERVICE_ITEM, MainCRMPage.SUBSERVICE_INDIVIDUALS_ITEM, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            individualDashboard.selectOptionFromDashboardList(IndividualDashboard.ALL_INDIVIDUALS_DASHBOARD_OPTION, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            individualDashboard.searchIntoDashboard(SquadTestData2.getUserIDNumber(), FailureHandler.FailureHandling.STOP_ON_FAILURE);

       individualDashboard.clickOnIndividualFromDashboard(SquadTestData2.getUserIDNumber(), FailureHandler.FailureHandling.STOP_ON_FAILURE);
*/









            SquadTestData2.setUserIDNumber("1088401540");
            SquadTestData2.setUserGender("ذكر");
            SquadTestData2.setUserFullName("احمد صالح عبدالله الباز");
            SquadTestData2.setUserBirthOfDate("22/09/1990");
            SquadTestData2.setUserMobileNumber("966507855344");



            SmartUIValidator.addStep("Test Data ↓↓↓↓");
            SmartUIValidator.addStep("USER ID Number " + "966507850000");
            SmartUIValidator.addStep("USER Gender " + SquadTestData2.getUserGender());
            SmartUIValidator.addStep("User Full Name " + SquadTestData2.getUserFullName());
            SmartUIValidator.addStep("USER Birth Date " + SquadTestData2.getUserBirthOfDate());
            SmartUIValidator.addStep("USER Mobile Number " + SquadTestData2.getUserMobileNumber());







        }
        finally {
            SmartUIValidator.assertAll();
        }

    }

    @Test(priority = 6)
    public void
    validateAuthroizedCompletedIdentity() {

        // 9 15
        try {



            getDriver().manage().deleteAllCookies();
            DevTools devTools = ((ChromeDriver) getDriver()).getDevTools();
            devTools.createSession();
            devTools.send(Network.clearBrowserCache());
            devTools.send(Network.clearBrowserCookies());


            identitySSOLoginPage.navigateToLen(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            identitySSOLoginPage.loginByNafath(SquadTestData2.getUserIDNumber(), FailureHandler.FailureHandling.STOP_ON_FAILURE);
            identitySSOLoginPage.acceptToUseCurrentDevice(FailureHandler.FailureHandling.STOP_ON_FAILURE);
          // SmartUIValidator.waitForPageLoad(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE, 30);

            homePage.validateUserisInsideHomePage(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            homePage.waitUntilLoadingViewDisappear(FailureHandler.FailureHandling.STOP_ON_FAILURE);



            homePage.navigateToUserProfilePage(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            SmartUIValidator.waitForPageLoad(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE, 30);
            homePage.waitUntilLoadingViewDisappear(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);

            userProfilePage.validateID(SquadTestData2.getUserIDNumber(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            userProfilePage.validateMobileNumber(SquadTestData2.getUserMobileNumber(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            userProfilePage.validateGender(SquadTestData2.getUserGender() , FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            userProfilePage.validateFullName(SquadTestData2.getUserFullName(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            userProfilePage.validateBirthOfDate(SquadTestData2.getUserBirthOfDate(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);





        }

        finally {
            SmartUIValidator.assertAll();

        }






    }




}


