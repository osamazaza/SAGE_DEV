package com.sage.tests.Systems.ProductiveFamiliesPortal;

import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import com.sage.model.LocalBeneficiary;
import com.sage.pages.systems.CrmDynamics.CRMAppsPage;
import com.sage.pages.systems.CrmDynamics.FinanceBrokersManagementSystem;
import com.sage.pages.systems.CrmDynamics.LoanRequestTicketPage;
import com.sage.pages.systems.CrmDynamics.MainCRMPage;
import com.sage.pages.systems.ProductiveFamiliesPlatform.HomePage.MainHomePage;
import com.sage.pages.systems.ProductiveFamiliesPlatform.LoginPage.LoginPage;
import com.sage.pages.systems.ProductiveFamiliesPlatform.RegistrationPage.RegistrationPage;
import com.sage.pages.systems.ProductiveFamiliesPlatform.services.LoanRequestPage;
import com.sage.pages.systems.ProductiveFamiliesPlatform.services.ProductiveFamiliesCertificationPage;
import com.sage.pages.systems.ProductiveFamiliesPlatform.services.UpdateDataFormPage;
import com.sage.testdata.dataDriven.RunTimeAttributes.SquadTestData1;
import com.sage.testdata.generators.GenericGenerator;
import com.sage.testdata.generators.IDGenerator;
import com.sage.testdata.generators.LocalBeneficiaryGenerator;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateAndAcceptLoanRequestTest extends TestBase {
    // for productive family portal
    LoginPage loginPage;
    RegistrationPage registrationPage;
    MainHomePage mainHomePage;
    LoanRequestPage loanRequestPage;
    ProductiveFamiliesCertificationPage productiveFamiliesCertificationPage;
    UpdateDataFormPage updateDataFormPage;


    // for crm
    MainCRMPage mainCRMPage;
    CRMAppsPage crmAppsPage;
    FinanceBrokersManagementSystem financeBrokersManagementSystem;
    LoanRequestTicketPage loanRequestTicketPage;






    @BeforeClass
    public void initPage() {
// for productive family portal
        loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        registrationPage = PageFactory.initElements(getDriver(), RegistrationPage.class);
        mainHomePage = PageFactory.initElements(getDriver(), MainHomePage.class);
        loanRequestPage = PageFactory.initElements(getDriver(), LoanRequestPage.class);
        productiveFamiliesCertificationPage = PageFactory.initElements(getDriver(), ProductiveFamiliesCertificationPage.class);
        updateDataFormPage = PageFactory.initElements(getDriver(), UpdateDataFormPage.class);

       // for crm
        crmAppsPage = PageFactory.initElements(getDriver(), CRMAppsPage.class);
        mainCRMPage = PageFactory.initElements(getDriver(), MainCRMPage.class);
        financeBrokersManagementSystem = PageFactory.initElements(getDriver(), FinanceBrokersManagementSystem.class);
        loanRequestTicketPage = PageFactory.initElements(getDriver(), LoanRequestTicketPage.class);


    }


    @AfterClass
    public void cleanData(){
        System.out.println("Clear out the data For Create Loan Request Test");
        SquadTestData1.clear();
    }




    @Test(priority = 1)
    public void setupRegistrationTestData(){

        SquadTestData1.setUserIDNumber( IDGenerator.getIdNumber(1));


        //SquadTestData1.userIDNumber = "1096407814";


        GenericGenerator genericGenerator = new GenericGenerator();
        String randomDigits = genericGenerator.generateRandomDigit(9);

        SquadTestData1.setUserName("FTest_" + randomDigits);


        LocalBeneficiaryGenerator localBeneficiaryGenerator = new LocalBeneficiaryGenerator();


        LocalBeneficiary randomLocalBeneficiary  = localBeneficiaryGenerator.generateRandomLocalBeneficiary();


        SquadTestData1.setLocalBeneficiary(randomLocalBeneficiary);






        SmartUIValidator.addStep("Test Data ↓↓↓↓");
        SmartUIValidator.addStep("userIDNumber: " + SquadTestData1.getUserIDNumber());
        SmartUIValidator.addStep("UserName: " + SquadTestData1.getUserName());
        SmartUIValidator.addStep("userPassword: " + SquadTestData1.getUserPassword());
        SmartUIValidator.addStep("Local Beneficiary: " + SquadTestData1.getLocalBeneficiary().toString());


    }

    @Test(priority = 2)
    public void registerNewUser() {

        try {

  /*          MyTestClass myTestClass = new MyTestClass();
            String methodID = myTestClass.getUniqueIdentifier(RegistrationPage.class, "registerNewUser");
            TestCaseDependencyManager testCaseDependencyManager =TestCaseDependencyManager.logTestCaseDep(methodID);
            testCaseDependencyManager.validateFailedDep();*/


            registrationPage.navigateToCreateNewAccountRegistrationPage(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            registrationPage.insertUserName(SquadTestData1.getUserName(), FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            registrationPage.insertPassword(SquadTestData1.getUserPassword(), FailureHandler.FailureHandling.STOP_ON_FAILURE);
            registrationPage.insertConfirmationPassword(SquadTestData1.getUserPassword(), FailureHandler.FailureHandling.STOP_ON_FAILURE);


            registrationPage.insertFirstNameAr(SquadTestData1.getLocalBeneficiary().getFirstNameAR(), FailureHandler.FailureHandling.STOP_ON_FAILURE);
            registrationPage.insertFatherNameAr(SquadTestData1.getLocalBeneficiary().getFirstNameAR() + "أ", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            registrationPage.insertGrandFatherNameAr(SquadTestData1.getLocalBeneficiary().getLastNameAR() + "ج" , FailureHandler.FailureHandling.STOP_ON_FAILURE);
            registrationPage.insertLastNameAr(SquadTestData1.getLocalBeneficiary().getLastNameAR() , FailureHandler.FailureHandling.STOP_ON_FAILURE);




            registrationPage.insertFirstNameEn( SquadTestData1.getLocalBeneficiary().getFirstNameEN(), FailureHandler.FailureHandling.STOP_ON_FAILURE);
            registrationPage.insertFatherNameEn( SquadTestData1.getLocalBeneficiary().getFirstNameEN() + "F" , FailureHandler.FailureHandling.STOP_ON_FAILURE);
            registrationPage.insertGrandFatherNameEn( SquadTestData1.getLocalBeneficiary().getFirstNameEN() + "G" , FailureHandler.FailureHandling.STOP_ON_FAILURE);
            registrationPage.insertLastNameEn(SquadTestData1.getLocalBeneficiary().getLastNameEN() , FailureHandler.FailureHandling.STOP_ON_FAILURE);


            registrationPage.selectIdType("هوية", FailureHandler.FailureHandling.STOP_ON_FAILURE);



            registrationPage.insertIdNumber(SquadTestData1.getUserIDNumber(), FailureHandler.FailureHandling.STOP_ON_FAILURE);
            registrationPage.selectGenderType("Male", FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            registrationPage.selectNationalityType("سعودي", FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            registrationPage.insertBirthDate("07152000", FailureHandler.FailureHandling.CONTINUE_ON_FAILURE); // mm/dd/yyy
            registrationPage.clickOnCreateNewAccount(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            registrationPage.validateAccountRegistered(FailureHandler.FailureHandling.STOP_ON_FAILURE);
        }

        finally {
            SmartUIValidator.assertAll();

        }



        //assertEquals(3, 5);


    }

    @Test(priority = 3)
    public void login() {




        try {
         /*   MyTestClass myTestClass = new MyTestClass();
            String methodID = myTestClass.getUniqueIdentifier(RegistrationTest.class, "registerNewUser");
            TestCaseDependencyManager testCaseDependencyManager =TestCaseDependencyManager.logTestCaseDep(methodID);
            testCaseDependencyManager.validateFailedDep();
*/



            System.out.println("Is skip current test cases inside login ? " + getReportAttributes().isSkipCurrentTestCase());


            loginPage.navigateToLoginPage(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            loginPage.insertUserName(SquadTestData1.getUserName(), FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loginPage.insertPassword(SquadTestData1.getUserPassword(), FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loginPage.clickOnLoginButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);




            System.out.println("I am here Update");

            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        finally {
            SmartUIValidator.assertAll();

        }



        //assertEquals(3, 5);


    }


    @Test(priority = 4)
    public void updateUserData() {

        try {



     /*       MyTestClass myTestClass = new MyTestClass();
            String methodID = myTestClass.getUniqueIdentifier(LoginTest.class, "login");
            TestCaseDependencyManager testCaseDependencyManager =TestCaseDependencyManager.logTestCaseDep(methodID);
            testCaseDependencyManager.validateFailedDep();

            System.out.println("Is skip current test cases inside update ? " + getReportAttributes().isSkipCurrentTestCase());
*/

            /*
            navgation bar
            need to ass function to wait until navigation bar finsiedh
            scope: for any task that may need loading.
            <div _ngcontent-xfs-c2="" class="sk-chase"><div _ngcontent-xfs-c2="" class="sk-chase-dot"></div><div _ngcontent-xfs-c2="" class="sk-chase-dot"></div><div _ngcontent-xfs-c2="" class="sk-chase-dot"></div><div _ngcontent-xfs-c2="" class="sk-chase-dot"></div><div _ngcontent-xfs-c2="" class="sk-chase-dot"></div><div _ngcontent-xfs-c2="" class="sk-chase-dot"></div></div>

             */


            SmartUIValidator.refreshPage(FailureHandler.FailureHandling.STOP_ON_FAILURE);// to close up any annoying popup
            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);



            // move to update information card
            //mainHomePage.navigateToHomePage(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            mainHomePage.clickOnUpdateDataServiceCardButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            // validating inserting primary data
            updateDataFormPage.clickOnPrimaryDataFragmentButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            updateDataFormPage.validateIDReflection(FailureHandler.FailureHandling.STOP_ON_FAILURE, SquadTestData1.getUserIDNumber());

            updateDataFormPage.selectEducationalLevel(UpdateDataFormPage.BACHELOR_EDUCATION_LEVEL, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            updateDataFormPage.selectJobSectorType(UpdateDataFormPage.GOVERNMENT_SECTOR_JOB_LEVEL, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            updateDataFormPage.insertMonthlyIncome("2500", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            updateDataFormPage.insertNumberOfDependants("4", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            updateDataFormPage.selectSocialStatusType(UpdateDataFormPage.MARRIED_SOCIAL_LEVEL, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            updateDataFormPage.insertPlaceOfBirth("Riyadh", FailureHandler.FailureHandling.STOP_ON_FAILURE);


            updateDataFormPage.saveInformation(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            // validating inserting national address information
            updateDataFormPage.clickOnNationalAddressFragmentButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);





            String nationalAddressValue = updateDataFormPage.selectNationalAddressType(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            nationalAddressValue = nationalAddressValue.trim();

            if(nationalAddressValue.contains("أبها") || nationalAddressValue.contains("ابها")){
                // change city as there is defect for this city.
                updateDataFormPage.selectNationalAddressType(2, FailureHandler.FailureHandling.STOP_ON_FAILURE);

            }


            updateDataFormPage.selectTypeOfAccommodation(UpdateDataFormPage.RENT_ACCOMMODATION_TYPE, FailureHandler.FailureHandling.STOP_ON_FAILURE);



            // updateDataFormPage.selectFromResidenceCityDDL(1, FailureHandler.FailureHandling.STOP_ON_FAILURE); defect on dev team should be removed.






            updateDataFormPage.saveInformation(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            updateDataFormPage.clickOnContactFragmentButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            // validating inserting contact information
            updateDataFormPage.insertTelephoneNumber("0111111111", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            updateDataFormPage.insertMobileNumber("0590768641", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            updateDataFormPage.insertEmailAddress("ozaza.c@sdb.gov.sa", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            updateDataFormPage.saveInformation(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            System.out.println("I am here Update");

            /* removed from the system
            // validating inserting bank details information
            updateDataFormPage.clickOnBankDetailsData(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            updateDataFormPage.insertIban("SA1745000000590701599701", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            updateDataFormPage.selectBankName(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            updateDataFormPage.selectHasSavingAccount("YES", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            updateDataFormPage.saveInformationAfterUpdatingBankDetailsInformation(FailureHandler.FailureHandling.STOP_ON_FAILURE);
*/




            // mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);




            mainHomePage.navigateToHomePage(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            mainHomePage.verifyUpdateDataServiceTaskIsCompleted(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            //productiveFamiliesCertificationPage.clickOnTrainingTutorialsFragmentButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);




        } finally {
            SmartUIValidator.assertAll();

        }


        //assertEquals(3, 5);


    }






    @Test(priority = 5)
    public void validateIssuingProductiveFamiliesCertification() {

        try {
   /*         MyTestClass myTestClass = new MyTestClass();
            String methodID = myTestClass.getUniqueIdentifier(UpdateUserDataTest.class, "updateUserData");
            TestCaseDependencyManager testCaseDependencyManager = TestCaseDependencyManager.logTestCaseDep(methodID);
            testCaseDependencyManager.validateFailedDep();


            System.out.println("Is skip current test cases inside validate issuing productive families ? " + getReportAttributes().isSkipCurrentTestCase());

*/
            SmartUIValidator.refreshPage(FailureHandler.FailureHandling.STOP_ON_FAILURE);// to close up any annoying popup
            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            // move into family certification Service
            mainHomePage.clickOnProductiveFamilyCertificateServiceCardButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            productiveFamiliesCertificationPage.clickOnActivityAddressFragmentButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            // validate data insertion related for Activity Address forum
            productiveFamiliesCertificationPage.clickOnAcceptTerms(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            //productiveFamiliesCertificationPage.selectActivityAddress(" حي طويق - الخرج ", FailureHandler.FailureHandling.STOP_ON_FAILURE);

            productiveFamiliesCertificationPage.selectActivityAddress(1, FailureHandler.FailureHandling.STOP_ON_FAILURE); // new change


            productiveFamiliesCertificationPage.selectRegion(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            productiveFamiliesCertificationPage.selectArea(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            productiveFamiliesCertificationPage.selectCenter(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
        /*
        productiveFamiliesCertificationPage.insertCityAddress("الرياض", FailureHandler.FailureHandling.STOP_ON_FAILURE);
        productiveFamiliesCertificationPage.insertDistrictAR("المربع", FailureHandler.FailureHandling.STOP_ON_FAILURE);
        productiveFamiliesCertificationPage.insertDistrictEN("Almuraba", FailureHandler.FailureHandling.STOP_ON_FAILURE);
        productiveFamiliesCertificationPage.insertStreet("الوشم", FailureHandler.FailureHandling.STOP_ON_FAILURE);
        productiveFamiliesCertificationPage.insertBuildingNumber("1234", FailureHandler.FailureHandling.STOP_ON_FAILURE);
        productiveFamiliesCertificationPage.insertAdditionalNumber("222", FailureHandler.FailureHandling.STOP_ON_FAILURE);
        productiveFamiliesCertificationPage.insertUnitNumber("123", FailureHandler.FailureHandling.STOP_ON_FAILURE);
*/

            // validate data insertion related for project data

            productiveFamiliesCertificationPage.clickOnProjectDataFragmentButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            productiveFamiliesCertificationPage.selectActivityCategory(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            productiveFamiliesCertificationPage.selectMainActivity(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            productiveFamiliesCertificationPage.selectSubActivity(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            productiveFamiliesCertificationPage.selectCurrentProjectLocation(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            productiveFamiliesCertificationPage.insertYearsOfExperience("5", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            productiveFamiliesCertificationPage.insertEmailAddress("ozaza.c@sdb.gov.sa", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            productiveFamiliesCertificationPage.saveInformation(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            productiveFamiliesCertificationPage.clickOnTrainingTutorialsFragmentButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            productiveFamiliesCertificationPage.watchFirstThreeVideos(FailureHandler.FailureHandling.STOP_ON_FAILURE);



            productiveFamiliesCertificationPage.clickOnSendButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            productiveFamiliesCertificationPage.clickOnNavigateToHomePagePopupButton(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);


            mainHomePage.navigateToHomePage(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            //productiveFamiliesCertificationPage.clickOnNavigateBackToMainPage(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            mainHomePage.retryUntilFamilyCertificationDataCachedIsCleared(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            // steps to avodid cashing issue


            //mainHomePage.retryUntilFamilyCertificationDataCachedIsCleared(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            mainHomePage.verifyProductiveFamilyCertificationTaskIsCompleted(FailureHandler.FailureHandling.STOP_ON_FAILURE);
        }
        finally {
            SmartUIValidator.assertAll();
        }


    }







    @Test(priority = 6)
    public void requestNewLoan() {

        try {


/*
            MyTestClass myTestClass = new MyTestClass();
            String methodID = myTestClass.getUniqueIdentifier(ProductiveFamiliesCertificationTest.class, "validateIssuingProductiveFamiliesCertification");
            TestCaseDependencyManager testCaseDependencyManager =TestCaseDependencyManager.logTestCaseDep(methodID);
            testCaseDependencyManager.validateFailedDep();


            System.out.println("Is skip current test cases inside requestNewLoan ? " + getReportAttributes().isSkipCurrentTestCase());

*/


            SmartUIValidator.refreshPage(FailureHandler.FailureHandling.STOP_ON_FAILURE);// to close up any annoying popup
            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            mainHomePage.clickOnLoanRequestServiceCardButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.clickOnPrimaryDataFragmentButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            loanRequestPage.insertIbanNumberTextField("SA1745000000590701599701", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectFinanceBrokerType("حاج", FailureHandler.FailureHandling.STOP_ON_FAILURE); // 3 = حاج type
            loanRequestPage.selectGracePeriod(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectLoanAmount(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.insertProjectNameTextField("OsamaAutomationProject", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectFreeForProject(2, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectProjectGeneralGoals(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectHasExperienceInProjectActivity(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectHasCertificateInProjectActivity(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);

            loanRequestPage.clickOnSaveAndContinueButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            loanRequestPage.clickOnFinancialObligationFragmentButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectHasObtainedLoanFromCommercialBanksDDL(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectFromHasFinancialObligationsDDL(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.clickOnSaveAndContinueButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);



            loanRequestPage.clickOnFeasibilityStudyFragmentButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectFromMarketingMethods(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectIsProjectExisting(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectProjectLocation(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectIsThereSimilarProjectExisting(1, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.insertProductsNumberTextField("7", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.clickOnSaveAndContinueButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            loanRequestPage.clickOnTotalProjectCostsFragmentButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            mainHomePage.waitUntilProgressParFinishLoading(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            loanRequestPage.insertGovernmentLicensingFees("500", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.insertBillingFees("500", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectIsProjectNeededEquipment("NO", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.insertDecorationCost("500", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.insertPropertyCost("1000", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.insertManPowerCost("890", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.insertRawMaterialExpenses("300", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.insertMarketingExpenses("250", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.insertDepreciationExpense("100", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.insertOtherExpense("5", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectIsContributedInProjectCostDDL("NO", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.insertProjectTotalSupportRequired("10000", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.clickOnSaveAndContinueButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestPage.selectisThereGurantorDDL("NO", FailureHandler.FailureHandling.STOP_ON_FAILURE);



            loanRequestPage.clickOnSendButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);






            // int serviceNumber = loanRequestPage.getServiceNumberText(FailureHandler.FailureHandling.CONTINUE_ON_FAILURE);
            //  SmartUIValidator.addStep("Service Number is : "  + serviceNumber);

            loanRequestPage.clickOnNavigateToMainHomePageButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            mainHomePage.retryUntilLoanRequestDataCachedIsCleared(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            SquadTestData1.setReferenceNumber(loanRequestPage.getLastReferenceNumber(FailureHandler.FailureHandling.STOP_ON_FAILURE));

            System.out.println("Reference number of the ticket created is : " + SquadTestData1.getReferenceNumber());

            try {
                Thread.sleep(9000 * 3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SmartUIValidator.refreshPage(FailureHandler.FailureHandling.OPTIONAL);



/*
          SmartUIValidator.refreshAndWaitForAPIRequest("GetMasterData", FailureHandler.FailureHandling.STOP_ON_FAILURE, "GetMasterData", 5, 30);

          Map<String, String > api =  SmartUIValidator.getAPIRequestAndResponse("GetMasterData", FailureHandler.FailureHandling.OPTIONAL);

           String requestKey =  api.get("requestKey");
           String requestResponse =  api.get("responseKey");

           System.out.println("Request Key is : " +  requestKey);
            System.out.println("requestResponse : " +  requestKey);

     List<String > data =  SmartUIValidator.extractAttributeFromMyRequestModels(requestResponse, "RequestNumber");

     System.out.println("Request number returned from api is : " +  data.get(0));
*/





            // mainHomePage.verifyUpdateDataServiceTaskIsCompleted(FailureHandler.FailureHandling.STOP_ON_FAILURE);

        } finally {
            SmartUIValidator.assertAll();

        }


        //assertEquals(3, 5);


    }




    @Test(priority = 7)
    public void setupAcceptLoanTestData(){
        try {

            SquadTestData1.setCrmUserId("ozaza.c@sdb.gov.sa");
            SquadTestData1.setUserPassword("Neoleap@19711");



            SmartUIValidator.addStep("CRM user ID: " + SquadTestData1.getCrmUserId());
            SmartUIValidator.addStep("CRM user Password: " + SquadTestData1.getCrmUserPassword());


            SmartUIValidator.addStep("Test Data ↓↓↓↓");
            SmartUIValidator.addStep("Loan Request Number: " + SquadTestData1.getReferenceNumber()); // from loan request creation test
        }
        finally {
            SmartUIValidator.assertAll();
        }

    }




    @Test(priority = 8)
    public void
    acceptLoanRequest() {

        try {


            // variables to add as suqad variables

            String requestNumber = SquadTestData1.getReferenceNumber();
            // String requestNumber = "90226";
            //String requestNumber = "90216";


            //90080
            //90078

            // crmAppsPage.navigateToCRMApps("ozaza.c", "Neoleap@1971", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            //crmAppsPage.clickOnFinanceBrokersManagementSystemButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            //SmartUIValidator.switchToDefaultContent(FailureHandler.FailureHandling.STOP_ON_FAILURE);



            mainCRMPage.loginToCRM(SquadTestData1.getCrmUserId(), SquadTestData1.getCrmUserPassword(), FailureHandler.FailureHandling.STOP_ON_FAILURE);




            SmartUIValidator.waitForPageLoad(FailureHandler.FailureHandling.STOP_ON_FAILURE, 30);



            mainCRMPage.navigateToFinanceBrokersManagementSystemPage(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.selectViewFromViewSwitcher(FinanceBrokersManagementSystem.LOAN_MANAGEMENT_OPTION, FailureHandler.FailureHandling.STOP_ON_FAILURE);


            financeBrokersManagementSystem.selectOptionFromNavigationSideBar(FinanceBrokersManagementSystem.LOAN_REQUEST_SIDEBAR_OPTION, FailureHandler.FailureHandling.STOP_ON_FAILURE);

            financeBrokersManagementSystem.selectOptionFromDashboardList("Loan Applications", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.searchIntoDashboard(requestNumber, FailureHandler.FailureHandling.STOP_ON_FAILURE);

            financeBrokersManagementSystem.checkRequestItemDisplayedInDashboard(0, requestNumber, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.doubleClickOnRequestItemFromDashboard(0, FailureHandler.FailureHandling.STOP_ON_FAILURE);

            //SmartUIValidator.verifyEqual(3,4, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            System.out.println("Complete testing after blocker");


            //boolean isWaitingIbanValidationStatusDisplayed = loanRequestTicketPage.validateRequestStatus(LoanRequestTicketPage.WAITING_FOR_CHECKING_BANKING_ACCOUNT_REQ_STATUS, FailureHandler.FailureHandling.OPTIONAL);
            boolean  isWaitingIbanValidationStatusDisplayed = false;




// iban validation
/*
            if (isWaitingIbanValidationStatusDisplayed){

                //loanRequestTicketPage.validateRequestStatus(LoanRequestTicketPage.WAITING_FOR_CHECKING_BANKING_ACCOUNT_REQ_STATUS, FailureHandler.FailureHandling.STOP_ON_FAILURE);


                System.out.println("Running javascript code");
            CRMJavaScriptUtil crmJavaScriptUtil = new CRMJavaScriptUtil();
            crmJavaScriptUtil.unlockAllCRMFields(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            financeBrokersManagementSystem.assignTicketToMe(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.clickOnRefreshButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.assignTicketToMe(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.clickOnRefreshButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            // loanRequestTicketPage.selectItemFromMoreOptions(LoanRequestTicketPage.SAMA_IBAN_VALIDATION_REQ_MORE_OPTION, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            //loanRequestTicketPage.selectIbanStatus(LoanRequestTicketPage.IBAN_SUCCESS_STATUS, FailureHandler.FailureHandling.STOP_ON_FAILURE);

            //crmJavaScriptUtil.setRequesterValue("1031e0fe-973d-ed11-b82c-00505692a571", FailureHandler.FailureHandling.STOP_ON_FAILURE);


            // Sama Iban validation


            loanRequestTicketPage.selectItemFromMoreOptions(LoanRequestTicketPage.SAMA_IBAN_VALIDATION_REQ_MORE_OPTION, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            crmJavaScriptUtil.unlockAllCRMFields(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            loanRequestTicketPage.selectItemFromIbanStatusDDL(LoanRequestTicketPage.IBAN_SUCCESS_STATUS, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            loanRequestTicketPage.selectItemFromValidIbanDDL(LoanRequestTicketPage.IBAN_VALID_VALIDITY, FailureHandler.FailureHandling.STOP_ON_FAILURE);


            loanRequestTicketPage.selectItemFromBypassIbanValidationOptions(LoanRequestTicketPage.IBAN_YES_BYPASS_DECISION, FailureHandler.FailureHandling.STOP_ON_FAILURE);


            financeBrokersManagementSystem.saveAndClose(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            financeBrokersManagementSystem.selectOptionFromDashboardList("Loan Applications", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.searchIntoDashboard(requestNumber, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.doubleClickOnRequestItemFromDashboard(0, FailureHandler.FailureHandling.STOP_ON_FAILURE);


            // check status

        }




*/



            loanRequestTicketPage.validateRequestStatus(LoanRequestTicketPage.WAITING_FOR_LOAN_AUDITOR_STATUS, FailureHandler.FailureHandling.STOP_ON_FAILURE);

            financeBrokersManagementSystem.assignTicketToMe(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.clickOnRefreshButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.assignTicketToMe(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.clickOnRefreshButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);

// stop here need to check xpath for accept button
            loanRequestTicketPage.clickOnAcceptLoanRequestButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            // loanRequestTicketPage.validateRequestStatus(LoanRequestTicketPage.COMPLETED_LOAN_STATUS, FailureHandler.FailureHandling.STOP_ON_FAILURE);

            SmartUIValidator.waitForPageLoad(FailureHandler.FailureHandling.STOP_ON_FAILURE, 30);


            // replacment for back
            financeBrokersManagementSystem.selectOptionFromDashboardList("Loan Applications", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.searchIntoDashboard(requestNumber, FailureHandler.FailureHandling.STOP_ON_FAILURE);

            financeBrokersManagementSystem.checkRequestItemDisplayedInDashboard(0, requestNumber, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.doubleClickOnRequestItemFromDashboard(0, FailureHandler.FailureHandling.STOP_ON_FAILURE);



            //SmartUIValidator.back(FailureHandler.FailureHandling.STOP_ON_FAILURE);





            loanRequestTicketPage.validateRequestStatus(LoanRequestTicketPage.WAITING_FOR_LOAN_SUPERVISOR_APPROVAL_STATUS, FailureHandler.FailureHandling.STOP_ON_FAILURE);


            financeBrokersManagementSystem.assignTicketToMe(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.clickOnRefreshButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.assignTicketToMe(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.clickOnRefreshButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            //financeBrokersManagementSystem.saveAndClose(FailureHandler.FailureHandling.OPTIONAL);
            loanRequestTicketPage.clickOnApproveLoanRequestButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            SmartUIValidator.waitForPageLoad(FailureHandler.FailureHandling.STOP_ON_FAILURE, 30);
            //SmartUIValidator.back(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            // replacment for back
            financeBrokersManagementSystem.selectOptionFromDashboardList("Loan Applications", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.searchIntoDashboard(requestNumber, FailureHandler.FailureHandling.STOP_ON_FAILURE);

            financeBrokersManagementSystem.checkRequestItemDisplayedInDashboard(0, requestNumber, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.doubleClickOnRequestItemFromDashboard(0, FailureHandler.FailureHandling.STOP_ON_FAILURE);




            loanRequestTicketPage.validateRequestStatus(LoanRequestTicketPage.WAITING_FOR_LOAN_REGIONAL_MANAGER_APPROVAL_STATUS, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.assignTicketToMe(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.clickOnRefreshButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            financeBrokersManagementSystem.assignTicketToMe(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.clickOnRefreshButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);
            //financeBrokersManagementSystem.saveAndClose(FailureHandler.FailureHandling.OPTIONAL);





            loanRequestTicketPage.clickOnApproveLoanRequestButton(FailureHandler.FailureHandling.STOP_ON_FAILURE);


            SmartUIValidator.waitForPageLoad(FailureHandler.FailureHandling.STOP_ON_FAILURE, 30);
            //SmartUIValidator.back(FailureHandler.FailureHandling.STOP_ON_FAILURE);

            // replacment for back
            financeBrokersManagementSystem.selectOptionFromDashboardList("Loan Applications", FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.searchIntoDashboard(requestNumber, FailureHandler.FailureHandling.STOP_ON_FAILURE);

            financeBrokersManagementSystem.checkRequestItemDisplayedInDashboard(0, requestNumber, FailureHandler.FailureHandling.STOP_ON_FAILURE);
            financeBrokersManagementSystem.doubleClickOnRequestItemFromDashboard(0, FailureHandler.FailureHandling.STOP_ON_FAILURE);


            loanRequestTicketPage.validateRequestStatus(LoanRequestTicketPage.COMPLETED_LOAN_STATUS, FailureHandler.FailureHandling.STOP_ON_FAILURE);


        }

        finally {
            SmartUIValidator.assertAll();

        }






    }




}
