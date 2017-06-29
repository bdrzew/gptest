package com.gp.model.accounts.balance;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "currencyCode",
        "balance",
        "availableFunds",
        "withdrawableFunds"
})
@Data
public class Account {

    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonProperty("balance")
    private String balance;
    @JsonProperty("availableFunds")
    private String availableFunds;
    @JsonProperty("withdrawableFunds")
    private String withdrawableFunds;

}