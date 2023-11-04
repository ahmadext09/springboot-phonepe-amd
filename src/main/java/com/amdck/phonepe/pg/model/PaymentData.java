package com.amdck.phonepe.pg.model;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentData {
    @JsonProperty("merchantId")
    private String merchantId;

    @JsonProperty("merchantTransactionId")
    private String merchantTransactionId;

    @JsonProperty("transactionId")
    private String transactionId;

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("state")
    private String state;

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("payResponseCode")
    private String payResponseCode;


    @JsonProperty("responseCodeDescription")
    private String responseCodeDescription;

    @JsonProperty("paymentInstrument")
    private PaymentInstrument paymentInstrument;


    public PaymentData() {
    }

    public PaymentData(String merchantId, String merchantTransactionId, String transactionId, long amount, String state, String payResponseCode, PaymentInstrument paymentInstrument) {
        this.merchantId = merchantId;
        this.merchantTransactionId = merchantTransactionId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.state = state;
        this.responseCode = payResponseCode;
        this.paymentInstrument = paymentInstrument;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseCodeDescription() {
        return responseCodeDescription;
    }

    public void setResponseCodeDescription(String responseCodeDescription) {
        this.responseCodeDescription = responseCodeDescription;
    }

    public PaymentInstrument getPaymentInstrument() {
        return paymentInstrument;
    }

    public void setPaymentInstrument(PaymentInstrument paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
    }

    public String getPayResponseCode() {
        return payResponseCode;
    }

    public void setPayResponseCode(String payResponseCode) {
        this.payResponseCode = payResponseCode;
    }


    @Override
    public String toString() {
        return "PaymentData{" +
                "merchantId='" + merchantId + '\'' +
                ", merchantTransactionId='" + merchantTransactionId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", state='" + state + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", payResponseCode='" + payResponseCode + '\'' +
                ", responseCodeDescription='" + responseCodeDescription + '\'' +
                ", paymentInstrument=" + paymentInstrument.toString() +
                '}';
    }
}



