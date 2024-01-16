package com.amdck.phonepe.pg.model.razorpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {
    @JsonProperty("id")
    private String id;

    @JsonProperty("entity")
    private String entity;

    @JsonProperty("name")
    private String name;

    @JsonProperty("last4")
    private String last4;

    @JsonProperty("network")
    private String network;

    @JsonProperty("type")
    private String type;

    @JsonProperty("issuer")
    private String issuer;

    @JsonProperty("international")
    private boolean international;

    @JsonProperty("emi")
    private boolean emi;

    public Card() {
    }

    public Card(@JsonProperty("id") String id,
                      @JsonProperty("entity") String entity,
                      @JsonProperty("name") String name,
                      @JsonProperty("last4") String last4,
                      @JsonProperty("network") String network,
                      @JsonProperty("type") String type,
                      @JsonProperty("issuer") String issuer,
                      @JsonProperty("international") boolean international,
                      @JsonProperty("emi") boolean emi) {
        this.id = id;
        this.entity = entity;
        this.name = name;
        this.last4 = last4;
        this.network = network;
        this.type = type;
        this.issuer = issuer;
        this.international = international;
        this.emi = emi;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public boolean isInternational() {
        return international;
    }

    public void setInternational(boolean international) {
        this.international = international;
    }

    public boolean isEmi() {
        return emi;
    }

    public void setEmi(boolean emi) {
        this.emi = emi;
    }

    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", entity='" + entity + '\'' +
                ", name='" + name + '\'' +
                ", last4='" + last4 + '\'' +
                ", network='" + network + '\'' +
                ", type='" + type + '\'' +
                ", issuer='" + issuer + '\'' +
                ", international=" + international +
                ", emi=" + emi +
                '}';
    }
}
