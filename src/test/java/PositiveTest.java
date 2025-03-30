import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
public class PositiveTest {
    private WebDriver driver;
    private WebDriverWait wait;
    @BeforeEach
    public void setUp()
    {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @AfterEach
    public void tearDown(){
        if (driver!=null){
            driver.quit();
        }
    }
    @Test
    public void testSearch(){
        driver.get("https://animego.one");

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#navbar-search")));
        searchButton.click();

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[name='q']")));
        searchInput.sendKeys("Джо");

        searchInput.submit();
        // Ищем содержат ли названия "Джо"
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Джо')]")));
        // Проверяем первое найденое название аниме
        WebElement animeText = driver.findElement(
                By.xpath("//div[@class='h5 font-weight-normal mb-2 card-title text-truncate']/a"));
        String actualTitleFromAttribute  = animeText.getAttribute("title");
        assertEquals("Невероятное приключение ДжоДжо: Рыцари звёздной пыли",actualTitleFromAttribute);
    }
    @Test
    public void testSuccessfulLoginFormElements() {
        // Открываем страницу логина
        driver.get("https://animego.one/login");

        String titleText = driver.findElement(By.className("custom-control-description")).getText();
        assertEquals("Запомнить меня", titleText);
        // Проверяем наличие полей ввода
        assertNotNull(driver.findElement(By.id("username")));
        assertNotNull(driver.findElement(By.id("password")));

        // Проверяем наличие кнопки "Войти"
        assertNotNull(driver.findElement(By.id("_submit")));
    }
}
