package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By loginTitle = By.xpath("//h2[text()='Вход']");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By loginSubmitButton = By.xpath("//button[text()='Войти']");
    private final By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        sendKeys(emailInput, email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        click(loginSubmitButton);
    }

    @Step("Выполнить вход: {email}")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Нажать ссылку 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        click(forgotPasswordLink);
    }

    public boolean isOnLoginPage() {
        return getCurrentUrl().contains("login") && isElementDisplayed(loginTitle);
    }

}
