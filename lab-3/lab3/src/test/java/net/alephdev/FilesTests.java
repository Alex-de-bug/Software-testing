package net.alephdev;

import net.alephdev.pages.FilesPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FilesTests {
    private static WebDriver driver;
    @BeforeAll
    public static void setUpClass() {
        driver = Drivers.getDriver();
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("start-page"));
    }

    @AfterAll
    public static void tearDownClass() {
        if (driver != null)
            driver.quit();
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(0);
    }

    @Test
    void getLinks() throws InterruptedException {
        Utils.clickAndWait(FilesPage.getPicturesLink(driver), "Картинки", 2);
        Utils.assertFrameDomain(driver, "vpravo", "http://angely-sveta.ru/russian/obrazky_ru.htm");

        Utils.clickAndWait(FilesPage.getVideosLink(driver), "Видео", 2);
        Utils.assertFrameDomain(driver, "vpravo", "http://angely-sveta.ru/russian/video_ru.htm");

        Utils.clickAndWait(FilesPage.getAudiosLink(driver), "Аудио", 2);
        Utils.assertFrameDomain(driver, "vpravo", "http://angely-sveta.ru/russian/sound_ru.htm");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/images.csv")
    void verifyImageDownload(String link) throws Exception {
        Utils.clickAndWait(FilesPage.getPicturesLink(driver), "Картинки", 2);
        driver.switchTo().defaultContent();
        driver.switchTo().frame("vpravo");
        WebElement downloadLink = FilesPage.getDownloadLink(driver, link);
        Utils.getDownloadObject(driver, downloadLink);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/videos.csv")
    void verifyVideoDownload(String link) throws Exception {
        Utils.clickAndWait(FilesPage.getVideosLink(driver), "Видео", 2);
        driver.switchTo().defaultContent();
        driver.switchTo().frame("vpravo");
        WebElement downloadLink = FilesPage.getDownloadLink(driver, link);
        Utils.getDownloadObject(driver, downloadLink);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/audios.csv")
    void verifyAudioDownload(String link) throws Exception {
        Utils.clickAndWait(FilesPage.getAudiosLink(driver), "Аудио", 2);
        driver.switchTo().defaultContent();
        driver.switchTo().frame("vpravo");
        WebElement downloadLink = FilesPage.getDownloadLink(driver, link);
        Utils.getDownloadObject(driver, downloadLink);
    }
}
