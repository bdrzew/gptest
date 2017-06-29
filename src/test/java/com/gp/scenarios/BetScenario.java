package com.gp.scenarios;

import com.gp.config.PropertiesManager;
import com.gp.model.accounts.balance.AccountBalance;
import com.gp.model.bets.Bet;
import com.gp.model.User;
import com.gp.model.bets.BetsHistory;
import io.restassured.response.Response;

public class BetScenario extends Scenario{

    public static Bet placeSingleBet(String outcomeId, String stake, User user) {
        Response response = authenticated(user)
            .when()
                .get(PropertiesManager.getMessage("api.bets.history"))
            .then()
                .statusCode(200)
            .extract()
                .response();

        response.body().prettyPrint();
        System.out.println(response.getStatusCode());
        return new Bet();
    }

    public static BetsHistory getBetsHistory(User user, int size, int num) {
        Response response =
            authenticated(user)
                .queryParam("blockSize",size)
                .queryParam("blockNum",num)
            .when()
                .get(PropertiesManager.getMessage("api.bets.history"))
            .then()
                .statusCode(200)
            .extract()
                .response();

        response.body().prettyPrint();
        System.out.println(response.getStatusCode());
        return new BetsHistory();
    }
}
