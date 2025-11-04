package com.sage.pages.systems.ProductiveFamiliesPlatform.services;



import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;

public class UpdateDataFormPage extends TestBase {



    By primaryDataFragmentButton =By.xpath("//a[contains(@class, 'tab-item') and contains(text(), 'البيانات الأساسية')]");
    By nationalAddressFragmentButton =By.xpath("//a[contains(@class, 'tab-item') and contains(text(), 'العنوان الوطني')]");
    By contactDataFragmentButton =By.xpath("//a[contains(@class, 'tab-item') and contains(text(), 'بيانات التواصل')]");
    //By bankDetailsDataFragmentButton =By.xpath("//a[contains(@class, 'tab-item') and contains(text(), 'بيانات الحساب البنكي')]"); // removed from the system




    // 1 Primary Data fragment [functions, attributes, and data]
/// ///////////////////////////////////////////////////////////////////
///
    // 3 select drop down list

    By educationalLevelTypeDDL = By.xpath("//select[@formcontrolname='educationalLevel']");
    // educational Level type values


    public static final String NONE_EDUCATION_LEVEL = "لا يوجد";
    public static final String PRIMARY_EDUCATION_LEVEL = "إبتدائي";
    public static final String INTERMEDIATE_EDUCATION_LEVEL = "متوسط";
    public static final String SECONDARY_EDUCATION_LEVEL = "ثانوي";
    public static final String DIPLOMA_EDUCATION_LEVEL = "دبلوم";
    public static final String BACHELOR_EDUCATION_LEVEL = " بكالوريوس ";
    public static final String MASTER_EDUCATION_LEVEL = "ماجستير";
    public static final String DOCTORATE_EDUCATION_LEVEL = "دكتوراه";




    By jobSectorTypeDDL = By.xpath("//select[@formcontrolname='jobSector']");

    // job sector Level type values

    public static final String PERSONAL_WORK_JOB_LEVEL = "عمل شخصي";
    public static final String NONE_JOB_LEVEL = "لا يوجد";
    public static final String GOVERNMENT_SECTOR_JOB_LEVEL = "قطاع حكومي";
    public static final String PRIVATE_SECTOR_JOB_LEVEL = "قطاع خاص";
    public static final String MILITARY_JOB_LEVEL = "عسكري";
    public static final String STUDENT_JOB_LEVEL = "طالب";
    public static final String RETIRED_GOV_JOB_LEVEL = "متقاعد من القطاع الحكومي";
    public static final String RETIRED_PRIVATE_JOB_LEVEL = "متقاعد من القطاع الخاص";
    public static final String AFFILIATE_JOB_LEVEL = "متتسب";




    By socialStatusTypeDDL = By.xpath("//select[@formcontrolname='socialStatus']");


    public static final String MARRIED_SOCIAL_LEVEL = "متزوج";
    public static final String DIVORCED_SOCIAL_LEVEL = "مطلق";
    public static final String SINGLE_SOCIAL_LEVEL = "أعزب";
    public static final String WIDOWED_SOCIAL_LEVEL = "أرمل";
    public static final String SEPARATED_SOCIAL_LEVEL = "مهجور";


    // 4 text fields

    By idTextField = By.xpath("//input[@formcontrolname='nationalID']"); // READ ONLY
    By monthlyIncomeTextField = By.xpath("//input[@formcontrolname='monthlyIncome']");
    By numberOfDependentsTextField = By.xpath("//input[@formcontrolname='numberOfDependents']");
    By placeOfBirthTextField = By.xpath("//input[@formcontrolname='placeOfBirth']");





    // fragment buttons

    By saveButton =By.xpath("//button[contains(@class, 'btn-style') and contains(text(), 'حفظ')]");
    By yesConfirmationPopupButton = By.xpath("(//button[normalize-space(text())='نعم' and contains(@class, 'btn-popup')])[1]");
    By moveToHomeMainPagePopupButton = By.xpath("(//a[contains(@class, 'btn-popup') and contains(., 'الصفحة الرئيسية')])[1]");


    // reamining functions





    public void selectEducationalLevel(String educationalLevelTypeVisibleText, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.selectDropdownOption(educationalLevelTypeDDL, educationalLevelTypeVisibleText, null, -1,false, failureHandling, "Educational Level Type Drop Down List");

    }



    public void selectJobSectorType(String jobSectorTypeVisibleText, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.selectDropdownOption(jobSectorTypeDDL, jobSectorTypeVisibleText, null, -1, false, failureHandling, "Job Sector Level Type Drop Down List");

    }



    public void selectSocialStatusType(String socialStatusTypeVisibleText, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.selectDropdownOption(socialStatusTypeDDL, socialStatusTypeVisibleText, null, -1, false, failureHandling, "Social Status Level Type Drop Down List");

    }





    public void validateIDReflection(FailureHandler.FailureHandling failureHandling, String expectedIDValue){
        String helpfulMsg = "ID reflected at IDTextField is: ";
        String actualIDValue = SmartUIValidator.getText(idTextField, failureHandling, "ID Text Field");
        SmartUIValidator.verifyEqual(helpfulMsg + expectedIDValue, helpfulMsg + actualIDValue, failureHandling);
    }



    public void insertMonthlyIncome(String monthlyIncome, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(monthlyIncomeTextField, failureHandling, "Monthly Income TextField");
        SmartUIValidator.sendKeys(monthlyIncomeTextField, failureHandling, "Monthly Income TextField", monthlyIncome);

    }

    public void insertNumberOfDependants(String numberOfDependants, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(numberOfDependentsTextField, failureHandling, "Number of Dependants TextField");
        SmartUIValidator.sendKeys(numberOfDependentsTextField, failureHandling, "Number of Dependants TextField", numberOfDependants);

    }


    public void insertPlaceOfBirth(String placeOfBirth, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(placeOfBirthTextField, failureHandling, "Place of birth TextField");
        SmartUIValidator.sendKeys(placeOfBirthTextField, failureHandling, "place of birth TextField", placeOfBirth);

    }








    public void clickOnSaveButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(saveButton, failureHandling, "Save button");

    }


    public void clickOnYesConfirmationButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(yesConfirmationPopupButton, failureHandling, "Yes Confirmation Button");

    }


    public void clickOnMainPageConfirmationButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(moveToHomeMainPagePopupButton, failureHandling, "move To Home Main Page Popup Button");

    }





    public void clickOnPrimaryDataFragmentButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(primaryDataFragmentButton, failureHandling, "PrimaryData Fragment button");

    }

    public void clickOnNationalAddressFragmentButton(FailureHandler.FailureHandling failureHandling) {



            SmartUIValidator.click(nationalAddressFragmentButton, failureHandling, "National Address Fragment button");




    }


    public void clickOnContactFragmentButton(FailureHandler.FailureHandling failureHandling) {


            SmartUIValidator.click(contactDataFragmentButton, failureHandling, "Contact Data Fragment Button");



    }


/*
    public void clickOnBankDetailsData(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(bankDetailsDataFragmentButton, failureHandling, "Bank Details Data Fragment Button");

    }
*/

/// ///////////////////////////////////////////////////////////////////////

    // 2 National Address [functions, attributes, and data]

By nationalAddressDDL = By.xpath("//select[@formcontrolname='selectedNationalAddress']");
    public static final String NATIONAL_ADDRESS_YARMOOK = " حي اليرموك - الرياض ";



    public String selectNationalAddressType(int index, FailureHandler.FailureHandling failureHandling) {

        return SmartUIValidator.selectDropdownOption(nationalAddressDDL, null, null, index, false, failureHandling, "National  Address  Drop Down List");

    }









By typeOfAccommodation = By.xpath("//select[@formcontrolname='typeOfAccommodation']");
    public static final String RENT_ACCOMMODATION_TYPE = "إيجار";
    public static final String ACCOMMODATE_WITH_FAMILY_ACCOMMODATION_TYPE = "السكن مع الأهل";
    public static final String OTHER_ACCOMMODATION_TYPE = "غير ذلك";
    public static final String PERSONAL_PROPERTY_ACCOMMODATION_TYPE = "ملك";




    public void selectTypeOfAccommodation(String typeOfAccommodationVisibleText, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.selectDropdownOption(typeOfAccommodation, typeOfAccommodationVisibleText, null, -1, false, failureHandling, "Type of Accommodation DDL");

    }


    By residenceCityDDL = By.xpath("//select[@formcontrolname='residenceCity']");


    public void selectFromResidenceCityDDL(int index, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.selectDropdownOption(residenceCityDDL, null, null, index, false, failureHandling, "residenceCity Drop down list");

    }


    /// //////////////////////////////////////////////////////////


    // 3 contact Info [functions, attributes, and data]

    By telephoneNumberTextField = By.xpath("//input[@formcontrolname='phoneNumber']");
    By mobileNumberTextField = By.xpath("//input[@formcontrolname='mobileNumber']");
    By emailAddressTextField = By.xpath("//input[@formcontrolname='emailAddress']");




    public void insertTelephoneNumber(String telephoneNumber, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(telephoneNumberTextField, failureHandling, "Telephone Numnber TextField");
        SmartUIValidator.sendKeys(telephoneNumberTextField, failureHandling, "Telephone Number TextField", telephoneNumber);

    }




    public void insertMobileNumber(String mobileNumber, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(mobileNumberTextField, failureHandling, "Mobile Number TextField");
        SmartUIValidator.sendKeys(mobileNumberTextField, failureHandling, "Mobile Number TextField", mobileNumber);

    }




    public void insertEmailAddress(String emailAddress, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(emailAddressTextField, failureHandling, "Email Address TextField");
        SmartUIValidator.sendKeys(emailAddressTextField, failureHandling, "Email Address TextField", emailAddress);

    }


   // 4 bank details data
    // removed from the system
   // By ibanTextField = By.xpath("//input[@formcontrolname='ibanNumber']"); // READ ONLY
   // By bankNameDDL = By.xpath("//select[@formcontrolname='bankName']");
   // By haveSavingAccountDDL = By.xpath("//select[@formcontrolname='haveSavingAccount']");



/*
    public void insertIban(String iban, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(ibanTextField, failureHandling, "iban Text Field");
        SmartUIValidator.sendKeys(ibanTextField, failureHandling, "iban Text Field", iban);

    }
*/


/*
    public void selectBankName(int index, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.selectDropdownOption(bankNameDDL, null, null, index, false, failureHandling, "Bank Name DDL");
    }
*/


/*    public void selectHasSavingAccount(String yesNoSelection, FailureHandler.FailureHandling failureHandling) {

       if(yesNoSelection.equalsIgnoreCase("YES")){
           yesNoSelection = "نعم";
       }

        if(yesNoSelection.equalsIgnoreCase("NO")){
            yesNoSelection = "لا";
        }


        SmartUIValidator.selectDropdownOption(haveSavingAccountDDL, yesNoSelection, null, -1, false, failureHandling, "Have Saving Account Drop Down List");
    }*/






    public  void saveInformation(FailureHandler.FailureHandling failureHandling){
     clickOnSaveButton(failureHandling);
       clickOnYesConfirmationButton(failureHandling);
        //SmartUIValidator.refreshPage(failureHandling);
    }


/*    public  void saveInformationAfterUpdatingBankDetailsInformation(FailureHandler.FailureHandling failureHandling){
        clickOnSaveButton(failureHandling);
        clickOnYesConfirmationButton(failureHandling);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SmartUIValidator.refreshPage(failureHandling);
    }*/










}
