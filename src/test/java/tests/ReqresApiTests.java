package tests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqresApiTests extends TestBase {

    @Test
    void checkListUsersTest() {
        given()
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12))
                .body("data", hasSize(6));
    }

    @Test
    void createUserTest() {
        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }

    @Test
    void updateUserTest() {
        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given()
                .body(requestBody)
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .body("job", is("zion resident"))
                .body("updatedAt", notNullValue());
    }

    @Test
    void deleteUserTest() {
        given()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    void unsuccessfulRegisterTest() {
        String requestBody = "{ \"email\": \"sydney@fife\" }";

        given()
                .body(requestBody)
                .when()
                .post("/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}