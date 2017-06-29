package com.gp.scenarios;

import com.gp.config.PropertiesManager;
import com.gp.model.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginScenario extends Scenario {

    /**
     * This method takes {@link User} as a parameter and uses it to login by using session api.
     * If scenario is successful token isset inside of given user model.
     *
     * @param user Model built with data necessary for successful login
     * @return token retrieved from sesions endpoint
     */
    public static String loginWithUser(User user) {
        String body = "username=" + user.getUserName() + "&password=" +  user.getPassword();
        Response response =
            anonymous()
                .header(WHOAPIKEY, user.getApiKey())
                .header(WHOSECRET, user.getApiSecret())
                .contentType(ContentType.URLENC)
                .body(body)
            .log()
                .all()
            .when()
                .post(PropertiesManager.getMessage("api.sessions"))
            .then()
                .statusCode(201)
            .extract()
                .response();

            response.body().prettyPrint();
            String token = response
                .jsonPath()
                .getString("whoSessions.ticket");

            user.setToken(token);
        return token;
    }

    /**
     * This method logouts user provided as a parameter
     * @param user useer to be logged out
     */
    public static void logoutUser(User user) {
        authenticated(user)
            .when()
                .delete(PropertiesManager.getMessage("api.sessions.logout", user.getToken()))
            .then()
                .statusCode(200);
        user.setToken(null);
    }

}
