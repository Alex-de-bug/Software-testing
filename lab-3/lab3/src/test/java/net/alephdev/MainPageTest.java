package net.alephdev;

import net.alephdev.pages.MainPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPageTest {
    private static WebDriver driver;
    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        driver = Drivers.getDriver();
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("start-path"));
        Thread.sleep(2000);
        Utils.login(driver);
        Thread.sleep(2000);
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
    public void testProfileClick() throws InterruptedException {
        Utils.clickAndWait(MainPage.getProfileButton(driver), "Кнопка профиля");
        Utils.assertPage(driver, "profile");
    }
    @Test
    public void testEventsClick() throws InterruptedException {
        Utils.clickAndWait(MainPage.getEventsButton(driver), "Кнопка событий");
        Utils.assertPage(driver, "event-list");
    }
    @Test
    public void testRatingClick() throws InterruptedException {
        Utils.clickAndWait(MainPage.getRatingButton(driver), "Кнопка рейтинга");
        Utils.assertPage(driver, "rating");
    }
    @Test
    public void testSearchClick() throws InterruptedException {
        Utils.clickAndWait(MainPage.getSearchButton(driver), "Кнопка поиска");
        Utils.assertPage(driver, "search");
    }
}
