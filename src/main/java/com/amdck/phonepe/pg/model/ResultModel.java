package com.amdck.phonepe.pg.model;

public class ResultModel {
    private String response;


    public ResultModel(String response) {

        this.response = response;
    }


    public ResultModel() {

    }
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
