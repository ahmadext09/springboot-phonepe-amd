package com.amdck.phonepe.pg.model.razorpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload {

    @JsonProperty("payment")
    private Payment payment;

    @JsonProperty("order")
    private Order order;


    public Payload() {
    }

    public Payload(@JsonProperty("payment") Payment payment,
                   @JsonProperty("order") Order order) {
        this.payment = payment;
        this.order = order;
    }


    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "payment=" + payment +
                ", order=" + order +
                '}';
    }
}
