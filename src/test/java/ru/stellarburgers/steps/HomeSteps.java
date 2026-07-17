package ru.stellarburgers.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.pages.HomePage;
import static org.junit.Assert.assertTrue;

public class HomeSteps {

    private final HomePage homePage;

    public HomeSteps(WebDriver driver) {
        this.homePage = new HomePage(driver);
    }

    @Step("Открыть главную страницу")
    public HomeSteps openHomePage() {
        homePage.openHomePage();
        return this;
    }

    @Step("Проверить загрузку главной страницы")
    public HomeSteps verifyHomePageLoaded() {
        assertTrue("Главная страница не загрузилась", homePage.isHomePageLoaded());
        return this;
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public HomeSteps clickLoginButton() {
        homePage.clickLoginButton();
        return this;
    }

    @Step("Нажать кнопку 'Личный кабинет'")
    public HomeSteps clickProfileButton() {
        homePage.clickProfileButton();
        return this;
    }

}
