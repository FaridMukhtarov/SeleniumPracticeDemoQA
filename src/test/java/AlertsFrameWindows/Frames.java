package AlertsFrameWindows;

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

public class Frames {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/frames");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void closeSession() throws InterruptedException, IOException {

        Thread.sleep(1000);
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C://Screenshot//frames.png"));
        driver.quit();
    }

    @Test(priority = 1)
    public void mainPage() {
        WebElement pageVisibly = driver.findElement(By.xpath("//div[text()='Frames']"));
        if (pageVisibly.isDisplayed()) {
            System.out.println("Main Page: " + pageVisibly.getText());
        }
    }

    @Test (priority = 2)
    public void bigFrame() {
        driver.switchTo().frame("frame1");
        WebElement sampleHeading = driver.findElement(By.id("sampleHeading"));
        Assert.assertTrue(sampleHeading.isDisplayed());
        driver.switchTo().parentFrame();
    }

    @Test (priority = 3)
    public void smallFrame() {
        driver.switchTo().frame("frame2");
        WebElement sampleHeadingSmall = driver.findElement(By.id("sampleHeading"));
        if (sampleHeadingSmall.isDisplayed()) {
            System.out.println(sampleHeadingSmall.getText());
        }
    }
}
