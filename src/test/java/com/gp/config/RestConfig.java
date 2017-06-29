package com.gp.config;

import com.gp.model.User;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestConfig {
    public static final String WHOAPIKEY = "who-apiKey";
    public static final String WHOSECRET = "who-secret";
    public static final String WHOTICKET = "who-ticket";
    public static final String WHOFAULTS_FAULTCODE = "whoFaults.faultCode[0]";
    public static final String WHOFAULTS_FAULTSTRING = "whoFaults.faultString[0]";
    public static final String WHOFAULTS_FAULTNAME = "whoFaults.faultName[0]";

    public static RequestSpecification anonymous() {
        return RestAssured
            .given()
                .baseUri(PropertiesManager.getMessage("api.baseuri"))
                .accept("application/json")
            .log()
                .all();
    }

    public static RequestSpecification authenticated(User user) {
        return RestAssured
            .given()
                .baseUri(PropertiesManager.getMessage("api.baseuri"))
                .header(WHOAPIKEY, user.getApiKey())
                .header(WHOSECRET, user.getApiSecret())
                .header(WHOTICKET, user.getToken())
                .accept("application/json")
            .log()
                .all();
    }
}
