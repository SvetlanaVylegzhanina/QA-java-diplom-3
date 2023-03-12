package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    // локаторы формы "Вход"
    // локатор поля "email"
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement emailField;
    // локатор поля "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement passwordField;
    // локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement loginButton;

    // заполнить поле "email"
    public void setEmail(String email) {
        emailField.setValue(email);
    }

    // заполнить поле "Пароль"
    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    // нажать "Войти"
    public void clickLoginButton() {
        loginButton.shouldBe(enabled).click();
    }

    @Step("Авторизоваться в системе")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    @Step("Проверить отображение элемента с текстом \"Войти\"")
    public void checkVisibleElementLogin(String text) {
        $(byText(text)).shouldBe(Condition.visible);
    }

}