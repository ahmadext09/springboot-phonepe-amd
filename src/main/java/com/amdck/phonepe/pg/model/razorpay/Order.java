package com.amdck.phonepe.pg.model.razorpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    @JsonProperty("entity")
    private OrderEntity entity;

    public Order() {
    }

    public Order(OrderEntity entity) {

        this.entity = entity;
    }

    public OrderEntity getEntity() {
        return entity;
    }

    public void setEntity(OrderEntity entity) {
        this.entity = entity;
    }

    public String toString() {
        return "Order{" +
                "entity=" + entity +
                '}';
    }

}
