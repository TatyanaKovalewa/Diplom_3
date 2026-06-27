package ru.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.After;
import org.junit.Test;
import ru.stellarburgers.models.UserModel;
import ru.stellarburgers.utils.TestDataGenerator;

@Epic("Тестирование Stellar Burgers")
@Feature("Регистрация")
@Story("Создание нового пользователя")
public class RegistrationTests extends BaseTest {

    private String createdUserEmail;
    private String createdUserPassword;

    @Override
    protected boolean shouldCreateUser() {
        return false; // Не создаем пользователя через API для тестов регистрации
    }

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Проверка успешной регистрации с валидными данными")
    public void successfulRegistrationTest() {
        // Создаем НОВОГО пользователя через UI (не используем пользователя из BaseTest)
        createdUserEmail = TestDataGenerator.generateRandomEmail();
        String newUserName = TestDataGenerator.generateRandomName();
        createdUserPassword = TestDataGenerator.generateValidPassword();

        registerSteps
                .openRegisterPage()
                .verifyRegisterPageLoaded()
                .registerUser(newUserName, createdUserEmail, createdUserPassword);

        loginSteps
                .verifyOnLoginPage();
    }

    @Test
    @DisplayName("Ошибка при регистрации с коротким паролем")
    @Description("Проверка отображения ошибки при пароле менее 6 символов")
    public void registrationWithInvalidPasswordTest() {
        String newUserEmail = TestDataGenerator.generateRandomEmail();
        String newUserName = TestDataGenerator.generateRandomName();

        registerSteps
                .openRegisterPage()
                .verifyRegisterPageLoaded()
                .registerUser(newUserName, newUserEmail , invalidPassword)
                .verifyPasswordErrorDisplayed()
                .verifyPasswordErrorText()
                .verifyOnRegisterPage();
    }

    @After
    public void cleanUpUser() {
        // Удаляем пользователя, если он был создан через UI
        if (createdUserEmail != null && createdUserPassword != null && !createdUserPassword.equals(invalidPassword)) {
            try {
                userApiClient.loginUser(new UserModel(createdUserEmail, createdUserPassword, null));
                userApiClient.deleteUser();
                System.out.println("Пользователь удален: " + createdUserEmail);
            } catch (Exception e) {
                System.out.println("Не удалось удалить пользователя: " + e.getMessage());
            }
        }
    }

}
