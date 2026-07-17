package ru.stellarburgers.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stellarburgers.pages.LoginPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private final WebDriver driver;
    private final LoginPage loginPage;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    @Step("Выполнить вход с email: {email}")
    public LoginSteps login(String email, String password) {
        loginPage.login(email, password);
        return this;
    }

    @Step("Нажать ссылку 'Восстановить пароль'")
    public LoginSteps clickForgotPasswordLink() {
        loginPage.clickForgotPasswordLink();
        return this;
    }

    @Step("Проверить, что мы на странице входа")
    public LoginSteps verifyOnLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("login"));

        assertTrue("Должны быть на странице входа", loginPage.isOnLoginPage());
        return this;
    }

    @Step("Проверить переход на страницу входа")
    public LoginSteps verifyRedirectToLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("login"));

        assertTrue("Должны перейти на страницу входа", loginPage.isLoginPageLoaded());
        return this;
    }
}
