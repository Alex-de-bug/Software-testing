package net.alephdev;

import java.io.File;
import java.net.URISyntaxException;
import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.alephdev.pages.MainPage;
import net.alephdev.pages.ProfilePage;

public class ProfileSettingsTest {
    private static WebDriver driver;
    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        driver = Drivers.getDriver();
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("start-path"));
        Thread.sleep(2000);
        Utils.login(driver);
        Thread.sleep(2000);
        Utils.clickAndWait(MainPage.getProfileButton(driver), "Кнопка профиля");
        Utils.assertPage(driver, "profile");
        Thread.sleep(1000);
    }
    @AfterClass
    public static void tearDownClass() {
        if (driver != null)
            driver.quit();
    }
    @Before
    public void setUp() throws InterruptedException {
        Utils.waitForCaptcha(driver);
        Utils.removeDistractions(driver);
    }
    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
    }
    @Test
    public void uploadPhoto() throws InterruptedException, URISyntaxException{
        Utils.clickAndWait(ProfilePage.getUploadPhotoButton(driver), "Кнопка загрузки фото");
        if (driver.getCurrentUrl().contains("/captcha")) {
            System.out.println("Обнаружена капча. Ожидание ручного решения...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Utils.CAPTCHA_TIMEOUT));
            wait.until(webDriver -> !webDriver.getCurrentUrl().contains("/captcha"));
            System.out.println("Капча пройдена. Продолжаем выполнение теста.");
            Utils.removeDistractions(driver);
            Utils.clickAndWait(ProfilePage.getUploadPhotoButton(driver), "Кнопка загрузки фото");
        }
        
        By fileInputLocator = By.cssSelector("input[data-name='input-file-upload']");

        java.net.URL resource = getClass().getResource("/test.png"); 
        if (resource == null) {
            throw new IllegalStateException("Resource 'test.png' not found in classpath. Ensure it’s in src/test/resources.");
        }

        String filePath;
        try {
            filePath = new File(resource.toURI()).getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to convert resource URL to file path", e);
        }
        System.out.println("File path: " + filePath);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(fileInputLocator));
        ((JavascriptExecutor)driver).executeScript(
            "arguments[0].style.display='block'; arguments[0].style.visibility='visible';", 
            fileInput
        );

        fileInput.sendKeys(filePath);
        Thread.sleep(2000);

        Utils.clickAndWait(ProfilePage.getUploadPhotoSaveButton(driver), "Кнопка загрузки фото сохранить");

        Thread.sleep(2000);
    }

    @Test
    public void changeName() throws InterruptedException, URISyntaxException{
        Utils.clickAndWait(ProfilePage.getChangeNameButton(driver), "Кнопка изменения имени");

        if (driver.getCurrentUrl().contains("/captcha")) {
            System.out.println("Обнаружена капча. Ожидание ручного решения...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Utils.CAPTCHA_TIMEOUT));
            wait.until(webDriver -> !webDriver.getCurrentUrl().contains("/captcha"));
            System.out.println("Капча пройдена. Продолжаем выполнение теста.");
            Utils.removeDistractions(driver);
            Utils.clickAndWait(ProfilePage.getChangeNameButton(driver), "Кнопка изменения имени");
        }
    
        Utils.assertPage(driver, "profile/1827786018/dating-profile/field-name/name");
        
        WebElement nameField = driver.findElement(By.cssSelector("input[name='name']"));
        
        nameField.clear();
        
        String newName = "НовоеИмя";
        nameField.sendKeys(newName);
        
        Assert.assertEquals("Имя не было изменено", newName, nameField.getAttribute("value"));
    }

    @Test
    public void changeDescription() throws InterruptedException, URISyntaxException{
        Utils.clickAndWait(ProfilePage.getChangeDescriptionButton(driver), "Кнопка изменения описания");

        if (driver.getCurrentUrl().contains("/captcha")) {
            System.out.println("Обнаружена капча. Ожидание ручного решения...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Utils.CAPTCHA_TIMEOUT));
            wait.until(webDriver -> !webDriver.getCurrentUrl().contains("/captcha"));
            System.out.println("Капча пройдена. Продолжаем выполнение теста.");
            Utils.removeDistractions(driver);
            Utils.clickAndWait(ProfilePage.getChangeDescriptionButton(driver), "Кнопка изменения описания");
        }
    
        Utils.assertPage(driver, "profile/1827786018/dating-profile/field-name/about-me");
        
        WebElement descrField = driver.findElement(By.cssSelector("textarea[name='textarea']"));
        
        descrField.clear();
        
        String newDescr = "что-то о себе";
        descrField.sendKeys(newDescr);
    }
}
