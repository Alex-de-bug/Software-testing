package net.alephdev;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.alephdev.pages.ForgotPassPage;
import net.alephdev.pages.LoginPage;

public class LoginTest {
    private static WebDriver driver;
    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        System.out.println(Properties.getProperty("active-driver"));
        driver = Drivers.getDriver();
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("start-path"));
        Thread.sleep(2000);
    }
    @AfterClass
    public static void tearDownClass() {
        if (driver != null)
            driver.quit();
    }
    @Before
    public void setUp() throws InterruptedException {
        Utils.waitForCaptcha(driver);
    }
    @Test
    public void titleTest() {
        assertEquals(driver.getTitle(), "Бесплатный сайт знакомств - регистрация | Мамба");
    }
    @Test
    public void forgetTest() throws InterruptedException {
        Utils.clickAndWait(LoginPage.getLoginButton(driver), "Кнопка логин");
        Utils.clickAndWait(LoginPage.getLoginWithEmailAndPasswordButton(driver), "Кнопка логина с паролем", 2000);
        Utils.clickAndWait(LoginPage.getForgotButton(driver), "Кнопка не помню пароль", 2000);
        Utils.type(ForgotPassPage.getEnterEmailField(driver), Properties.getProperty("email") + "fail", "Поле email");
        Utils.clickAndWait(ForgotPassPage.getNextStepButton(driver), "Кнопка продолжить", 2000);
        if (driver.getCurrentUrl().contains("/captcha")) {
            System.out.println("Обнаружена капча. Ожидание ручного решения...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Utils.CAPTCHA_TIMEOUT));
            wait.until(webDriver -> !webDriver.getCurrentUrl().contains("/captcha"));
            System.out.println("Капча пройдена. Продолжаем выполнение теста.");
            Utils.removeDistractions(driver);
            Utils.type(ForgotPassPage.getEnterEmailField(driver), Properties.getProperty("email") + "fail", "Поле email");
            Utils.clickAndWait(ForgotPassPage.getNextStepButton(driver), "Кнопка продолжить", 2000);
        }
        Thread.sleep(1000);
        Utils.assertPage(driver, Properties.getProperty("start-path") + "/restore-password" + "/email-success");
        Utils.clickAndWait(ForgotPassPage.getRestorePasswordSuccessOk(driver), "Кнопка продолжить", 2000);
        Utils.assertPage(driver, Properties.getProperty("start-path")+"/login");
    }
    @Test
    public void testLogin() throws InterruptedException {
        Utils.login(driver);
    }
}
