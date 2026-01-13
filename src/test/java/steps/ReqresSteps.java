package steps;

import io.qameta.allure.Step;
import models.*;
import specs.ReqresSpecs;

import static io.restassured.RestAssured.given;

public class ReqresSteps {

    @Step("Получить список пользователей (page={page})")
    public ListUsersResponse getUsers(int page) {
        return given()
                .spec(ReqresSpecs.reqresRequestSpec)
                .when()
                .get("/users?page=" + page)
                .then()
                .spec(ReqresSpecs.status200)
                .extract().as(ListUsersResponse.class);
    }

    @Step("Создать пользователя: name={body.name}, job={body.job}")
    public CreateUserResponse createUser(CreateUserBody body) {
        return given()
                .spec(ReqresSpecs.reqresRequestSpec)
                .body(body)
                .when()
                .post("/users")
                .then()
                .spec(ReqresSpecs.status201)
                .extract().as(CreateUserResponse.class);
    }

    @Step("Обновить пользователя id={userId}: job={body.job}")
    public UpdateUserResponse updateUser(int userId, CreateUserBody body) {
        return given()
                .spec(ReqresSpecs.reqresRequestSpec)
                .body(body)
                .when()
                .put("/users/" + userId)
                .then()
                .spec(ReqresSpecs.status200)
                .extract().as(UpdateUserResponse.class);
    }

    @Step("Удалить пользователя id={userId}")
    public void deleteUser(int userId) {
        given()
                .spec(ReqresSpecs.reqresRequestSpec)
                .when()
                .delete("/users/" + userId)
                .then()
                .spec(ReqresSpecs.status204);
    }

    @Step("Неуспешная регистрация (email={body.email})")
    public RegisterErrorResponse unsuccessfulRegister(RegisterBody body) {
        return given()
                .spec(ReqresSpecs.reqresRequestSpec)
                .body(body)
                .when()
                .post("/register")
                .then()
                .spec(ReqresSpecs.status400)
                .extract().as(RegisterErrorResponse.class);
    }
}