package ru.praktikum.stellarburgers.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By titleLogin   = By.xpath("//h2[normalize-space()='Вход']");
    private final By emailInput   = By.xpath("//label[normalize-space()='Email']/following-sibling::input");
    private final By passInput    = By.xpath("//label[normalize-space()='Пароль']/following-sibling::input");
    private final By loginButton  = By.xpath("//button[normalize-space()='Войти']");
    private final By registerLink = By.xpath("//a[@href='/register' or normalize-space()='Зарегистрироваться']");
    private final By resetLink    = By.xpath("//a[contains(@href,'forgot') or normalize-space()='Восстановить пароль']");

    public LoginPage(WebDriver driver) { this.driver = driver; }

    @Step("Перейти на форму регистрации")
    public void goToRegister() { driver.findElement(registerLink).click(); }

    @Step("Перейти на форму восстановления пароля")
    public void goToReset() { driver.findElement(resetLink).click(); }

    @Step("Проверить, что открыта форма логина")
    public boolean isOpened() {
        return !driver.findElements(titleLogin).isEmpty()
                || !driver.findElements(loginButton).isEmpty();
    }

    @Step("Войти: {email}")
    public void login(String email, String password) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passInput).clear();
        driver.findElement(passInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
