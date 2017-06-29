package com.gp.test.integration.session;

import com.gp.config.PropertiesManager;
import com.gp.config.RestAssuredTest;
import com.gp.config.UserFactory;
import com.gp.model.User;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class LoginNegativeTest extends RestAssuredTest{
    private static final String SESSIONS_ENDPOINT = PropertiesManager.getMessage("api.sessions");
    private User user = UserFactory.getDefaultUser();

    @Test
    public void invalidBodyTest() {
        String body = "userne=";
        defaultRequestSpecification()
                .body(body)
            .when()
                .post(SESSIONS_ENDPOINT)
            .then()
                .statusCode(200)
                .body(WHOFAULTS_FAULTCODE,  equalTo("20010"))
                .body(WHOFAULTS_FAULTSTRING,  equalTo("Missing request parameter"))
                .body(WHOFAULTS_FAULTNAME,  equalTo("username"));
    }

    @Test
    public void missingUsernameTest() {
        String body = "password=" + user.getPassword();
        defaultRequestSpecification()
                .body(body)
            .when()
                .post(SESSIONS_ENDPOINT)
            .then()
                .statusCode(200)
                .body(WHOFAULTS_FAULTCODE,  equalTo("20010"))
                .body(WHOFAULTS_FAULTSTRING,  equalTo("Missing request parameter"))
                .body(WHOFAULTS_FAULTNAME,  equalTo("username"));
    }

    @Test
    public void missingPasswordTest() {
        String body = "username=" + user.getUserName() + "&paword=" + user.getPassword();
        defaultRequestSpecification()
                .body(body)
            .when()
                .post(SESSIONS_ENDPOINT)
            .then()
                .statusCode(200)
                .body(WHOFAULTS_FAULTCODE,  equalTo("20010"))
                .body(WHOFAULTS_FAULTSTRING,  equalTo("Missing request parameter"))
                .body(WHOFAULTS_FAULTNAME,  equalTo("password"));
    }

    @Test
    public void wrongPasswordTest() {
        String body = "username=" + user.getUserName() + "&password=1" +  user.getPassword();
        defaultRequestSpecification()
                .body(body)
            .when()
                .post(SESSIONS_ENDPOINT)
            .then()
                .statusCode(401)
                .body(WHOFAULTS_FAULTCODE,  equalTo("20000"))
                .body(WHOFAULTS_FAULTSTRING,  equalTo("Credentials failed to validate"))
                .body(WHOFAULTS_FAULTNAME,  equalTo(""));
    }

    @Test
    public void missingApiKeyTest() {
        String body = "username=" + user.getUserName() + "&password=" +  user.getPassword();
        anonymous()
            .contentType(ContentType.URLENC)
            .body(body)
        .when()
            .post(SESSIONS_ENDPOINT)
        .then()
            .statusCode(400)
            .body(WHOFAULTS_FAULTCODE,  equalTo("10010"))
            .body(WHOFAULTS_FAULTSTRING,  equalTo("No API key provided within the request"))
            .body(WHOFAULTS_FAULTNAME,  equalTo(""));
    }

    @Test
    public void missingSecretTest() {
        String body = "username=" + user.getUserName() + "&password=" +  user.getPassword();
        anonymous()
            .header(WHOAPIKEY, user.getApiKey())
            .contentType(ContentType.URLENC)
            .body(body)
        .when()
            .post(SESSIONS_ENDPOINT)
        .then()
            .statusCode(400)
            .body(WHOFAULTS_FAULTCODE,  equalTo("10020"))
            .body(WHOFAULTS_FAULTSTRING,  equalTo("An API secret is mandatory for this request"))
            .body(WHOFAULTS_FAULTNAME,  equalTo(""));
    }

    private RequestSpecification defaultRequestSpecification() {
        return anonymous()
                .header(WHOAPIKEY, user.getApiKey())
                .header(WHOSECRET, user.getApiSecret())
                .contentType(ContentType.URLENC);
    }
}
