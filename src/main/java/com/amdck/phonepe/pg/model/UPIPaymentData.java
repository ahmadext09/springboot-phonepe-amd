package com.amdck.phonepe.pg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UPIPaymentData {

        @JsonProperty("merchantId")
        private String merchantId;

        @JsonProperty("merchantTransactionId")
        private String merchantTransactionId;

        @JsonProperty("instrumentResponse")
        private UpiPaymentInstrument instrumentResponse;

        // Getters and setters

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

        public UpiPaymentInstrument getInstrumentResponse() {
            return instrumentResponse;
        }

        public void setInstrumentResponse(UpiPaymentInstrument instrumentResponse) {
            this.instrumentResponse = instrumentResponse;
        }
    }

