import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI ="http://localhost:4004";
    }

    @Test
    public void shouldReturnOkWithValidToken() {
        //How to structure the test
        // 1. Arrange
        // 2. Act  (Actually calling endpoint)
        // 3. Assert (Checking the response)

        String loginPayload = """
                  {
                      "email": "testuser@test.com",
                      "password": "password123"
                  }
                """;
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract().response();

        System.out.println("Generated Token: " + response.jsonPath().getString("token"));

    }

    @Test
    public void shouldReturnUnauthorizedOnInvalidLogin() {
        //How to structure the test
        // 1. Arrange
        // 2. Act  (Actually calling endpoint)
        // 3. Assert (Checking the response)

        String loginPayload = """
                  {
                      "email": "wrongiduser@test.com",
                      "password": "wrongPassword"
                  }
                """;
        RestAssured.given()
                .header("Content-Type", "application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401);

    }
}