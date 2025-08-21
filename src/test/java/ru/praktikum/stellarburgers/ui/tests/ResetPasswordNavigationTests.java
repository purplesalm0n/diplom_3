package ru.praktikum.stellarburgers.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.praktikum.stellarburgers.ui.config.WebBaseTest;
import ru.praktikum.stellarburgers.ui.pages.LoginPage;
import ru.praktikum.stellarburgers.ui.pages.MainPage;
import ru.praktikum.stellarburgers.ui.pages.ResetPasswordPage;

import static org.junit.Assert.assertTrue;

@Feature("Навигация восстановления пароля")
public class ResetPasswordNavigationTests extends WebBaseTest {

    @Test
    @Story("Переход на форму восстановления и возврат на логин")
    public void openReset_and_backToLogin_ok() {
        new MainPage(driver).clickPersonalAccount();
        new LoginPage(driver).goToReset();

        ResetPasswordPage reset = new ResetPasswordPage(driver);
        assertTrue(reset.isOpened());

        reset.goToLogin();
        assertTrue(new LoginPage(driver).isOpened());
    }
}
