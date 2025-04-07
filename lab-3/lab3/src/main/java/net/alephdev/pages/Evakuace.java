package net.alephdev.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Evakuace {
    // Добавляем метод для переключения на фрейм если нужно
    private static void ensureInFrame(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
            driver.switchTo().frame("vpravo");
        } catch (Exception e) {
            // Возможно уже во фрейме
        }
    }
    
    public static WebElement getEvacuationTerritories(WebDriver driver) {
        ensureInFrame(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//td[contains(text(), 'EVAKUAČNÍ TERITORIA') or contains(., 'EVAKUAČNÍ TERITORIA')]")));
    }
    
    public static WebElement getEvacuationSpaceShips(WebDriver driver) {
        ensureInFrame(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//td[contains(text(), 'VESMÍRNÉ LODĚ') or contains(., 'VESMÍRNÉ LODĚ')]")));
    }
    
    public static WebElement getEvacuationAccesses(WebDriver driver) {
        ensureInFrame(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//td[contains(text(), 'PŘÍSTUPY') or contains(., 'PŘÍSTUPY')]")));
    }
    
    public static WebElement getEvacuationUno(WebDriver driver) {
        ensureInFrame(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//td[contains(text(), 'OSN') or contains(., 'OSN')]")));
    }
}
