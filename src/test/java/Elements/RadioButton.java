package Elements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class RadioButton {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    @Parameters ({"browser"})
    public void startSession(String browser) {

        if (browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/radio-button");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void closeSession() throws InterruptedException, IOException {
        Thread.sleep(1000);
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("C://Screenshot//RadioButton.png"));
        driver.quit();
    }

    @Test (priority = 1)
    public void pagVisibly (){
        WebElement pageVisibly = driver.findElement(By.xpath("//div[text()='Do you like the site?']"));
        String actual = pageVisibly.getText();
        String expected = "Do you like the site?";
        Assert.assertEquals(actual,expected);
        System.out.println(actual);
    }

    @Test (priority = 2, groups = {"Smoke"})
    public void yesRadioButton(){
        WebElement yesRadio = driver.findElement(By.xpath("//label[@for='yesRadio']"));
        boolean isEnabled = yesRadio.isEnabled();
        if (isEnabled){
            yesRadio.click();
            System.out.println("Yes Radio Button is checked");
        }

        WebElement yesRadioButton = driver.findElement(By.id("yesRadio"));
        boolean isSelected = yesRadioButton.isSelected();
        if (isSelected){
            WebElement text = driver.findElement(By.cssSelector("p.mt-3"));
            System.out.println(text.getText());
        }
    }
}