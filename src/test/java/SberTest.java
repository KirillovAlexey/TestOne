import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SberTest {
    private WebDriver driver;

    @Test
    public void Test() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        String url = "http://www.sberbank.ru/ru/person";
        driver.manage().window().maximize();
        driver.get(url);
        //Wait wait = new WebDriverWait(driver,5,1000);
        driver.findElement(By.xpath("//a[contains(@class,'hd-ft-region')]")).click();

        driver.findElement(By.xpath("//input[contains(@class,'kit-input__control')][@type='search']")).sendKeys("Нижегородская область");

        driver.findElement(By.xpath("//input[contains(@class, 'kit-input__control')][contains(@type,'search')]")).click();

        driver.findElement(By.xpath("//a[contains(text(), 'Нижегородская область')]")).click();

        //if(driver.findElement(By.xpath("//a[contains(@class,'hd-ft-region')]")).getText().equals("Нижегородская область")){
        //System.out.println("yeap");
        WebElement element = driver.findElement(By.xpath("//footer"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);

        //}
    }

    @After
    public void asserts() throws Exception {
        assertEquals("Нижегородская область", driver.findElement(By.xpath("//a[contains(@class,'hd-ft-region')]")).getText());
        assertTrue(driver.findElement(By.tagName("span")).isDisplayed());
        driver.close();
    }
}
