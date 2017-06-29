package com.gp.model.errors;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "whoFaults"
})
@Data
public class WhoFaults {

    @JsonProperty("whoFaults")
    private List<WhoFault> whoFaults = null;

}
