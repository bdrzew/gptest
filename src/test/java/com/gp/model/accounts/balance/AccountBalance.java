package com.gp.model.accounts.balance;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "whoAccounts"
})
@Data
public class AccountBalance {

    @JsonProperty("whoAccounts")
    public WhoAccounts whoAccounts;

}
