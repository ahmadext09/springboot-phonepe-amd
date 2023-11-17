package com.amdck.phonepe.pg.model;

public class PhonePeSdkResponse {

        private String requestBase64;
        private String xVerify;

        // Default constructor
        public PhonePeSdkResponse() {
        }

        // Parameterized constructor
        public PhonePeSdkResponse(String requestBase64, String xVerify) {
            this.requestBase64 = requestBase64;
            this.xVerify = xVerify;
        }

        // Getter for requestBase64
        public String getRequestBase64() {
            return requestBase64;
        }

        // Setter for requestBase64
        public void setRequestBase64(String requestBase64) {
            this.requestBase64 = requestBase64;
        }

        // Getter for xVerify
        public String getXVerify() {
            return xVerify;
        }

        // Setter for xVerify
        public void setXVerify(String xVerify) {
            this.xVerify = xVerify;
        }
    }


