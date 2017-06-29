package com.gp.scenarios;

import com.gp.config.PropertiesManager;
import com.gp.model.User;
import com.gp.model.competitions.events.InplayEvent;

public class CompetitionsScenario extends Scenario {
    public static InplayEvent getUpcomingInplayEvent(User user) {
        return anonymous()
                .header(WHOAPIKEY, user.getApiKey())
                .accept("application/vnd.who.Sportsbook+xml;v=1;charset=utf-8")
            .log()
                .all()
            .when()
                .get(PropertiesManager.getMessage("api.inplay.upcoming"))
            .then()
            .extract()
                .response()
                .as(InplayEvent.class);
    }

    public static String getOutcomeFromEvent(InplayEvent event, User user) {
        return null;
    }
}
