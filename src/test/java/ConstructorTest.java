import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static constants.ConstantsApi.URL_MAIN_PAGE;

public class ConstructorTest {
    private User user;
    private String userToken;

    @Before
    public void setUp() {
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;

        //для Яндекс браузера
        //System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
    }

    @Test
    @DisplayName("Переход к разделу \"Булки\"")
    public void goToBunsSectionPassed() {
        MainPage mainPage = open(URL_MAIN_PAGE, MainPage.class);
        mainPage.goToSaucesSection();
        mainPage.goToBunsSection();
    }

    @Test
    @DisplayName("Переход к разделу \"Соусы\"")
    public void goToSaucesSectionPassed() {
        MainPage mainPage = open(URL_MAIN_PAGE, MainPage.class);
        mainPage.goToSaucesSection();
    }

    @Test
    @DisplayName("Переход к разделу \"Начинки\"")
    public void goToFillingsSectionPassed() {
        MainPage mainPage = open(URL_MAIN_PAGE, MainPage.class);
        mainPage.goToFillingsSection();
    }

}
