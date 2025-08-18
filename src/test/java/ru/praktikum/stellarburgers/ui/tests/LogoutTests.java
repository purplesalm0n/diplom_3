package ru.praktikum.stellarburgers.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.praktikum.stellarburgers.ui.config.WebBaseTest;
import ru.praktikum.stellarburgers.ui.pages.LoginPage;
import ru.praktikum.stellarburgers.ui.pages.MainPage;
import ru.praktikum.stellarburgers.ui.pages.ProfilePage;
import ru.praktikum.stellarburgers.ui.steps.ApiUserSteps;
import ru.praktikum.utils.DataGenerator;

import static org.junit.Assert.assertTrue;

@Feature("Логаут")
public class LogoutTests extends WebBaseTest {

    private final ApiUserSteps api = new ApiUserSteps();
    private String email, pass, token;

    @Before
    public void login() {
        email = DataGenerator.randomEmail();
        pass  = DataGenerator.randomPassword();
        api.register(email, pass, DataGenerator.randomName());
        token = api.loginAndGetAccessToken(email, pass);

        MainPage main = new MainPage(driver);
        main.clickPersonalAccount();
        new LoginPage(driver).login(email, pass);
    }

    @After
    public void cleanup() {
        api.delete(token);
    }

    @Test
    @Story("Выход из профиля")
    public void logout_ok() {
        MainPage main = new MainPage(driver);
        main.clickPersonalAccount();                // зайти в личный кабинет
        new ProfilePage(driver).logout();           // выполнить выход

        assertTrue(new LoginPage(driver).isOpened());// проверка: открыта форма входа
    }
}
