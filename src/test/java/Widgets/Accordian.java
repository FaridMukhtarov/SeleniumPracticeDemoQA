package Widgets;

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

public class Accordian {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/accordian");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public void closeSession() throws InterruptedException, IOException {
        Thread.sleep(Duration.ofSeconds(3));
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C://Screenshot//accordianPage.png"));
        driver.quit();
    }

    @Test(priority = 1)
    public void pageVisibly() {
        WebElement pageVisibly = driver.findElement(By.cssSelector(".main-header"));
        if (pageVisibly.isDisplayed()) {
            System.out.println("Page visibility: Test Passed");
        } else {
            System.out.println("Page visibility: Test Failed");
        }
    }

    @Test(priority = 2)
    public void accordian_1() throws IOException {
        WebElement textCheck = driver.findElement(By.xpath("//div[@class='collapse show']"));
        String text = textCheck.getText();
        if (!text.isEmpty()) {
            System.out.println("First Accordian: Test Passed");
        } else {
            System.out.println("First Accordian:Test Failed");
        }

        String value = textCheck.getAttribute("class");
        System.out.println("Before Value: " + value);

        WebElement section1Heading = driver.findElement(By.id("section1Heading"));
        section1Heading.click();

        value = textCheck.getAttribute("class");
        System.out.println("After Value: " + value);

    }

    @Test(priority = 3)
    public void accordian_2() {
        WebElement accordian_2 = driver.findElement(By.id("section2Heading"));
        accordian_2.click();

        String valueShow = driver.findElement(By.xpath("//div[@class='collapse show']")).getAttribute("class");

        if (valueShow.equals("collapse show")) {
            WebElement text2 = driver.findElement(By.id("section2Content"));
            Assert.assertTrue(text2.isDisplayed());
        } else {
            System.out.println("Accordian-2: Test Failed");
        }
    }

    @Test(priority = 4)
    public void accordian_3() {
        WebElement section3Heading = driver.findElement(By.id("section3Heading"));
        section3Heading.click();

        WebElement text_3 = driver.findElement(By.xpath("(//div/p)[4]"));
        if (text_3.isDisplayed()){
            System.out.println(text_3.getText());
        }
    }
}
