package ru.praktikum.stellarburgers.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.praktikum.stellarburgers.ui.config.WebBaseTest;
import ru.praktikum.stellarburgers.ui.pages.LoginPage;
import ru.praktikum.stellarburgers.ui.pages.MainPage;
import ru.praktikum.stellarburgers.ui.pages.ProfilePage;
import ru.praktikum.stellarburgers.ui.steps.ApiUserSteps;
import ru.praktikum.utils.DataGenerator;

import static org.junit.Assert.assertTrue;

@Feature("Логин")
public class LoginTests extends WebBaseTest {

    private final ApiUserSteps api = new ApiUserSteps();
    private String email, pass, accessToken;

    private void ensureUserExists() {
        email = DataGenerator.randomEmail();
        pass  = DataGenerator.randomPassword();
        api.register(email, pass, DataGenerator.randomName());
    }

    @After
    public void cleanup() {
        if (accessToken == null || accessToken.isBlank()) {
            accessToken = api.tryLoginGetTokenOrNull(email, pass);
        }
        api.delete(accessToken);
    }

    @Test
    @Story("Вход с главной по кнопке 'Войти в аккаунт'")
    public void login_fromMainButton_ok() {
        ensureUserExists();

        MainPage main = new MainPage(driver);
        main.clickEnterAccount();

        new LoginPage(driver).login(email, pass);

        // чтобы убедиться, что авторизованы — заходим в ЛК и видим кнопку Выход
        main.clickPersonalAccount();
        assertTrue(new ProfilePage(driver).isOpened());
    }

    @Test
    @Story("Вход через ссылку 'Личный кабинет' в шапке")
    public void login_fromHeader_ok() {
        ensureUserExists();

        MainPage main = new MainPage(driver);
        main.clickPersonalAccount();          // открываем форму входа

        new LoginPage(driver).login(email, pass);

        main.clickPersonalAccount();          // снова в ЛК — видим кнопку Выход
        assertTrue(new ProfilePage(driver).isOpened());
    }
}
