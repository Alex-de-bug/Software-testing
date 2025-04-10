package net.alephdev;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    protected static void clickAndWait(WebElement element, String name, int sleep) throws InterruptedException {
        assert element.isDisplayed() : "Элемент " + name + " не отображается";
        element.click();
        Thread.sleep(sleep);
    }

    protected static void type(WebElement element, String text, String name) {
        assert element.isDisplayed() : "Элемент " + name + " не отображается";
        element.sendKeys(text);
    }

    protected static boolean startsWith(String actual, String expected) {
        return actual.startsWith(expected)
                || actual.replace("https://", "http://").startsWith(expected)
                || actual.startsWith(expected.replace("https://", "http://"))
                || actual.replace("http://", "https://").startsWith(expected)
                || actual.startsWith(expected.replace("http://", "https://"));
    }

    protected static void assertPage(WebDriver driver, String path) {
        String expectedUrl = Properties.getProperty("base-url") + path;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(webDriver -> startsWith(webDriver.getCurrentUrl(), expectedUrl));
        } catch (Exception e) {
            assert startsWith(driver.getCurrentUrl(), expectedUrl)
                    : "Открылась страница: " + driver.getCurrentUrl() + ", ожидалась: " + expectedUrl;
        }
    }

    protected static void assertDomain(WebDriver driver, String domain) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(webDriver -> startsWith(webDriver.getCurrentUrl(), domain));
        } catch (Exception e) {
            assert startsWith(driver.getCurrentUrl(), domain)
                    : "Открылась страница: " + driver.getCurrentUrl() + ", ожидалась: " + domain;
        }
    }

    protected static void assertFrameDomain(WebDriver driver, String frameName, String domain) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(webDriver -> checkFrameDomain(webDriver, frameName, domain));
        } catch (Exception e) {
            assert checkFrameDomain(driver, frameName, domain)
                    : "Фрейм открылся на: " + getFrameUrl(driver, frameName) + ", ожидался домен: " + domain;
        }
    }

    protected static boolean checkFrameDomain(WebDriver driver, String frameName, String domain) {
        return startsWith(getFrameUrl(driver, frameName), domain);
    }

    protected static boolean checkJsDomain(WebDriver driver, String domain) {
        return startsWith(getUrlViaJs(driver), domain);
    }

    public static String getFrameUrl(WebDriver driver, String frameName) {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(frameName);
        String frameUrl = getUrlViaJs(driver);
        driver.switchTo().defaultContent();
        return frameUrl;
    }

    public static String getUrlViaJs(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.location.href");
    }

    public static void getDownloadObject(WebDriver driver, WebElement downloadLink) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(downloadLink));
        String downloadUrl = downloadLink.getAttribute("href");
        HttpURLConnection connection = (HttpURLConnection) new URL(downloadUrl).openConnection();
        connection.setRequestMethod("HEAD");
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("File is not available for download. Response code: " + responseCode);
        }
                
        long fileSize = connection.getContentLengthLong();
        if (fileSize <= 0) {
            throw new Exception("File size is 0 or not available");
        }
        
        System.out.println("File is available for download. Size: " + fileSize + " bytes");
    }
}
