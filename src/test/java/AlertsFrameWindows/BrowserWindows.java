package AlertsFrameWindows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BrowserWindows  {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    String url = "https://demoqa.com/browser-windows";


    @BeforeMethod
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void closeSession() throws IOException, InterruptedException {

        Thread.sleep(Duration.ofSeconds(1));
        driver.quit();
    }

    @Test(priority = 1)
    public void urlSet() {
        String expectedUrl = "https://demoqa.com/browser-windows";
        Assert.assertEquals(url, expectedUrl);
    }

    @Test(priority = 2)
    public void newTab() {
        WebElement newTab = driver.findElement(By.id("tabButton"));
        newTab.click();

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        System.out.println(tabs.size());

        driver.switchTo().window(tabs.get(1));
        System.out.println(driver.getCurrentUrl());

        WebElement sampleHeading = driver.findElement(By.id("sampleHeading"));
        String expectedText = "This is a sample page";
        Assert.assertEquals(sampleHeading.getText(), expectedText);
    }

    @Test(priority = 3)
    public void newWindow() {
        WebElement windowButton = driver.findElement(By.id("windowButton"));
        windowButton.click();

        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        System.out.println(windows.size());

        driver.switchTo().window(windows.get(1));

        WebElement windowButtonText = driver.findElement(By.id("sampleHeading"));
        System.out.println(windowButtonText.getText());
    }

    @Test(priority = 4)
    public void newWindowMessage() {
        WebElement messageWindowButton = driver.findElement(By.id("messageWindowButton"));
        messageWindowButton.click();

        List<String> mesWindows = new ArrayList<>(driver.getWindowHandles());
        Assert.assertEquals(mesWindows.size(), 2);

        driver.switchTo().window(mesWindows.get(1));
        driver.close();
    }
}
