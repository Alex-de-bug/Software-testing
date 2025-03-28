package net.alephdev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public static WebElement getLoginButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[@data-name='login-action']"));
    }
    public static WebElement getLoginWithEmailAndPasswordButton(WebDriver driver) {
        return driver.findElement(By.xpath("//button[@data-name='login-with-email-and-password-action']"));
    }
    public static WebElement getEmailField(WebDriver driver) {
        return driver.findElement(By.xpath("//input[@name='login']"));
    }
    public static WebElement getPasswordField(WebDriver driver) {
        return driver.findElement(By.xpath("//input[@name='password']"));
    }
    public static WebElement getSubmitButton(WebDriver driver) {
        return driver.findElement(By.xpath("//button[@type='submit']"));
    }
    public static WebElement getForgotButton(WebDriver driver) {
        return driver.findElement(By.xpath("//a[@data-name='forgot-password-action']"));
    }
}
