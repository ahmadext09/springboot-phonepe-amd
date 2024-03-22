package com.amdck.phonepe.pg;


import com.amdck.phonepe.pg.model.PhonepeOrder;
import com.amdck.phonepe.pg.model.RazorpayOrder;
import com.amdck.phonepe.pg.model.RazorpayOrderIDRequest;
import com.amdck.phonepe.pg.model.paytm.PaytmCallbackData;
import com.amdck.phonepe.pg.model.paytm.PaytmTxnOrder;
import com.amdck.phonepe.pg.model.razorpay.OrderPaidEvent;
import com.paytm.pg.merchant.PaytmChecksum;
import jakarta.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Objects;

@Controller
@RequestMapping("/paytm_pg")
public class PaytmController {


    @Autowired
    private AppService appService;


    @PostMapping(AppConstants.Endpoints.INITIATE_PAYTM_TXN)
    @ResponseBody
    public ResponseEntity<String> initiatePaytmTransaction(@RequestBody PaytmTxnOrder paytmTxnOrder) {
        try {
            JSONObject paytmParams = new JSONObject();
            String callbackUrl = AppConstants.Usage.APP_BASE_URL + "/paytm_pg/txn_callback";
            JSONObject body = new JSONObject();
            body.put("requestType", "Payment");
            body.put("mid", AppConstants.PAYTM_KEYS.paytmMIdProd);
            body.put("websiteName", "DEFAULT");

            body.put("orderId", paytmTxnOrder.getOrderId());
            body.put("callbackUrl", callbackUrl);

            JSONObject txnAmount = new JSONObject();
            txnAmount.put("value", paytmTxnOrder.getAmount());
            txnAmount.put("currency", "INR");

            JSONObject userInfo = new JSONObject();
            userInfo.put("custId", paytmTxnOrder.getCustId());
            userInfo.put("email", paytmTxnOrder.getEmail());

            JSONObject extendInfo = new JSONObject();
            extendInfo.put("udf1", paytmTxnOrder.getEmail());

            body.put("txnAmount", txnAmount);
            body.put("userInfo", userInfo);
            body.put("extendInfo", extendInfo);

            String checksum = PaytmChecksum.generateSignature(body.toString(), AppConstants.PAYTM_KEYS.paytmKeyProd);

            JSONObject head = new JSONObject();
            head.put("signature", checksum);

            paytmParams.put("body", body);
            paytmParams.put("head", head);

            String apiUrl = String.format(AppConstants.PAYTM_KEYS.paytmTransactionApiProdBaseUrl, body.getString("mid"), body.getString("orderId"));

            ResponseEntity<String> responseEntity = callExternalApi(paytmParams, apiUrl, body.toString());

            JSONObject responseBody = new JSONObject(responseEntity.getBody());
            JSONObject resultBody = responseBody.getJSONObject("body");
            resultBody.put("orderId", body.getString("orderId"));
            resultBody.put("amount", txnAmount.getString("value"));
            return ResponseEntity.ok().body(responseBody.toString());


//            JSONObject responseBody = new JSONObject(responseEntity.getBody());
//            JSONObject resultBody = responseBody.getJSONObject("body");
//            JSONObject resultInfo = resultBody.getJSONObject("resultInfo");
//
//            if (resultInfo.getString("resultStatus").equals("S")) {
//
//                String orderId = resultBody.getString("orderId");
//                String amount = resultBody.getString("amount");
//
//                resultBody.put("orderId", orderId);
//                resultBody.put("amount", amount);
//            }
//
//            return ResponseEntity.ok().body(responseBody.toString());

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + exception.getMessage());
        }
    }


    private ResponseEntity<String> callExternalApi(JSONObject requestParams, String apiUrl, String requestBody) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestEntity = new HttpEntity<>(requestParams.toString(), headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            System.out.println("response: " + responseEntity.toString());
            return responseEntity;
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during API call: " + exception.getMessage());
        }
    }


    @PostMapping("/txn_callback")
    @ResponseBody
    public ResponseEntity<String> handlePaytmCallback(
            @RequestParam(value = "BANKTXNID", required = false) String bankTxnId,
            @RequestParam(value = "CHECKSUMHASH", required = false) String checksumHash,
            @RequestParam(value = "CURRENCY", required = false) String currency,
            @RequestParam(value = "GATEWAYNAME", required = false) String gatewayName,
            @RequestParam(value = "MID", required = false) String mid,
            @RequestParam(value = "ORDERID", required = false) String orderId,
            @RequestParam(value = "PAYMENTMODE", required = false) String paymentMode,
            @RequestParam(value = "RESPCODE", required = false) String respCode,
            @RequestParam(value = "RESPMSG", required = false) String respMsg,
            @RequestParam(value = "STATUS", required = false) String status,
            @RequestParam(value = "TXNAMOUNT", required = false) String txnAmount,
            @RequestParam(value = "TXNDATE", required = false) String txnDate,
            @RequestParam(value = "TXNID", required = false) String txnId,
            @RequestParam(value = "UDF_1", required = false) String udf1,
            @RequestParam(value = "BANKNAME", required = false) String bankName) {

        try {

            PaytmCallbackData callbackData = new PaytmCallbackData(
                    bankTxnId, checksumHash, currency, gatewayName, mid, orderId,
                    paymentMode, respCode, respMsg, status, txnAmount, txnDate,
                    txnId, udf1, bankName);


            System.out.println("Bank Transaction ID: " + bankTxnId);
            System.out.println("Checksum Hash: " + checksumHash);
            System.out.println("Currency: " + currency);
            System.out.println("Gateway Name: " + gatewayName);
            System.out.println("Merchant ID: " + mid);
            System.out.println("Order ID: " + orderId);
            System.out.println("Payment Mode: " + paymentMode);
            System.out.println("Response Code: " + respCode);
            System.out.println("Response Message: " + respMsg);
            System.out.println("Status: " + status);
            System.out.println("Transaction Amount: " + txnAmount);
            System.out.println("Transaction Date: " + txnDate);
            System.out.println("Transaction ID: " + txnId);
            System.out.println("User Defined Field 1: " + udf1);
            System.out.println("Bank Name: " + bankName);

            if (Objects.equals(callbackData.getStatus(), AppConstants.PAYTM_KEYS.PAYTM_STATUS_SUCCESS)) {
                addPoints(callbackData.getOrderId(), callbackData.getTxnAmount(), callbackData.getUdf1(), callbackData.getTxnId());
            }

            return ResponseEntity.ok("Callback received and processed successfully");
        } catch (Exception e) {
//            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing callback");
        }
    }

    public boolean addPoints(@Nullable String orderId, @Nullable String amount, @Nullable String userEmail, @Nullable String transactionId) {
        if (userEmail != null && orderId != null && transactionId != null && amount != null) {
            String text = "userEmail: " + userEmail + ", orderId: " + orderId + ", transactionId: " + transactionId + ", amount: " + amount;
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
