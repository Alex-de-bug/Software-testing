package net.alephdev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdvMeansPage {
    public static WebElement getWarnButton(WebDriver driver) {
        return driver.findElement(By.xpath("//b[contains(text(),\"WARNING TO PEOPLE\")]/.."));
    }

    public static WebElement getLogosBannersButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[contains(text(),\"Logos and banners\")]"));
    }
}
