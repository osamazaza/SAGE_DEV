package com.sage.base.Runners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import org.openqa.selenium.devtools.v139.network.Network;
import org.openqa.selenium.devtools.v139.network.model.Headers;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SeleniumDebugRunner {


    public static void main(String[] args) {
        System.out.println("🚀 Quick Selenium Test Started...");

        // ====== 1. Setup WebDriver ======
        //WebDriver driver = new ChromeDriver();

        // Maximize and set timeout
        //driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {

ChromeDriver ch = new ChromeDriver();
            EdgeDriver driver2 = new EdgeDriver();

            // 2. Start DevTools session
            DevTools devTools = driver2.getDevTools();
            devTools.createSession();

            // 3. Enable Network tracking
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));

            // 4. Create Authorization header (Basic base64(user:pass))
            String username = "ozaza.c";
            String password = "Neoleap@1971";
            String basicAuth = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

            Map<String, Object> headersMap = new HashMap<>();
            headersMap.put("Authorization", "Basic " + basicAuth);

            Headers headers = new Headers(headersMap);

            // 5. Inject headers using DevTools
            devTools.send(Network.setExtraHTTPHeaders(headers));

            Thread.sleep(6000);
            // 6. Navigate to the page requiring auth
            driver2.get("http://tstcrmsit.sdb.gov.sa/SITSDBCRM/apps");

            driver2.navigate().refresh();
            driver2.navigate().refresh();


            // 7. (Optional) Print page source or validate
            System.out.println(driver2.getPageSource());

            // 8. Cleanup
            //driver.quit();
            // ====== 2. Navigate to URL ======
           // driver.get("https://www.google.co.uk/");

            // ====== 3. Useful Operations (customize here) ======
            System.out.println("✅ Page Title: " + driver2.getTitle());




        } catch (Exception e) {
            System.err.println("❌ Exception occurred:");
            e.printStackTrace();
        } finally {
            // ====== 4. Clean up ======
            //driver.quit();
            System.out.println("🔚 Test Completed and Browser Closed.");
        }
    }



}
