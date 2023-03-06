package testReqres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class UsersTest {

    @Test
    @DisplayName("Пользователь не найден")
    void singleUserNotFound(){

        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    @DisplayName("Создание пользователя")
    void createUsersTestName(){
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));

    }

    @Test
    @DisplayName("Обновление пользователя")
    void updateUsersTest(){

        String body = "{ \"name\": \"morpheus\", \"job\": \"bom resident\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("bom resident"));



    }
    @Test
    @DisplayName("Получение пользователя по id")
    void getUserId(){
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.last_name", is("Weaver"));

    }

    @Test
    @DisplayName("Удаление пользователя")
    void deleteUserId() {
        given()
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}

