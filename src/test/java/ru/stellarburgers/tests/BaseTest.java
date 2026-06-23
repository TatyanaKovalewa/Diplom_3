package ru.stellarburgers.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.stellarburgers.steps.*;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    // Steps
    protected HomeSteps homeSteps;
    protected LoginSteps loginSteps;
    protected RegisterSteps registerSteps;
    protected ConstructorSteps constructorSteps;
    protected ForgotPasswordSteps forgotPasswordSteps;

    // Test data
    protected String testUserName;
    protected String testUserEmail;
    protected String testUserPassword = "Test123456";
    protected String invalidPassword = "12345";

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

        // Генерируем уникальные данные для каждого теста
        testUserName = "Тестовый Пользователь_" + System.currentTimeMillis() % 10000;
        testUserEmail = "test_" + System.currentTimeMillis() + "@test.com";

        // Инициализация Steps
        homeSteps = new HomeSteps(driver);
        loginSteps = new LoginSteps(driver);
        registerSteps = new RegisterSteps(driver);
        constructorSteps = new ConstructorSteps(driver);
        forgotPasswordSteps = new ForgotPasswordSteps(driver);
    }

    @After
    public void tearDown() {
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
        driver = new ChromeDriver(options);
        configureDriver();
    }

    private void configureDriver() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

}
