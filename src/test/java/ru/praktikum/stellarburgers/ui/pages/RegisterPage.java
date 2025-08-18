package ru.praktikum.stellarburgers.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;

    public final By nameInput     = By.xpath("//label[text()='Имя']/following-sibling::input");
    public final By emailInput    = By.xpath("//label[text()='Email']/following-sibling::input");
    public final By passwordInput = By.xpath("//label[text()='Пароль']/following-sibling::input");
    public final By registerButton= By.xpath("//button[normalize-space()='Зарегистрироваться']");
    public final By errorText     = By.xpath("//p[contains(@class,'input__error')]");
    public final By loginLink     = By.xpath("//a[normalize-space()='Войти']");

    public RegisterPage(WebDriver driver) { this.driver = driver; }

    @Step("Имя: {name}")
    public void setName(String name) { driver.findElement(nameInput).sendKeys(name); }

    @Step("Email: {email}")
    public void setEmail(String email) { driver.findElement(emailInput).sendKeys(email); }

    @Step("Пароль")
    public void setPassword(String password) { driver.findElement(passwordInput).sendKeys(password); }

    @Step("Отправить форму регистрации")
    public void submit() { driver.findElement(registerButton).click(); }

    public By error() { return errorText; }
}
