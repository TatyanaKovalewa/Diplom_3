package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By profileButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By constructorTitle = By.xpath("//h1[text()='Соберите бургер']");
    private final By bunsTab = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']/parent::div");
    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть главную страницу")
    public void openHomePage() {
        open(BASE_URL);
        waitForLoad();
    }

    public boolean isHomePageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(constructorTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        click(loginButton);
    }

    @Step("Нажать кнопку 'Личный кабинет'")
    public void clickProfileButton() {
        click(profileButton);
    }

    @Step("Нажать таб 'Булки'")
    public void clickBunsTab() {
        WebElement tab = waitForClickable(bunsTab);
        scrollToElement(tab);
        tab.click();
        waitForTabActivation("Булки");
    }

    @Step("Нажать таб 'Соусы'")
    public void clickSaucesTab() {
        WebElement tab = waitForClickable(saucesTab);
        scrollToElement(tab);
        tab.click();
        waitForTabActivation("Соусы");
    }

    @Step("Нажать таб 'Начинки'")
    public void clickFillingsTab() {
        WebElement tab = waitForClickable(fillingsTab);
        scrollToElement(tab);
        tab.click();
        waitForTabActivation("Начинки");
    }

    public boolean isActiveTab(String tabName) {
        try {
            wait.until(ExpectedConditions.textToBe(activeTab, tabName));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void waitForTabActivation(String tabName) {
        wait.until(ExpectedConditions.textToBe(activeTab, tabName));
    }

    private void waitForLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(constructorTitle));
        } catch (Exception e) {
            // Страница не загрузилась
        }
    }
}
