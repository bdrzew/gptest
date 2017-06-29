package com.gp.model.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "faultCode",
        "faultString",
        "faultName"
})
@Data
public class WhoFault {

    @JsonProperty("faultCode")
    private String faultCode;
    @JsonProperty("faultString")
    private String faultString;
    @JsonProperty("faultName")
    private String faultName;

}