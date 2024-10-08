/* Appium 1.x */
/* import io.appium.java_client.AppiumDriver; */
/* Appium 2.x */
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.util.Set;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class iOSApp {
    /* Retrieve keys from https://accounts.lambdatest.com/security */
    String userName = System.getenv("LT_USERNAME") == null ?
            "LT_USERNAME" : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ?
            "LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");
    /* Only used for app automation */
    /* String app_id = System.getenv("LT_APP_ID") == null ?
            "lt://proverbial-ios" : System.getenv("LT_APP_ID");
     */
    String grid_url = System.getenv("LT_GRID_URL") == null ?
            "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");

    private IOSDriver driver;
    public static String status = "passed";

    @BeforeClass
    @Parameters({"device", "version", "platform"})
    public void setUp(String device, String version, String platform) throws MalformedURLException
    {
        /* Appium 1.x*/
        /*
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "Java TestNG");
        capabilities.setCapability("name", "iOS" + " " + "iPhone 13" + " " + "15");
        capabilities.setCapability("deviceName", "iPhone 13");
        capabilities.setCapability("platformVersion", "15");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("network", false);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("devicelog", true);
        capabilities.setCapability("browserName", "Safari");
        */

        /* Appium 2.x */
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("build", "[Build] Pop-Up Handling in iOS Web App");
        ltOptions.put("name", "[Name] Pop-Up Handling in iOS Web App");
        ltOptions.put("w3c", true);
        /* ltOptions.put("platformName", "iOS"); */
        /* ltOptions.put("deviceName", "iPhone 13"); */
        /* ltOptions.put("platformVersion", "15"); */
        ltOptions.put("platformName", platform);
        ltOptions.put("deviceName", device);
        ltOptions.put("platformVersion", version);
        ltOptions.put("isRealMobile", true);
        /* Device from a DC in India */
        ltOptions.put("geoLocation", "IN");
        ltOptions.put("network", false);
        ltOptions.put("visual", true);
        ltOptions.put("devicelog", true);
        ltOptions.put("deviceOrientation", "portrait");
        ltOptions.put("devicelog", true);
        ltOptions.put("autoAcceptAlerts", true);
        /* Browser Combination */
        ltOptions.put("browserName", "Safari");
        ltOptions.put("browserVersion", "latest");

        capabilities.setCapability("lt:options", ltOptions);

        String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";
        /* Appium 1.x*/
        /* driver = new AppiumDriver(new URL(hub), capabilities); */
        driver = new IOSDriver(new URL(hub), capabilities);
    }

    @Test
    public void testTheInternetHerokuApp() throws InterruptedException {
        // Navigate to the website
        driver.get("https://the-internet.herokuapp.com/geolocation");
        /* Can be made dynamic, blocking wait is not a good practice */
        Thread.sleep(3000);

        try
        {
            /* Validate page title */
            String pageTitle = driver.getTitle();
            System.out.println("Page Title: " + pageTitle);
            Assert.assertTrue(pageTitle.contains("The Internet"));

            /* Click on the "Where Am I?" button */
            WebElement whereAmIButton = (WebElement) driver.findElement(By.cssSelector("button"));
            whereAmIButton.click();
            /* Can be made dynamic, blocking wait is not a good practice */
            Thread.sleep(3000);

            /* Get all contexts */
            Set<String> contextNames = driver.getContextHandles();
            for (String contextName : contextNames)
            {
                System.out.println("Available context: " + contextName);
            }

            /* Print the current context */
            String initialContext = driver.getContext();
            System.out.println("Initial context: " + initialContext);
            /* Can be made dynamic, blocking wait is not a good practice */
            Thread.sleep(3000);

            /* Switch to native app context (index 0 is native) */
            driver.context((String) contextNames.toArray()[0]);
            String nativeContext = driver.getContext();
            System.out.println("Switched to context: " + nativeContext);
            /* Can be made dynamic, blocking wait is not a good practice */
            Thread.sleep(3000);

            /* Click on "Allow Once" button */
            driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Allow Once']")).click();
            /* Can be made dynamic, blocking wait is not a good practice */
            Thread.sleep(3000);

            /* Switch back to WebView context (index 1 is WebView) */
            driver.context((String) contextNames.toArray()[1]);
            String webviewContext = driver.getContext();
            System.out.println("Switched back to context: " + webviewContext);

            /* Assert the context switch */
            Assert.assertTrue(webviewContext.contains("WEBVIEW"),
                    "Context did not switch back to WebView!");
            System.out.println("Successfully retrieved the latitude & longitude");
        }
        catch (Exception e)
        {
            status = "failed";
            driver.executeScript("lambda-status=failed");
            driver.quit();
        }
   }

    @AfterClass
    public void tearDown()
    {
        if (driver != null)
        {
            /* Update Status on the LambdaTest dashboard */
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}