package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private final By forgotTitle = By.xpath("//h2[text()='Восстановление пароля']");
    private final By loginButton = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать кнопку 'Войти' на странице восстановления пароля")
    public void clickLoginButton() {
        click(loginButton);
    }

    public boolean isOnForgotPasswordPage() {
        return getCurrentUrl().contains("forgot-password") && isElementDisplayed(forgotTitle);
    }

}
