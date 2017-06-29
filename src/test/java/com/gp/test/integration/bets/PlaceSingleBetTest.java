package com.gp.test.integration.bets;

import com.gp.config.RestAssuredTest;
import com.gp.config.UserFactory;
import com.gp.model.User;
import com.gp.model.accounts.balance.AccountBalance;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.gp.scenarios.AccountScenario.getAccountBalance;
import static com.gp.scenarios.LoginScenario.loginWithUser;
import static com.gp.scenarios.LoginScenario.logoutUser;
import static org.testng.Assert.fail;

public class PlaceSingleBetTest extends RestAssuredTest{
    private User user = UserFactory.getDefaultUser();
    private AccountBalance accountBalance;

    @BeforeMethod
    public void setUp() {
        loginWithUser(user);
        accountBalance = getAccountBalance(user);
    }

    @AfterMethod
    public void tearDown() {
        logoutUser(user);
    }

    @Test
    public void invalidOutcomeIdTest(){
        fail("Not yet implemented");
    }

    @Test
    public void missingOutcomeIdTest(){
        fail("Not yet implemented");
    }

    @Test
    public void invalidStakeTest(){
        fail("Not yet implemented");
    }

    @Test
    public void missingStakeTest(){
        fail("Not yet implemented");
    }

    @Test
    public void stakeExceedingBalanceTest(){
        fail("Not yet implemented");
    }

    @Test
    public void invalidAnyOtherFieldWhichIsRequired(){
        fail("Not yet implemented");
    }
}
