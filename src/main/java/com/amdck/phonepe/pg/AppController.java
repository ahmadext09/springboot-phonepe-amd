package com.amdck.phonepe.pg;



import com.amdck.phonepe.pg.model.PaymentResponse;
import com.amdck.phonepe.pg.model.PhonepeOrder;
import com.amdck.phonepe.pg.model.ResultModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Base64;



@Controller
@RequestMapping("/api")
public class AppController {

    @Autowired
    private AppService appService;
    private PaymentResponse paymentResult;

    String pgUrl = "/pg/v1/pay";
    String debugSaltKey = "05b8ed83-51ef-49ae-bb8b-aac4df140457";
    String debugMID = "PGTESTPAYUAT111";
    String debugApiUrl = "https://api-preprod.phonepe.com/apis/pg-sandbox/pg/v1/pay";

    String globalMerchantTransId="";

    int getEndPointCounter = 0;


    @PostMapping(AppConstants.Endpoints.INITIATE_PHONEPE_TXN)
    @ResponseBody
    public ResponseEntity<String> initiatePhonePeTxn(@RequestBody PhonepeOrder phonepeOrder) throws JSONException {
        String MERCHANT_TRANSACTION_ID = String.valueOf(System.currentTimeMillis());
        globalMerchantTransId=MERCHANT_TRANSACTION_ID;
        JSONObject phonePeBody = new JSONObject();
        phonePeBody.put("merchantId", debugMID);
        //phonePeBody.put("merchantTransactionId", System.currentTimeMillis() + "");
        phonePeBody.put("merchantTransactionId", MERCHANT_TRANSACTION_ID);
        phonePeBody.put("amount", Float.toString(phonepeOrder.getAmount()).replace(".", "") + "0");
        phonePeBody.put("merchantUserId", "samsungs9");
//        phonePeBody.put("redirectUrl", "https://www.youtube.com/");
        phonePeBody.put("redirectUrl", AppConstants.Usage.APP_BASE_URL+"/api/ui-redirect-url?merchantId=PGTESTPAYUAT111&merchantTransactionId="+MERCHANT_TRANSACTION_ID);
        phonePeBody.put("redirectMode", "REDIRECT");
        phonePeBody.put("callbackUrl", AppConstants.Usage.APP_BASE_URL+"/api/phonepe-callback?merchantId=PGTESTPAYUAT111&merchantTransactionId="+MERCHANT_TRANSACTION_ID);
        //phonePeBody.put("callbackUrl", "https://webhook.site/4b626c83-8d0e-47e8-8745-9928037adbec");
        JSONObject paymentInstrument = new JSONObject();
        paymentInstrument.put("type", "PAY_PAGE");
        phonePeBody.put("paymentInstrument", paymentInstrument);

        String request = AppUtility.encodeToBase64(phonePeBody.toString());

        String sha256 = AppUtility.hashToSHA256(request + pgUrl + debugSaltKey);
        String xVerify = sha256 + "###1";


        //return phonePeBody + "\n\n" + AppUtility.encodeToBase64(phonePeBody.toString()) + "\n\n" + xVerify + "\n\n" + debugCurl;
        return new ResponseEntity<>(callExternalApi(xVerify, request), HttpStatus.OK);
    }

    public String callExternalApi(String xVerify, String request) {
        RestTemplate restTemplate = new RestTemplate();

        String requestBody = "{\"request\": \"" + request + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/json");
        headers.set("x-verify", xVerify);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                debugApiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class);

        return responseEntity.getBody();
        /*if (responseEntity.getStatusCode().is2xxSuccessful()) {

            return responseEntity.getBody();
        } else {
            return "API call failed with status code: " + responseEntity.getStatusCodeValue();
        }*/
    }

    @PostMapping(AppConstants.Endpoints.PHONEPE_CALLBACK)
    @ResponseBody
    public ResponseEntity<String> phonepeCallback(@RequestBody String requestBody, @RequestParam("merchantId") String merchantId, @RequestParam("merchantTransactionId") String merchantTransactionId) throws IOException {
//    public ResponseEntity<String> phonepeCallback(@RequestBody String requestBody) throws IOException {
        String result = requestBody;

//        ObjectMapper objectMapper = new ObjectMapper();
//        ResultModel resultModel = objectMapper.readValue(result, ResultModel.class);
//
//        String base64EncodedData = resultModel.getResponse();
//
//        byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedData);
//        String resultjson = new String(decodedBytes);
//
//        ObjectMapper paymentObjectMapper = new ObjectMapper();
//        PaymentResponse paymentResponse = paymentObjectMapper.readValue(resultjson, PaymentResponse.class);
//        if (paymentResponse != null) {
//            paymentResult = paymentResponse;
//        }

        appService.sendMessage(AppConstants.Telegram.botToken, AppConstants.Telegram.chatId, "hello", "HTML");
//        appService.sendMessage(AppConstants.Telegram.botToken, AppConstants.Telegram.chatId, merchantId, "HTML");
//        appService.sendMessage(AppConstants.Telegram.botToken, AppConstants.Telegram.chatId, merchantTransactionId, "HTML");
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }


    @GetMapping("/get-redirect-url")
    @ResponseBody
    public PaymentResponse getRedirectUrl(@RequestParam("merchantId") String merchantId, @RequestParam("merchantTransactionId") String merchantTransactionId){
        getEndPointCounter++;
      //  return new ResponseEntity<>(callCheckStatusApi(merchantId, merchantTransactionId), HttpStatus.OK);
        String result = callCheckStatusApi(merchantId, merchantTransactionId);
        ObjectMapper paymentObjectMapper = new ObjectMapper();
        try {
            PaymentResponse paymentStatus = paymentObjectMapper.readValue(result, PaymentResponse.class);
            paymentResult =paymentStatus;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return paymentResult;
    }

    @PostMapping("/post-redirect-url")
    @ResponseBody
    public ResponseEntity<String> postRedirectUrl(@RequestParam("merchantId") String merchantId, @RequestParam("merchantTransactionId") String merchantTransactionId){
        return new ResponseEntity<>(callCheckStatusApi(merchantId, merchantTransactionId), HttpStatus.OK);
    }

    public String callCheckStatusApi( String merchantId, String merchantTransactionId){

        String pgStatusUrl = "/pg/v1/status/"+ merchantId +"/"+ merchantTransactionId;
        String sha256 = AppUtility.hashToSHA256(pgStatusUrl + debugSaltKey);
        String xVerify = sha256 + "###1";
        String debugStatusApiUrl = "https://api-preprod.phonepe.com/apis/pg-sandbox/pg/v1/status/"+merchantId+"/"+merchantTransactionId;


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/json");
        headers.set("x-verify", xVerify);
        headers.set("x-merchant-id", merchantId);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                debugStatusApiUrl,
                HttpMethod.GET,
                requestEntity,
                String.class);

        return responseEntity.getBody();
    }



    @GetMapping("/test")
    @ResponseBody
    public int getTestString() throws IOException {
//        if (paymentResult.isSuccess()) {
////            ObjectMapper objectMapper = new ObjectMapper();
////            ResultModel resultModel = objectMapper.readValue(result, ResultModel.class);
////
////            String base64EncodedData = resultModel.getResponse();
////
////            byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedData);
////            String resultjson = new String(decodedBytes);
//////            return resultjson;
////
//            long longValue = paymentResult.getData().getAmount();
//
//// Convert long to double
//            double doubleValue; // Dividing by 100 to move the decimal point
//            doubleValue = (double) longValue / 100;
//
//// Format double to have exactly two decimal places
//           // DecimalFormat decimalFormat = new DecimalFormat("#0.00");
//            String formattedValue = String.format("%.2f", doubleValue);
//           // String formattedValue = decimalFormat.format(doubleValue);
//
//            return formattedValue;
//           // return paymentResult;
//            //return longValue;
//        }
//        else
//           return "error from server";
        appService.sendMessage(AppConstants.Telegram.botToken, AppConstants.Telegram.chatId, "working", "HTML");
        return getEndPointCounter;
    }

    @GetMapping("/ui-redirect-url")
    public String redirectUIPage(@RequestParam("merchantId") String merchantId, @RequestParam("merchantTransactionId") String merchantTransactionId, Model model) {

        String paymentStatusText = "processing please wait";
        String result = callCheckStatusApi(merchantId, merchantTransactionId);
        ObjectMapper paymentObjectMapper = new ObjectMapper();
        try {
            PaymentResponse paymentStatus = paymentObjectMapper.readValue(result, PaymentResponse.class);
            if (paymentStatus.isSuccess()) {
                paymentStatusText = "Success, Points Added";
            } else
                paymentStatusText = "failed";
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("paymentStatus", paymentStatusText);

        return "redirect-ui";
    }



}

