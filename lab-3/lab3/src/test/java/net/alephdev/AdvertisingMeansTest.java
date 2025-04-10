package net.alephdev;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.alephdev.pages.AdvMeansPage;
import net.alephdev.pages.MainPage;

class AdvertisingMeansTest {
    private static WebDriver driver;
    @BeforeAll
    public static void setUpClass() throws InterruptedException {
        driver = Drivers.getDriver();
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("embedded-page"));
        Utils.clickAndWait(MainPage.getAdvResButton(driver), "Adv ресурсы", 2);
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
    void readWarningToPeople() throws InterruptedException {
        Utils.clickAndWait(AdvMeansPage.getWarnButton(driver), "Warn to people", 2);
        Utils.assertDomain(driver, "https://www.universe-people.com/english/html/reklamni_prostredky/varovani_lidem_en.htm");
        driver.navigate().back();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/download_files_warn.csv", numLinesToSkip = 0)
    void verifyFileDownloadWarnPeople(String link) throws Exception {
        Utils.clickAndWait(AdvMeansPage.getWarnButton(driver), "Warn to people", 2);
        WebElement downloadLink = AdvMeansPage.getDownloadLink(driver, link);
        Utils.getDownloadObject(driver, downloadLink);
        driver.navigate().back();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/download_files_logos.csv", numLinesToSkip = 0)
    void verifyFileDownloadLogosAndBanners(String link) throws Exception {
        Utils.clickAndWait(AdvMeansPage.getLogosBannersButton(driver), "Logos and banners", 2);
        WebElement downloadLink = AdvMeansPage.getDownloadLink(driver, link);
        Utils.getDownloadObject(driver, downloadLink);
        driver.navigate().back();
    }
}
