package com.gp.test.integration.bets;

import com.gp.config.RestAssuredTest;
import com.gp.config.UserFactory;
import com.gp.model.User;
import com.gp.model.accounts.balance.AccountBalance;
import com.gp.model.accounts.details.AccountDetails;
import com.gp.model.bets.Bet;
import com.gp.model.competitions.events.InplayEvent;
import com.gp.scenarios.BetScenario;
import com.gp.scenarios.CompetitionsScenario;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.gp.scenarios.AccountScenario.getAccountBalance;
import static com.gp.scenarios.AccountScenario.getAccountDetails;
import static com.gp.scenarios.CompetitionsScenario.getUpcomingInplayEvent;
import static com.gp.scenarios.LoginScenario.loginWithUser;
import static com.gp.scenarios.LoginScenario.logoutUser;
import static org.testng.Assert.fail;

public class BetsHistoryTest extends RestAssuredTest {
    private User user;

    @BeforeClass
    public void setUp() {
        user = UserFactory.getDefaultUser();
        loginWithUser(user);
        AccountDetails accountDetails = getAccountDetails(user);
        AccountBalance accountBalance = getAccountBalance(user);
        InplayEvent event = getUpcomingInplayEvent(user);
        String outcomeId = CompetitionsScenario.getOutcomeFromEvent(event, user);
        Bet bet = BetScenario.placeSingleBet(outcomeId, "1", user);
    }

    @AfterMethod
    public void tearDown() {
        logoutUser(user);
    }

    @Test
    public void validateSchema(){
        fail("Not yet implemented");
    }

    @Test
    public void missingApiKey(){
        fail("Not yet implemented");
    }

    @Test
    public void missingSecret(){
        fail("Not yet implemented");
    }

    @Test
    public void missingToken(){
        fail("Not yet implemented");
    }
}
