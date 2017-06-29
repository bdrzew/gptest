package com.gp.test.integration.accounts;

import com.gp.config.PropertiesManager;
import com.gp.config.RestAssuredTest;
import com.gp.config.UserFactory;
import com.gp.model.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.gp.scenarios.LoginScenario.loginWithUser;
import static com.gp.scenarios.LoginScenario.logoutUser;
import static org.hamcrest.Matchers.equalTo;

public class AccountTest extends RestAssuredTest {
    private User user;

    @BeforeMethod
    public void setUp() {
        user = UserFactory.getDefaultUser();
        loginWithUser(user);
    }

    @AfterMethod
    public void tearDown() {
        logoutUser(user);
    }

    @DataProvider
    public Object[][] getEndpointsData() {
        return new Object[][]{
                {PropertiesManager.getMessage("api.accounts"),
                        JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/accountDetails.json")},
                {PropertiesManager.getMessage("api.accounts.balance"),
                        JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/accountBalance.json")}};

    }

    @Test(dataProvider="getEndpointsData")
    public void validateSchemaTest(String endpoint, JsonSchemaValidator validator) {
        authenticated(user)
            .when()
                .get(endpoint)
            .then()
                .statusCode(200)
                .assertThat().body(validator);
    }

    @Test(dataProvider="getEndpointsData")
    public void missingTokenTest(String endpoint, JsonSchemaValidator validator) {
        anonymous()
                .header(WHOAPIKEY, user.getApiKey())
                .header(WHOSECRET, user.getApiSecret())
            .when()
                .get(endpoint)
            .then()
                .statusCode(200)
                .body(WHOFAULTS_FAULTCODE,  equalTo("10070"))
                .body(WHOFAULTS_FAULTSTRING,  equalTo("Missing request parameter"))
                .body(WHOFAULTS_FAULTNAME,  equalTo("who-ticket"));
    }

    @Test(dataProvider="getEndpointsData")
    public void missingApiKeyTest(String endpoint, JsonSchemaValidator validator) {
        anonymous()
                .header(WHOSECRET, user.getApiSecret())
                .header(WHOTICKET, user.getToken())
            .when()
                .get(endpoint)
            .then()
                .statusCode(400)
                .body(WHOFAULTS_FAULTCODE,  equalTo("10010"))
                .body(WHOFAULTS_FAULTSTRING,  equalTo("No API key provided within the request"))
                .body(WHOFAULTS_FAULTNAME,  equalTo(""));
    }

    @Test(dataProvider="getEndpointsData")
    public void missingSecretTest(String endpoint, JsonSchemaValidator validator) {
        anonymous()
                .header(WHOAPIKEY, user.getApiKey())
                .header(WHOTICKET, user.getToken())
            .when()
                .get(endpoint)
            .then()
                .statusCode(400)
                .body(WHOFAULTS_FAULTCODE,  equalTo("10020"))
                .body(WHOFAULTS_FAULTSTRING,  equalTo("An API secret is mandatory for this request"))
                .body(WHOFAULTS_FAULTNAME,  equalTo(""));
    }
}
