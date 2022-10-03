import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestWorkshop {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    @Before
    public void beforeTest() throws MalformedURLException {
       // WebDriverManager.chromedriver().setup();
       // webDriver = new ChromeDriver();

       // webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(15));

        DesiredCapabilities desiredCapabilities =  new DesiredCapabilities();
        desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME,"firefox");
        desiredCapabilities.setCapability("name","testViewProjectOfIphone14Pro");
        webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"),desiredCapabilities);

        webDriverWait = new WebDriverWait(webDriver,Duration.ofSeconds(30));

    }

    @After
    public void afterTest(){
        webDriver.quit();
    }

    @Test
    public void Test1() throws InterruptedException {
        // Create Chrome
        //WebDriverManager.chromedriver().setup();
       // WebDriver webDriver = new ChromeDriver();

        // Wait
        //webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(15));

        // Go to Apple Website
        webDriver.get("https://www.apple.com/");

        // Assign locator
        By iphoneLinkLocator = By.className("ac-gn-link-iphone");
        By iphone14ProLinkLocator = By.className("chapternav-item-iphone-14-pro");
        By iphone14ProOrderButtonLocator = By.className("ac-ln-action-button");

        // Wait iphoneLink to present
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(iphoneLinkLocator));

        // Find iphoneLink
        WebElement iphoneLink = webDriver.findElement(iphoneLinkLocator);

        // Click iphoneLink
        iphoneLink.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(iphone14ProLinkLocator));

        // Find iphone14ProLink
        WebElement iphone14ProLink = webDriver.findElement(iphone14ProLinkLocator);

        // Click iphone14ProLink
        iphone14ProLink.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(iphone14ProOrderButtonLocator));

        // Find iphone14ProOrderButton
        WebElement iphone14ProOrderButton = webDriver.findElement(iphone14ProOrderButtonLocator);

        // Click iphone14ProOrderButton
        iphone14ProOrderButton.click();


        // Sleep 5 sec
        Thread.sleep(5000);

        // Quit Chrome
        //webDriver.quit();
    }
    @Test
    public void Test2() throws InterruptedException {

        // Go to Apple Website
        webDriver.get("https://www.apple.com/");

        // Assign locator
        By SearchButtonLocator = By.id("ac-gn-link-search");
        By SearchInputLocator = By.id("ac-gn-searchform-input");

        // Wait iphoneLink to present
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(SearchButtonLocator));

        // Find iphoneLink
        WebElement searchButton = webDriver.findElement(SearchButtonLocator);

        // Click iphoneLink
        searchButton.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(SearchInputLocator));

        // Find iphone14ProLink
        WebElement searchInput = webDriver.findElement(SearchInputLocator);

        // Click iphone14ProLink
        searchInput.sendKeys("iphone 14 Pro Max");

        Thread.sleep(5000);

        searchInput.sendKeys(Keys.ENTER);

        //webDriverWait.until(ExpectedConditions.presenceOfElementLocated(iphone14ProOrderButtonLocator));

        // Find iphone14ProOrderButton
       // WebElement iphone14ProOrderButton = webDriver.findElement(iphone14ProOrderButtonLocator);

        // Click iphone14ProOrderButton
        //iphone14ProOrderButton.click();


        // Sleep 5 sec
        //Thread.sleep(5000);

    }
}
