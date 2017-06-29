package com.gp.model;

import com.gp.config.PropertiesManager;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String userName;
    private String password;
    private String apiKey;
    private String apiSecret;
    private String token;

}
