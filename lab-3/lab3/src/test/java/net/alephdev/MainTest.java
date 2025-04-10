package net.alephdev;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import net.alephdev.pages.EvakuacePage;
import net.alephdev.pages.MainPage;
import net.alephdev.pages.StartPages;

class MainTest {
    private static WebDriver driver;
    @BeforeAll
    public static void setUpClass() {
        driver = Drivers.getDriver();
        driver.get(Properties.getProperty("base-url"));
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
    void testWebsiteEnter() throws InterruptedException {
        driver.get(Properties.getProperty("base-url"));
        Utils.assertDomain(driver, Properties.getProperty("base-url"));
        Utils.clickAndWait(StartPages.getNextButton(driver), "Следующая картинка", 2);
        Utils.assertPage(driver, "russian/default_2_ru.htm");
        Utils.clickAndWait(StartPages.getNextButton(driver), "Следующая картинка", 2);
        Utils.assertPage(driver, "russian/default_14_ru.htm");
        Utils.clickAndWait(StartPages.getNextButton(driver), "Следующая картинка", 2);
        Utils.assertPage(driver, "russian/default_ru.htm");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/flags.csv")
    void testLanguagesByFlag(String flag, String url) throws InterruptedException {
        if(!Utils.checkJsDomain(driver, Properties.getProperty("embedded-page"))) {
            driver.get(Properties.getProperty("base-url") + Properties.getProperty("embedded-page"));
        }
        Utils.clickAndWait(MainPage.getFlag(driver, flag), "Флаг" + flag, 0);
        Utils.assertDomain(driver, url);
        driver.navigate().back();
    }

    @ParameterizedTest
    @CsvSource({
        "EVAKUAČNÍ TERITORIA, VESMÍRNÉ LODĚ, PŘÍSTUPY, OSN",
        "EVACUATION TERRITORIES, SPACESHIPS, ACCESS, UNO",
        "EVAKUIERUNGSTERRITORIEN, WELTRAUMSCHIFFE, ZUGRIFFE, OVN"
    })
    void testEvacuation(String territory, String spaceShip, String access, String uno) throws InterruptedException {

        List<String> info = Arrays.asList(territory, spaceShip, access, uno);

        if(!Utils.checkJsDomain(driver, Properties.getProperty("embedded-page"))) {
            driver.get(Properties.getProperty("base-url") + Properties.getProperty("embedded-page"));
        }
        Utils.clickAndWait(MainPage.getEvacuation(driver), "Эвакуация", 0);

        for (int i = 0; i < 4; i++) {
            EvakuacePage.frameBypass(driver);
            Utils.clickAndWait(EvakuacePage.getEvacuationInfo(driver, info.get(i)), "Информация о спасении", 5);
            Utils.assertFrameDomain(driver, "bottom", "https://www.vesmirni-lide.cz/html/evakuace/evakuace_" + (i + 1) + ".htm");
        }

        driver.navigate().back();
    }

    @Test
    void needToTalk() throws InterruptedException {
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("embedded-page"));
        Utils.clickAndWait(MainPage.getTalkButton(driver), "Разговоры с назиданием от друзей", 2);
        Utils.assertPage(driver, "russian/dil_ru.htm");
    }

    @Test
    void getAdvertisingResources() throws InterruptedException {
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("embedded-page"));
        Utils.clickAndWait(MainPage.getAdvResButton(driver), "Adv ресурсы", 2);
        Utils.assertDomain(driver, "https://www.universe-people.com/english/html/reklamni_prostredky/index_en.htm");
    }

    @Test
    void getReadLastCommits() throws InterruptedException {
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("embedded-page"));
        Utils.clickAndWait(MainPage.getLastRev(driver), "Последняя актуализация", 2);
        Utils.assertPage(driver, "russian/zmeny_ru.htm");
        
        driver.navigate().back();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/last_rev.csv", numLinesToSkip = 0)
    void verifyLinksPresence(String href) throws InterruptedException {
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("embedded-page"));
        Utils.clickAndWait(MainPage.getLastRev(driver), "Последняя актуализация", 2);
        
        MainPage.verifyLinkExists(driver, href);
        
        driver.navigate().back();
    }

}
