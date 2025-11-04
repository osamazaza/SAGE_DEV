package com.sage.base.validators.javascript;


import com.sage.base.TestBase;
import com.sage.base.validators.FailureHandler;
import com.sage.base.validators.SmartUIValidator;
import org.openqa.selenium.JavascriptExecutor;

public class CRMJavaScriptUtil extends TestBase {


    public void forceSaveForm() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("Xrm.Page.data.entity.save();");
    }


    public String getRecordGUID(FailureHandler.FailureHandling failureHandling) {
        return "";
    }


    public void setRequesterValue(String id, FailureHandler.FailureHandling failureHandling) {


        String setRequesterJavaScript =
                "Xrm.Page.getAttribute('ntw_requester').setValue(null);" +
                        "Xrm.Page.getAttribute('ntw_requester').setValue([{" +
                        "   id: '" + id + "'," +
                        "   entityType: 'contact'" +
                        "}]);";

        SmartUIValidator.executeJavaScript(setRequesterJavaScript, failureHandling, "set Requester Value");


    }


    public void unlockAllCRMFields(FailureHandler.FailureHandling failureHandling) {

        SmartUIValidator.addStep("Unlock All CRM Fields");



        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
// Unlock all fields
   String unlockFieldsScript =
           "var all = Xrm.Page.getAttribute();" +
           "for (var i in all) {" +
           "  var controls = all[i].controls.get();" +
           "  for (var j in controls) { controls[j].setDisabled(false); }" +
           "}";


        SmartUIValidator.executeJavaScript(unlockFieldsScript, failureHandling, "Unlock CRM fields");



    }

}
