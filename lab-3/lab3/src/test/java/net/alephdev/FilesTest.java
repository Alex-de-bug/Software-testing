package net.alephdev;

import net.alephdev.pages.CommonElements;
import net.alephdev.pages.LeftPanel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class FilesTest {
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

    void checkDomain(String domain, LeftPanel.Type type) throws InterruptedException {
        if(!Utils.checkJsDomain(driver, domain)) {
            Utils.clickAndWait(Objects.requireNonNull(LeftPanel.getLink(driver, type)), type.name(), 2);
            Utils.assertFrameDomain(driver, CommonElements.GENERAL_FRAME, domain);
            driver.switchTo().frame(CommonElements.GENERAL_FRAME);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/images.csv")
    void verifyImageDownload(String link) throws Exception {
        checkDomain(IMG_URL, LeftPanel.Type.PICTURES);
        WebElement downloadLink = CommonElements.getDownloadLink(driver, link);
        Utils.getDownloadObject(driver, downloadLink);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/videos.csv")
    void verifyVideoDownload(String link) throws Exception {
        checkDomain(VIDEO_URL, LeftPanel.Type.VIDEOS);
        WebElement downloadLink = CommonElements.getDownloadLink(driver, link);
        Utils.getDownloadObject(driver, downloadLink);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/audios.csv")
    void verifyAudioDownload(String link) throws Exception {
        checkDomain(AUDIO_URL, LeftPanel.Type.AUDIOS);
        WebElement downloadLink = CommonElements.getDownloadLink(driver, link);
        Utils.getDownloadObject(driver, downloadLink);
    }
}
