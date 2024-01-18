package com.amdck.phonepe.pg;

import com.amdck.phonepe.pg.model.PaymentResponse;
import com.amdck.phonepe.pg.model.PhonepeOrder;
import com.amdck.phonepe.pg.model.RazorpayOrder;
import com.amdck.phonepe.pg.model.RazorpayOrderIDRequest;
import com.amdck.phonepe.pg.model.razorpay.OrderPaidEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("/rpay")
public class RazorpayController {

    @Autowired
    private AppService appService;

    String razorpayOrderUrl = "https://api.razorpay.com/v1/orders";

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "RazorPay Test";
    }

    @PostMapping(AppConstants.Endpoints.INITIATE_RAZORPAY_TXN)
    @ResponseBody
    public ResponseEntity<String> initiateRazorpayTxn(@RequestBody RazorpayOrder razorpayOrder) throws JSONException {
        String receipt = "RF" + String.valueOf(System.currentTimeMillis());
        int amount = razorpayOrder.getAmount();
        RazorpayOrderIDRequest razorpayOrderbody = new RazorpayOrderIDRequest(amount*100, AppConstants.Usage.INDIAN_CURRENCY_ISO_CODE, receipt);
        return new ResponseEntity<>(makeExternalApiCall(razorpayOrderbody), HttpStatus.OK);

    }

    public String getEncodedAuthKey() {
        String combinedString = AppConstants.KEYS.razorpayTestKey + ":" + AppConstants.KEYS.razorpayTestSecret;
        byte[] bytes = combinedString.getBytes();
        byte[] encodedBytes = Base64.getEncoder().encode(bytes);
        String base64Encoded = new String(encodedBytes);
        String auth = "Basic " + base64Encoded;
        return auth;
    }


    public String makeExternalApiCall(RazorpayOrderIDRequest razorpayOrderBody) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", getEncodedAuthKey());
            HttpEntity<RazorpayOrderIDRequest> requestEntity = new HttpEntity<>(razorpayOrderBody, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    razorpayOrderUrl, HttpMethod.POST, requestEntity, String.class);
            return responseEntity.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            return ex.getResponseBodyAsString();
        }
    }


    @PostMapping(AppConstants.Endpoints.WEBHOOK_PAID_CALLBACK)
    @ResponseBody
    public ResponseEntity<String> paymentVerification(@RequestBody String requestbody) throws JSONException, JsonProcessingException {
        ObjectMapper paymentObjectMapper = new ObjectMapper();
        OrderPaidEvent orderPaidEvent = paymentObjectMapper.readValue(requestbody, OrderPaidEvent.class);
        addPoints(orderPaidEvent);
        return ResponseEntity.ok("Data processed successfully");
    }

    public boolean addPoints(OrderPaidEvent orderPaidEvent) {
        String email = orderPaidEvent.getPayload().getPayment().getEntity().getNotes().getUserEmail();
        String receipt = orderPaidEvent.getPayload().getOrder().getEntity().getReceipt();
        String payment_id = orderPaidEvent.getPayload().getPayment().getEntity().getId();
        Integer amount = orderPaidEvent.getPayload().getPayment().getEntity().getAmount();
        if (email != null && receipt != null && payment_id != null && amount != null) {
            String text = "email: " + email + ", receipt: " + receipt + ", payment_id: " + payment_id + ", ammount: " + amount;
            try {
                appService.sendMessage(AppConstants.Telegram.botToken, AppConstants.Telegram.chatId, text, "HTML");
            } catch (IOException e) {
                return false;
            }
            return true;
        }
        return false;
    }



}
