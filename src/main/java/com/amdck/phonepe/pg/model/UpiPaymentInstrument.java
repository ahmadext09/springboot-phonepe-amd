package com.amdck.phonepe.pg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class UpiPaymentInstrument {

        @JsonProperty("type")
        private String type;

        @JsonProperty("intentUrl")
        private String intentUrl;

        // Getters and setters

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIntentUrl() {
            return intentUrl;
        }

        public void setIntentUrl(String intentUrl) {
            this.intentUrl = intentUrl;
        }
    }

