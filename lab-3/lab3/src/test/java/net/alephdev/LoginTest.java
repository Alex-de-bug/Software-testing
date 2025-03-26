package net.alephdev;

import org.junit.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private static WebDriver driver;
    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        driver = Drivers.getDriver();
        driver.get(Properties.getProperty("base-url") + Properties.getProperty("start-path"));
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
    }
    @Test
    public void titleTest() {
        assertEquals(driver.getTitle(), "Бесплатный сайт знакомств - регистрация | Мамба");
    }
    @Test
    public void testLogin() throws InterruptedException {
        Utils.login(driver);
    }
}
