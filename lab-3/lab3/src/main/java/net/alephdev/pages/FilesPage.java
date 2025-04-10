package net.alephdev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FilesPage {
    public static void setFrame(WebDriver driver) {
        driver.switchTo().defaultContent();
        driver.switchTo().frame("Obsah");
    }
    public static WebElement getPicturesLink(WebDriver driver) {
        setFrame(driver);
        return driver.findElement(By.xpath("//b[contains(text(),\"Картинки\")]/.."));
    }
    public static WebElement getVideosLink(WebDriver driver) {
        setFrame(driver);
        return driver.findElement(By.xpath("//a[contains(text(),\"Видео записи\")]"));
    }
    public static WebElement getAudiosLink(WebDriver driver) {
        setFrame(driver);
        return driver.findElement(By.xpath("//a[contains(text(),\"Звуковые записи\")]"));
    }
    public static WebElement getDownloadLink(WebDriver driver, String link) {
        return driver.findElement(By.xpath("//a[contains(@href, '" + link + "')]"));
    }
}
