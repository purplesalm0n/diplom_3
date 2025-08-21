package ru.praktikum.stellarburgers.ui.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriveFactory {
    public enum Browser { CHROME, YANDEX }

    public static WebDriver create(Browser browser) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        if (browser == Browser.YANDEX) {
            String yandexBinary = System.getProperty("yandex.binary", "");
            String yandexDriver = System.getProperty("yandex.driver", "");
            if (!yandexBinary.isBlank()) options.setBinary(yandexBinary);
            if (!yandexDriver.isBlank()) {
                ChromeDriverService service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new java.io.File(yandexDriver))
                        .build();
                return new ChromeDriver(service, options);
            }
        }
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }
}
