package com.gp.test.integration.session;

import com.gp.config.PropertiesManager;
import com.gp.config.RestAssuredTest;
import com.gp.config.UserFactory;
import com.gp.model.User;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.gp.scenarios.LoginScenario.logoutUser;

public class LoginPositiveTest extends RestAssuredTest {
    private User user;
    private String token;

    @BeforeMethod
    public void setUp() {
        user = UserFactory.getDefaultUser();
    }

    @AfterMethod
    public void tearDown() {
        user.setToken(token);
        logoutUser(user);
    }

    @Test
    public void successfulLoginTest(){
        String body = "username=" + user.getUserName() + "&password=" +  user.getPassword();
        token = anonymous()
                .header(WHOAPIKEY, user.getApiKey())
                .header(WHOSECRET, user.getApiSecret())
                .contentType(ContentType.URLENC)
                .body(body)
            .when()
                .post(PropertiesManager.getMessage("api.sessions"))
            .then()
                .statusCode(201)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/login.json"))
            .extract()
                .jsonPath()
                .getString("whoSessions.ticket");
    }
}
