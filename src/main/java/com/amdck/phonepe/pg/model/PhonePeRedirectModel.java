package com.amdck.phonepe.pg.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.DataAmount;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class PhonePeRedirectModel {
    @JsonProperty("code")
    private String code;

    @JsonProperty("merchantId")
    private String merchantId;

    @JsonProperty("transactionId")
    private String transactionId;

    @JsonProperty("amount")
    private long amount;

    @JsonProperty("providerReferenceId")
    private String providerReferenceId;

    // Constructors, getters, and setters (or use Lombok to generate them)

    public PhonePeRedirectModel() {
        // Default constructor
    }

    public PhonePeRedirectModel(String code, String merchantId, String transactionId, int amount, String providerReferenceId) {
        this.code = code;
        this.merchantId = merchantId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.providerReferenceId = providerReferenceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getProviderReferenceId() {
        return providerReferenceId;
    }

    public void setProviderReferenceId(String providerReferenceId) {
        this.providerReferenceId = providerReferenceId;
    }


}
