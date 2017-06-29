package com.gp.model.accounts.details;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "accountNum",
        "customerId",
        "accountId",
        "countryCode",
        "currencyCode",
        "title",
        "firstName",
        "lastName",
        "street1",
        "street2",
        "street3",
        "city",
        "county",
        "postcode",
        "country",
        "email",
        "telephone",
        "mobile",
        "contactable",
        "partnerContactable",
        "flags"
})
@Data
public class Account {

    @JsonProperty("status")
    private String status;
    @JsonProperty("accountNum")
    private String accountNum;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonProperty("title")
    private String title;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("street1")
    private String street1;
    @JsonProperty("street2")
    private String street2;
    @JsonProperty("street3")
    private String street3;
    @JsonProperty("city")
    private String city;
    @JsonProperty("county")
    private String county;
    @JsonProperty("postcode")
    private String postcode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("email")
    private String email;
    @JsonProperty("telephone")
    private String telephone;
    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("contactable")
    private String contactable;
    @JsonProperty("partnerContactable")
    private String partnerContactable;
    @JsonProperty("flags")
    private List<Pocf> flags = null;

}
