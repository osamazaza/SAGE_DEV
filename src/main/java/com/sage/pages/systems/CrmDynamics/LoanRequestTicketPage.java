package com.sage.pages.systems.CrmDynamics;
import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


/*

1 allowMoreButton
//button[@data-id='more_button']
-----------------------------------------------
2 SamaIbanValidation dropDownListItem:
//li[normalize-space(text())='Sama Iban Validation']
-----------------------------------------------
3 ibanStatus
//select[@data-id='sdb_ibanstatus.fieldControl-option-set-select']
related values:
Success
Pending
Failed
None
-----------------------------------------------
4 validIban
//select[@data-id='sdb_validiban.fieldControl-option-set-select']

related values:
Valid
Invalid
None

-----------------------------------------------
5 bypassIbanValidation
Yes
//div[@data-id='sdb_bypassibanvalidation.fieldControl-checkbox-containercheckbox-inner-second']
No
//div[@data-id='sdb_bypassibanvalidation.fieldControl-checkbox-containercheckbox-inner-first']


 */



public class LoanRequestTicketPage extends TestBase {
















    public void selectIbanStatus(String ibanStatusVisibleText, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.selectDropdownOption(ibanStatusDDL, ibanStatusVisibleText, null, -1,false, failureHandling, "Iban Status  Drop Down List");

    }

    By requestMoreOptionsButton = By.xpath("//button[@data-id='more_button']");



    // Options
    public static  final String SAMA_IBAN_VALIDATION_REQ_MORE_OPTION = "Sama Iban Validation";

    public void selectItemFromMoreOptions(String optionToClick,  FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.click(requestMoreOptionsButton, failureHandling, "More Options Button" );

        String optionTextXpath = "//ul[@role='presentation']//li[@role='tab' and normalize-space(@aria-label)='"+optionToClick+"']";
        By optionMenuItem = By.xpath(optionTextXpath);

        SmartUIValidator.click(optionMenuItem, failureHandling, "Option : " + optionToClick );
    }


    // locators and functions for sama iban validation


    // 1 for iban status ddl
    By ibanStatusDDL = By.xpath("//select[@aria-label='Iban Status'  and contains(@id, 'sdb_ibanstatus')]");


    public static  final String IBAN_SUCCESS_STATUS = "Success";
    public static  final String IBAN_NONE_STATUS = "None";
    public static  final String IBAN_PENDING_STATUS = "Pending";
    public static  final String IBAN_FAILED_STATUS = "Failed";


    public void selectItemFromIbanStatusDDL(String optionToClickVisibleText,  FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.click(ibanStatusDDL, failureHandling, "Iban Status DDL" );

        SmartUIValidator.selectDropdownOption(ibanStatusDDL, optionToClickVisibleText, null, -1, false, failureHandling, "Iban Staus DDL");

    }



    ///  2 for valid iban

    By validIbanDDL = By.xpath("//select[@data-id='sdb_validiban.fieldControl-option-set-select']");


    public static  final String IBAN_VALID_VALIDITY = "Valid";
    public static  final String IBAN_INVALID_VALIDITY = "Invalid";
    public static  final String IBAN_NONE_VALIDITY = "None";



    public void selectItemFromValidIbanDDL(String optionToClickVisibleText,  FailureHandler.FailureHandling failureHandling){

        SmartUIValidator.selectDropdownOption(validIbanDDL, optionToClickVisibleText, null, -1, false, failureHandling, "Valid Iban DDL");

    }


    public static  final String IBAN_YES_BYPASS_DECISION = "YES";
    public static  final String IBAN_NO_BYPASS_DECISION = "NO";


     /*
YES
     //div[@data-id='sdb_bypassibanvalidation.fieldControl-checkbox-containercheckbox-inner-second']
No
//div[@data-id='sdb_bypassibanvalidation.fieldControl-checkbox-containercheckbox-inner-first']



      */





    By yesDecisionBypass = By.xpath("//div[@data-id='sdb_bypassibanvalidation.fieldControl-checkbox-container']//div[@role='radio' and @aria-label='Yes' and @aria-checked='true']");
    By noDecisionBypass = By.xpath("(//div[@role='radio' and @aria-label='No']/label)[1]");
    By ByPassView = By.xpath("//div[@data-id='sdb_bypassibanvalidation.fieldControl-checkbox-container']");
    By laonRequestScrollView = By.xpath("//*[@data-id='tabpanel-General_Info']");






    public void selectItemFromBypassIbanValidationOptions(String byPassDecision,  FailureHandler.FailureHandling failureHandling){
        boolean isDecisionItemDisplayed = false;
        System.out.println("selectItemFromBypassIbanValidationOptions is executed");

        if(byPassDecision.equalsIgnoreCase(IBAN_YES_BYPASS_DECISION)){

            System.out.println("Yes decision is selected");

             isDecisionItemDisplayed = SmartUIValidator.isDisplayed(yesDecisionBypass, FailureHandler.FailureHandling.OPTIONAL, "Yes bypass option");

            if(!isDecisionItemDisplayed){
                System.out.println("Yes option is not displayed displayed");

                // the item below is not logged.

                try {


                    WebElement view = getDriver().findElement(
                            By.xpath("//div[@data-id='sdb_bypassibanvalidation.fieldControl-checkbox-container']")
                    );
                    Thread.sleep(9000);

                    String script =
                            "function trigger(el) {" +
                                    "  ['mousedown','mouseup','click'].forEach(function(evt) {" +
                                    "    var e = new MouseEvent(evt, {bubbles: true, cancelable: true, view: window});" +
                                    "    el.dispatchEvent(e);" +
                                    "  });" +
                                    "}" +
                                    "trigger(arguments[0]);";

                    ((JavascriptExecutor) getDriver()).executeScript(script, view);





Actions actions = new Actions(getDriver());
actions.moveToElement(view).clickAndHold(view).build().perform();







                }




                catch(Exception e){
                    SmartUIValidator.handleSeleniumException(e, failureHandling, "Bypass view", ByPassView, "", null, null, null);

                }

                //SmartUIValidator.doubleClick(ByPassView, failureHandling, "ByPassView" );

            }



        }

        else if (byPassDecision.equalsIgnoreCase(IBAN_NO_BYPASS_DECISION)){




        }




    }












    // request Statuses.
    public static final String WAITING_FOR_CHECKING_BANKING_ACCOUNT_REQ_STATUS = "في انتظار التحقق من الحساب البنكي";
    public static final String WAITING_FOR_LOAN_AUDITOR_STATUS = "بانتظار ظابط الاقراض";
    public static final String WAITING_FOR_LOAN_SUPERVISOR_APPROVAL_STATUS = "بإنتظار موافقة مشرف الاقراض";
    public static final String WAITING_FOR_LOAN_REGIONAL_MANAGER_APPROVAL_STATUS = "المدير الاقليمي";

    public static final String COMPLETED_LOAN_STATUS = "مكتمل";




    public boolean validateRequestStatus(String expectedRequestValue,  FailureHandler.FailureHandling failureHandling){
String requestStatusXpath = "//div[@aria-label= '"+expectedRequestValue+"'  and contains(@class, 'flexbox')]";
        By requestStatusLabel = By.xpath(requestStatusXpath);

     return   SmartUIValidator.refreshAndWaitForView(requestStatusLabel, failureHandling, "Request Status: " + expectedRequestValue, 3, 15);

    }






    By iframeActionsGroup = By.xpath("//iframe[@id='WebResource_WebResource_Decision']");



    By acceptLoanRequestButton = By.xpath("//input[@type='button' and @value='Accept']");
    By approveLoanRequestButton = By.xpath("//input[@type='button' and @value='Approve']");





    public void clickOnApproveLoanRequestButton(FailureHandler.FailureHandling failureHandling){



        scrollToActions(failureHandling);



        // SmartUIValidator.scrollUntilVisible("//iframe[@id='WebResource_WebResource_Decision']");
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        SmartUIValidator.switchToIframe(iframeActionsGroup, failureHandling, "iframe Actions Group");


        SmartUIValidator.click(approveLoanRequestButton, failureHandling, "Approve Loan Request Button");

        SmartUIValidator.switchToDefaultContent(failureHandling);

    }


    public void scrollToActions(FailureHandler.FailureHandling failureHandling){

       if(!getReportAttributes().isSkipCurrentTestCase()){
           boolean isScrollViewDisplayed = SmartUIValidator.refreshAndWaitForView(laonRequestScrollView, failureHandling,"Scroll View", 3, 20 );
           if(isScrollViewDisplayed){
               System.out.println("Scroll View is displayed");
           }




           String scrollScript =
                   "// Scrollable element XPath\n" +
                           "const scrollableXpath = \"//*[@data-id='tabpanel-General_Info']\";\n" +
                           "// Target element to check during scroll\n" +
                           "const targetXpath = \"//iframe[@id='WebResource_WebResource_Decision']\";\n" +
                           "// Find scrollable element\n" +
                           "const scrollableElement = document.evaluate(\n" +
                           "    scrollableXpath,\n" +
                           "    document,\n" +
                           "    null,\n" +
                           "    XPathResult.FIRST_ORDERED_NODE_TYPE,\n" +
                           "    null\n" +
                           ").singleNodeValue;\n" +
                           "if (scrollableElement) {\n" +
                           "    let scrollCount = 0;\n" +
                           "    const maxScrolls = 15;\n" +
                           "    const scrollInterval = setInterval(() => {\n" +
                           "        const targetElement = document.evaluate(\n" +
                           "            targetXpath,\n" +
                           "            document,\n" +
                           "            null,\n" +
                           "            XPathResult.FIRST_ORDERED_NODE_TYPE,\n" +
                           "            null\n" +
                           "        ).singleNodeValue;\n" +
                           "        if (targetElement) {\n" +
                           "            clearInterval(scrollInterval);\n" +
                           "            console.log(\"✅ Target element found!\");\n" +
                           "            window.foundElement = targetElement;\n" +
                           "        } else {\n" +
                           "            scrollableElement.scrollBy(0, 150);\n" +
                           "            scrollCount++;\n" +
                           "            if (scrollCount >= maxScrolls) {\n" +
                           "                clearInterval(scrollInterval);\n" +
                           "                console.log(\"✅ Max scrolls reached, element not found.\");\n" +
                           "                window.foundElement = null;\n" +
                           "            }\n" +
                           "        }\n" +
                           "    }, 500);\n" +
                           "} else {\n" +
                           "    console.log(\"❌ Scrollable element not found\");\n" +
                           "}";
           System.out.println("Start swipe");
           JavascriptExecutor javascriptExecutor = (JavascriptExecutor)getDriver();
           javascriptExecutor.executeScript(scrollScript);
           try {
               Thread.sleep(9000 * 2);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
           System.out.println("Finish swipe");

       }



    }

    public void clickOnAcceptLoanRequestButton(FailureHandler.FailureHandling failureHandling){

        scrollToActions(failureHandling);



        //SmartUIValidator.scrollUntilVisible("//iframe[@id='WebResource_WebResource_Decision']");



        SmartUIValidator.switchToIframe(iframeActionsGroup, failureHandling, "iframe Actions Group");


        SmartUIValidator.click(acceptLoanRequestButton, failureHandling, "Accept Loan Request Button");
        SmartUIValidator.switchToDefaultContent(failureHandling);

    }




    /*

    assign button
"//button[.//span[normalize-space(text())='Assign']]"

// request status label
"//div[@aria-label='بإنتظار موافقة مشرف الاقراض' and contains(@class, 'flexbox')]"

approve button
"//input[@type='button' and @value='Approve']"
     */












}
