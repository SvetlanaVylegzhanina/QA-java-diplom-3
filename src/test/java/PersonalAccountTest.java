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
import pageobject.PersonalAccountPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static constants.ConstantsApi.*;

public class PersonalAccountTest {
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
    @DisplayName("Переход в \"Личный кабинет\"")
    public void loginToAccountPassed() {
        MainPage mainPage = open(URL_MAIN_PAGE, MainPage.class);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        mainPage.clickPersonalAccountButton();

        PersonalAccountPage personalAccountPage = page(PersonalAccountPage.class);
        personalAccountPage.checkVisibleElementOrderHistory("История заказов");
    }

    @Test
    @DisplayName("Переход из личного кабинета по кнопке \"Конструктор\"")
    public void goFromAccountByConstructorButtonPassed() {
        LoginPage loginPage = open(URL_LOGIN_PAGE, LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccountButton();

        PersonalAccountPage personalAccountPage = page(PersonalAccountPage.class);
        personalAccountPage.checkVisibleElementOrderHistory("История заказов");
        personalAccountPage.clickConstructorButton();

        mainPage.checkVisibleElementMakeOrder("Оформить заказ");
    }

    @Test
    @DisplayName("Переход из личного кабинета по логотипу \"Stellar Burgers\"")
    public void goFromAccountByLogotypePassed() {
        LoginPage loginPage = open(URL_LOGIN_PAGE, LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccountButton();

        PersonalAccountPage personalAccountPage = page(PersonalAccountPage.class);
        personalAccountPage.checkVisibleElementOrderHistory("История заказов");
        personalAccountPage.clickLogotype();

        mainPage.checkVisibleElementMakeOrder("Оформить заказ");
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void logoutFromAccountPassed() {
        LoginPage loginPage = open(URL_LOGIN_PAGE, LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccountButton();

        PersonalAccountPage personalAccountPage = page(PersonalAccountPage.class);
        personalAccountPage.checkVisibleElementOrderHistory("История заказов");
        personalAccountPage.clickExitButton();

        loginPage.checkVisibleElementLogin("Войти");
    }
}
