package ru.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Test;

@Epic("Тестирование Stellar Burgers")
@Feature("Конструктор")
@Story("Переключение между разделами")
public class ConstructorTests extends BaseTest {

    @Override
    protected boolean shouldCreateUser() {
        return false; // Не создаем пользователя через API для тестов конструктора
    }

    @Test
    @DisplayName("Переход к разделу 'Булки' в конструкторе")
    @Description("Проверка активации таба 'Булки' при клике")
    public void switchToBunsSectionTest() {
        constructorSteps
                .openHomePage()
                .verifyConstructorLoaded()
                .switchToSauces()      // Переключаемся на другой таб
                .switchToBuns();
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы' в конструкторе")
    @Description("Проверка активации таба 'Соусы' при клике")
    public void switchToSaucesSectionTest() {
        constructorSteps
                .openHomePage()
                .verifyConstructorLoaded()
                .switchToSauces();
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки' в конструкторе")
    @Description("Проверка активации таба 'Начинки' при клике")
    public void switchToFillingsSectionTest() {
        constructorSteps
                .openHomePage()
                .verifyConstructorLoaded()
                .switchToFillings();
    }

    @Test
    @DisplayName("Переключение между всеми табами последовательно")
    @Description("Проверка последовательного переключения между всеми табами конструктора")
    public void switchBetweenAllTabsTest() {
        constructorSteps
                .openHomePage()
                .verifyConstructorLoaded()
                .switchBetweenAllTabs();
    }
}