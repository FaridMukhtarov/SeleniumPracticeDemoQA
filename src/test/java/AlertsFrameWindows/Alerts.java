package AlertsFrameWindows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Alerts {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions action;

    @BeforeClass
    public void startSession (){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/alerts");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void closeSession () throws InterruptedException, IOException {
        Thread.sleep(1000);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C://Screenshot//Alerts.png"));

        driver.quit();
    }

    @Test (priority = 2, groups = {"Smoke"})
    public void clickAlertButton (){
        WebElement alertButton = driver.findElement(By.id("alertButton"));
        alertButton.click();
        String textAlertActual = driver.switchTo().alert().getText();
        String textAlertExpected = "You clicked a button";
        Assert.assertEquals(textAlertActual, textAlertExpected);
        driver.switchTo().alert().accept();
    }

    @Test (priority = 1)
    public void alertAppearAfter_5  (){
        WebElement timerAlertButton = driver.findElement(By.id("timerAlertButton"));
        timerAlertButton.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        String textAlerts2 = driver.switchTo().alert().getText();
        Assert.assertTrue(textAlerts2.equals("This alert appeared after 5 seconds"));
        driver.switchTo().alert().accept();
    }

    @Test (priority = 3)
    public void alertConfirm (){
        WebElement confirmButton = driver.findElement(By.id("confirmButton"));
        confirmButton.click();

        String textAlert3 = driver.switchTo().alert().getText();
        if (textAlert3.startsWith("Do you ")) {
            driver.switchTo().alert().accept();
            System.out.println("Test Passed");
        }
    }

    @Test (priority = 4)
    public void alertInput (){
        WebElement promtButton = driver.findElement(By.id("promtButton"));
        promtButton.click();

        driver.switchTo().alert().sendKeys("Farid");
        driver.switchTo().alert().accept();
        System.out.println(driver.findElement(By.id("promptResult")).getText());
    }
}
