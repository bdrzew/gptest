package com.gp.test.functional;

import com.gp.config.RestAssuredTest;
import com.gp.config.UserFactory;
import com.gp.model.User;
import com.gp.model.accounts.balance.AccountBalance;
import com.gp.model.accounts.details.AccountDetails;
import com.gp.model.bets.Bet;
import com.gp.model.bets.BetsHistory;
import com.gp.model.competitions.events.InplayEvent;
import org.testng.annotations.Test;

import static com.gp.scenarios.AccountScenario.getAccountBalance;
import static com.gp.scenarios.AccountScenario.getAccountDetails;
import static com.gp.scenarios.BetScenario.getBetsHistory;
import static com.gp.scenarios.BetScenario.placeSingleBet;
import static com.gp.scenarios.BetslipScenario.validateUserBetslips;
import static com.gp.scenarios.CompetitionsScenario.getOutcomeFromEvent;
import static com.gp.scenarios.CompetitionsScenario.getUpcomingInplayEvent;
import static com.gp.scenarios.LoginScenario.loginWithUser;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class BetFlowTest extends RestAssuredTest {

    private User user = UserFactory.getDefaultUser();

    /**
     * This is functional test - testing E2E scenario.
     * All detailed endpoint tests with edge cases should be placed accordingly in integration level testing.
     *
     * I have only one user with fixed settings so no fancy cases can be tested.
     * Ideally it would be to have mocked users for different scenarios.
     *
     * Login from Sessions API
     * Get customer accounts details from Accounts API
     * Get customer accounts balance from Accounts API
     * Get prices on a bet from Competitions API
     * Place a bet from Bets and Betslip API
     * Confirm bet placed from Bets API (bet history)
     */
    @Test
    public void basicBetTest() {
        loginWithUser(user);
        AccountDetails accountDetails = getAccountDetails(user);                        //probably should be used somewhere, but to small domain knowledge from my side here
        AccountBalance accountBalance = getAccountBalance(user);

        InplayEvent event = getUpcomingInplayEvent(user);                               //may return empty - should be verified on integration level with mocks
                                                                                        //here assume correct data is provided for test from env. level

        String outcomeId = getOutcomeFromEvent(event, user);                            //I don't know even what that is, but seems to be necessary to place bet

        Bet bet = placeSingleBet(outcomeId, "1", user);                           //stake should be less than balance, and larger than 0 but the point in this test
                                                                                        //should not be how to "magically make it happen with whatever data"
                                                                                        //but to be sure what is an input data and test each variation in integration test
                                                                                        //this suppose to be covered in com.gp.test.integration.bets.PlaceSingleBetTest

        validateUserBetslips(outcomeId, user);                                          //not sure what is it for
        BetsHistory betsHistory = getBetsHistory(user, 5, 0);

        assertTrue("Bets history does not contain expected betId",                   //some basic validation to check bet was placed in history
                betsHistory.getBet().keySet().contains(bet.getBetId()));                //detailed history behavior itself should be verified in mocked environment
        assertEquals("Bets history does not contain expected outcomeId",
                betsHistory.getBet().get(bet.getBetId()).getOutcomeId(), outcomeId);

        //At this point I know that flow works
        //All the variations, field value checks, missing/invalid requests should be done via integration tests
    }
}
