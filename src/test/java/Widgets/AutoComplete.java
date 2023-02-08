package Widgets;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class AutoComplete {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    String expectedUrl = "https://demoqa.com/auto-complete";

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(expectedUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @AfterClass
    public void closeSession() throws InterruptedException, IOException {
        Thread.sleep(Duration.ofSeconds(3));
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C://Screenshot//AutoComplete.png"));

    }
    @Test
    public void correctUrl(){
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);
    }
    @Test
    public void multipleColor(){
        WebElement completeMultiple = driver.findElement(By.id("autoCompleteMultipleInput"));
        completeMultiple.sendKeys("e");

        List<WebElement> colorsMultiple = driver.findElements(By.cssSelector("div.auto-complete__option"));

        for (WebElement color : colorsMultiple){
            String text = color.getText();
            System.out.println(text);

            if (text.equalsIgnoreCase("yellow")){
                color.click();
                break;

            }
        }
    }
    @Test
    public void singleColor(){

    }
}
