package net.alephdev;

import net.alephdev.pages.LoginPage;
import net.alephdev.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {
    private static final int CAPTCHA_TIMEOUT = 300;

    protected static void login(WebDriver driver) throws InterruptedException {
        WebElement loginButton = LoginPage.getLoginButton(driver);
        assert loginButton.isDisplayed() : "Кнопка логина не отображается на странице";
        loginButton.click();
        Thread.sleep(1000);
        WebElement loginWithEmailAndPasswordButton = LoginPage.getLoginWithEmailAndPasswordButton(driver);
        assert loginWithEmailAndPasswordButton.isDisplayed() : "Кнопка логина с паролем не отображается на странице";
        loginWithEmailAndPasswordButton.click();
        Thread.sleep(2000);
        WebElement emailField = LoginPage.getEmailField(driver);
        assert emailField.isDisplayed() : "Поле email не отображается на странице";
        emailField.sendKeys(Properties.getProperty("email"));
        WebElement passwordField = LoginPage.getPasswordField(driver);
        assert passwordField.isDisplayed() : "Поле пароля не отображается на странице";
        passwordField.sendKeys(Properties.getProperty("password"));
        Thread.sleep(1000);
        WebElement submitButton = LoginPage.getSubmitButton(driver);
        assert submitButton.isDisplayed() : "Кнопка отправки не отображается на странице";
        submitButton.click();
        Thread.sleep(2000);
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
        assert driver.getCurrentUrl().startsWith(Properties.getProperty("base-url") + path)
                : "Открылась страница: " + driver.getCurrentUrl() + ", ожидалась: " + Properties.getProperty("base-url") + path;
    }
}
