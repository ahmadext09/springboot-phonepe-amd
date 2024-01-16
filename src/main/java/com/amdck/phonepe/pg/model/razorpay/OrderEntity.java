package com.amdck.phonepe.pg.model.razorpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderEntity {

    @JsonProperty("id")
    private String id;

    @JsonProperty("entity")
    private String entity;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("amount_paid")
    private int amount_paid;

    @JsonProperty("amount_due")
    private int amount_due;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("receipt")
    private String receipt;

    @JsonProperty("offer_id")
    private String offer_id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("attempts")
    private int attempts;




    @JsonProperty("created_at")
    private long created_at;

    public OrderEntity() {
    }

    public OrderEntity(String id, String entity, int amount, int amount_paid, int amount_due,
                       String currency, String receipt, String offer_id, String status, int attempts,
                        long created_at) {
        this.id = id;
        this.entity = entity;
        this.amount = amount;
        this.amount_paid = amount_paid;
        this.amount_due = amount_due;
        this.currency = currency;
        this.receipt = receipt;
        this.offer_id = offer_id;
        this.status = status;
        this.attempts = attempts;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(int amount_paid) {
        this.amount_paid = amount_paid;
    }

    public int getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(int amount_due) {
        this.amount_due = amount_due;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }


    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id='" + id + '\'' +
                ", entity='" + entity + '\'' +
                ", amount=" + amount +
                ", amount_paid=" + amount_paid +
                ", amount_due=" + amount_due +
                ", currency='" + currency + '\'' +
                ", receipt='" + receipt + '\'' +
                ", offer_id='" + offer_id + '\'' +
                ", status='" + status + '\'' +
                ", attempts=" + attempts +
                ", created_at=" + created_at +
                '}';
    }
}
