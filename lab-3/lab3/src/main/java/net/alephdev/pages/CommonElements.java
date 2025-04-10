package net.alephdev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonElements {
    public static final String GENERAL_FRAME = "vpravo";
    public static WebElement getDownloadLink(WebDriver driver, String link) {
        return driver.findElement(By.xpath("//a[contains(@href, '" + link + "')]"));
    }
}
