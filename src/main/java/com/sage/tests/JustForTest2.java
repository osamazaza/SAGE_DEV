package com.sage.tests;

import com.sage.base.TestBase;
import com.sage.base.*;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class JustForTest2 extends TestBase {


    @Test(priority = 1)
public void x3(){



        try {

            SmartUIValidator.verifyEqual(3,4, FailureHandler.FailureHandling.STOP_ON_FAILURE);

            //SmartUIValidator.navigateToUrl("https://tstcrmsit.sdb.gov.sa/SITSDBCRM/main.aspx", FailureHandler.FailureHandling.STOP_ON_FAILURE);

            //SmartUIValidator.navigateToUrlWithCredentials("https://tstcrmsit.sdb.gov.sa/SITSDBCRM/main.aspx", FailureHandler.FailureHandling.STOP_ON_FAILURE,"planningsit1",  "UatGhadeer@Crm30","tstcrmsit.sdb.gov.sa" );






        }

        finally {
            SmartUIValidator.assertAll();

        }


    }



    @Test(priority = 2)
    public void x4(){


        try {

            By locator = By.xpath("//button");
            SmartUIValidator.click(locator, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE,"Button");

            SmartUIValidator.verifyEqual(4,4, FailureHandler.FailureHandling.STOP_ON_FAILURE);






        }

        finally {
            SmartUIValidator.assertAll();

        }


    }





    @Test(priority = 3)
    public void x5(){


        try {

            By locator = By.xpath("//button");
            SmartUIValidator.click(locator, FailureHandler.FailureHandling.CONTINUE_ON_FAILURE,"Button");



            SmartUIValidator.verifyEqual(4,1, FailureHandler.FailureHandling.STOP_ON_FAILURE);






        }

        finally {
            SmartUIValidator.assertAll();

        }


    }






}
