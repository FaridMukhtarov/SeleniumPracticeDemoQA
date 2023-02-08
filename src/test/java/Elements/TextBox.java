package Elements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TextBox {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");
    }

    @AfterClass
    public void closeSession() throws InterruptedException, IOException {
        Thread.sleep(1000);
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("C://Screenshot//TextBox.png"));
        driver.quit();
    }

    @Test (priority = 1)
    public void pageVisibly(){
        WebElement pageVisibly = driver.findElement(By.className("main-header"));
        Assert.assertTrue(pageVisibly.isDisplayed());
    }

    @Test (priority = 2)
    public void userName(){
        WebElement userName = driver.findElement(By.id("userName"));
        userName.sendKeys("Farid");
    }

    @Test (priority = 3)
    public void userEmail(){
        WebElement userEmail = driver.findElement(By.id("userEmail"));
        userEmail.sendKeys("farid1995@gmail.com");
    }

    @Test (priority = 4)
    public void currentAddress(){
        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("Baku, Azerbaijan");
    }

    @Test (priority = 5)
    public void permanentAddress(){
        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        permanentAddress.sendKeys("Baku, Azerbaijan");
    }

    @Test (priority = 6)
    public void submit(){
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
    }

    @Test (priority = 7)
    public void outputText(){
        WebElement outputText = driver.findElement(By.cssSelector(".border.col-md-12.col-sm-12"));
        String actual = outputText.getText();
        String expected = "Name:Farid\n"
                + "Email:farid1995@gmail.com\n"
                + "Current Address :Baku, Azerbaijan\n"
                + "Permananet Address :Baku, Azerbaijan";
        Assert.assertEquals(actual,expected);
    }
}
