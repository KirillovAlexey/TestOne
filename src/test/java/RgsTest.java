import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;

public class RgsTest {
    private WebDriver driver;


    @Test
    public void Test() throws Exception {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        String url = "https://www.rgs.ru/";
        driver.get(url);
        //ожидание загрузки элемента "Страхование"
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(driver.findElement
                (By.xpath("//li[contains(@class, 'dropdown adv-analytics')]/a[contains(text(),'Страхование')]"))));
        //Выбор элемент "Страхование"
        driver.findElement(By.xpath("//li[contains(@class, 'dropdown adv-analytics')]/a[contains(text(),'Страхование')]")).click();
        //Выбор элемента ДМС
        driver.findElement(By.xpath("//ul[contains(@class, 'collapse animated')]/li[contains(@class,'adv-analytics-navigation')]/a[contains(text(),'ДМС')]")).click();
        //Ожидание загрузки элемента "Отправить заявку"
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(driver.findElement
                (By.xpath("//a[contains(text(), 'Отправить заявку')]"))));
        //Выбор элемента "Отправить заявку"
        driver.findElement(By.xpath("//a[contains(text(), 'Отправить заявку')]")).click();
        //Проверка наличия текста Заявка на добровольное медицинское страхование
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(driver.findElement
                (By.xpath("//b[contains(text(),'Заявка на добровольное медицинское страхование')]"))));
        assertEquals("Заявка на добровольное медицинское страхование",
                driver.findElement(By.xpath("//b[contains(text(),'Заявка на добровольное медицинское страхование')]")).getText());
        //Заполнение полей формы
        driver.findElement(By.xpath("//input[contains(@data-bind, 'LastName')]")).sendKeys("Иванов");
        driver.findElement(By.xpath("//input[contains(@data-bind, 'FirstName')]")).sendKeys("Иван");
        driver.findElement(By.xpath("//input[contains(@data-bind, 'MiddleName')]")).sendKeys("Иванович");
        driver.findElement(By.xpath("//select[contains(@name,'Region')]")).click();
        //((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();"
        //  ,webElement);
        //new Select(driver.findElement(By.name("Region"))).selectByVisibleText("Москва");
        new Select(driver.findElement(By.name("Region"))).selectByVisibleText("Москва");
        driver.findElement(By.xpath("//input[contains(@data-bind,'Phone')]")).sendKeys("1234567890");
        driver.findElement(By.name("Email")).sendKeys("qwertyqwerty");////input[contains(@data-bind,'Email')]
        driver.findElement(By.name("Comment")).sendKeys("Очень хороший банк");////textarea[contains(@data-bind,'Comment')]
        //Поиск элемента "Я согласен на все"
        driver.findElement(By.xpath("//input[contains(@data-bind, 'IsProcessingPersonalData')]")).click();////input[contains(@data-bind, 'IsProcessingPersonalData')]
        //Поиск кнопки "Отправить"
        driver.findElement(By.xpath("//button[contains(@class,btn)][contains(@data-bind,'click:SubmitForm')]")).click();////button[contains(@data-bind,'click:SubmitForm')]
    }

    @After
    public void assertIs() throws Exception {
        assertEquals("Иванов", driver.findElement(By.name("LastName")).getAttribute("value"));
        assertEquals("Иван", driver.findElement(By.name("FirstName")).getAttribute("value"));
        assertEquals("Иванович", driver.findElement(By.name("MiddleName")).getAttribute("value"));
        assertEquals("Москва",
                new Select(driver.findElement(By.name("Region"))).getAllSelectedOptions().get(0).getText());
        //assertEquals("+7 (123) 456-78-90",driver.findElement(By.name("Phone")).getAttribute("value"));
        //assertEquals("qwertyqwerty", driver.findElement(By.name("Email")).getAttribute("value"));
        assertEquals("Очень хороший банк", driver.findElement(By.name("Comment")).getAttribute("value"));
        driver.close();
    }
}
