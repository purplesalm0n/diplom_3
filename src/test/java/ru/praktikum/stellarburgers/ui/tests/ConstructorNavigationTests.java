package ru.praktikum.stellarburgers.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Test;
import ru.praktikum.stellarburgers.ui.config.WebBaseTest;
import ru.praktikum.stellarburgers.ui.pages.ConstructorPage;
import ru.praktikum.stellarburgers.ui.pages.MainPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Feature("Навигация в конструкторе")
public class ConstructorNavigationTests extends WebBaseTest {

    @Test
    @Story("Клик по 'Булки' – показывает раздел 'Булки'")
    public void openBuns_showsBuns() {
        new MainPage(driver); // просто открыта главная
        ConstructorPage constructor = new ConstructorPage(driver);

        constructor.openSauces();
        constructor.openBuns();

        assertThat(constructor.currentTitle(), equalTo("Булки"));
    }

    @Test
    @Story("Клик по 'Соусы' – показывает раздел 'Соусы'")
    public void openSauces_showsSauces() {
        new MainPage(driver);
        ConstructorPage constructor = new ConstructorPage(driver);

        constructor.openSauces();
        assertThat(constructor.currentTitle(), equalTo("Соусы"));
    }

    @Test
    @Story("Клик по 'Начинки' – показывает раздел 'Начинки'")
    public void openFillings_showsFillings() {
        new MainPage(driver);
        ConstructorPage constructor = new ConstructorPage(driver);

        constructor.openFillings();
        assertThat(constructor.currentTitle(), equalTo("Начинки"));
    }
}