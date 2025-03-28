package net.alephdev;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.alephdev.pages.LoginPage;
import net.alephdev.pages.MainPage;

public class Utils {
    protected static final int CAPTCHA_TIMEOUT = 300;

    protected static void clickAndWait(WebElement element, String name) throws InterruptedException {
        clickAndWait(element, name, 1000);
    }

    protected static void clickAndWait(WebElement element, String name, int sleep) throws InterruptedException {
        assert element.isDisplayed() : "Элемент " + name + " не отображается";
        element.click();
        Thread.sleep(sleep);
    }

    protected static void type(WebElement element, String text, String name) {
        assert element.isDisplayed() : "Элемент " + name + " не отображается";
        element.sendKeys(text);
    }

    protected static void login(WebDriver driver) throws InterruptedException {
        clickAndWait(LoginPage.getLoginButton(driver), "Кнопка логин");
        clickAndWait(LoginPage.getLoginWithEmailAndPasswordButton(driver), "Кнопка логина с паролем", 2000);
        type(LoginPage.getEmailField(driver), Properties.getProperty("email"), "Поле email");
        type(LoginPage.getPasswordField(driver), Properties.getProperty("password"), "Поле пароля");
        Thread.sleep(1000);
        clickAndWait(LoginPage.getSubmitButton(driver), "Кнопка отправки", 3000);
        assertPage(driver, "rating");
    }

    protected static void waitForCaptcha(WebDriver driver) throws InterruptedException {
        if (driver.getCurrentUrl().contains("/captcha")) {
            System.out.println("Обнаружена капча. Ожидание ручного решения...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(CAPTCHA_TIMEOUT));
            wait.until(webDriver -> !webDriver.getCurrentUrl().contains("/captcha"));
            System.out.println("Капча пройдена. Продолжаем выполнение теста.");
            removeDistractions(driver);
        }
    }

    protected static void removeDistractions(WebDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        WebElement distraction = MainPage.getDistraction(driver);
        if (distraction != null && distraction.isDisplayed()) {
            distraction.click();
        }
    }

    protected static void assertPage(WebDriver driver, String path) throws InterruptedException {
        waitForCaptcha(driver);
        String expectedUrl = Properties.getProperty("base-url") + path;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(webDriver -> webDriver.getCurrentUrl().startsWith(expectedUrl));
        } catch (Exception e) {
            assert driver.getCurrentUrl().startsWith(expectedUrl)
                    : "Открылась страница: " + driver.getCurrentUrl() + ", ожидалась: " + expectedUrl;
        }
    }
}
