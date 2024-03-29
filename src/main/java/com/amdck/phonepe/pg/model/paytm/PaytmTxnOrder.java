package com.amdck.phonepe.pg.model.paytm;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaytmTxnOrder {

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("email")
    private String email;

    @JsonProperty("orderId")
    private String orderId;

    @JsonProperty("custId")
    private String custId;
}

