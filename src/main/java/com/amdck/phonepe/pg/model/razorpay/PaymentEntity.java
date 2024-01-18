package com.amdck.phonepe.pg.model.razorpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentEntity {

    @JsonProperty("id")
    private String id;

    @JsonProperty("entity")
    private String entity;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("status")
    private String status;

    @JsonProperty("order_id")
    private String order_id;

    @JsonProperty("invoice_id")
    private String invoice_id;

    @JsonProperty("international")
    private boolean international;

    @JsonProperty("method")
    private String method;

    @JsonProperty("amount_refunded")
    private int amount_refunded;

    @JsonProperty("refund_status")
    private String refund_status;

    @JsonProperty("captured")
    private boolean captured;

    @JsonProperty("description")
    private String description;

    @JsonProperty("card")
    private Card card;

    @JsonProperty("card_id")
    private String card_id;

    @JsonProperty("bank")
    private String bank;

    @JsonProperty("wallet")
    private String wallet;

    @JsonProperty("vpa")
    private String vpa;

    @JsonProperty("email")
    private String email;

    @JsonProperty("contact")
    private String contact;


    @JsonProperty("notes")
    private Notes notes;

    @JsonProperty("fee")
    private int fee;

    @JsonProperty("tax")
    private int tax;

    @JsonProperty("error_code")
    private String error_code;

    @JsonProperty("error_description")
    private String error_description;

    @JsonProperty("created_at")
    private long created_at;


    public PaymentEntity() {
    }


    public PaymentEntity(@JsonProperty("id") String id,
                         @JsonProperty("entity") String entity,
                         @JsonProperty("amount") int amount,
                         @JsonProperty("currency") String currency,
                         @JsonProperty("status") String status,
                         @JsonProperty("order_id") String order_id,
                         @JsonProperty("invoice_id") String invoice_id,
                         @JsonProperty("international") boolean international,
                         @JsonProperty("method") String method,
                         @JsonProperty("amount_refunded") int amount_refunded,
                         @JsonProperty("refund_status") String refund_status,
                         @JsonProperty("captured") boolean captured,
                         @JsonProperty("description") String description,
                         @JsonProperty("card_id") String card_id,
                         @JsonProperty("card") Card card,
                         @JsonProperty("bank") String bank,
                         @JsonProperty("wallet") String wallet,
                         @JsonProperty("vpa") String vpa,
                         @JsonProperty("email") String email,
                         @JsonProperty("contact") String contact,
                         @JsonProperty("fee") int fee,
                         @JsonProperty("tax") int tax,
                         @JsonProperty("error_code") String error_code,
                         @JsonProperty("error_description") String error_description,
                         @JsonProperty("created_at") long created_at) {
        this.id = id;
        this.entity = entity;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.order_id = order_id;
        this.invoice_id = invoice_id;
        this.international = international;
        this.method = method;
        this.amount_refunded = amount_refunded;
        this.refund_status = refund_status;
        this.captured = captured;
        this.description = description;
        this.card_id = card_id;
        this.card = card;
        this.bank = bank;
        this.wallet = wallet;
        this.vpa = vpa;
        this.email = email;
        this.contact = contact;
//        this.notes = notes;
        this.fee = fee;
        this.tax = tax;
        this.error_code = error_code;
        this.error_description = error_description;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public boolean isInternational() {
        return international;
    }

    public void setInternational(boolean international) {
        this.international = international;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getAmount_refunded() {
        return amount_refunded;
    }

    public void setAmount_refunded(int amount_refunded) {
        this.amount_refunded = amount_refunded;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getVpa() {
        return vpa;
    }

    public void setVpa(String vpa) {
        this.vpa = vpa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }



    @Override
    public String toString() {
        return "PaymentEntity{" +
                "id='" + id + '\'' +
                ", entity='" + entity + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                ", order_id='" + order_id + '\'' +
                ", invoice_id='" + invoice_id + '\'' +
                ", international=" + international +
                ", method='" + method + '\'' +
                ", amount_refunded=" + amount_refunded +
                ", refund_status='" + refund_status + '\'' +
                ", captured=" + captured +
                ", description='" + description + '\'' +
                ", card=" + card +
                ", card_id='" + card_id + '\'' +
                ", bank='" + bank + '\'' +
                ", wallet='" + wallet + '\'' +
                ", vpa='" + vpa + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", fee=" + fee +
                ", tax=" + tax +
                ", error_code='" + error_code + '\'' +
                ", error_description='" + error_description + '\'' +
                ", created_at=" + created_at +
                '}';
    }



}
