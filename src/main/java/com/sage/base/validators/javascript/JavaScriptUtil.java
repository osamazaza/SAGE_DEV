package com.sage.base.validators.javascript;


import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;

import org.openqa.selenium.JavascriptExecutor;

public class JavaScriptUtil extends TestBase {



public void speedVideo(FailureHandler.FailureHandling failureHandling){
    SmartUIValidator.executeJavaScript("document.querySelectorAll('video').forEach(v => v.playbackRate = 8.0);", failureHandling, "Speed Video");

}



}
