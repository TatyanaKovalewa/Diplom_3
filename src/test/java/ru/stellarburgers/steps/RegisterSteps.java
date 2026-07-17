package ru.stellarburgers.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.pages.RegisterPage;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class RegisterSteps {

    private final RegisterPage registerPage;

    public RegisterSteps(WebDriver driver) {
        this.registerPage = new RegisterPage(driver);
    }

    @Step("Открыть страницу регистрации")
    public RegisterSteps openRegisterPage() {
        registerPage.openRegisterPage();
        return this;
    }

    @Step("Проверить загрузку страницы регистрации")
    public RegisterSteps verifyRegisterPageLoaded() {
        assertTrue("Страница регистрации не загрузилась", registerPage.isRegisterPageLoaded());
        return this;
    }

    @Step("Выполнить регистрацию пользователя: {name}")
    public RegisterSteps registerUser(String name, String email, String password) {
        registerPage.register(name, email, password);
        return this;
    }

    @Step("Нажать ссылку 'Войти'")
    public RegisterSteps clickLoginLink() {
        registerPage.clickLoginLink();
        return this;
    }

    @Step("Проверить, что мы на странице регистрации")
    public RegisterSteps verifyOnRegisterPage() {
        assertTrue("Должны быть на странице регистрации", registerPage.isOnRegisterPage());
        return this;
    }

    @Step("Проверить отображение ошибки пароля")
    public RegisterSteps verifyPasswordErrorDisplayed() {
        assertTrue("Должна появиться ошибка о некорректном пароле",
                registerPage.isPasswordErrorDisplayed());
        return this;
    }

    @Step("Проверить текст ошибки пароля")
    public RegisterSteps verifyPasswordErrorText() {
        String errorText = registerPage.getPasswordErrorText();
        assertNotNull("Текст ошибки не должен быть null", errorText);
        return this;
    }

}
