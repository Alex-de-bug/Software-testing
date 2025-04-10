package net.alephdev.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.alephdev.Properties;

public class EvakuacePage {
    public static void frameBypass(WebDriver driver) {
        if(driver.getCurrentUrl().contains(Properties.getProperty("start-page"))) {
            driver.get(Properties.getProperty("base-url") + Properties.getProperty("embedded-page"));
        }
        driver.switchTo().defaultContent();
        driver.switchTo().frame("top");
    }
    
    public static WebElement getEvacuationInfo(WebDriver driver, String info) {
        frameBypass(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(text(), '" + info + "') or contains(., '" + info + "')]")));
    }
}
