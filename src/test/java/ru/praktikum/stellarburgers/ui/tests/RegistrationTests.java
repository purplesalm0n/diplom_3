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
import ru.praktikum.stellarburgers.ui.pages.RegisterPage;
import ru.praktikum.stellarburgers.ui.steps.ApiUserSteps;
import ru.praktikum.utils.DataGenerator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@Feature("Регистрация")
public class RegistrationTests extends WebBaseTest {

    private final ApiUserSteps api = new ApiUserSteps();
    private String savedEmail, savedPass, token;

    @After
    public void cleanup() {
        if (token == null || token.isBlank()) {
            token = api.tryLoginGetTokenOrNull(savedEmail, savedPass);
        }
        api.delete(token);
    }

    @Test
    @Story("Успешная регистрация")
    public void register_success() {
        new MainPage(driver).clickEnterAccount();
        new LoginPage(driver).goToRegister();

        savedEmail = DataGenerator.randomEmail();
        savedPass  = DataGenerator.randomPassword();
        String name= DataGenerator.randomName();

        RegisterPage reg = new RegisterPage(driver);
        reg.setName(name);
        reg.setEmail(savedEmail);
        reg.setPassword(savedPass);
        reg.submit();

        token = api.loginAndGetAccessToken(savedEmail, savedPass);
        assertThat(token, containsString("Bearer "));
    }

    @Test
    @Story("Ошибка для некорректного (короткого) пароля")
    public void register_shortPassword_error() {
        new MainPage(driver).clickEnterAccount();
        new LoginPage(driver).goToRegister();

        savedEmail = DataGenerator.randomEmail();
        savedPass  = "12345";

        RegisterPage reg = new RegisterPage(driver);
        reg.setName(DataGenerator.randomName());
        reg.setEmail(savedEmail);
        reg.setPassword(savedPass);
        reg.submit();

        String text = driver.findElement(reg.error()).getText();
        assertThat(text, containsString("Некорректный пароль"));
    }
}
