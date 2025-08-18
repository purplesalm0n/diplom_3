package ru.praktikum.stellarburgers.ui.config;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import ru.praktikum.stellarburgers.ui.config.Env;
import ru.praktikum.stellarburgers.ui.config.DriveFactory.Browser;

public abstract class WebBaseTest {
    protected WebDriver driver;

    @Before
    @Step("Init WebDriver and open baseUrl")
    public void setUp() {
        String param = System.getProperty("browser", "chrome").toUpperCase();
        Browser browser = param.startsWith("Y") ? Browser.YANDEX : Browser.CHROME;
        driver = DriveFactory.create(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7)); // <— добавлено
        driver.get(Env.baseUrl());
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Rule
    public TestWatcher allureWatcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            try {
                if (driver instanceof TakesScreenshot) {
                    byte[] shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.getLifecycle().addAttachment("screenshot", "image/png", "png", shot);
                }
            } catch (Exception ignored) { }
        }
    };
}