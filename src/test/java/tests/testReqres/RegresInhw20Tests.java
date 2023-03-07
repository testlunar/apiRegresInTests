package tests.testReqres;

import models.lombok.createUser.CreateUserBody;
import models.lombok.createUser.CreateUserResponse;
import models.lombok.singleUserSearch.SingleUserResponse;
import models.lombok.updateUser.UpdateUserBody;
import models.lombok.updateUser.UpdateUserResponce;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static specs.LoginSpecs.loginRequestSpec;

public class RegresInhw20Tests {

    @Test
    void createUserWithSpecsTest() {
        CreateUserBody data = new CreateUserBody();
        data.setName("morpheus");
        data.setJob("leader");

        CreateUserResponse response = given(loginRequestSpec)
                .body(data)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(CreateUserResponse.class);

        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");
    }

    @Test
    @DisplayName("Обновление пользователя")
    void updateUsersWithSpecsTest(){
        UpdateUserBody data = new UpdateUserBody();
        data.setName("morpheus");
        data.setJob("bom resident");

        UpdateUserResponce response = given(loginRequestSpec)
                .body(data)
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(UpdateUserResponce.class);

        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("bom resident");
    }

    @Test
    @DisplayName("Пользователь не найден")
    void singleUserNotFound(){

        given(loginRequestSpec)
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    @DisplayName("Получение пользователя по id")
    void getUserId(){
        SingleUserResponse response =  given(loginRequestSpec)
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(SingleUserResponse.class);

        assertThat(response.getData().getLast_name()).isEqualTo("Weaver");

    }

    @Test
    @DisplayName("Удаление пользователя")
    void deleteUserId() {
        given(loginRequestSpec)
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
