package net.alephdev;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import net.alephdev.pages.Evakuace;
import net.alephdev.pages.MainPage;
import net.alephdev.pages.StartPages;

class StartTest {
    private static WebDriver driver;
    @BeforeAll
    public static void setUpClass() throws InterruptedException {
        driver = Drivers.getDriver();
        driver.get(Properties.getProperty("base-url"));
        Thread.sleep(2000);
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
        Thread.sleep(1000);
        for(int i = 0; i < 3; i++)
            Utils.clickAndWait(StartPages.getNextButton(driver), "Следующая картинка");
        Utils.assertPage(driver, "russian/default_ru.htm");
    }

    @ParameterizedTest
    @CsvSource({
            "flag_czech, https://www.vesmirni-lide.cz/",
            "flag_uk, https://www.universe-people.com/",
            "flag_germany, http://www.himmels-engel.de/",
            "flag_spain, http://www.angeles-luz.es/",
            "flag_france, http://www.anges-lumiere.fr/",
            "flag_italy, http://www.angelo-luce.it/",
            "flag_poland, http://www.anioly-nieba.pl/",
            "flag_hungary, http://www.feny-angyalai.hu/",
            "flag_croatia, http://www.andjeli-neba.com.hr/",
            "flag_portugal, http://www.anjos-ceu.eu/",
            "flag_bulgaria, http://www.angeli-raja.eu/",
            "flag_netherlands, http://www.engelen-hemel.nl/",
            "flag_romania, http://www.ingerii-cerului.ro/",
            "flag_norway, https://www.universe-people.com/norsk/",
            "flag_sweden, http://www.himmelens-anglar.se/",
            "flag_finland, https://www.universe-people.com/suomi/",
            "flag_turkey, http://www.cennetin-melekleri.web.tr/",
            "flag_slovenia, https://www.universe-people.com/slovenski/",
            "flag_greece, https://www.universe-people.com/ellhnika/",
            "flag_china, https://www.universe-people.com/chinese/",
            "flag_syria, https://www.angels-light-arab.org/",
    })
    void testLanguagesByFlag(String flag, String url) throws InterruptedException {
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("start-page"));
        Utils.assertDomain(driver, Properties.getProperty("base-url"));
        Utils.clickAndWait(MainPage.getFlag(driver, flag), "Флаг" + flag, 0);
        Utils.assertDomain(driver, url);
    }

    @ParameterizedTest
    @CsvSource({
        "EVAKUAČNÍ TERITORIA, VESMÍRNÉ LODĚ, PŘÍSTUPY, OSN",
        "EVACUATION TERRITORIES, SPACESHIPS, ACCESS, UNO",
        "EVAKUIERUNGSTERRITORIEN, WELTRAUMSCHIFFE, ZUGRIFFE, OVN"
    })
    void testEvacuation(String territory, String spaceShip, String access, String uno) throws InterruptedException {

        List<String> info = Arrays.asList(territory, spaceShip, access, uno);

        driver.get(Properties.getProperty("base-url") + Properties.getProperty("embedded-page"));
        Utils.assertDomain(driver, Properties.getProperty("base-url"));
        Utils.clickAndWait(MainPage.getEvacuation(driver), "Эвакуация", 500);

        for (int i = 0; i < 4; i++) {
            Evakuace.frameBypass(driver);
            Utils.clickAndWait(Evakuace.getEvacuationInfo(driver, info.get(i)), "Информация о спасении", 5);
            Utils.assertFrameDomain(driver, "bottom", "https://www.vesmirni-lide.cz/html/evakuace/evakuace_" + (i + 1) + ".htm");
        }

        driver.navigate().back();
    }

    @Test
    void needToTalk() throws InterruptedException {
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("embedded-page"));
        Utils.assertDomain(driver, Properties.getProperty("base-url"));
        Utils.clickAndWait(MainPage.getTalkButton(driver), "Разговоры с назиданием от друзей", 2);
        Utils.assertDomain(driver, "http://angely-sveta.ru/russian/dil_ru.htm");
    }

    @Test
    void getAdvertisingResources() throws InterruptedException {
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("embedded-page"));
        Utils.assertDomain(driver, Properties.getProperty("base-url"));
        Utils.clickAndWait(MainPage.getAdvResButton(driver), "Adv ресурсы", 2);
        Utils.assertDomain(driver, "https://www.universe-people.com/english/html/reklamni_prostredky/index_en.htm");
    }
}
