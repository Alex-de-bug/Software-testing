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
        WebElement profileButton = MainPage.getProfileButton(driver);
        assert profileButton.isDisplayed() : "Кнопка профиля не отображается на странице";
        profileButton.click();
        Utils.assertPage(driver, "profile");
    }
    @Test
    public void testEventsClick() throws InterruptedException {
        WebElement eventsButton = MainPage.getEventsButton(driver);
        assert eventsButton.isDisplayed() : "Кнопка событий не отображается на странице";
        eventsButton.click();
        Utils.assertPage(driver, "event-list");
    }
    @Test
    public void testRatingClick() throws InterruptedException {
        WebElement ratingButton = MainPage.getRatingButton(driver);
        assert ratingButton.isDisplayed() : "Кнопка рейтинга не отображается на странице";
        ratingButton.click();
        Utils.assertPage(driver, "rating");
    }
    @Test
    public void testSearchClick() throws InterruptedException {
        WebElement searchButton = MainPage.getSearchButton(driver);
        assert searchButton.isDisplayed() : "Кнопка поиска не отображается на странице";
        searchButton.click();
        Utils.assertPage(driver, "search");
    }
}
