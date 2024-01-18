package com.amdck.phonepe.pg.model.razorpay;


import com.fasterxml.jackson.annotation.JsonProperty;

public  class Notes {

        @JsonProperty("user_email")
        private String userEmail;

        public Notes() {

        }
        public Notes(String userEmail) {
            this.userEmail = userEmail;
        }
        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }
    }

