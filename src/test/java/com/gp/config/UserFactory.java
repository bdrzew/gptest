package com.gp.config;

import com.gp.model.User;

public class UserFactory {

    public static User getDefaultUser() {
        return getUser("user1");
    }
    public static User getUser(String userId) {
        return User.builder()
                .apiKey(PropertiesManager.getMessage(userId + ".key"))
                .apiSecret(PropertiesManager.getMessage(userId + ".secret"))
                .userName(PropertiesManager.getMessage(userId + ".name"))
                .password(PropertiesManager.getMessage(userId + ".password"))
                .build();
    }
}
