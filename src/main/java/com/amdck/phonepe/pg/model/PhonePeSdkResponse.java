package com.amdck.phonepe.pg.model;

public class PhonePeSdkResponse {

        private String requestBase64;
        private String xVerify;


        public PhonePeSdkResponse() {
        }


        public PhonePeSdkResponse(String requestBase64, String xVerify) {
            this.requestBase64 = requestBase64;
            this.xVerify = xVerify;
        }


        public String getRequestBase64() {
            return requestBase64;
        }


        public void setRequestBase64(String requestBase64) {
            this.requestBase64 = requestBase64;
        }


        public String getXVerify() {
            return xVerify;
        }


        public void setXVerify(String xVerify) {
            this.xVerify = xVerify;
        }
    }


