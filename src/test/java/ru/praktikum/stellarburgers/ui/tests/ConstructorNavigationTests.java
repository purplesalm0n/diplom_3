package ru.praktikum.stellarburgers.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.praktikum.stellarburgers.ui.config.WebBaseTest;
import ru.praktikum.stellarburgers.ui.pages.ConstructorPage;
import ru.praktikum.stellarburgers.ui.pages.MainPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Feature("Навигация в конструкторе")
public class ConstructorNavigationTests extends WebBaseTest {

    @Test
    @Story("Переходы по вкладкам: Соусы / Начинки / Булки")
    public void tabs_switch_ok() {
        new MainPage(driver);
        ConstructorPage constructor = new ConstructorPage(driver);

        constructor.openSauces();
        assertThat(constructor.currentTitle(), equalTo("Соусы"));

        constructor.openFillings();
        assertThat(constructor.currentTitle(), equalTo("Начинки"));

        constructor.openBuns();
        assertThat(constructor.currentTitle(), equalTo("Булки"));
    }
}
