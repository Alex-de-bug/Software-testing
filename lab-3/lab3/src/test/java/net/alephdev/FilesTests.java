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
    private final String IMG_URL = "http://angely-sveta.ru/russian/obrazky_ru.htm";
    private final String VIDEO_URL = "http://angely-sveta.ru/russian/video_ru.htm";
    private final String AUDIO_URL = "http://angely-sveta.ru/russian/sound_ru.htm";

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

    void checkDomain(String domain, FilesPage.Type type) throws InterruptedException {
        if(!Utils.checkJsDomain(driver, domain)) {
            Utils.clickAndWait(FilesPage.getLink(driver, type), type.name(), 2);
            Utils.assertFrameDomain(driver, "vpravo", domain);
            driver.switchTo().frame("vpravo");
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/images.csv")
    void verifyImageDownload(String link) throws Exception {
        checkDomain(IMG_URL, FilesPage.Type.PICTURES);
        WebElement downloadLink = FilesPage.getDownloadLink(driver, link);
        Utils.getDownloadObject(driver, downloadLink);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/videos.csv")
    void verifyVideoDownload(String link) throws Exception {
        checkDomain(VIDEO_URL, FilesPage.Type.VIDEOS);
        WebElement downloadLink = FilesPage.getDownloadLink(driver, link);
        Utils.getDownloadObject(driver, downloadLink);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/audios.csv")
    void verifyAudioDownload(String link) throws Exception {
        checkDomain(AUDIO_URL, FilesPage.Type.AUDIOS);
        WebElement downloadLink = FilesPage.getDownloadLink(driver, link);
        Utils.getDownloadObject(driver, downloadLink);
    }
}
