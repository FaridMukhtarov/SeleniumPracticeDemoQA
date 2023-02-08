package Elements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class WebTables {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/webtables");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void closeSession() throws InterruptedException, IOException {
        Thread.sleep(1000);
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("C://Screenshot//WebTables.png"));
        driver.quit();
    }
    
    @Test
    public void test() {
        WebElement pageVisibly = driver.findElement(By.xpath("//div[text()='Web Tables']"));
        boolean page = pageVisibly.isDisplayed();
        System.out.println(page);

        WebElement addNewRecordButton = driver.findElement(By.id("addNewRecordButton"));
        addNewRecordButton.click();

        Actions actinon = new Actions(driver);
        WebElement firstName = driver.findElement(By.id("firstName"));
        actinon.click(firstName)
                .sendKeys("Farid")
                .sendKeys(Keys.TAB)
                .sendKeys("Mukhtarov")
                .sendKeys(Keys.TAB)
                .sendKeys("farid123@gmail.com")
                .sendKeys(Keys.TAB)
                .sendKeys("27")
                .sendKeys(Keys.TAB)
                .sendKeys("2500")
                .sendKeys(Keys.TAB)
                .sendKeys("IT")
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .perform();

        WebElement searchBox = driver.findElement(By.id("searchBox"));
        searchBox.sendKeys("Farid");
        WebElement visibly = driver.findElement(By.xpath("//div[@class='rt-tr -odd']"));
        System.out.println(visibly.getText());
        WebElement delete = driver.findElement(By.id("delete-record-4"));
        delete.click();

        WebElement noRowsFound = driver.findElement(By.xpath("//div[text()='No rows found']"));
        boolean noRows = noRowsFound.isDisplayed();
        String actualResult = noRowsFound.getText();
        String expectedResult = "No rows found";
        Assert.assertEquals(actualResult, expectedResult);
        System.out.println(actualResult);

    }
}
