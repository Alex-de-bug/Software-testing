package net.alephdev;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Drivers {

    public static WebDriver getDriver() {
        return getDriver(Properties.getProperty("active-driver"));
    }

    public static WebDriver getDriver(String driver) {

        if (driver == null || driver.isEmpty()) {
            throw new RuntimeException("active-driver не установлен в application.properties");
        }

        WebDriver webDriver;

        switch (driver.toLowerCase()) {
            case "chrome":
                String chromePath = Properties.getProperty("chrome-driver-path");
                if (chromePath.isEmpty()) {
                    throw new RuntimeException("chrome-driver-path не установлен в application.properties");
                }
                System.setProperty("webdriver.chrome.driver", chromePath);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*", "--wm-window-animations-disabled", "--animation-duration-scale=0");
                webDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                String firefoxPath = Properties.getProperty("firefox-driver-path");
                if (firefoxPath.isEmpty()) {
                    throw new RuntimeException("firefox-driver-path не установлен в application.properties");
                }
                System.setProperty("webdriver.gecko.driver", firefoxPath);
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("permissions.default.image", 2);
                firefoxOptions.addPreference("toolkit.cosmeticAnimations.enabled", false);
                firefoxOptions.addPreference("webdriver.load.strategy", "eager");
                firefoxOptions.addPreference("browser.cache.disk.enable", true);
                firefoxOptions.addPreference("browser.cache.memory.enable", true);
                //firefoxOptions.setHeadless(true);
                webDriver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new RuntimeException("Некорректный драйвер: " + driver + ". Поддерживаются: chrome, firefox");
        }

        return webDriver;
    }
}