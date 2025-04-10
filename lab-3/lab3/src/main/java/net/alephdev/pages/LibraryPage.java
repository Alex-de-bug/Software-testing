package net.alephdev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LibraryPage {
    public static WebElement getBookLink(WebDriver driver, String book) {
        return driver.findElement(By.xpath("//b[contains(text(),\"" + book + "\")]/.."));
    }
    public static WebElement getBookDownloadLink(WebDriver driver, String book) {
        return driver.findElement(By.xpath("//b[contains(text(),\"" + book + "\")]/../../../td[@class='left']/a"));
    }
}
