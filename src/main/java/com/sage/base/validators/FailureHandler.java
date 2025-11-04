package com.sage.base.validators;

import java.util.HashMap;
import java.util.Map;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class FailureHandler {

    public enum FailureHandling {
    	STOP_ON_FAILURE,
        CONTINUE_ON_FAILURE,
        OPTIONAL
    }
    
    
    public static Map<Integer, SoftAssert> softAssertMap = new HashMap<Integer, SoftAssert>();

    
       



    
    //public static final ThreadLocal<SoftAssert> softAssert = ThreadLocal.withInitial(SoftAssert::new);

    
    
    
    
    



}
