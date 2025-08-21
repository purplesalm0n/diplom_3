package ru.praktikum.stellarburgers.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;

    private final By logoutButton = By.xpath("//button[normalize-space()='Выход']");

    public ProfilePage(WebDriver driver) { this.driver = driver; }

    @Step("Выйти из аккаунта")
    public void logout() { driver.findElement(logoutButton).click(); }

    @Step("Кнопка 'Выход' отображается?")
    public boolean isLogoutButtonDisplayed() {
        return !driver.findElements(logoutButton).isEmpty()
                && driver.findElement(logoutButton).isDisplayed();
    }

    @Step("Страница профиля открыта?")
    public boolean isOpened() {
        return isLogoutButtonDisplayed();
    }
}
