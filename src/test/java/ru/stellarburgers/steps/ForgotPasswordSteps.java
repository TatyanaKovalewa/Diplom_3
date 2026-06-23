package ru.stellarburgers.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stellarburgers.pages.ForgotPasswordPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class ForgotPasswordSteps {

    private final WebDriver driver;
    private final ForgotPasswordPage forgotPasswordPage;

    public ForgotPasswordSteps(WebDriver driver) {
        this.driver = driver;
        this.forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @Step("Нажать кнопку 'Войти' на странице восстановления")
    public ForgotPasswordSteps clickLoginButton() {
        forgotPasswordPage.clickLoginButton();
        return this;
    }

    @Step("Проверить, что мы на странице восстановления пароля")
    public ForgotPasswordSteps verifyOnForgotPasswordPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("forgot-password"));

        assertTrue("Должны быть на странице восстановления пароля",
                forgotPasswordPage.isOnForgotPasswordPage());
        return this;
    }

}
