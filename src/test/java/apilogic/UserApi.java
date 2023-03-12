package apilogic;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.User;
import model.UserCredentials;

import static constants.ConstantsApi.*;
import static constants.ConstantsApi.USER_API_LOGIN;
import static io.restassured.RestAssured.given;

public class UserApi {

    private static RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(URL_MAIN_PAGE)
                .build();
    }

    @Step("Создание пользователя")
    public static Response createUser(User user) {
        return given()
                .spec(getSpec())
                .and()
                .body(user)
                .when()
                .post(USER_API_CREATE);
    }

    @Step("Удаление пользователя")
    public static Response deleteUser(String userToken) {
        return given()
                .spec(getSpec())
                .header("Authorization", userToken)
                .and()
                .when()
                .delete(USER_API_DELETE);
    }

    @Step("Авторизация пользователя в системе")
    public static Response loginUser(UserCredentials credentials) {
        return given()
                .spec(getSpec())
                .and()
                .body(credentials)
                .when()
                .post(USER_API_LOGIN);
    }
}
