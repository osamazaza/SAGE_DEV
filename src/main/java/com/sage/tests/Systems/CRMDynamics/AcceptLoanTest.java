package com.sage.tests.Systems.CRMDynamics;

import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import com.sage.pages.systems.CrmDynamics.CRMAppsPage;
import com.sage.pages.systems.CrmDynamics.FinanceBrokersManagementSystem;
import com.sage.pages.systems.CrmDynamics.LoanRequestTicketPage;
import com.sage.pages.systems.CrmDynamics.MainCRMPage;
import com.sage.testdata.dataDriven.RunTimeAttributes.SquadTestData1;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import com.sdb.pages.systems.CrmDynamics
public class AcceptLoanTest extends TestBase {
    MainCRMPage mainCRMPage;
    CRMAppsPage crmAppsPage;
    FinanceBrokersManagementSystem financeBrokersManagementSystem;
    LoanRequestTicketPage loanRequestTicketPage;







    @BeforeClass
    public void initPage() {

        crmAppsPage = PageFactory.initElements(getDriver(), CRMAppsPage.class);
        mainCRMPage = PageFactory.initElements(getDriver(), MainCRMPage.class);
        financeBrokersManagementSystem = PageFactory.initElements(getDriver(), FinanceBrokersManagementSystem.class);
        loanRequestTicketPage = PageFactory.initElements(getDriver(), LoanRequestTicketPage.class);


    }


    @Test(priority = 1)
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

    @Test(priority = 2)
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



    /*
    @Test
    public void createParentUsersTest() {
        // Prepare headers
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        List<Map<String, String>> headers = new ArrayList<>();
        headers.add(headerMap);

        // Loop 20 times
        for (int i = 22; i <= 42; i++) {
            String nationalId = IDGenerator.getIdNumber(1); // ✅ valid Saudi ID
            String userName = "PTest" + i;                  // ✅ sequential username

            String requestBody = "{\n" +
                    "    \"SDBLogID\": \"SDBLogID-1\",\n" +
                    "    \"contactID\": \"\",\n" +
                    "    \"nationality\": \"113\",\n" +
                    "    \"national_id\": \"" + nationalId + "\",\n" +
                    "    \"gender\": \"Male\",\n" +
                    "    \"nationalIDType\": \"هوية\",\n" +
                    "    \"UserName\": \"" + userName + "\",\n" +
                    "    \"confirmPassword\": \"000\",\n" +
                    "    \"Password\": \"000\",\n" +
                    "    \"genderEnumValue\": \"\",\n" +
                    "    \"idTypeEnumValue\": \"\",\n" +
                    "    \"idExperiationDatehijri\": \"\",\n" +
                    "    \"idExperiationDategregorian\": \"\",\n" +
                    "    \"iqamaExperiationDatehijri\": \"\",\n" +
                    "    \"iqamaExperiationDategregorian\": \"\",\n" +
                    "    \"card_issue_date_gregorian\": \"\",\n" +
                    "    \"card_issue_date_hijri\": \"\",\n" +
                    "    \"dob\": \"2000-10-10\",\n" +
                    "    \"arabic_first_name\": \"احمد\",\n" +
                    "    \"english_first_name\": \"rihan\",\n" +
                    "    \"arabic_father_name\": \"احمد\",\n" +
                    "    \"english_father_name\": \"Test\",\n" +
                    "    \"arabic_grand_father_name\": \"احمد\",\n" +
                    "    \"english_grand_father_name\": \"Test\",\n" +
                    "    \"arabic_family_name\": \"احمد\",\n" +
                    "    \"english_family_name\": \"Test\",\n" +
                    "    \"cRM_OrganizationId\": \"1012\"\n" +
                    "}";

            APIConfiguration registerRequest = new APIConfiguration(
                    "https://tstlengateway.sdb.gov.sa/partnerportal/register",
                    "POST",
                    headers,   // headers list
                    null,      // No query params
                    requestBody
            );

            System.out.println("----- Register API Request for User: " + userName + " -----");
            Response response = registerRequest.pushRequest(
                    com.sdb.base.FailureHandler.FailureHandling.CONTINUE_ON_FAILURE
            );

            if (response != null) {
                System.out.println("✅ " + userName + " created | National ID: " + nationalId);
                System.out.println("Response Code: " + response.getStatusCode());
                System.out.println("Response Body:\n" + response.getBody().asPrettyString());
            } else {
                System.out.println("❌ No response received for " + userName);
            }
        }
    }
*/


/*
    @Test
    public void createFamilyUsersTest() {
        // Prepare headers
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        List<Map<String, String>> headers = new ArrayList<>();
        headers.add(headerMap);

        // Create 20 users: FTest2 ... FTest21
        for (int i = 2; i <= 21; i++) {
            String nationalId = IDGenerator.getIdNumber(1); // ✅ valid Saudi ID
            String userName = "FTest" + i;                  // ✅ sequential usernames

            String requestBody = "{\n" +
                    "    \"SDBLogID\": \"SDBLogID-1\",\n" +
                    "    \"contactID\": \"\",\n" +
                    "    \"nationality\": \"113\",\n" +
                    "    \"national_id\": \"" + nationalId + "\",\n" +
                    "    \"gender\": \"Female\",\n" +
                    "    \"nationalIDType\": \"هوية\",\n" +
                    "    \"UserName\": \"" + userName + "\",\n" +
                    "    \"confirmPassword\": \"000\",\n" +
                    "    \"Password\": \"000\",\n" +
                    "    \"genderEnumValue\": \"\",\n" +
                    "    \"idTypeEnumValue\": \"\",\n" +
                    "    \"idExperiationDatehijri\": \"\",\n" +
                    "    \"idExperiationDategregorian\": \"\",\n" +
                    "    \"iqamaExperiationDatehijri\": \"\",\n" +
                    "    \"iqamaExperiationDategregorian\": \"\",\n" +
                    "    \"card_issue_date_gregorian\": \"\",\n" +
                    "    \"card_issue_date_hijri\": \"\",\n" +
                    "    \"dob\": \"2000-10-10\",\n" +
                    "    \"arabic_first_name\": \"احمد\",\n" +
                    "    \"english_first_name\": \"ahmed\",\n" +
                    "    \"arabic_father_name\": \"احمد\",\n" +
                    "    \"english_father_name\": \"ahmed\",\n" +
                    "    \"arabic_grand_father_name\": \"احمد\",\n" +
                    "    \"english_grand_father_name\": \"ahmed\",\n" +
                    "    \"arabic_family_name\": \"احمد\",\n" +
                    "    \"english_family_name\": \"ahmed\"\n" +
                    "}";

            APIConfiguration registerRequest = new APIConfiguration(
                    "https://tstpfgateway.sdb.gov.sa/api/profile/register",
                    "POST",
                    headers,   // headers list
                    null,      // No query params
                    requestBody
            );

            System.out.println("----- Register API Request for User: " + userName + " -----");
            Response response = registerRequest.pushRequest(
                    com.sdb.base.FailureHandler.FailureHandling.CONTINUE_ON_FAILURE
            );

            if (response != null) {
                System.out.println("✅ " + userName + " created | National ID: " + nationalId);
                System.out.println("Response Code: " + response.getStatusCode());
                System.out.println("Response Body:\n" + response.getBody().asPrettyString());
            } else {
                System.out.println("❌ No response received for " + userName);
            }
        }
    }
*/





    }


