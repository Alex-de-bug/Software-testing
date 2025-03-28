package net.alephdev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage {
    public static WebElement getUploadPhotoButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[@data-name='upload-photo']"));
    }
    public static WebElement getUploadPhotoSaveButton(WebDriver driver) {
        return driver.findElement(By.xpath("//button[@class='sc-n1mfaw-3 jFWABs']"));
    }
    public static WebElement getChangeNameButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[@data-name='profile-name-action']"));
    }
    public static WebElement getChangeDescriptionButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[@data-name='profile-greeting']"));
    }
}
