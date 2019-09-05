import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

import static junit.framework.TestCase.assertEquals;

public class RgsTest {
    private WebDriver driver;
    private String url;
    //Элемент страхование
    private final String SAFE_SOUL = "//li[contains(@class, 'dropdown adv-analytics')]/a[contains(text(),'Меню')]";
    private final String SEND = "//a[contains(text(), 'Отправить заявку')]";
    private final String CHECK_TEXT = "//b[contains(text(),'Заявка на добровольное медицинское страхование')]";
    private final String PHONE = "//input[contains(@data-bind,'Phone')]";

    @Test
    public void Test() throws Exception {
        Pattern pattern = Pattern.compile("\\+\\d \\(\\d{3}\\) \\d{3}\\-\\d{2}\\-\\d{2}");
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        url = "https://www.rgs.ru/";
        driver.get(url);
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(driver.findElement
                (By.xpath(SAFE_SOUL))));
        driver.findElement(By.xpath(SAFE_SOUL)).click();

        driver.findElement(By.linkText("ДМС")).click();
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOf(driver.findElement
                (By.xpath(SEND))));

        driver.findElement(By.xpath(SEND)).click();
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(driver.findElement
                (By.xpath(CHECK_TEXT))));
        assertEquals("Заявка на добровольное медицинское страхование",
                driver.findElement(By.xpath(CHECK_TEXT)).getText());
        //Заполнение полей формы
        driver.findElement(By.name("LastName")).sendKeys("Иванов");
        driver.findElement(By.name("FirstName")).sendKeys("Иван");
        driver.findElement(By.name("MiddleName")).sendKeys("Иванович");
        driver.findElement(By.xpath("//select[contains(@name,'Region')]")).click();
        new Select(driver.findElement(By.name("Region"))).selectByVisibleText("Москва");
        driver.findElement(By.xpath(PHONE)).sendKeys("1234567890");
        driver.findElement(By.name("Email")).sendKeys("qwertyqwerty");//
        driver.findElement(By.name("Comment")).sendKeys("Очень хороший банк");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.id("button-m")).click();
    }

    @After
    public void assertIs() throws Exception {
        assertEquals("Иванов", driver.findElement(By.name("LastName")).getAttribute("value"));
        assertEquals("Иван", driver.findElement(By.name("FirstName")).getAttribute("value"));
        assertEquals("Иванович", driver.findElement(By.name("MiddleName")).getAttribute("value"));
        assertEquals("Москва",
                new Select(driver.findElement(By.name("Region"))).getAllSelectedOptions().get(0).getText());
        assertEquals("+7 (123) 456-78-90", driver.findElement(By.xpath(PHONE)).getAttribute("value"));
        /*assertEquals("Введите корректное значение",
                driver.findElement(By.xpath("//*[text()='Телефон']/..//span[@class='validation-error-text']")).getText());*/
        assertEquals("qwertyqwerty", driver.findElement(By.name("Email")).getAttribute("value"));
        assertEquals("Введите адрес электронной почты",
                driver.findElement(By.xpath("//span[@class='validation-error-text'][text()='Введите адрес электронной почты']")).getText());
        assertEquals("Очень хороший банк", driver.findElement(By.name("Comment")).getAttribute("value"));
        driver.quit();
    }
}