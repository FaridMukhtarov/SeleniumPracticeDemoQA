package Elements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class UploadAndDownload {
    static WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/upload-download");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void closeSession() throws IOException {

        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C://Screenshot//Upload.png"));
        driver.quit();
    }

    @Test(priority = 1)
    public void pageVisibly() {
        WebElement textVisibly = driver.findElement(By.xpath("//div[text()='Upload and Download']"));
        boolean isDisplayed = textVisibly.isDisplayed();
        if (isDisplayed) {
            System.out.println(textVisibly.getText());
        }
    }

    @Test(priority = 2)
    public void download() throws InterruptedException {
        WebElement downloadButton = driver.findElement(By.id("downloadButton"));
        downloadButton.click();

        String path = "C:/Users/farid/Downloads";
        String fileName = "sampleFile.jpeg";
        Thread.sleep(Duration.ofSeconds(3));
        boolean isDownloaded = isFileDownloaded(path, fileName);
        System.out.println(isDownloaded);
    }

    public static boolean isFileDownloaded(String downloadPath, String fileName) {
        File file = new File(downloadPath);
        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[1].getName().equals(fileName)) {
                //files[1].delete();
                return true;
            }
        }
        return false;
    }

    @Test(priority = 3)
    public void upload() {
        WebElement uploadFile = driver.findElement(By.id("uploadFile"));
        uploadFile.sendKeys("C:/Users/farid/Downloads/sampleFile.jpeg");
    }
}
