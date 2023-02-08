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

public class CheckBox {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/checkbox");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterClass
    public void closeSession() throws InterruptedException, IOException {
        Thread.sleep(1000);
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("C://Screenshot//checkbox.png"));
        driver.quit();
    }

    @Test (priority = 1)
    public void checkBoxVisibly(){
        WebElement checkBoxVisibly = driver.findElement(By.className("main-header"));
        String actual = checkBoxVisibly.getText();
        String expected = "Check Box";
        Assert.assertEquals(actual,expected);
    }

    @Test (priority = 2)
    public void toggle() throws InterruptedException {
        WebElement  toggle = driver.findElement(By.cssSelector("span button[type='button'] svg"));
        toggle.click();

        WebElement checkBoxClick = driver.findElement(By.cssSelector("label[for='tree-node-home'] span.rct-checkbox svg"));
        String checkBoxValuebefore = checkBoxClick.getAttribute("class");
        System.out.println(checkBoxValuebefore);

        boolean isEnabled = checkBoxClick.isEnabled();
        if (isEnabled == true) {
            checkBoxClick.click();
            System.out.println("CheckBox is checked");

        }else {
            System.out.println("Test Failed");
        }

        WebElement result = driver.findElement(By.id("result"));
        System.out.println(result.getText());
    }
}
