package ru.praktikum.stellarburgers.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import ru.praktikum.stellarburgers.ui.config.WebBaseTest;
import ru.praktikum.stellarburgers.ui.pages.LoginPage;
import ru.praktikum.stellarburgers.ui.pages.MainPage;
import ru.praktikum.stellarburgers.ui.steps.ApiUserSteps;
import ru.praktikum.utils.DataGenerator;

import static org.junit.Assert.assertTrue;

@Feature("Навигация: профиль ↔ конструктор")
public class ProfileNavigationTests extends WebBaseTest {

    private final ApiUserSteps api = new ApiUserSteps();
    private String email, pass, token;

    private static final By CONSTRUCTOR_TITLE =
            By.xpath("//h1[normalize-space()='Соберите бургер']");

    @Before
    public void login() {
        email = DataGenerator.randomEmail();
        pass  = DataGenerator.randomPassword();
        api.register(email, pass, DataGenerator.randomName());
        token = api.loginAndGetAccessToken(email, pass);

        new MainPage(driver).clickPersonalAccount();
        new LoginPage(driver).login(email, pass);
    }

    @After
    public void cleanup() { api.delete(token); }

    @Test
    @Story("Из профиля в конструктор по ссылке Консуктор")
    public void fromProfile_toConstructor_byHeaderLink_ok() {
        new MainPage(driver).clickConstructor();
        assertTrue(driver.findElement(CONSTRUCTOR_TITLE).isDisplayed());
    }

    @Test
    @Story("Из профиля в конструктор по логотипу")
    public void fromProfile_toConstructor_byLogo_ok() {
        new MainPage(driver).clickLogo();
        assertTrue(driver.findElement(CONSTRUCTOR_TITLE).isDisplayed());
    }
}