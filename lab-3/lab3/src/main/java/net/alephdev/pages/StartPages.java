package net.alephdev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartPages {
    public static WebElement getNextButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a"));
    }
}
