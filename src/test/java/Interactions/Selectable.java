package Interactions;

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
import java.util.List;

public class Selectable {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/selectable");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void closeSession () throws InterruptedException, IOException {
        Thread.sleep(1000);
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("C://Screenshot//selectable.png"));
       // driver.quit();
    }

    @Test
    public  void selectable (){

        List<WebElement> lists = driver.findElements(By.xpath("//ul/li[@class='mt-2 list-group-item list-group-item-action']"));
        for(WebElement list : lists){
            //list.click();
            if (list.equals("Morbi leo risus")){
                list.submit();

            }
        }

//        WebElement verticalListContainer = driver.findElement(By.id("verticalListContainer"));
//        Select select = new Select(verticalListContainer);
//
//        if (select.isMultiple()){
//            select.selectByVisibleText("Morbi leo risus");
//        }
    }
}
