package ru.praktikum.stellarburgers.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;

    private final By personalAccountLink =
            By.xpath("//p[contains(@class,'AppHeader_header__linkText__')][normalize-space()='Личный Кабинет']");
    private final By constructorLink =
            By.xpath("//p[contains(@class,'AppHeader_header__linkText__')][normalize-space()='Конструктор']");
    private final By logoLink =
            By.xpath("//div[contains(@class,'AppHeader_header__logo')]/a");
    private final By loginAccountButton =
            By.xpath("//button[normalize-space()='Войти в аккаунт']");

    public MainPage(WebDriver driver) { this.driver = driver; }

    @Step("Перейти в личный кабинет")
    public void clickPersonalAccount() { driver.findElement(personalAccountLink).click(); }

    @Step("Перейти в конструктор")
    public void clickConstructor() { driver.findElement(constructorLink).click(); }

    @Step("Клик по логотипу")
    public void clickLogo() { driver.findElement(logoLink).click(); }

    @Step("Войти в аккаунт с главной")
    public void clickEnterAccount() { driver.findElement(loginAccountButton).click(); }
}
