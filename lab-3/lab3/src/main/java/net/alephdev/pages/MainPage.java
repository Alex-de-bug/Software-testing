package net.alephdev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    public static void setFrame(WebDriver driver) {
        driver.switchTo().frame("vpravo");
    }
    public static WebElement getFlag(WebDriver driver, String flag) {
        setFrame(driver);
        return driver.findElement(By.xpath("//img[@src=\"images/" + flag + ".gif\"]/.."));
    }
}
