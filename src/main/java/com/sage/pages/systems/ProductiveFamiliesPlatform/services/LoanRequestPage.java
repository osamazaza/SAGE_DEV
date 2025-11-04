package com.sage.pages.systems.ProductiveFamiliesPlatform.services;

import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import com.sage.utils.Calculator;

import org.openqa.selenium.By;

public class LoanRequestPage extends TestBase {



    By primaryDataFragmentButton =By.xpath("//a[contains(@class, 'tab-item') and contains(text(), 'البيانات الأساسية')]");
    By FinancialObligationFragmentButton =By.xpath("//a[contains(@class, 'tab-item') and contains(text(), 'الإلتزامات المالية')]");
    By feasibilityStudyFragmentButton =By.xpath("//a[contains(@class, 'tab-item') and contains(text(), 'دراسة الجدوى')]");
    By totalProjectCostsFragmentButton =By.xpath("//a[contains(@class, 'tab-item') and contains(text(), 'التكاليف الكلية للمشروع')]");
    By gurantorFragmentButton =By.xpath("//a[contains(@class, 'tab-item') and contains(text(), 'الكفالة')]");
    By lastLoanRequestNumber = By.xpath("(//a[starts-with(@href, '/loan/view/')])[1]");






    public String getLastReferenceNumber(FailureHandler.FailureHandling failureHandling){
       return SmartUIValidator.getText(lastLoanRequestNumber, failureHandling, "Last Reference Number Text");
    }


    public void clickOnPrimaryDataFragmentButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(primaryDataFragmentButton, failureHandling, "PrimaryData Fragment button");

    }


    public void clickOnFinancialObligationFragmentButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(FinancialObligationFragmentButton, failureHandling, "Financial Obligation Fragment button");
    }

    public void clickOnFeasibilityStudyFragmentButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(feasibilityStudyFragmentButton, failureHandling, "Feasibility Study Fragment button");
    }

    public void clickOnTotalProjectCostsFragmentButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(totalProjectCostsFragmentButton, failureHandling, "Total Project Costs Fragment button");
    }

    public void clickOnGurantorFragmentButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(gurantorFragmentButton, failureHandling, "Gurantor Fragment button");
    }


    // 4 text fields
    By ibanNumberTextField = By.xpath("//input[@formcontrolname='ibanNumber']");
    By installmentAmountTextField = By.xpath("//input[@name='installmentAmount' and @inputmode='decimal']"); // READ ONLY
    By lastInstallmentAmountTextField = By.xpath("//input[@name='lastInstallmentAmount' and @inputmode='decimal']"); // READ ONLY
    By projectNameTextField = By.xpath("//input[@formcontrolname='projectName']"); // READ ONLY



    public void insertIbanNumberTextField(String ibanNumber, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(ibanNumberTextField, failureHandling, "IBAN Number TextField");
        SmartUIValidator.sendKeys(ibanNumberTextField, failureHandling, "IBan Number TextField", ibanNumber);

    }

    public void insertInstallmentAmountTextField(String amount, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(installmentAmountTextField, failureHandling, "Installment Amount TextField");
        SmartUIValidator.sendKeys(installmentAmountTextField, failureHandling, "Installment Amount TextField", amount);
    }


    public void insertLastInstallmentAmountTextField(String amount, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(lastInstallmentAmountTextField, failureHandling, "Last Installment Amount TextField");
        SmartUIValidator.sendKeys(lastInstallmentAmountTextField, failureHandling, "Last Installment Amount TextField", amount);
    }


    public void insertProjectNameTextField(String projectName, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(projectNameTextField, failureHandling, "Project Name TextField");
        SmartUIValidator.sendKeys(projectNameTextField, failureHandling, "Project Name TextField", projectName);
    }


    // 7 drop down lists
    By financeBrokerDDL = By.xpath("//select[@formcontrolname='financebroker']");
    By gracePeriodDDL = By.xpath("//select[@formcontrolname='graceperiod']");
    By loanAmountDDL = By.xpath("//select[@formcontrolname='loanAmount']");
    By freeForProjectDDL = By.xpath("//select[@formcontrolname='freeForProject']");
    By projectGeneralGoalsDDL = By.xpath("//select[@formcontrolname='projectGeneralGoals']");
    By hasExperienceinProjectActivityDDL = By.xpath("//select[@formcontrolname='hasExperienceinProjectActivity']");
    By hasCertificateinProjectActivityDDL = By.xpath("//select[@formcontrolname='hascertificateinProjectActivity']");




    public void selectFinanceBrokerType(String visibleText, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.selectDropdownOption(financeBrokerDDL, visibleText, null, -1, false, failureHandling, "financeBroker Drop Down List");

    }

    public void selectGracePeriod(int index, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.selectDropdownOption(gracePeriodDDL, null, null, index, false, failureHandling, "Grace Period Drop Down List");
    }

    public void selectLoanAmount(int index, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.selectDropdownOption(loanAmountDDL, null, null, index, false, failureHandling, "Loan Amount Drop Down List");
    }


    public void selectFreeForProject(int index, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.selectDropdownOption(freeForProjectDDL, null, null, index, false, failureHandling, "Free For Project Drop Down List");
    }


    public void selectProjectGeneralGoals(int index, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.selectDropdownOption(projectGeneralGoalsDDL, null, null, index, false, failureHandling, "Project General Goals Drop Down List");
    }


    public void selectHasExperienceInProjectActivity(int index, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.selectDropdownOption(hasExperienceinProjectActivityDDL, null, null, index, false, failureHandling, "Has Experience in Project Activity Drop Down List");
    }


    public void selectHasCertificateInProjectActivity(int index, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.selectDropdownOption(hasCertificateinProjectActivityDDL, null, null, index, false, failureHandling, "Has Certificate in Project Activity Drop Down List");
    }


    /// //////////////////////////////////////


    // 2 financial obligation

    By hasObtainedLoanFromCommercialBanksDDL = By.xpath("//select[@formcontrolname='obtainedloanFromCommercialBanks']");
    By hasFinancialObligationsDDL = By.xpath("//select[@formcontrolname='hasFinancialObligations']");


    public void selectHasObtainedLoanFromCommercialBanksDDL(int index, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.selectDropdownOption(hasObtainedLoanFromCommercialBanksDDL, null, null, index, false, failureHandling, "obtainedLoanFromCommercialBanks Drop Down List");
    }

    public void selectFromHasFinancialObligationsDDL(int index, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.selectDropdownOption(hasFinancialObligationsDDL, null, null, index, false, failureHandling, "has Financial Obligations Drop Down List");
    }

    /// //////////////////////////////////////////////

    // 3 feasibility study

    // 4 drop down lists
    By marketingMethodsDDL = By.xpath("//select[@formcontrolname='marketingMethods']");
    By isProjectExistingDDL = By.xpath("//select[@formcontrolname='isProjectExisting']");
    By projectLocationDDL = By.xpath("//select[@formcontrolname='projectLocation']");
    By isThereSimilarProjectExistingDDL = By.xpath("//select[@formcontrolname='isThereSimilarProjectExisting']");

    public void selectFromMarketingMethods(int index, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.selectDropdownOption(marketingMethodsDDL, null, null, index, false, failureHandling, "marketingMethods Drop Down List");
    }

    public void selectIsProjectExisting(int index, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.selectDropdownOption(isProjectExistingDDL, null, null, index, false, failureHandling, "Is Project Existing Drop Down List");
    }

    public void selectProjectLocation(int index, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.selectDropdownOption(projectLocationDDL, null, null, index, false, failureHandling, "Project Location Drop Down List");
    }


    public void selectIsThereSimilarProjectExisting(int index, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.selectDropdownOption(isThereSimilarProjectExistingDDL, null, null, index, false, failureHandling, "Is There Similar Project Existing Drop Down List");
    }




    By productsNumberTextField = By.xpath("//input[@formcontrolname='productsNumber']");


    public void insertProductsNumberTextField(String productNumber, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(productsNumberTextField, failureHandling, "Product Number TextField");
        SmartUIValidator.sendKeys(productsNumberTextField, failureHandling, "Product Number TextField", productNumber);
    }




    // total Project costs

    // 11 text fields

    By governmentLicensingFeesTextField = By.xpath("//input[@formcontrolname='governmentLicensingFees']"); // READ ONLY
    By billingFeesTextField = By.xpath("//input[@formcontrolname='billingFees']"); // READ ONLY
    By decorationCostTextField = By.xpath("//input[@formcontrolname='decorationCost']"); // READ ONLY
    By propertyCostTextField = By.xpath("//input[@formcontrolname='propertyCost']"); // READ ONLY
    By manPowerCostTextField = By.xpath("//input[@formcontrolname='manPowerCost']"); // READ ONLY
    By rawMaterialExpensesTextField = By.xpath("//input[@formcontrolname='rawMaterialExpenses']"); // READ ONLY
    By marketingExpensesTextField = By.xpath("//input[@formcontrolname='marketingExpenses']"); // READ ONLY
    By depreciationExpenseTextField = By.xpath("//input[@formcontrolname='depreciationExpense']"); // READ ONLY
    By otherExpenseTextField = By.xpath("//input[@formcontrolname='otherExpense']"); // READ ONLY
    By totalProjectCostsTextField = By.xpath("//input[@name='projectTotalCost']"); // READ ONLY
    By projectTotalSupportRequiredTextField = By.xpath("//input[@formcontrolname='projectTotalSupportRequired']"); // READ ONLY


        public void insertGovernmentLicensingFees(String governmentLicensingFees, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(governmentLicensingFeesTextField, failureHandling, "governmentLicensingFees TextField");
        SmartUIValidator.sendKeys(governmentLicensingFeesTextField, failureHandling, "governmentLicensingFees TextField", governmentLicensingFees);
      }




    public void insertBillingFees(String billingFees, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(billingFeesTextField, failureHandling, "Billing Fees TextField");
        SmartUIValidator.sendKeys(billingFeesTextField, failureHandling, "Billing Fees TextField", billingFees);
    }

    public void insertDecorationCost(String decorationCost, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(decorationCostTextField, failureHandling, "Decoration Cost TextField");
        SmartUIValidator.sendKeys(decorationCostTextField, failureHandling, "Decoration Cost TextField", decorationCost);
    }

    public void insertPropertyCost(String propertyCost, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(propertyCostTextField, failureHandling, "Property Cost TextField");
        SmartUIValidator.sendKeys(propertyCostTextField, failureHandling, "Property Cost TextField", propertyCost);
    }

    public void insertManPowerCost(String manPowerCost, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(manPowerCostTextField, failureHandling, "Man Power Cost TextField");
        SmartUIValidator.sendKeys(manPowerCostTextField, failureHandling, "Man Power Cost TextField", manPowerCost);
    }

    public void insertRawMaterialExpenses(String rawMaterialExpenses, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(rawMaterialExpensesTextField, failureHandling, "Raw Material Expenses TextField");
        SmartUIValidator.sendKeys(rawMaterialExpensesTextField, failureHandling, "Raw Material Expenses TextField", rawMaterialExpenses);
    }

    public void insertMarketingExpenses(String marketingExpenses, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(marketingExpensesTextField, failureHandling, "Marketing Expenses TextField");
        SmartUIValidator.sendKeys(marketingExpensesTextField, failureHandling, "Marketing Expenses TextField", marketingExpenses);
    }

    public void insertDepreciationExpense(String depreciationExpense, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(depreciationExpenseTextField, failureHandling, "Depreciation Expense TextField");
        SmartUIValidator.sendKeys(depreciationExpenseTextField, failureHandling, "Depreciation Expense TextField", depreciationExpense);
    }

    public void insertOtherExpense(String otherExpense, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(otherExpenseTextField, failureHandling, "Other Expense TextField");
        SmartUIValidator.sendKeys(otherExpenseTextField, failureHandling, "Other Expense TextField", otherExpense);
    }

    public void insertTotalProjectCosts(String totalProjectCosts, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(totalProjectCostsTextField, failureHandling, "Total Project Costs TextField");
        SmartUIValidator.sendKeys(totalProjectCostsTextField, failureHandling, "Total Project Costs TextField", totalProjectCosts);
    }

    public void insertProjectTotalSupportRequired(String projectTotalSupportRequired, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(projectTotalSupportRequiredTextField, failureHandling, "Project Total Support Required TextField");
        SmartUIValidator.sendKeys(projectTotalSupportRequiredTextField, failureHandling, "Project Total Support Required TextField", projectTotalSupportRequired);
    }


    // 2 drop down lists


    By IsProjectNeededEquipmentsDDL = By.xpath("//select[@formcontrolname='projectNeedEquipments']");
    By IsContributedInProjectCostDDL = By.xpath("//select[@formcontrolname='contributeInProjectCost']");


    public void selectIsProjectNeededEquipment(String yesNoValVisibleText, FailureHandler.FailureHandling failureHandling) {


        if (yesNoValVisibleText.equalsIgnoreCase("YES")) {
            yesNoValVisibleText = "نعم";

        } else if (yesNoValVisibleText.equalsIgnoreCase("NO")) {
            yesNoValVisibleText = "لا";

        }

        SmartUIValidator.selectDropdownOption(IsProjectNeededEquipmentsDDL, yesNoValVisibleText, null, -1, false, failureHandling, "IsProjectNeededEquipments Drop Down List");

    }


    public void selectIsContributedInProjectCostDDL(String yesNoValVisibleText, FailureHandler.FailureHandling failureHandling) {


        if (yesNoValVisibleText.equalsIgnoreCase("YES")) {
            yesNoValVisibleText = "نعم";

        } else if (yesNoValVisibleText.equalsIgnoreCase("NO")) {
            yesNoValVisibleText = "لا";

        }

        SmartUIValidator.selectDropdownOption(IsContributedInProjectCostDDL, yesNoValVisibleText, null, -1, false, failureHandling, "IsContributedInProjectCost Drop Down List");

    }


    By isThereGurantorDDL = By.xpath("//select[@formcontrolname='isThereGurantor']");



    public void selectisThereGurantorDDL(String yesNoValVisibleText, FailureHandler.FailureHandling failureHandling) {


        if (yesNoValVisibleText.equalsIgnoreCase("YES")) {
            yesNoValVisibleText = "نعم";

        } else if (yesNoValVisibleText.equalsIgnoreCase("NO")) {
            yesNoValVisibleText = "لا";

        }

        SmartUIValidator.selectDropdownOption(isThereGurantorDDL, yesNoValVisibleText, null, -1, false, failureHandling, "isThereGurantorDDL Drop Down List");

    }




    By sendButton = By.xpath("//button[normalize-space(text())='إرسال']");

    public void clickOnSendButton(FailureHandler.FailureHandling failureHandling) {




        SmartUIValidator.click(sendButton, failureHandling, "send Button");

    }


    By navigateToMainHomePageButton = By.xpath("//a[normalize-space(text())='الصفحة الرئيسية' and contains(@href, '/home')]");

    public void clickOnNavigateToMainHomePageButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(navigateToMainHomePageButton, failureHandling, "navigateToMainHomePage Button");

    }



    By serviceNumberTextView = By.xpath("substring-after(//p[contains(text(), 'رمز الخدمة')], 'رمز الخدمة')");


    public int getServiceNumberText(FailureHandler.FailureHandling failureHandling) {

        String serviceNumber = SmartUIValidator.getText(serviceNumberTextView, failureHandling, "Service Number textView");
        System.out.println("Service Number is: " + serviceNumber);
        int serviceNumberAsInteger = (int) Calculator.extractNumbersFromText(serviceNumber);
        return serviceNumberAsInteger;
    }
















    // 1 button
    By saveAndContinueButton = By.xpath("//*[contains(@class,'active') or contains(@class,'show') or contains(@class,'ng-star-inserted')]//button[normalize-space(.)='حفظ و متابعة']");

    public void clickOnSaveAndContinueButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(saveAndContinueButton, failureHandling, "Save And Continue Button");

    }







}
