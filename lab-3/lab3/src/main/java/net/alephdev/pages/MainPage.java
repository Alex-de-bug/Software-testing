package net.alephdev.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    public static void setFrame(WebDriver driver) {
        driver.switchTo().frame("vpravo");
    }
    public static WebElement getFlag(WebDriver driver, String flag) {
        return driver.findElement(By.xpath("//img[@src=\"images/" + flag + ".gif\"]/.."));
    }
    public static WebElement getEvacuation(WebDriver driver) {
        return driver.findElement(By.xpath("//img[@src=\"images/evakuace_ru.gif\"]/.."));
    }
    public static WebElement getTalkButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[contains(@href,\"dil_ru.htm\")]"));
    }
    public static WebElement getAdvResButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[contains(@href,\"http://www.universe-people.com/english/html/reklamni_prostredky/index_en.htm\")]"));
    }
    public static WebElement getLastRev(WebDriver driver) {
        return driver.findElement(By.xpath("//a[contains(@href,\"zmeny_ru.htm\")]"));
    }

    public static WebElement findLinkByHrefAndText(WebDriver driver, String href) {
        return driver.findElement(
            By.xpath(String.format("//a[contains(@href, '%s')]", href))
        );
    }

    public static void verifyLinkExists(WebDriver driver, String href) {
        WebElement link = findLinkByHrefAndText(driver, href);
        if (!link.isDisplayed() || !link.isEnabled()) {
            throw new AssertionError(
                String.format("Link with href='%s' and text='%s' is not visible or not clickable", href)
            );
        }
    }
}
