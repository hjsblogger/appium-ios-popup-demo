import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

public class AndroidApp {

//    String userName = System.getenv("LT_USERNAME") == null ? "LT_USERNAME" : System.getenv("LT_USERNAME");
//    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");
    String userName="belalahmad";
    String accessKey="cousQqH3syuMR3H55LiQfG4QqCyPHRsZs3XJ3mbEle94hOdYLj";
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");

    private AndroidDriver driver;
    public static String status = "passed";

    @BeforeClass
    @Parameters({"device", "version", "platform"})
    public void setUp(String device, String version, String platform) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("build", "[Build] Pop-Up Handling in Android Web App");
        ltOptions.put("name", "[Name] Pop-Up Handling in Android Web App");
        ltOptions.put("w3c", true);
        ltOptions.put("platformName", platform);
        ltOptions.put("deviceName", device);
        ltOptions.put("platformVersion", version);
        ltOptions.put("isRealMobile", true);
        ltOptions.put("geoLocation", "IN");
        ltOptions.put("network", false);
        ltOptions.put("visual", true);
        ltOptions.put("devicelog", true);
        ltOptions.put("browserName", "Chrome");
        ltOptions.put("browserVersion", "latest");

        capabilities.setCapability("lt:options", ltOptions);

        String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";
        driver = new AndroidDriver(new URL(hub), capabilities);
    }

    @Test
    public void testTheInternetHerokuApp() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/geolocation");
        Thread.sleep(3000);

        try {
            String pageTitle = driver.getTitle();
            System.out.println("Page Title: " + pageTitle);
            Assert.assertTrue(pageTitle.contains("The Internet"));

            WebElement whereAmIButton = driver.findElement(By.cssSelector("button"));
            whereAmIButton.click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id='content']/div/button")).click();
            Thread.sleep(5000);
            // To accept/block the popup, you need to switch the context to “NATIVE_APP“ and click on the Allow/Block button.
            driver.context("NATIVE_APP");
            driver.findElement(By.xpath(".//android.widget.Button[@text='Allow']")).click();
            Thread.sleep(5000);
            System.out.println("Successfully retrieved the latitude & longitude");

        } catch (Exception e) {
            status = "failed";
            driver.executeScript("lambda-status=failed");
            driver.quit();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
