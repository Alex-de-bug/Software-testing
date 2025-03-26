package net.alephdev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    public static WebElement getDistraction(WebDriver driver) {
        try {
            return driver.findElement(By.xpath("//a[@data-name='close-action']"));
        } catch (Exception e) {
            return null;
        }
    }
    public static WebElement getProfileButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[@data-name='profile-action']"));
    }
    public static WebElement getEventsButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[@data-name='events-action']"));
    }
    public static WebElement getRatingButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[@data-name='link-main-menu__rating-action']"));
    }
    public static WebElement getSearchButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[@data-name='link-main-menu__search-action']"));
    }
}
