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

    public static WebElement getLink(WebDriver driver, Type type) {
        switch (type) {
            case PICTURES:
                return getPicturesLink(driver);
            case VIDEOS:
                return getVideosLink(driver);
            case AUDIOS:
                return getAudiosLink(driver);
        }
        return null;
    }

    public enum Type {PICTURES, VIDEOS, AUDIOS}
}
