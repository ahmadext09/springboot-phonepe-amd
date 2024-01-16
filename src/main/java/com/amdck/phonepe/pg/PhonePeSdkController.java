package com.amdck.phonepe.pg;

import com.amdck.phonepe.pg.model.PhonePeSdkResponse;
import com.amdck.phonepe.pg.model.PhonepeOrder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sdk")
public class PhonePeSdkController {

    String pgUrl = "/pg/v1/pay";
    String debugSaltKey = "05b8ed83-51ef-49ae-bb8b-aac4df140457";
    String debugMID = "PGTESTPAYUAT111";
    String debugApiUrl = "https://api-preprod.phonepe.com/apis/pg-sandbox/pg/v1/pay";
    String debugBaseStatusUrl = "https://api-preprod.phonepe.com/apis/pg-sandbox/pg/v1/status/";

    String prodSaltKey ="2a1b307d-afb9-46f2-aa85-77951f84ee12";
    String prodMID ="M12C74831DKZ";

    String prodApiUrl = "https://api.phonepe.com/apis/hermes/pg/v1/pay";


    @PostMapping(AppConstants.Endpoints.INITIATE_PHONEPE_TXN_SDK)
    @ResponseBody
    public ResponseEntity<PhonePeSdkResponse> initiatePhonePeTxnSdk(@RequestBody PhonepeOrder phonepeOrder) throws JSONException {
        String MERCHANT_TRANSACTION_ID = String.valueOf(System.currentTimeMillis());
        JSONObject phonePeBody = new JSONObject();
        phonePeBody.put("merchantId", prodMID);
        phonePeBody.put("merchantTransactionId", MERCHANT_TRANSACTION_ID);
        phonePeBody.put("amount", Double.toString(phonepeOrder.getAmount()).replace(".", "") + "0");
        phonePeBody.put("merchantUserId", Long.toString(phonepeOrder.getUserId()));
        phonePeBody.put("mobileNumber", "9616987031");

        phonePeBody.put("callbackUrl", AppConstants.Usage.APP_BASE_URL + AppConstants.Endpoints.REQUEST_MAPPING + AppConstants.Endpoints.PHONEPE_CALLBACK + "?merchantId=" + prodMID + "&merchantTransactionId=" + MERCHANT_TRANSACTION_ID + "&userEmail=" + phonepeOrder.getEmail() + "&userAmount=" + phonepeOrder.getAmount());
        JSONObject deviceContext = new JSONObject();
        deviceContext.put("deviceOS", "ANDROID");
        phonePeBody.put("deviceContext", deviceContext);
        JSONObject paymentInstrument = new JSONObject();
        paymentInstrument.put("type", "UPI_INTENT");

        paymentInstrument.put("targetApp", "net.one97.paytm");
        phonePeBody.put("paymentInstrument", paymentInstrument);

        String request = AppUtility.encodeToBase64(phonePeBody.toString());

        String sha256 = AppUtility.hashToSHA256(request + pgUrl + prodSaltKey);
        String xVerify = sha256 + "###1";
        PhonePeSdkResponse response = new PhonePeSdkResponse(request, xVerify);

        return ResponseEntity.ok(response);
    }
}
