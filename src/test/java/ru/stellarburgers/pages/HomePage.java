package ru.stellarburgers.pages;

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

    public void clickLoginButton() {
        click(loginButton);
    }

    public void clickProfileButton() {
        click(profileButton);
    }

    public void clickBunsTab() {
        WebElement tab = waitForClickable(bunsTab);
        scrollToElement(tab);
        tab.click();
        waitForTabActivation("Булки");
    }

    public void clickSaucesTab() {
        WebElement tab = waitForClickable(saucesTab);
        scrollToElement(tab);
        tab.click();
        waitForTabActivation("Соусы");
    }

    public void clickFillingsTab() {
        WebElement tab = waitForClickable(fillingsTab);
        scrollToElement(tab);
        tab.click();
        waitForTabActivation("Начинки");
    }

    public String getActiveTabName() {
        try {
            Thread.sleep(500);
            WebElement active = wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab));
            return active.getText();
        } catch (Exception e) {
            return null;
        }
    }

    private void waitForTabActivation(String tabName) {
        try {
            wait.until(ExpectedConditions.textToBe(activeTab, tabName));
        } catch (Exception e) {
            // Таб не активировался
        }
    }

    private void waitForLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(constructorTitle));
        } catch (Exception e) {
            // Страница не загрузилась
        }
    }
}
