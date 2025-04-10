package net.alephdev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdvMeansPage {
    public static WebElement getWarnButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[contains(@href,\"varovani_lidem_en.htm\")]"));
    }

    public static WebElement getLogosBannersButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[contains(@href,\"loga_a_bannery_en.htm\")]"));
    }
}
