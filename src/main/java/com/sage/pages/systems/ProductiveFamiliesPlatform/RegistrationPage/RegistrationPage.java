package com.sage.pages.systems.ProductiveFamiliesPlatform.RegistrationPage;


import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;

public class RegistrationPage extends TestBase {


    String registerationPageURL = "https://tstproductivefamilyportal.sdb.gov.sa/register";

    //13 text fields
    By userNameTextField = By.xpath("//input[@formcontrolname='UserName']");
    By passwordTextField = By.xpath("//input[@formcontrolname='Password']");
    By passwordConfirmationTextField = By.xpath("//input[@formcontrolname='confirmPassword']");


    By firstNameTextFieldAr = By.xpath("//input[@formcontrolname='arabic_first_name']");
    By fatherNameTextFieldAr = By.xpath("//input[@formcontrolname='arabic_father_name']");
    By grandFatherTextFieldAr = By.xpath("//input[@formcontrolname='arabic_grand_father_name']");
    By lastNameTextFieldAr = By.xpath("//input[@formcontrolname='arabic_family_name']");


    By firstNameTextFieldEn = By.xpath("//input[@formcontrolname='english_first_name']");
    By fatherNameTextFieldEn = By.xpath("//input[@formcontrolname='english_father_name']");
    By grandFatherTextFieldEn = By.xpath("//input[@formcontrolname='english_grand_father_name']");
    By lastNameTextFieldEn = By.xpath("//input[@formcontrolname='english_family_name']");


    By idNumberTextField = By.xpath("//input[@formcontrolname='national_id']");
    By birthDateTextField = By.xpath("//input[@formcontrolname='dob']");


    // 2 drop down list (select) Done
    By idTypeDDL = By.xpath("//select[@formcontrolname='nationalIDType']");
    By genderTypeDDL = By.xpath("//select[@formcontrolname='gender']");
    By nationalityTypeDDL = By.xpath("//select[@formcontrolname='nationality']");





    // 2 buttons Done

    By createNewAccountButton = By.xpath("//button[normalize-space(text())='إنشاء حساب جديد']");
    By cancelButton = By.xpath("//button[normalize-space(text())='إلغاء']");

    // 1 label
    By accountRegisteredLabel = By.xpath("(//p[normalize-space(text())='تم التسجيل بنجاح'])[1]");





    public void navigateToCreateNewAccountRegistrationPage(FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.navigateToUrl(registerationPageURL, failureHandling);
    }

    public void insertUserName(String userNameData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(userNameTextField, failureHandling, "UserName TextField", 10);
        SmartUIValidator.sendKeys(userNameTextField, failureHandling, "UserName TextField", userNameData, 10);

    }


    public void insertPassword(String passwordData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(passwordTextField, failureHandling, "password text field");
        SmartUIValidator.sendKeys(passwordTextField, failureHandling, "password text field", passwordData);

    }

    public void insertConfirmationPassword(String passwordData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(passwordConfirmationTextField, failureHandling, "password Confirmation text field");
        SmartUIValidator.sendKeys(passwordConfirmationTextField, failureHandling, "password Confirmation text field", passwordData);

    }


    public void insertFirstNameAr(String firstNameData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(firstNameTextFieldAr, failureHandling, "Arabic firstName TextField");
        SmartUIValidator.sendKeys(firstNameTextFieldAr, failureHandling, "Arabic firstName  TextField", firstNameData);
    }


    public void insertFatherNameAr(String fatherNameData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(fatherNameTextFieldAr, failureHandling, "Arabic fatherName TextField");
        SmartUIValidator.sendKeys(fatherNameTextFieldAr, failureHandling, "Arabic fatherName  TextField", fatherNameData);

    }


    public void insertGrandFatherNameAr(String grandFatherData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(grandFatherTextFieldAr, failureHandling, "Arabic Grand Father TextField");
        SmartUIValidator.sendKeys(grandFatherTextFieldAr, failureHandling, "Arabic Grand Father  TextField", grandFatherData);

    }


    public void insertLastNameAr(String lastNameData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(lastNameTextFieldAr, failureHandling, "Arabic lastName TextField");
        SmartUIValidator.sendKeys(lastNameTextFieldAr, failureHandling, "Arabic lastName  TextField", lastNameData);

    }



    // english fields

    public void insertFirstNameEn(String firstNameData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(firstNameTextFieldEn, failureHandling, "English firstName TextField");
        SmartUIValidator.sendKeys(firstNameTextFieldEn, failureHandling, "English firstName  TextField", firstNameData);
    }


    public void insertFatherNameEn(String fatherNameData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(fatherNameTextFieldEn, failureHandling, "English fatherName TextField");
        SmartUIValidator.sendKeys(fatherNameTextFieldEn, failureHandling, "English fatherName  TextField", fatherNameData);

    }


    public void insertGrandFatherNameEn(String grandFatherData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(grandFatherTextFieldEn, failureHandling, "English Grand Father TextField");
        SmartUIValidator.sendKeys(grandFatherTextFieldEn, failureHandling, "English Grand Father  TextField", grandFatherData);

    }


    public void insertLastNameEn(String lastNameData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(lastNameTextFieldEn, failureHandling, "English lastName TextField");
        SmartUIValidator.sendKeys(lastNameTextFieldEn, failureHandling, "English lastName  TextField", lastNameData);

    }


    public void insertIdNumber(String idNumberData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(idNumberTextField, failureHandling, "ID Number TextField");
        SmartUIValidator.sendKeys(idNumberTextField, failureHandling, "ID Number TextField", idNumberData);

    }


    public void insertBirthDate(String birthDateData, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.clearText(birthDateTextField, failureHandling, "Birth Date TextField");
        SmartUIValidator.sendKeys(birthDateTextField, failureHandling, "Birth Date TextField", birthDateData);

    }



    public void selectIdType(String idDataValue, FailureHandler.FailureHandling failureHandling) {

        if (idDataValue.trim().equalsIgnoreCase("هوية")) {

            idDataValue = "هوية";
        } else if (idDataValue.trim().equalsIgnoreCase("إقامة")) {

            idDataValue = "إقامة";
        }


        SmartUIValidator.selectDropdownOption(idTypeDDL, null, idDataValue, -1, false, FailureHandler.FailureHandling.STOP_ON_FAILURE, "ID Type Drop Down List");

    }




    public void selectNationalityType(String nationalityTypeVisibleText, FailureHandler.FailureHandling failureHandling) {

        if (nationalityTypeVisibleText.trim().equalsIgnoreCase("سعودي")) {

            nationalityTypeVisibleText = " سعودي";
        } else if (nationalityTypeVisibleText.trim().equalsIgnoreCase("غير سعودي")) {

            nationalityTypeVisibleText = " غير سعودي ";
        }


        SmartUIValidator.selectDropdownOption(nationalityTypeDDL, nationalityTypeVisibleText, null, -1, false, failureHandling, "Nationality Type Drop Down List");

    }


    public void selectGenderType(String genderDataValue, FailureHandler.FailureHandling failureHandling) {

        if (genderDataValue.equalsIgnoreCase("male")) {

            genderDataValue = "Male";
        } else if (genderDataValue.equalsIgnoreCase("female")) {

            genderDataValue = "Female";
        }


        SmartUIValidator.selectDropdownOption(genderTypeDDL, null, genderDataValue, -1, false,  failureHandling, "Gender Type Drop Down List");

    }



    public void clickOnCreateNewAccount(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(createNewAccountButton, failureHandling, "Create New Account Button");

    }

    public void clickOnCancelNewAccount(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(cancelButton, failureHandling, "Cancel New Account Button");

    }




    public void validateAccountRegistered(FailureHandler.FailureHandling failureHandling){
        SmartUIValidator.isDisplayed(accountRegisteredLabel, failureHandling, "Account Registered label");
    }



}












