package ru.praktikum.stellarburgers.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
    private final WebDriver driver;

    private final By bunsTab =
            By.xpath("//span[@class='text text_type_main-default' and text()='Булки']");
    private final By saucesTab =
            By.xpath("//span[@class='text text_type_main-default' and text()='Соусы']");
    private final By fillingsTab =
            By.xpath("//span[@class='text text_type_main-default' and text()='Начинки']");
    private final By activeTab =
            By.xpath("//div[contains(@class,'tab_tab_type_current')]//span");

    public ConstructorPage(WebDriver driver) { this.driver = driver; }

    @Step("Открыть 'Булки'")
    public void openBuns() { driver.findElement(bunsTab).click(); }

    @Step("Открыть 'Соусы'")
    public void openSauces() { driver.findElement(saucesTab).click(); }

    @Step("Открыть 'Начинки'")
    public void openFillings() { driver.findElement(fillingsTab).click(); }

    @Step("Текст активного таба")
    public String activeTabText() { return driver.findElement(activeTab).getText(); }

    public By activeTabLocator() { return activeTab; }

    @Step("Текущий заголовок активной вкладки")
    public String currentTitle() { return driver.findElement(activeTab).getText(); }
}
