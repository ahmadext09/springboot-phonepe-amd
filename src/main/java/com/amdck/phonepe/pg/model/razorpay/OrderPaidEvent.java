package com.amdck.phonepe.pg.model.razorpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderPaidEvent {

    @JsonProperty("entity")
    private String entity;

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("event")
    private String event;

    @JsonProperty("contains")
    private List<String> contains;

    @JsonProperty("payload")
    private Payload payload;

    @JsonProperty("created_at")
    private long createdAt;

    public OrderPaidEvent() {
    }

    public OrderPaidEvent(String entity, String accountId, String event, List<String> contains, Payload payload, long createdAt) {
        this.entity = entity;
        this.accountId = accountId;
        this.event = event;
        this.contains = contains;
        this.payload = payload;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OrderPaidEvent{" +
                "entity='" + entity + '\'' +
                ", accountId='" + accountId + '\'' +
                ", event='" + event + '\'' +
                ", contains=" + contains +
                ", payload=" + payload +
                ", createdAt=" + createdAt +
                '}';
    }



    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public List<String> getContains() {
        return contains;
    }

    public void setContains(List<String> contains) {
        this.contains = contains;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
