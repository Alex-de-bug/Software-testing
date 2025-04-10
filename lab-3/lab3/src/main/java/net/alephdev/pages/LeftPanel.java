package net.alephdev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeftPanel {
    public static void setFrame(WebDriver driver) {
        driver.switchTo().defaultContent();
        driver.switchTo().frame("Obsah");
    }
    public static WebElement getBoldLink(WebDriver driver, String text) {
        setFrame(driver);
        return driver.findElement(By.xpath("//b[contains(text(),\"" + text + "\")]/.."));
    }
    public static WebElement getRegularLink(WebDriver driver, String text) {
        setFrame(driver);
        return driver.findElement(By.xpath("//a[contains(text(),\"" + text + "\")]"));
    }

    public static WebElement getLibraryLink(WebDriver driver) {
        return getBoldLink(driver, "Рекомендованная литература");
    }
    public static WebElement getPicturesLink(WebDriver driver) {
        return getBoldLink(driver, "Картинки");
    }
    public static WebElement getVideosLink(WebDriver driver) {
        return getRegularLink(driver, "Видео записи");
    }
    public static WebElement getAudiosLink(WebDriver driver) {
        return getRegularLink(driver, "Звуковые записи");
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
