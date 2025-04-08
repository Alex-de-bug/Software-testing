package net.alephdev;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

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

    protected static void assertPage(WebDriver driver, String path) {
        String expectedUrl = Properties.getProperty("base-url") + path;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(webDriver -> webDriver.getCurrentUrl().startsWith(expectedUrl));
        } catch (Exception e) {
            assert driver.getCurrentUrl().startsWith(expectedUrl)
                    : "Открылась страница: " + driver.getCurrentUrl() + ", ожидалась: " + expectedUrl;
        }
    }

    protected static void assertDomain(WebDriver driver, String domain) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(webDriver -> webDriver.getCurrentUrl().startsWith(domain));
        } catch (Exception e) {
            assert driver.getCurrentUrl().startsWith(domain)
                    : "Открылась страница: " + driver.getCurrentUrl() + ", ожидалась: " + domain;
        }
    }

    protected static void assertFrameDomain(WebDriver driver, String frameName, String domain) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(webDriver -> getFrameUrl(webDriver, frameName).startsWith(domain));
        } catch (Exception e) {
            assert getFrameUrl(driver, frameName).startsWith(domain)
                    : "Фрейм открылся на: " + getFrameUrl(driver, frameName) + ", ожидался домен: " + domain;
        }
    }

    public static String getFrameUrl(WebDriver driver, String frameName) {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(frameName);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String frameUrl = (String) jsExecutor.executeScript("return document.location.href");
        driver.switchTo().defaultContent();
        return frameUrl;
    }
}
