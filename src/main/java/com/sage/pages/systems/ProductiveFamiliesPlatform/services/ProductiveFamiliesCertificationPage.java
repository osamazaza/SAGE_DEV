package com.sage.pages.systems.ProductiveFamiliesPlatform.services;


import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;

public class ProductiveFamiliesCertificationPage extends TestBase {





    By activityAddressFragmentButton =By.xpath("//a[contains(@class, 'tab-item') and contains(text(), 'عنوان النشاط')]");
    By  projectDataFragmentButton =By.xpath("//a[contains(@class, 'tab-item') and contains(text(), 'بيانات المشروع')]");
    By trainingTutorialsFragmentButton =By.xpath("//a[contains(@class, 'tab-item') and contains(text(), 'الدورات التدريبية')]");






    public void clickOnActivityAddressFragmentButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(activityAddressFragmentButton, failureHandling, "Activity Address Fragment button");

    }

    public void clickOnTrainingTutorialsFragmentButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(trainingTutorialsFragmentButton, failureHandling, "Training Tutorial Fragment button");
    }


    public void clickOnProjectDataFragmentButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(projectDataFragmentButton, failureHandling, "Project Data Fragment button");
    }



// عنوان النشاط




    // activity label:
    By acceptTermsButton = By.xpath("//input[@type='checkbox' and@formcontrolname='acceptTems']");

 // 4 drop down lists
    By activityAddressDDL = By.xpath("//select[@formcontrolname='selectedNationalAddress']"); // عنوان النشاط

    public static final String  ACTIVITY_ADDRESS_OTHERS = "أخرى";




    By regionDDL = By.xpath("//select[@formcontrolname='government']"); // المنطقة
    public static final String  REGION_OTHERS = "أخرى";

    By areaDDL = By.xpath("//select[@formcontrolname='area']"); // المحافظة المدينة

    public static final String JAHRA_REGION = "الجهراء";
    public static final String QARYAH_REGION = "قريه"; // This translates to "Village"
    public static final String SUDAIR_REGION = "سدير";
    public static final String SABT_ALALAYA_REGION = "سبت العلايه";
    public static final String SHUABAT_ALJANSIAH_REGION = "شعبة الجنسية"; // This translates to "Nationality Division"
    public static final String DHAHRAN_REGION = "الظهران";
    public static final String HAWAT_SUDAIR_REGION = "حوطة سدير";
    public static final String HADITHA_REGION = "الحديثه";
    public static final String DIRAH_REGION = "الدره";
    public static final String KING_FAHD_CAUSEWAY_REGION = "جسر الملك فهد"; // This translates to "King Fahd Causeway"
    public static final String SALWA_REGION = "سلوى";
    public static final String ADEED_REGION = "العديد";
    public static final String KHADRA_REGION = "الخضراء";
    public static final String ALB_REGION = "علي";
    public static final String BATHAA_REGION = "بطحاء";
    public static final String SAFWA_REGION = "صفوى";




    // ignore center for now as it can accept no value
    By centerDDL = By.xpath("//select[@formcontrolname='station']");// المركز




    // 7 text fields
    By cityAddressTextField = By.xpath("//input[@formcontrolname='cityAddress']");
    By districtTextFieldAR = By.xpath("//input[@formcontrolname='districtAr']");
    By districtTextFieldEN = By.xpath("//input[@formcontrolname='districtEn']");
    By streetTextField = By.xpath("//input[@formcontrolname='street']");
    By buildingNumberTextField = By.xpath("//input[@formcontrolname='buildingNumber']");
    By addtionalNumberTextField = By.xpath("//input[@formcontrolname='additionalNumber']");
    By unitNumberTextField = By.xpath("//input[@formcontrolname='unitNumber']");


    // 1 button
    By saveAndContinueButton = By.xpath("//button[@id='draftButton' and contains(normalize-space(text()), 'حفظ')]");
    By yesConfirmationPopupButton = By.xpath("(//button[normalize-space(text())='نعم' and contains(@class, 'btn-popup')])[1]");






    public void clickOnAcceptTerms(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(acceptTermsButton, failureHandling, "Save And Continue Button");

    }

    public void selectActivityAddress(int index, FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.selectDropdownOption(activityAddressDDL, null, null, index,false, failureHandling, "Activity Address DDL");

    }


    public String selectRegion(int index, FailureHandler.FailureHandling failureHandling) {

       String selectedRegion =  SmartUIValidator.selectDropdownOption(regionDDL, null, null, index, false, failureHandling, "Region DDL");
       return selectedRegion;
    }



    public String selectArea(int index, FailureHandler.FailureHandling failureHandling) {
        String selectedArea =  SmartUIValidator.selectDropdownOption(areaDDL, null, null, index, false, failureHandling, "Area DDL");
        return selectedArea;

    }



    public String selectCenter(int index, FailureHandler.FailureHandling failureHandling) {
        String selectedCenter =  SmartUIValidator.selectDropdownOption(centerDDL, null, null, index, false, failureHandling, "Center DDL");
        return selectedCenter;

    }





    public void insertCityAddress(String cityAddress, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(cityAddressTextField, failureHandling, "city Address text field");
        SmartUIValidator.sendKeys(cityAddressTextField, failureHandling, "city address text field", cityAddress);
    }

    public void insertDistrictAR(String districtAR, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(districtTextFieldAR, failureHandling, "district AR text field");
        SmartUIValidator.sendKeys(districtTextFieldAR, failureHandling, "district AR text field", districtAR);
    }

    public void insertDistrictEN(String districtEN, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(districtTextFieldEN, failureHandling, "district EN text field");
        SmartUIValidator.sendKeys(districtTextFieldEN, failureHandling, "district EN text field", districtEN);
    }

    public void insertStreet(String street, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(streetTextField, failureHandling, "street text field");
        SmartUIValidator.sendKeys(streetTextField, failureHandling, "street text field", street);
    }

    public void insertBuildingNumber(String buildingNumber, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(buildingNumberTextField, failureHandling, "building number text field");
        SmartUIValidator.sendKeys(buildingNumberTextField, failureHandling, "building number text field", buildingNumber);
    }

    public void insertAdditionalNumber(String additionalNumber, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(addtionalNumberTextField, failureHandling, "additional number text field");
        SmartUIValidator.sendKeys(addtionalNumberTextField, failureHandling, "additional number text field", additionalNumber);
    }

    public void insertUnitNumber(String unitNumber, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(unitNumberTextField, failureHandling, "unit number text field");
        SmartUIValidator.sendKeys(unitNumberTextField, failureHandling, "unit number text field", unitNumber);
    }


    public void clickOnSaveAndContinue(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(saveAndContinueButton, failureHandling, "Save And Continue Button");

    }


    /// /////////////////////////////////////////

    // بيانات المشروع attributea and functions

    //4 ddls
    By activityCategoryDDL = By.xpath("//select[@formcontrolname='activityCategory']"); // المنطقة
    By mainActivityDDL = By.xpath("//select[@formcontrolname='mainActivity']"); // المنطقة
    By subActivityDDL = By.xpath("//select[@formcontrolname='subActivity']"); // المنطقة
    By currentProjectLocationDDL = By.xpath("//select[@formcontrolname='currentProjectLocation']"); // المنطقة


    public String selectActivityCategory(int index, FailureHandler.FailureHandling failureHandling) {
       String activtyCategory =  SmartUIValidator.selectDropdownOption(activityCategoryDDL, null, null, index,false, failureHandling, "Activity Category DDL");
        return activtyCategory;
    }

    public String selectMainActivity( int index, FailureHandler.FailureHandling failureHandling) {
        return SmartUIValidator.selectDropdownOption(mainActivityDDL, null, null, index, false, failureHandling, "Main Activity DDL");
    }

    public String selectSubActivity(int index, FailureHandler.FailureHandling failureHandling) {
        return SmartUIValidator.selectDropdownOption(subActivityDDL, null, null, index, false, failureHandling, "Sub Activity DDL");
    }

    public String selectCurrentProjectLocation(int index, FailureHandler.FailureHandling failureHandling) {
        return SmartUIValidator.selectDropdownOption(currentProjectLocationDDL, null, null, index, false, failureHandling, "Current Project Location DDL");
    }


    By yearsOfExperienceTextField = By.xpath("//input[@formcontrolname='workExperience']");
    By emailAddressTextField = By.xpath("//input[@formcontrolname='emailAddress']");



    public void insertYearsOfExperience(String yearsOfExperience, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(yearsOfExperienceTextField, failureHandling, "Years of experience text field");
        SmartUIValidator.sendKeys(yearsOfExperienceTextField, failureHandling, "Years Of experience text field", yearsOfExperience);
    }


    public void insertEmailAddress(String emailAddress, FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.clearText(emailAddressTextField, failureHandling, "Email text field");
        SmartUIValidator.sendKeys(emailAddressTextField, failureHandling, "Email Text Field", emailAddress);
    }



    public void clickOnYesConfirmationButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(yesConfirmationPopupButton, failureHandling, "Yes Confirmation Button");

    }


    public  void saveInformation(FailureHandler.FailureHandling failureHandling){
        clickOnSaveAndContinue(failureHandling);
        clickOnYesConfirmationButton(failureHandling);
       // SmartUIValidator.refreshPage(failureHandling);
    }

/// ////////////////////////////////////////////////////////////////////////


    // الدورات التدريبية
By firstVideoStartWatchingButton = By.xpath("(//button[normalize-space(text())='بدء المشاهدة'])[1]");
By firstVideoPlayButton = By.xpath("(//button[@id='play-pause'])[1]");
By firstVideoCloseButton = By.xpath("(//a[@class='close' and normalize-space(text())='×'])[1]");
By firstVideoCompletedStatus = By.xpath("(//h3[normalize-space(text())='مكتمل'])[1]");


By secondVideoStartWatchingButton = By.xpath("(//button[normalize-space(text())='بدء المشاهدة'])[1]");
By secondVideoPlayButton = By.xpath("(//button[@id='play-pause'])[2]");
By secondVideoCloseButton = By.xpath("(//a[@class='close' and normalize-space(text())='×'])[2]");
By secondVideoCompletedStatus = By.xpath("(//h3[normalize-space(text())='مكتمل'])[2]");




By sendButton = By.xpath("//button[normalize-space(text())='إرسال' and contains(@class, 'btn-style-mobile')]");
By navigateBackToMainPage =  By.xpath("//span[contains(@class, 'fnt-14') and contains(text(), 'الرئيسية')]");




public void watchFirstThreeVideos(FailureHandler.FailureHandling failureHandling){
/*    SmartUIValidator.executeJavaScript(
            "document.querySelectorAll('video').forEach((v,i) => { if(i < 3){ v.playbackRate = 4.0; v.currentTime = v.duration; v.dispatchEvent(new Event('ended')); } });",
            failureHandling,
            "Fast-forward first three videos"
    );


    SmartUIValidator.executeJavaScript(
            "document.querySelectorAll('video').forEach((v,i) => {" +
                    "  if(i < 3) {" +
                    "    v.playbackRate = 4.0;" +
                    "    v.onloadedmetadata = () => {" +
                    "      v.currentTime = v.duration - 0.1;" +
                    "      v.dispatchEvent(new Event('ended'));" +
                    "    };" +
                    "    if(v.readyState >= 1) {" +
                    "      v.currentTime = v.duration - 0.1;" +
                    "      v.dispatchEvent(new Event('ended'));" +
                    "    }" +
                    "  }" +
                    "});",
            failureHandling,
            "Fast-forward first three videos"
    );

 */
    try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    SmartUIValidator.executeJavaScript(
            "document.querySelectorAll('video').forEach((v,i) => {" +
                    " if (i < 3) {" +
                    "  v.playbackRate = 4.0;" +
                    "  const skipToEnd = () => {" +
                    "   if (v.duration > 0) {" +
                    "    v.currentTime = v.duration - 0.1;" +
                    "    v.dispatchEvent(new Event('ended'));" +
                    "   }" +
                    "  };" +
                    "  v.onloadedmetadata = skipToEnd;" +
                    "  if (v.readyState >= 1) {" +
                    "   skipToEnd();" +
                    "  }" +
                    " }" +
                    "});",
            failureHandling,
            "Fast-forward first three videos (FIXED)"
    );

     //SmartUIValidator.click(firstVideoStartWatchingButton,failureHandling, "first Video Start Watching Button" );
    //SmartUIValidator.click(firstVideoPlayButton,failureHandling, "first Video Play Button" );
    //SmartUIValidator.click(firstVideoCloseButton,failureHandling, "first Video Close Button" );





    /*
    SmartUIValidator.click(firstVideoStartWatchingButton,failureHandling, "first Video Start Watching Button" );
 SmartUIValidator.click(firstVideoPlayButton,failureHandling, "first Video Play Button" );
    try {
        Thread.sleep(9000 * 4);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    SmartUIValidator.click(firstVideoCloseButton,failureHandling, "first Video Close Button" );
    SmartUIValidator.isDisplayed(firstVideoCompletedStatus, failureHandling, "First Video Completed Status" );




    SmartUIValidator.click(secondVideoStartWatchingButton,failureHandling, "second Video Start Watching Button" );
    SmartUIValidator.click(secondVideoPlayButton,failureHandling, "second Video Play Button" );
    try {
        Thread.sleep(9000 * 4);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    SmartUIValidator.click(secondVideoCloseButton,failureHandling, "second Video Close Button" );
    SmartUIValidator.isDisplayed(secondVideoCompletedStatus, failureHandling, "Second Video Completed Status" );
*/


}

//sendButton
public void clickOnNavigateBackToMainPage(FailureHandler.FailureHandling failureHandling) {
    SmartUIValidator.click(navigateBackToMainPage, failureHandling, "Navigate Back Button");
}


    //sendButton
    public void clickOnSendButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.actionAndWaitForViewCondition(

                () ->  getDriver().navigate().refresh(),
                () -> getDriver().findElement(sendButton).isEnabled(),
                failureHandling,
                "Send Button",
                "Refresh Page",
                "Send Button is Enabled",
                3,
                15
        );
        SmartUIValidator.click(sendButton, failureHandling, "Send Button");
    }



By navigateToHomePagePopupButton = By.xpath("(//a[normalize-space(text())='الصفحة الرئيسية' and @href='/home'])[2]");


    public void clickOnNavigateToHomePagePopupButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(navigateToHomePagePopupButton, failureHandling, "Home Page Button");
    }
















    //
    //
    //





}
