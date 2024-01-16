package com.amdck.phonepe.pg.model.razorpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {

    @JsonProperty("entity")
    private PaymentEntity entity;

    public Payment() {
    }

    public Payment(PaymentEntity entity) {

        this.entity = entity;
    }

    public PaymentEntity getEntity() {
        return entity;
    }

    public void setEntity(PaymentEntity entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "entity=" + entity +
                '}';
    }
}
