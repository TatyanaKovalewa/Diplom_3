package ru.stellarburgers.pages;

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

    public void enterEmail(String email) {
        sendKeys(emailInput, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    public void clickLoginButton() {
        click(loginSubmitButton);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void clickForgotPasswordLink() {
        click(forgotPasswordLink);
    }

    public boolean isOnLoginPage() {
        return getCurrentUrl().contains("login") && isElementDisplayed(loginTitle);
    }

}
