package Elements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Buttons {
    WebDriverWait wait;
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/buttons");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void closeSession () throws InterruptedException, IOException {
        Thread.sleep(1000);
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("C://Screenshot//buttons.png"));
        driver.quit();
    }

    @Test (priority = 1)
    public void  pageVisibly (){
        String actualUrl = driver.getCurrentUrl();
        String url = "https://demoqa.com/buttons";
        Assert.assertEquals(actualUrl, url);
    }

    @Test (priority = 2)
    public void doubleClick (){
        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
        Actions action = new Actions(driver);
        action.doubleClick(doubleClickBtn).perform();

        WebElement doubleClickMessage = driver.findElement(By.id("doubleClickMessage"));
        boolean isDisplayed = doubleClickMessage.isDisplayed();
        if (isDisplayed){
            System.out.println(doubleClickMessage.getText());
        }
    }

    @Test (priority = 3)
    public void rightClickBtn(){
        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
        Actions action = new Actions(driver);
        action.contextClick(rightClickBtn).perform();

        String expected = "You have done a right click";
        String actual = driver.findElement(By.id("rightClickMessage")).getText();
        Assert.assertEquals(actual,expected);
    }

    @Test (priority = 4)
    public void dynamicClick (){
        WebElement dynamicClickButton = driver.findElement(By.xpath("//button[text()='Click Me']"));
        dynamicClickButton.click();

        WebElement dynamicClickMessage = driver.findElement(By.id("dynamicClickMessage"));
        if (dynamicClickMessage.isDisplayed()){
            System.out.println("Test Passed");
        }
    }
}

