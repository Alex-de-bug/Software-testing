package net.alephdev.pages;

import java.time.Duration;

import net.alephdev.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Evakuace {
    private static void frameBypass(WebDriver driver) {
        if(driver.getCurrentUrl().contains(Properties.getProperty("start-page"))) {
            driver.get(Properties.getProperty("base-url") + Properties.getProperty("embedded-page"));
        }
        driver.switchTo().defaultContent();
        driver.switchTo().frame("top");
    }
    
    public static WebElement getEvacuationTerritories(WebDriver driver) {
        frameBypass(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(text(), 'EVAKUAČNÍ TERITORIA') or contains(., 'EVAKUAČNÍ TERITORIA')]")));
    }
    
    public static WebElement getEvacuationSpaceShips(WebDriver driver) {
        frameBypass(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(text(), 'VESMÍRNÉ LODĚ') or contains(., 'VESMÍRNÉ LODĚ')]")));
    }
    
    public static WebElement getEvacuationAccesses(WebDriver driver) {
        frameBypass(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(text(), 'PŘÍSTUPY') or contains(., 'PŘÍSTUPY')]")));
    }
    
    public static WebElement getEvacuationUno(WebDriver driver) {
        frameBypass(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(text(), 'OSN') or contains(., 'OSN')]")));
    }
}
