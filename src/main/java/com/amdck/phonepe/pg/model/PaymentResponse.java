package com.amdck.phonepe.pg.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentResponse {
    @JsonProperty("success")
    private boolean success;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PaymentData data;

    public PaymentResponse() {
    }

    public PaymentResponse(boolean success, String code, String message, PaymentData data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PaymentData getData() {
        return data;
    }

    public void setData(PaymentData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data.toString()+
                '}';
    }
}


