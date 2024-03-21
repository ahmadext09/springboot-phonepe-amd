package com.amdck.phonepe.pg.model.paytm;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaytmCallbackData {



    @Nullable
    @JsonProperty("BANKTXNID")
    private String bankTxnId;

    @Nullable
    @JsonProperty("CHECKSUMHASH")
    private String checksumHash;

    @Nullable
    @JsonProperty("CURRENCY")
    private String currency;

    @Nullable
    @JsonProperty("GATEWAYNAME")
    private String gatewayName;

    @Nullable
    @JsonProperty("MID")
    private String mid;

    @Nullable
    @JsonProperty("ORDERID")
    private String orderId;

    @Nullable
    @JsonProperty("PAYMENTMODE")
    private String paymentMode;

    @Nullable
    @JsonProperty("RESPCODE")
    private String respCode;

    @Nullable
    @JsonProperty("RESPMSG")
    private String respMsg;

    @Nullable
    @JsonProperty("STATUS")
    private String status;

    @Nullable
    @JsonProperty("TXNAMOUNT")
    private String txnAmount;

    @Nullable
    @JsonProperty("TXNDATE")
    private String txnDate;

    @Nullable
    @JsonProperty("TXNID")
    private String txnId;

    @Nullable
    @JsonProperty("UDF_1")
    private String udf1;

    @Nullable
    @JsonProperty("BANKNAME")
    private String bankName;



    public PaytmCallbackData() {

    }

    public PaytmCallbackData(
            @Nullable @JsonProperty("BANKTXNID") String bankTxnId,
            @Nullable @JsonProperty("CHECKSUMHASH") String checksumHash,
            @Nullable @JsonProperty("CURRENCY") String currency,
            @Nullable @JsonProperty("GATEWAYNAME") String gatewayName,
            @Nullable @JsonProperty("MID") String mid,
            @Nullable @JsonProperty("ORDERID") String orderId,
            @Nullable @JsonProperty("PAYMENTMODE") String paymentMode,
            @Nullable @JsonProperty("RESPCODE") String respCode,
            @Nullable @JsonProperty("RESPMSG") String respMsg,
            @Nullable @JsonProperty("STATUS") String status,
            @Nullable @JsonProperty("TXNAMOUNT") String txnAmount,
            @Nullable @JsonProperty("TXNDATE") String txnDate,
            @Nullable @JsonProperty("TXNID") String txnId,
            @Nullable @JsonProperty("UDF_1") String udf1,
            @Nullable @JsonProperty("BANKNAME") String bankName) {
        this.bankTxnId = bankTxnId;
        this.checksumHash = checksumHash;
        this.currency = currency;
        this.gatewayName = gatewayName;
        this.mid = mid;
        this.orderId = orderId;
        this.paymentMode = paymentMode;
        this.respCode = respCode;
        this.respMsg = respMsg;
        this.status = status;
        this.txnAmount = txnAmount;
        this.txnDate = txnDate;
        this.txnId = txnId;
        this.udf1 = udf1;
        this.bankName = bankName;
    }


}
