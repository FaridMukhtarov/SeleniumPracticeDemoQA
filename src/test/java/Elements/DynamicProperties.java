package Elements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class DynamicProperties {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeMethod
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/dynamic-properties");
    }

    @AfterMethod
    public void closeSession() throws InterruptedException, IOException {
        Thread.sleep(1000);

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("C://Screenshot//dynamic.png"));

        driver.quit();
    }

    @Test
    public void randomId() {
        WebElement randomId = driver.findElement(By.xpath("//p[text()='This text has random Id']"));
        String actual = randomId.getText();
        String expected = "This text has random Id";
        Assert.assertEquals(actual, expected);
    }

    @Test (groups = {"Smoke"})
    public void enable5Seconds() {
        WebElement willEnable = driver.findElement(By.id("enableAfter"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(willEnable));
        willEnable.click();
    }

    @Test
    public void colorChange() {
        WebElement colorChange = driver.findElement(By.id("colorChange"));
        String atrubutValue = colorChange.getAttribute("class");
        System.out.println("Before Value: " + atrubutValue);

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeToBe(colorChange, "class", "mt-4 text-danger btn btn-primary"));
        atrubutValue = colorChange.getAttribute("class");
        System.out.println("After Value: " + atrubutValue);
    }

    @Test
    public void after5Seconds() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter")));
        WebElement visibleAfter = driver.findElement(By.id("visibleAfter"));
        visibleAfter.click();
        System.out.println(visibleAfter.getText());
    }
}
