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

public class ModalDialogs {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    @BeforeClass
    public void StartSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/modal-dialogs");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void closeSession() throws InterruptedException, IOException {

        Thread.sleep(Duration.ofSeconds(1));
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C://Screenshot//modals.png"));
        driver.quit();
    }

    @Test (priority = 1)
    public void pageVisibly() {
        WebElement page = driver.findElement(By.xpath("//div[text()='Modal Dialogs']"));
        boolean isDisplayed = page.isDisplayed();
        if (isDisplayed) {
            System.out.println(page.getText());
        }
    }

    @Test (priority = 2)
    public void smallModal() {
        WebElement showSmallModal = driver.findElement(By.id("showSmallModal"));
        showSmallModal.click();

        WebElement smallText = driver.findElement(By.xpath("//div[text()='This is a small modal. It has very less content']"));
        if (smallText.isDisplayed()) {
            System.out.println("Small Text: " + smallText.getText());
            driver.findElement(By.id("closeSmallModal")).click();
        }
    }

    @Test (priority = 3)
    public void largeModal() throws InterruptedException {
        WebElement showLargeModal = driver.findElement(By.id("showLargeModal"));
        showLargeModal.click();
        Assert.assertTrue(showLargeModal.isDisplayed());

        WebElement closeLargeModal = driver.findElement(By.id("closeLargeModal"));
        closeLargeModal.click();
    }
}
