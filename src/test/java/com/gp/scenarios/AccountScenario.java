package com.gp.scenarios;

import com.gp.config.PropertiesManager;
import com.gp.model.User;
import com.gp.model.accounts.balance.AccountBalance;
import com.gp.model.accounts.details.AccountDetails;

public class AccountScenario extends Scenario {

    /**
     * Returns account details for given user.
     *
     * @param user used to gather account details
     * @return account details of given user
     */
    public static AccountDetails getAccountDetails(User user) {
        return authenticated(user)
            .when()
                .get(PropertiesManager.getMessage("api.accounts"))
            .then()
                .statusCode(200)
            .extract()
                .response()
                .as(AccountDetails.class);
    }

    /**
     * Returns account balance for given user.
     *
     * @param user used to gather account balance
     * @return account balance of given user
     */
    public static AccountBalance getAccountBalance(User user) {
        return authenticated(user)
            .when()
                .get(PropertiesManager.getMessage("api.accounts.balance"))
            .then()
                .statusCode(200)
            .extract()
                .response()
                .as(AccountBalance.class);
    }
}
