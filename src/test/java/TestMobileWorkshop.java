import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;

public class TestMobileWorkshop {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    @Before
    public void beforeTest() throws MalformedURLException {
        // Set common desired capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2"); //android automation
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "false");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "600000"); //milliseconds
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/roychow/downloads/hi-uat.apk");

        // Set android desired capabilities
        capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, "3456"); //must not in use

        // Launch Mobile App

        webDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }


    @Test
    public void Test1() throws InterruptedException, IOException {

        AndroidDriver androidDriver = (AndroidDriver) webDriver;

        androidDriver.startRecordingScreen(new AndroidStartScreenRecordingOptions().withTimeLimit(Duration.ofSeconds(600))
                .enableForcedRestart());

        By loginTextLocator = AppiumBy.androidUIAutomator("text(\"Login\")");


        //By phoneNumberInputLocator = By.id("edittext_phone");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(loginTextLocator));

       // WebElement phoneNumberInput = webDriver.findElement(loginTextLocator);

       // phoneNumberInput.sendKeys("120293819");

        Thread.sleep(10000);

        // Stop record video
        String video = androidDriver.stopRecordingScreen();

        byte[] decode = Base64.getDecoder().decode(video);
        File videoFile = new File("text.mp4");
        FileUtils.writeByteArrayToFile(videoFile,decode);

    }
}
