import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SberTest {
    private WebDriver driver;
    private final String SOCIAL_TAG = "//li//a[contains(@class, 'social_link')]";
    private final String REGION = "//a[contains(@class,'hd-ft-region')]";
    private final String NREGION = "//input[contains(@class,'kit-input__control')][@type='search']";
// //span[@class=('footer__social_logo footer__social_fb') or (@class = 'footer__social_logo footer__social_tw') or
// (@class = 'footer__social_logo footer__social_yt') or (@class = 'footer__social_logo footer__social_ins') or
// (@class = 'footer__social_logo footer__social_vk') or (@class = 'footer__social_logo footer__social_ok') or

    @Test
    public void Test() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        String url = "http://www.sberbank.ru/ru/person";
        driver.manage().window().maximize();
        driver.get(url);
        //Wait wait = new WebDriverWait(driver,5,1000);
        driver.findElement(By.xpath(REGION)).click();

        driver.findElement(By.xpath(NREGION)).sendKeys("Нижегородская область");

        driver.findElement(By.xpath(NREGION)).click();

        driver.findElement(By.xpath("//a[contains(text(), 'Нижегородская область')]")).click();
        System.out.println("wow");
        WebElement element = driver.findElement(By.xpath("//footer"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);

        //}
    }

    @After
    public void asserts() throws Exception {
        assertEquals("Нижегородская область", driver.findElement(By.xpath(REGION)).getText());
        assertTrue(driver.findElement(By.tagName("span")).isDisplayed());
        assertTrue(driver.findElement(By.tagName("span")).isEnabled());
        List<WebDriver> list = new ArrayList(driver.findElements(By.xpath(SOCIAL_TAG)));
        assertEquals(list.size(),driver.findElements(By.xpath(SOCIAL_TAG)).size());
        driver.quit();
    }
}
