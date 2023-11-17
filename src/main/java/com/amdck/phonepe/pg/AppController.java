package com.amdck.phonepe.pg;


import com.amdck.phonepe.pg.model.*;
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
import java.util.Base64;


@Controller
@RequestMapping("/api")
public class AppController {

    @Autowired
    private AppService appService;
    private PaymentResponse paymentResultGlobal;

    private String test;
    private String test2;

    String pgUrl = "/pg/v1/pay";
    String debugSaltKey = "05b8ed83-51ef-49ae-bb8b-aac4df140457";
    String debugMID = "PGTESTPAYUAT111";
    String debugApiUrl = "https://api-preprod.phonepe.com/apis/pg-sandbox/pg/v1/pay";
    String debugBaseStatusUrl = "https://api-preprod.phonepe.com/apis/pg-sandbox/pg/v1/status/";

    String prodSaltKey ="2a1b307d-afb9-46f2-aa85-77951f84ee12";
    String prodMID ="M12C74831DKZ";

    String prodApiUrl = "https://api.phonepe.com/apis/hermes/pg/v1/pay";


    @PostMapping(AppConstants.Endpoints.INITIATE_PHONEPE_TXN)
    @ResponseBody
    public ResponseEntity<String> initiatePhonePeTxn(@RequestBody PhonepeOrder phonepeOrder) throws JSONException {
        String MERCHANT_TRANSACTION_ID = String.valueOf(System.currentTimeMillis());
        JSONObject phonePeBody = new JSONObject();
        phonePeBody.put("merchantId", prodMID);
        phonePeBody.put("merchantTransactionId", MERCHANT_TRANSACTION_ID);
        phonePeBody.put("amount", Double.toString(phonepeOrder.getAmount()).replace(".", "") + "0");
        phonePeBody.put("merchantUserId", Long.toString(phonepeOrder.getUserId()));
        phonePeBody.put("redirectUrl", AppConstants.Usage.APP_BASE_URL + AppConstants.Endpoints.REQUEST_MAPPING + AppConstants.Endpoints.REDIRECT_URL + "?merchantId=" + prodMID + "&merchantTransactionId=" + MERCHANT_TRANSACTION_ID + "&userEmail=" + phonepeOrder.getEmail());
       // phonePeBody.put("redirectUrl",AppConstants.Usage.APP_BASE_URL+AppConstants.Endpoints.REQUEST_MAPPING+"/redirect");
        phonePeBody.put("redirectMode", "REDIRECT");
        phonePeBody.put("callbackUrl", AppConstants.Usage.APP_BASE_URL + AppConstants.Endpoints.REQUEST_MAPPING + AppConstants.Endpoints.PHONEPE_CALLBACK + "?merchantId=" + prodMID + "&merchantTransactionId=" + MERCHANT_TRANSACTION_ID + "&userEmail=" + phonepeOrder.getEmail()+"&userAmount="+phonepeOrder.getAmount());
        JSONObject paymentInstrument = new JSONObject();
        paymentInstrument.put("type", "PAY_PAGE");
        phonePeBody.put("paymentInstrument", paymentInstrument);

        String request = AppUtility.encodeToBase64(phonePeBody.toString());

        String sha256 = AppUtility.hashToSHA256(request + pgUrl + prodSaltKey);
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

        ResponseEntity<String> responseEntity = restTemplate.exchange(prodApiUrl, HttpMethod.POST, requestEntity, String.class);

        return responseEntity.getBody();
        /*if (responseEntity.getStatusCode().is2xxSuccessful()) {

            return responseEntity.getBody();
        } else {
            return "API call failed with status code: " + responseEntity.getStatusCodeValue();
        }*/
    }

    @PostMapping(AppConstants.Endpoints.PHONEPE_CALLBACK)
    @ResponseBody
    public ResponseEntity<String> phonepeCallback(@RequestBody String requestBody, @RequestParam("merchantId") String merchantId, @RequestParam("merchantTransactionId") String merchantTransactionId, @RequestParam("userEmail") String userEmail, @RequestParam("userAmount") double userAmount) throws IOException {

        test = requestBody;
        String result = requestBody;


        ObjectMapper objectMapper = new ObjectMapper();
        ResultModel resultModel = objectMapper.readValue(result, ResultModel.class);

        String base64EncodedData = resultModel.getResponse();

        byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedData);
        String resultjson = new String(decodedBytes);
        test2 = resultjson;
        ObjectMapper paymentObjectMapper = new ObjectMapper();
        PaymentResponse paymentResponse = paymentObjectMapper.readValue(resultjson, PaymentResponse.class);

        if (paymentResponse.isSuccess() && paymentResponse.getCode().equals(AppConstants.CODE.PAYMENT_SUCCESS) && userAmount== (double) paymentResponse.getData().getAmount() / 100.0) {
            boolean isPointAdded = addPoints(paymentResponse.getData().getAmount(), paymentResponse.getData().getTransactionId(), paymentResponse.getData().getMerchantTransactionId(), userEmail);
        }

//        String payAmt= String.valueOf(paymentResponse.getData().getAmount());
//
//
//        appService.sendMessage(AppConstants.Telegram.botToken, AppConstants.Telegram.chatId, payAmt, "HTML");
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }



    public String callCheckStatusApi(String merchantId, String merchantTransactionId) {

        String pgStatusUrl = "/pg/v1/status/" + merchantId + "/" + merchantTransactionId;
        String sha256 = AppUtility.hashToSHA256(pgStatusUrl + debugSaltKey);
        String xVerify = sha256 + "###1";
        String debugStatusApiUrl = debugBaseStatusUrl + merchantId + "/" + merchantTransactionId;


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/json");
        headers.set("x-verify", xVerify);
        headers.set("x-merchant-id", merchantId);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
       // ResponseEntity<String> responseEntity;
        int maxRetries = 12; // Maximum number of retries
        int retryDelayMilliseconds = 5000; // Delay in milliseconds (2 seconds)
        for (int retryCount = 0; retryCount < maxRetries; retryCount++) {
            try {
                ResponseEntity<String> responseEntity = restTemplate.exchange(debugStatusApiUrl, HttpMethod.GET, requestEntity, String.class);
                if (responseEntity.getStatusCode() == HttpStatus.OK) {
                    return responseEntity.getBody(); // Successful response, exit the loop
                }
            }catch (Exception e){
                try {
                    Thread.sleep(retryDelayMilliseconds); // Wait for the specified delay before retrying
                } catch (InterruptedException err) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new RuntimeException("API call failed after maximum retries.");

    }


    @GetMapping(AppConstants.Endpoints.REDIRECT_URL)
    public String redirectUIPage(@RequestParam("merchantId") String merchantId, @RequestParam("merchantTransactionId") String merchantTransactionId, @RequestParam("userEmail") String userEmail, Model model) {

        String paymentStatusText = "processing please wait";
        String result = callCheckStatusApi(merchantId, merchantTransactionId);
        ObjectMapper paymentObjectMapper = new ObjectMapper();
        PaymentResponse paymentStatus = null;
        try {
            paymentStatus = paymentObjectMapper.readValue(result, PaymentResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        paymentResultGlobal = paymentStatus;
        if (paymentStatus.isSuccess()) {
            if (paymentStatus.getCode().equals(AppConstants.CODE.PAYMENT_SUCCESS)) {
                paymentStatusText = AppConstants.STRING.STATUS_SUCCESS;
            } else {
                paymentStatusText = AppConstants.STRING.STATUS_PAYMENT_FAILED;
            }
        } else paymentStatusText = AppConstants.STRING.STATUS_PROCESS_FAILED;


        model.addAttribute("paymentStatus", paymentStatusText);

        return "redirect-ui";
    }


    public boolean addPoints(long amount, String transactionId, String merchantTransactionId, String userEmail) {

        String text = "amount = " + String.valueOf(amount) + ", transactionId = " + transactionId + ", merchantTransactionId = " + merchantTransactionId + ", userEmail = " + userEmail;
        try {
            appService.sendMessage(AppConstants.Telegram.botToken, AppConstants.Telegram.chatId, text, "HTML");
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {

        return  paymentResultGlobal.toString();
    }


   @PostMapping("/redirect")
    public  String redirect (@RequestParam("code") String responseCode,
                             @RequestParam("merchantId") String merchantId,
                             @RequestParam("transactionId") String transactionId,
                             @RequestParam("amount") long amount,
                             @RequestParam("providerReferenceId") String providerReferenceId, Model model) throws JsonProcessingException {
     //  String result = requestBody;
//       PhonePeRedirectModel rep =requestBody;

//       ObjectMapper paymentObjectMapper = new ObjectMapper();
//       PhonePeRedirectModel redirectResponse = paymentObjectMapper.readValue(rep, PhonePeRedirectModel.class);

       PhonePeRedirectModel response = new PhonePeRedirectModel();
       response.setCode(responseCode);
       response.setMerchantId(merchantId);
       response.setTransactionId(transactionId);
       response.setAmount(amount);
       response.setProviderReferenceId(providerReferenceId);


       String paymentStatusText = "processing please wait";
        String statusResult = callCheckStatusApi(response.getMerchantId(), response.getTransactionId());
        ObjectMapper paymentStatusObjectMapper = new ObjectMapper();
        PaymentResponse paymentStatus = null;
        try {
            paymentStatus = paymentStatusObjectMapper.readValue(statusResult, PaymentResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (paymentStatus.isSuccess()) {
            if (paymentStatus.getCode().equals(AppConstants.CODE.PAYMENT_SUCCESS)) {
                paymentStatusText = AppConstants.STRING.STATUS_SUCCESS;
            } else {
                paymentStatusText = AppConstants.STRING.STATUS_PAYMENT_FAILED;
            }
        } else paymentStatusText = AppConstants.STRING.STATUS_PROCESS_FAILED;


        model.addAttribute("paymentStatus", paymentStatusText);

        return "redirect-ui";
    }



    @PostMapping(AppConstants.Endpoints.INITIATE_PHONEPE_TXN_INTENT)
    @ResponseBody
    public ResponseEntity<String> initiatePhonePeTxnIntent(@RequestBody PhonepeOrder phonepeOrder) throws JSONException {
        String MERCHANT_TRANSACTION_ID = String.valueOf(System.currentTimeMillis());
        JSONObject phonePeBody = new JSONObject();
        phonePeBody.put("merchantId", debugMID);
        phonePeBody.put("merchantTransactionId", MERCHANT_TRANSACTION_ID);
        phonePeBody.put("amount", Double.toString(phonepeOrder.getAmount()).replace(".", "") + "0");
        phonePeBody.put("merchantUserId", Long.toString(phonepeOrder.getUserId()));
       // phonePeBody.put("redirectUrl", AppConstants.Usage.APP_BASE_URL + AppConstants.Endpoints.REQUEST_MAPPING + AppConstants.Endpoints.REDIRECT_URL + "?merchantId=" + debugMID + "&merchantTransactionId=" + MERCHANT_TRANSACTION_ID + "&userEmail=" + phonepeOrder.getEmail());
//         phonePeBody.put("redirectUrl",AppConstants.Usage.APP_BASE_URL+AppConstants.Endpoints.REQUEST_MAPPING+"/redirect");
//        phonePeBody.put("redirectMode", "REDIRECT");
        phonePeBody.put("callbackUrl", AppConstants.Usage.APP_BASE_URL + AppConstants.Endpoints.REQUEST_MAPPING + AppConstants.Endpoints.PHONEPE_CALLBACK + "?merchantId=" + debugMID + "&merchantTransactionId=" + MERCHANT_TRANSACTION_ID + "&userEmail=" + phonepeOrder.getEmail()+"&userAmount="+phonepeOrder.getAmount());
        JSONObject deviceContext = new JSONObject();
        deviceContext.put("deviceOS", "ANDROID");
        phonePeBody.put("deviceContext", deviceContext);
        JSONObject paymentInstrument = new JSONObject();
        paymentInstrument.put("type", "UPI_INTENT");
//        paymentInstrument.put("targetApp", "com.google.android.apps.nbu.paisa.user");
        paymentInstrument.put("targetApp", "net.one97.paytm");

        // Optional: Add account constraints if required
//        JSONArray accountConstraintsArray = new JSONArray();
//        JSONObject accountConstraint = new JSONObject();
//        accountConstraint.put("accountNumber", "50200070686170");
//        accountConstraint.put("ifsc", "HDFC0009504");
//        accountConstraintsArray.put(accountConstraint);
//
//        paymentInstrument.put("accountConstraints", accountConstraintsArray);

        phonePeBody.put("paymentInstrument", paymentInstrument);

        String request = AppUtility.encodeToBase64(phonePeBody.toString());

        String sha256 = AppUtility.hashToSHA256(request + pgUrl + debugSaltKey);
        String xVerify = sha256 + "###1";


        //return phonePeBody + "\n\n" + AppUtility.encodeToBase64(phonePeBody.toString()) + "\n\n" + xVerify + "\n\n" + debugCurl;
        return new ResponseEntity<>(callExternalApiUpiIntent(xVerify, request), HttpStatus.OK);
    }



    public String callExternalApiUpiIntent(String xVerify, String request) {
        RestTemplate restTemplate = new RestTemplate();

        String requestBody = "{\"request\": \"" + request + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/json");
        headers.set("x-verify", xVerify);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(debugApiUrl, HttpMethod.POST, requestEntity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UpiPaymentResponse  upiIntent = objectMapper.readValue( responseEntity.getBody(), UpiPaymentResponse.class);
            return upiIntent.getData().getInstrumentResponse().getIntentUrl();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }



    }


}

