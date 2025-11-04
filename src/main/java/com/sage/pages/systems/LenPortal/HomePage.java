package com.sage.pages.systems.LenPortal;

import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;

public class HomePage extends TestBase {

//1088401540

By loadingDataView = By.xpath("(//*[text()='تحميل...' or contains(@class, 'loading-text')])[1]");
By homePageMenuItem = By.xpath("//a[@routerlink='/home']");

By userProfileView = By.xpath("//*[@id='kt_quick_user_toggle']");
By userProfileMenuItem = By.xpath("(//a[ @href='/web-17871/userProfile' or .//div[normalize-space(text())='الملف الشخصي'] ])[1]");




public void waitUntilLoadingViewDisappear(FailureHandler.FailureHandling failureHandling){
    SmartUIValidator.isNotVisibleOrAbsent(loadingDataView, failureHandling, "Loading Data View");
}


public boolean validateUserisInsideHomePage(FailureHandler.FailureHandling failureHandling){

  return SmartUIValidator.isDisplayed(homePageMenuItem, failureHandling, "homePage MenuItem");

 }


 public void  navigateToUserProfilePage(FailureHandler.FailureHandling failureHandling){
SmartUIValidator.click(userProfileView, failureHandling, "User Profile View");
     SmartUIValidator.click(userProfileMenuItem, failureHandling, "User Profile Menu Item");
 }








}
