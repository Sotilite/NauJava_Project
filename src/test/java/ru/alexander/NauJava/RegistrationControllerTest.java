package ru.alexander.NauJava;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.alexander.NauJava.entity.CustomUserDetails;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RegistrationControllerTest {
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
