package net.alephdev;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class Drivers {

    public static List<WebDriver> getAllDrivers() {
        return new ArrayList<>(List.of(getDriver("chrome"), getDriver("firefox")));
    }

    public static WebDriver getDriver() {
        return getDriver(Properties.getProperty("active-driver"));
    }

    public static WebDriver getDriver(String driver) {

        if (driver == null || driver.isEmpty()) {
            throw new RuntimeException("active-driver не установлен в application.properties");
        }

        switch (driver.toLowerCase()) {
            case "chrome":
                String chromePath = Properties.getProperty("chrome-driver-path");
                if (chromePath == null || chromePath.isEmpty()) {
                    throw new RuntimeException("chrome-driver-path не установлен в application.properties");
                }
                System.setProperty("webdriver.chrome.driver", chromePath);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*", "--start-maximized");
                return new ChromeDriver(options);

            case "firefox":
                String firefoxPath = Properties.getProperty("firefox-driver-path");
                if (firefoxPath == null || firefoxPath.isEmpty()) {
                    throw new RuntimeException("firefox-driver-path не установлен в application.properties");
                }
                System.setProperty("webdriver.gecko.driver", firefoxPath);
                return new FirefoxDriver();

            default:
                throw new RuntimeException("Некорректный драйвер: " + driver + ". Поддерживаются: chrome, firefox");
        }
    }
}