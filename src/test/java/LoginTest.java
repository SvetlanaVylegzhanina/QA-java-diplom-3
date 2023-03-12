import apilogic.UserApi;
import apilogic.UserGenerator;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.PasswordRecoveryPage;
import pageobject.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static constants.ConstantsApi.*;

public class LoginTest {
    private User user;
    private String userToken;

    @Before
    public void setUp() {
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;

        //для Яндекс браузера
        //System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");

        user = UserGenerator.getRandomUser();
        Response createUserResponse = UserApi.createUser(user);
        createUserResponse.then().statusCode(200);
        userToken = createUserResponse.path("accessToken");
    }

    @After
    public void clearAfter() {
        UserApi.deleteUser(userToken);
    }

    @Test
    @DisplayName("Авторизация по кнопке \"Войти в аккаунт\" на главной странице")
    public void loginToAccountPassed() {
        MainPage mainPage = open(URL_MAIN_PAGE, MainPage.class);
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        mainPage.checkVisibleElementMakeOrder("Оформить заказ");
    }

    @Test
    @DisplayName("Авторизация через \"Личный кабинет\" на главной странице")
    public void loginToPersonalAccountPassed() {
        MainPage mainPage = open(URL_MAIN_PAGE, MainPage.class);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        mainPage.checkVisibleElementMakeOrder("Оформить заказ");
    }

    @Test
    @DisplayName("Авторизация через форму регистрации")
    public void loginFromRegisterFormPassed() {
        RegistrationPage registrationPage = open(URL_REGISTER_PAGE, RegistrationPage.class);
        registrationPage.clickLoginButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        MainPage mainPage = page(MainPage.class);
        mainPage.checkVisibleElementMakeOrder("Оформить заказ");
    }

    @Test
    @DisplayName("Авторизация через форму восстановления пароля")
    public void loginFromPasswordRecoveryFormPassed() {
        PasswordRecoveryPage passwordRecoveryPage = open(URL_FORGOT_PASSWORD_PAGE, PasswordRecoveryPage.class);
        passwordRecoveryPage.clickLoginButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        MainPage mainPage = page(MainPage.class);
        mainPage.checkVisibleElementMakeOrder("Оформить заказ");
    }


}
