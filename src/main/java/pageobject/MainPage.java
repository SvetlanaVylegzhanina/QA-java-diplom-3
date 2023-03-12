package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    // локатор кнопки "Личный Кабинет"
    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private static SelenideElement personalAccountButton;

    // локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private static SelenideElement loginAccountButton;

    // локаторы раздела "Конструктор"
    // локатор вкладки "Булки"
    @FindBy(how = How.XPATH, using = ".//span[text()= 'Булки']")
    private static SelenideElement bunsTab;
    //локатор вкладки "Соусы"
    @FindBy(how = How.XPATH, using = ".//span[text()= 'Соусы']")
    private static SelenideElement saucesTab;
    //локатор вкладки "Начинки"
    @FindBy(how = How.XPATH, using = ".//span[text()= 'Начинки']")
    private static SelenideElement fillingsTab;

    @Step("Нажать \"Войти в аккаунт\"")
    public static void clickLoginAccountButton() {
        loginAccountButton.shouldBe(enabled).click();
    }

    @Step("Нажать \"Личный Кабинет\"")
    public static void clickPersonalAccountButton() {
        personalAccountButton.shouldBe(enabled).click();
    }

    @Step("Перейти к разделу \"Булки\"")
    public static void goToBunsSection() {
        bunsTab.parent().shouldNotHave(attributeMatching("class", ".*current.*")).click();
        bunsTab.parent().shouldHave(attributeMatching("class", ".*current.*"));
    }

    @Step("Перейти к разделу \"Соусы\"")
    public static void goToSaucesSection() {
        saucesTab.parent().shouldNotHave(attributeMatching("class", ".*current.*")).click();
        saucesTab.parent().shouldHave(attributeMatching("class", ".*current.*"));
    }

    @Step("Перейти к разделу \"Начинки\"")
    public static void goToFillingsSection() {
        fillingsTab.parent().shouldNotHave(attributeMatching("class", ".*current.*")).click();
        fillingsTab.parent().shouldHave(attributeMatching("class", ".*current.*"));
    }

    @Step("Проверить отображение элемента с текстом \"Оформить заказ\"")
    public void checkVisibleElementMakeOrder(String text) {
        $(byText(text)).shouldBe(Condition.visible);
    }
}
