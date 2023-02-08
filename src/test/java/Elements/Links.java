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
import java.util.List;

public class Links {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void startSession () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/links");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void closeSession () throws IOException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("C://Screenshot//links.png"));
        driver.quit();
    }

    @Test
    public void linkSize (){
        WebElement mainPage = driver.findElement(By.id("linkWrapper"));
        List<WebElement> mainPageLinks = mainPage.findElements(By.tagName("a"));
        System.out.println(mainPageLinks.size());
        Assert.assertEquals(mainPageLinks.size(), 9);
    }

    @Test
    public void clickLinks () throws IOException {

        List<WebElement> links = driver.findElements(By.xpath("//div[@id='linkWrapper']/p/a[text()='Home']"));
        for (WebElement link : links) {
            link.click();
            System.out.println(link.getText());
        }
    }

    @Test
    public void apiCall (){
        List<WebElement> apis = driver.findElements(By.xpath("//a[@href='javascript:void(0)']"));
        for (WebElement api : apis){
            api.click();
            WebElement responded = driver.findElement(By.id("linkResponse"));
            Assert.assertTrue(responded.isDisplayed());
            System.out.println(responded.getText());
        }
    }
}
