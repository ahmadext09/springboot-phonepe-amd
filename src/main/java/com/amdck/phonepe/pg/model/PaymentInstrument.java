package com.amdck.phonepe.pg.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentInstrument {
    @JsonProperty("type")
    private String type;

    @JsonProperty("utr")
    private String utr;

    @JsonProperty("upiTransactionId")
    private String upiTransactionId;

    @JsonProperty("accountHolderName")
    private String accountHolderName;

    @JsonProperty("cardType")
    private String cardType;

    @JsonProperty("pgTransactionId")
    private String pgTransactionId;

    @JsonProperty("bankTransactionId")
    private String bankTransactionId;

    @JsonProperty("pgAuthorizationCode")
    private String pgAuthorizationCode;

    @JsonProperty("arn")
    private String arn;

    @JsonProperty("bankId")
    private String bankId;

    @JsonProperty("brn")
    private String brn;

    @JsonProperty("pgServiceTransactionId")
    private String pgServiceTransactionId;

    @JsonProperty("vpa")
    private String vpa;
    @JsonProperty("maskedAccountNumber")
    private String maskedAccountNumber;

    @JsonProperty("ifsc")
    private String ifsc;



    public PaymentInstrument() {
    }

    public PaymentInstrument(String type, String utr, String upiTransactionId, String accountHolderName) {
        this.type = type;
        this.utr = utr;
        this.upiTransactionId = upiTransactionId;
        this.accountHolderName = accountHolderName;
    }

    public PaymentInstrument(String type, String cardType, String pgTransactionId, String bankTransactionId, String pgAuthorizationCode, String arn, String bankId, String brn) {
        this.type = type;
        this.cardType = cardType;
        this.pgTransactionId = pgTransactionId;
        this.bankTransactionId = bankTransactionId;
        this.pgAuthorizationCode = pgAuthorizationCode;
        this.arn = arn;
        this.bankId = bankId;
        this.brn = brn;

    }

    public PaymentInstrument(String type, String pgTransactionId, String pgServiceTransactionId, String bankTransactionId, String bankId) {
        this.type = type;
        this.pgTransactionId = pgTransactionId;
        this.bankTransactionId = bankTransactionId;
        this.pgServiceTransactionId = pgServiceTransactionId;
        this.bankId = bankId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUtr() {
        return utr;
    }

    public void setUtr(String utr) {
        this.utr = utr;
    }

    public String getUpiTransactionId() {
        return upiTransactionId;
    }

    public void setUpiTransactionId(String upiTransactionId) {
        this.upiTransactionId = upiTransactionId;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getPgTransactionId() {
        return pgTransactionId;
    }

    public void setPgTransactionId(String pgTransactionId) {
        this.pgTransactionId = pgTransactionId;
    }

    public String getBankTransactionId() {
        return bankTransactionId;
    }

    public void setBankTransactionId(String bankTransactionId) {
        this.bankTransactionId = bankTransactionId;
    }

    public String getPgAuthorizationCode() {
        return pgAuthorizationCode;
    }

    public void setPgAuthorizationCode(String pgAuthorizationCode) {
        this.pgAuthorizationCode = pgAuthorizationCode;
    }

    public String getArn() {
        return arn;
    }

    public void setArn(String arn) {
        this.arn = arn;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBrn() {
        return brn;
    }

    public void setBrn(String brn) {
        this.brn = brn;
    }

    public String getPgServiceTransactionId() {
        return pgServiceTransactionId;
    }

    public void setPgServiceTransactionId(String pgServiceTransactionId) {
        this.pgServiceTransactionId = pgServiceTransactionId;
    }

}

