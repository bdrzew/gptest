package com.gp.model.accounts.details;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "whoAccounts"
})
@Data
public class AccountDetails {

    @JsonProperty("whoAccounts")
    private WhoAccounts whoAccounts;

}
