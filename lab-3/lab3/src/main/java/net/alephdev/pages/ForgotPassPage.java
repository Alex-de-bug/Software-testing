package net.alephdev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPassPage {
    public static WebElement getCancelButtonForForgotPass(WebDriver driver) {
        return driver.findElement(By.xpath("//div[@data-name='go-back-action']"));
    }
    public static WebElement getEnterEmailField(WebDriver driver) {
        return driver.findElement(By.xpath("//input[@name='email-or-phone']"));
    }
    public static WebElement getNextStepButton(WebDriver driver) {
        return driver.findElement(By.xpath("//button[@data-name='next-step-action']"));
    }
    public static WebElement getRestorePasswordSuccessOk(WebDriver driver) {
        return driver.findElement(By.xpath("//button[@data-name='restore-password-success-ok-action']"));
    }
}
