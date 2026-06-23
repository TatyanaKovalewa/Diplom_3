package ru.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Test;

@Epic("Тестирование Stellar Burgers")
@Feature("Регистрация")
@Story("Создание нового пользователя")
public class RegistrationTests extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Проверка успешной регистрации с валидными данными")
    public void successfulRegistrationTest() {
        registerSteps
                .openRegisterPage()
                .verifyRegisterPageLoaded()
                .registerUser(testUserName, testUserEmail, testUserPassword);

        loginSteps
                .verifyOnLoginPage();
    }

    @Test
    @DisplayName("Ошибка при регистрации с коротким паролем")
    @Description("Проверка отображения ошибки при пароле менее 6 символов")
    public void registrationWithInvalidPasswordTest() {
        registerSteps
                .openRegisterPage()
                .verifyRegisterPageLoaded()
                .registerUser(testUserName, testUserEmail, invalidPassword)
                .verifyPasswordErrorDisplayed()
                .verifyPasswordErrorText()
                .verifyOnRegisterPage();
    }
}
