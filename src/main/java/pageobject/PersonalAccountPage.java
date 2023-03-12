package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PersonalAccountPage {

    // локатор кнопки "Выход"
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private static SelenideElement logoutButton;

    // локатор кнопки "Конструктор"
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private static SelenideElement constructorButton;

    // локатор логотипа "Stellar Burgers"
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private static SelenideElement logotype;

    @Step("Проверить отображение элемента с текстом \"История заказов\"")
    public void checkVisibleElementOrderHistory(String text) {
        $(byText(text)).shouldBe(Condition.visible);
    }

    @Step("Нажать \"Выход\" в личном кабинете")
    public static void clickExitButton() {
        logoutButton.shouldBe(enabled).click();
    }

    @Step("Нажать на кнопку \"Конструктор\"")
    public static void clickConstructorButton() {
        constructorButton.shouldBe(enabled).click();
    }

    @Step("Нажать на логотип \"Stellar Burgers\"")
    public static void clickLogotype() {
        logotype.shouldBe(enabled).click();
    }
}
