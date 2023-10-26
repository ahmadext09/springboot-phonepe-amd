package com.amdck.phonepe.pg;



import com.amdck.phonepe.pg.model.PhonepeOrder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private AppService appService;

    String pgUrl = "/pg/v1/pay";
    String debugSaltKey = "05b8ed83-51ef-49ae-bb8b-aac4df140457";
    String debugMID = "PGTESTPAYUAT111";
    String debugApiUrl = "https://api-preprod.phonepe.com/apis/pg-sandbox/pg/v1/pay";

    @PostMapping(AppConstants.Endpoints.INITIATE_PHONEPE_TXN)
    public ResponseEntity<String> initiatePhonePeTxn(@RequestBody PhonepeOrder phonepeOrder) throws JSONException {
        JSONObject phonePeBody = new JSONObject();
        phonePeBody.put("merchantId", debugMID);
        phonePeBody.put("merchantTransactionId", System.currentTimeMillis() + "");
        phonePeBody.put("amount", Float.toString(phonepeOrder.getAmount()).replace(".", "") + "0");
        phonePeBody.put("merchantUserId", phonepeOrder.getEmail());
        phonePeBody.put("redirectUrl", "https://www.youtube.com/");
        phonePeBody.put("redirectMode", "POST");
        phonePeBody.put("callbackUrl", "https://upset-plums-sort.loca.lt/api/phonepe-callback");
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
    public ResponseEntity<String> phonepeCallback(@RequestBody String requestBody) throws IOException {
        appService.sendMessage( AppConstants.Telegram.botToken, AppConstants.Telegram.chatId,"hello","HTML");
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/test")
    public String getTestString(){
        return "project working";
    }


}

