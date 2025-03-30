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

public class NegativeTest {
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
    public void unsuccessfulLoginTest() {
        // Открываем страницу логина
        driver.get("https://animego.one/login");

        // Заполняем поля логином и неправильным паролем
        driver.findElement(By.id("username")).sendKeys("valid_username");
        driver.findElement(By.id("password")).sendKeys("invalid_password");

        // Нажимаем кнопку Войти
        driver.findElement(By.cssSelector("#_submit")).click();

        // Проверяем, что пользователь не вошел в систему
        assertFalse(driver.getCurrentUrl().contains("/dashboard"));
    }
}

