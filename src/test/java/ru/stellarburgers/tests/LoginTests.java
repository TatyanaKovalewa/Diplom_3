package ru.stellarburgers.tests;

import io.qameta.allure.Description;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

@Epic("Тестирование Stellar Burgers")
@Feature("Авторизация")
@Story("Вход в систему")
public class LoginTests extends BaseTest {

    // Пользователь будет создан через API

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    @Description("Проверка входа через кнопку 'Войти в аккаунт' на главной странице")
    public void loginViaMainPageButtonTest() {
        homeSteps
                .openHomePage()
                .verifyHomePageLoaded()
                .clickLoginButton();

        loginSteps
                .verifyOnLoginPage()
                .login(testUserEmail, testUserPassword);

        homeSteps
                .verifyHomePageLoaded();
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка входа через кнопку 'Личный кабинет' в шапке сайта")
    public void loginViaProfileButtonTest() {
        homeSteps
                .openHomePage()
                .verifyHomePageLoaded()
                .clickProfileButton();

        loginSteps
                .verifyRedirectToLoginPage()
                .login(testUserEmail, testUserPassword);

        homeSteps
                .verifyHomePageLoaded();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через кнопку 'Войти' на странице регистрации")
    public void loginViaRegisterFormButtonTest() {
        registerSteps
                .openRegisterPage()
                .verifyRegisterPageLoaded()
                .clickLoginLink();

        loginSteps
                .verifyRedirectToLoginPage()
                .login(testUserEmail, testUserPassword);

        homeSteps
                .verifyHomePageLoaded();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через кнопку 'Войти' на странице восстановления пароля")
    public void loginViaForgotPasswordFormButtonTest() {
        homeSteps
                .openHomePage()
                .verifyHomePageLoaded()
                .clickLoginButton();

        loginSteps
                .verifyRedirectToLoginPage()
                .clickForgotPasswordLink();

        forgotPasswordSteps
                .verifyOnForgotPasswordPage()
                .clickLoginButton();

        loginSteps
                .verifyOnLoginPage()
                .login(testUserEmail, testUserPassword);

        homeSteps
                .verifyHomePageLoaded();
    }
}
