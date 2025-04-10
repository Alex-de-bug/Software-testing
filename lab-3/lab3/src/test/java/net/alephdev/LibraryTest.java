package net.alephdev;

import net.alephdev.pages.CommonElements;
import net.alephdev.pages.LeftPanel;
import net.alephdev.pages.LibraryPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;

class LibraryTest {
    private static WebDriver driver;
    @BeforeAll
    public static void setUpClass() throws InterruptedException {
        driver = Drivers.getDriver();
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("start-page"));
    }

    @AfterAll
    public static void tearDownClass() {
        if (driver != null)
            driver.quit();
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        Utils.clickAndWait(LeftPanel.getLibraryLink(driver), "Библиотека", 2);
        Utils.assertFrameDomain(driver, CommonElements.GENERAL_FRAME, "http://angely-sveta.ru/russian/biblioteka-sveta_ru.htm");
        driver.switchTo().frame(CommonElements.GENERAL_FRAME);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/library.csv")
    void verifyBooks(String name, String link) throws Exception {
        Utils.clickAndWait(LibraryPage.getBookLink(driver, name), name, 2);
        Utils.assertFrameDomain(driver, CommonElements.GENERAL_FRAME, link);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/library.csv")
    void downloadBooks(String name) throws Exception {
        Utils.getDownloadObject(driver, LibraryPage.getBookDownloadLink(driver, name));
    }
}
