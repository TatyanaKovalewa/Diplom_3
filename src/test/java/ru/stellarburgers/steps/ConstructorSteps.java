package ru.stellarburgers.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.pages.HomePage;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ConstructorSteps {

    private final HomePage homePage;

    public ConstructorSteps(WebDriver driver) {
        this.homePage = new HomePage(driver);
    }

    @Step("Открыть главную страницу")
    public ConstructorSteps openHomePage() {
        homePage.openHomePage();
        return this;
    }

    @Step("Проверить загрузку конструктора")
    public ConstructorSteps verifyConstructorLoaded() {
        assertTrue("Конструктор не загрузился", homePage.isHomePageLoaded());
        return this;
    }

    @Step("Проверить активный таб: {expectedTab}")
    public ConstructorSteps verifyActiveTab(String expectedTab) {
        String actualTab = homePage.getActiveTabName();
        assertEquals("Активным должен быть таб '" + expectedTab + "'",
                expectedTab, actualTab);
        return this;
    }

    @Step("Проверить, что таб 'Булки' активен по умолчанию")
    public ConstructorSteps verifyDefaultTab() {
        String activeTab = homePage.getActiveTabName();
        assertEquals("По умолчанию активен таб 'Булки'", "Булки", activeTab);
        return this;
    }

    @Step("Переключиться на таб 'Булки'")
    public ConstructorSteps switchToBuns() {
        homePage.clickBunsTab();
        verifyActiveTab("Булки");
        return this;
    }

    @Step("Переключиться на таб 'Соусы'")
    public ConstructorSteps switchToSauces() {
        homePage.clickSaucesTab();
        verifyActiveTab("Соусы");
        return this;
    }

    @Step("Переключиться на таб 'Начинки'")
    public ConstructorSteps switchToFillings() {
        homePage.clickFillingsTab();
        verifyActiveTab("Начинки");
        return this;
    }

    @Step("Переключиться между всеми табами последовательно")
    public ConstructorSteps switchBetweenAllTabs() {
        homePage.clickSaucesTab();
        verifyActiveTab("Соусы");

        homePage.clickFillingsTab();
        verifyActiveTab("Начинки");

        homePage.clickBunsTab();
        verifyActiveTab("Булки");
        return this;
    }
}
