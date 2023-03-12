package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.enabled;

public class RegistrationPage {
    // локаторы формы "Регистрация"
    // локатор поля "Имя"
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement nameField;
    // локатор поля "Email"
    @FindBy(how = How.XPATH, using = ".//fieldset[2]/div/div/input")
    private SelenideElement emailField;
    // локатор поля "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement passwordField;
    // локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    // локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginButton;

    // локатор ошибки "Некорректный пароль"
    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private SelenideElement wrongPassword;

    // заполнить поле "Имя"
    public void setName(String name) {
        nameField.setValue(name);
    }

    // заполнить поле "Email"
    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    // заполнить поле "Пароль"
    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    // нажать "Зарегистрироваться"
    public void clickRegisterButton() {
        registerButton.shouldBe(enabled).click();
    }

    // нажать "Войти"
    public void clickLoginButton() {
        loginButton.shouldBe(enabled).click();
    }

    @Step("Регистрация в системе")
    public void registration(String name,String email, String password) {
        setName(name);
        setEmailField(email);
        setPasswordField(password);
        clickRegisterButton();
    }

    @Step("Отображение ошибки, если введен неверный пароль")
    public void displayErrorWrongPassword() {
        wrongPassword.shouldBe(Condition.visible);
    }

}
