package ru.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    private final By registerTitle = By.xpath("//h2[text()='Регистрация']");
    private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By passwordError = By.xpath("//p[@class='input__error text_type_main-default']");
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void openRegisterPage() {
        open(BASE_URL + "register");
        waitForLoad();
    }

    public boolean isRegisterPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(registerTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void enterName(String name) {
        sendKeys(nameInput, name);
    }

    public void enterEmail(String email) {
        sendKeys(emailInput, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    public void clickRegisterButton() {
        click(registerButton);
    }

    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    public void clickLoginLink() {
        click(loginLink);
    }

    public boolean isPasswordErrorDisplayed() {
        return isElementDisplayed(passwordError);
    }

    public String getPasswordErrorText() {
        try {
            return waitForVisibility(passwordError).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isOnRegisterPage() {
        return getCurrentUrl().contains("register") && isElementDisplayed(registerTitle);
    }

    private void waitForLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(registerTitle));
        } catch (Exception e) {
            // Страница не загрузилась
        }
    }
}
