import apilogic.UserApi;
import model.UserCredentials;
import apilogic.UserGenerator;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.User;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static constants.ConstantsApi.URL_REGISTER_PAGE;

public class RegistrationTest {
    private User user;
    private String userToken;

    @Before
    public void setUp() {
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;

        //для Яндекс браузера
        //System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");

        user = UserGenerator.getRandomUser();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void registrationPassed() {
        RegistrationPage registrationPage = open(URL_REGISTER_PAGE, RegistrationPage.class);
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());

        LoginPage loginPage = page(LoginPage.class);
        loginPage.checkVisibleElementLogin("Войти");

        Response response = UserApi.loginUser(new UserCredentials(user.getEmail(), user.getPassword()));
        userToken = response.path("accessToken");
        UserApi.deleteUser(userToken);
    }

    @Test
    @Step("Регистрация с некорректным паролем")
    public void registrationWithWrongPasswordFailed() {
        RegistrationPage registrationPage = open(URL_REGISTER_PAGE, RegistrationPage.class);
        registrationPage.registration(user.getName(), user.getEmail(), "pw_!");
        registrationPage.displayErrorWrongPassword();
    }
}
