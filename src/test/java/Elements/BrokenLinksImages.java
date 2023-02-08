package Elements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BrokenLinksImages {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    String url = "https://demoqa.com/broken";


    @BeforeMethod
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void closeSession() throws IOException, InterruptedException {

        Thread.sleep(Duration.ofSeconds(2));
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C://Screenshot//BrokenLinks.png"));
        driver.quit();
    }

    @Test (priority = 1)
    public void pageVisibly() {
        WebElement page = driver.findElement(By.xpath("//div[text()='Broken Links - Images']"));
        if (page.isDisplayed()) {
            System.out.println("Test Passed");
        }
    }

    @Test (priority = 2)
    public void validImage() throws IOException {
        WebElement valid = driver.findElement(By.xpath("//div/img[1]"));
        String validImageUrl = valid.getAttribute("src");
        System.out.println("Valid Image Url: " + validImageUrl);

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(validImageUrl);     // For Request
        HttpResponse response = client.execute(request);  // For Response
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);

        if (statusCode == 200) {
            System.out.println("Valid Image");
        } else if (statusCode == 500) {
            System.out.println("Broken Image");
        }
    }

    @Test (priority = 3)
    public void brokenImage() throws IOException {
        WebElement broken = driver.findElement(By.xpath("//div/img[2]"));
        String brokenImageUrl = broken.getAttribute("src");
        System.out.println("Broken Image Url: " + brokenImageUrl);

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(brokenImageUrl);     // For Request
        HttpResponse response = client.execute(request);  // For Response
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);

        if (statusCode >= 300) {
            System.out.println("Broken Image:");
        } else {
            System.out.println("Valid Image:");
        }
    }

    @Test (priority = 4)
    public void validLink() throws IOException {
        WebElement validLink = driver.findElement(By.xpath("//a[text()='Click Here for Valid Link']"));
        validLink.click();

        String url = driver.getCurrentUrl();

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Valid Link: " + statusCode);
    }

    @Test (priority = 5)
    public void brokenLink() throws IOException {
        WebElement BrokenLink = driver.findElement(By.xpath("//a[text()='Click Here for Broken Link']"));
        BrokenLink.click();

        String url = driver.getCurrentUrl();

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Broken Link: " + statusCode);
    }
}
