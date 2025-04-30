package ru.alexander.NauJava;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.alexander.NauJava.entity.CustomUserDetails;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationControllerTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void testGettingRegistrationPage() {
        given().when()
                .get("/registration")
                .then()
                .statusCode(200).contentType(MediaType.TEXT_HTML_VALUE)
                .body(containsString("registration"));
    }

    @Test
    void testSuccessfulUserRegistration() {
        var userDetails = new CustomUserDetails(
                "Alexander",
                "12345qwerty",
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        given()
                .contentType("application/json")
                .body(userDetails)
                .when()
                .post("/registration")
                .then()
                .statusCode(302)
                .header("Location", containsString("/login"));
    }
}
