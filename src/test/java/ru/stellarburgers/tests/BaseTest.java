package ru.stellarburgers.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.stellarburgers.api.UserApiClient;
import ru.stellarburgers.models.UserModel;
import ru.stellarburgers.steps.*;
import ru.stellarburgers.utils.TestDataGenerator;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;

    // Steps
    protected HomeSteps homeSteps;
    protected LoginSteps loginSteps;
    protected RegisterSteps registerSteps;
    protected ConstructorSteps constructorSteps;
    protected ForgotPasswordSteps forgotPasswordSteps;

    // API Client
    protected UserApiClient userApiClient;

    // Test data
    protected String testUserName;
    protected String testUserEmail;
    protected String testUserPassword;
    protected String invalidPassword;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("chrome")) {
            initChromeDriver();
        } else if (browser.equalsIgnoreCase("yandex")) {
            initYandexDriver();
        } else {
            initChromeDriver();
        }

        // Инициализация API клиента
        userApiClient = new UserApiClient();

        // Генерируем уникальные данные для каждого теста
        testUserName = TestDataGenerator.generateRandomName();
        testUserEmail = TestDataGenerator.generateRandomEmail();
        testUserPassword = TestDataGenerator.generateValidPassword();
        invalidPassword = TestDataGenerator.generateInvalidPassword();

        // Инициализация Steps
        homeSteps = new HomeSteps(driver);
        loginSteps = new LoginSteps(driver);
        registerSteps = new RegisterSteps(driver);
        constructorSteps = new ConstructorSteps(driver);
        forgotPasswordSteps = new ForgotPasswordSteps(driver);

        // Создаем тестового пользователя через API, только если нужно
        if (shouldCreateUser()) {
            userApiClient.createUser(new UserModel(testUserEmail, testUserPassword, testUserName));
        }

    }

    @After
    public void tearDown() {
        // Удаляем пользователя, если он был создан
        if (shouldCreateUser() && userApiClient != null) {
            // Если токен не установлен, пробуем войти
            if (userApiClient.getAccessToken() == null) {
                userApiClient.loginUser(new UserModel(testUserEmail, testUserPassword, null));
            }
            userApiClient.deleteUser();
        }

        if (driver != null) {
            driver.quit();
        }
    }

    protected void initChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        configureDriver();
    }

    protected void initYandexDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tanja\\Desktop\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\tanja\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        // Отключаем безопасность для Яндекса
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-features=VizDisplayCompositor");

        driver = new ChromeDriver(options);
        configureDriver();
    }

    private void configureDriver() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    protected boolean shouldCreateUser(){
        return true; // По умолчанию создаем пользователя
    }
}
