package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.enabled;

public class PasswordRecoveryPage {
    // локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginButton;

    @Step("Нажать \"Войти\"")
    public void clickLoginButton() {
        loginButton.shouldBe(enabled).click();
    }
}
