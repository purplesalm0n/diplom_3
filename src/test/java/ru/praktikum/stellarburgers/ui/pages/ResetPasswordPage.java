package ru.praktikum.stellarburgers.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    private final WebDriver driver;

    private final By title = By.xpath("//h2[normalize-space()='Восстановление пароля']");
    private final By loginLink = By.xpath("//a[@href='/login' or normalize-space()='Войти']");

    public ResetPasswordPage(WebDriver driver) { this.driver = driver; }

    @Step("Страница восстановления открыта?")
    public boolean isOpened() {
        return !driver.findElements(title).isEmpty();
    }

    @Step("Вернуться на форму логина")
    public void goToLogin() {
        driver.findElement(loginLink).click();
    }
}
