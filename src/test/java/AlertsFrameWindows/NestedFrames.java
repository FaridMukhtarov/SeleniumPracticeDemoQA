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

public class NestedFrames {
    WebDriverWait wait;
    WebDriver driver;
    JavascriptExecutor js;
    String url = "https://demoqa.com/nestedframes";

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void closeSession() throws InterruptedException, IOException {

        Thread.sleep(1000);
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C://Screenshot//nestedFrames.png"));
        driver.quit();
    }

    @Test(priority = 1)
    public void verifyPageLink() {
        Assert.assertTrue(url.equals("https://demoqa.com/nestedframes"));
    }

    @Test(priority = 2)
    public void nestedFrame() {
        driver.switchTo().frame("frame1");
        WebElement parentFrameText = driver.findElement(By.xpath("//body[text()='Parent frame']"));
        System.out.println("Parent Frame: " + parentFrameText.getText());

        driver.switchTo().frame(0);
        WebElement childFrameText = driver.findElement(By.xpath("//p[text()='Child Iframe']"));
        if (childFrameText.isDisplayed()) {

            System.out.println("Child Frame: " + childFrameText.getText());

            for (int i = 0; i < 2; i++) {
                driver.switchTo().parentFrame();
            }
            System.out.println(driver.findElement(By.xpath("//div[text()='Nested Frames']")).getText());
        }
    }
}
