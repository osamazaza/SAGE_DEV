package com.sage.pages.systems.CrmDynamics;


import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;

import java.awt.Robot;
import java.awt.event.KeyEvent;
public class CRMAppsPage extends TestBase {

    public static final String CRM_APPS_LINK = "https://tstcrmsit.sdb.gov.sa/SITSDBCRM/apps";
    public static final String CRM_APPS_HOST = "tstcrmsit.sdb.gov.sa";


   By crmAppsIframe = By.xpath("//iframe[@id='contentIFrame0' and @name='contentIFrame0' and @title='Content Area']");
    By FinanceBrokersManagementSystemButton = By.xpath("//div[@data-type='app-title' and @title='نظام تسجيل وإدارة القروض' and normalize-space(text())='نظام تسجيل وإدارة القروض']");

    private static void typeString(Robot robot, String text) {
        for (char c : text.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);

            // Handle uppercase and special characters requiring Shift
            boolean useShift = Character.isUpperCase(c) || isShiftNeededForChar(c);

            if (useShift) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }

            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);

            if (useShift) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }

            robot.delay(50); // Small delay between key presses
        }
    }

    /**
     * Helper to determine if the Shift key is needed for a common non-alphanumeric character.
     * This is a simplified check and may not cover all keyboards/characters.
     */
    private static boolean isShiftNeededForChar(char c) {
        return c == '!' || c == '@' || c == '#' || c == '$' ||
                c == '%' || c == '^' || c == '&' || c == '*' ||
                c == '(' || c == ')';
    }
public void navigateToCRMApps(String userName, String password, FailureHandler.FailureHandling failureHandling){

    try {






    SmartUIValidator.navigateWithBasicAuth(CRM_APPS_LINK, "ozaza.c", "Neoleap@19711");

    // SmartUIValidator.navigateToUrlWithCredentials(CRM_APPS_LINK, failureHandling, userName, password,CRM_APPS_HOST );
      SmartUIValidator.switchToIframe(crmAppsIframe, failureHandling, "CRM Apps Iframe");


    } catch (Exception e) {
        e.printStackTrace();
    }
}














    public void clickOnFinanceBrokersManagementSystemButton(FailureHandler.FailureHandling failureHandling) {
        SmartUIValidator.click(FinanceBrokersManagementSystemButton, failureHandling, "FinanceBrokersManagementSystem Button ");

    }

}
