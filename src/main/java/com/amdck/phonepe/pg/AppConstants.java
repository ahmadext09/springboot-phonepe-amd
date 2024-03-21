package com.amdck.phonepe.pg;

public class AppConstants {

    interface Endpoints {
        String INITIATE_PHONEPE_TXN = "/initiate_payment";
        String INITIATE_PHONEPE_TXN_INTENT = "/initiate_payment_intent";

        String INITIATE_PAYTM_TXN = "/initiate_paytm_transaction";

        String PHONEPE_CALLBACK = "/phonepe-callback";

        String INITIATE_PHONEPE_TXN_SDK = "/initiate_payment_sdk";


        String REDIRECT_URL = "/ui-redirect-url";

        String REQUEST_MAPPING = "/api";

        String INITIATE_RAZORPAY_TXN = "/create_order";

        String WEBHOOK_PAID_CALLBACK = "/payment-verification";
    }

    interface Telegram {
//        String botToken = "5644977891:AAGYiDBwBeBNL3r5gSQ3JCmZQSULTm0DzhM";
//        long chatId = -4003148302L;

        String botToken = "5644977891:AAGYiDBwBeBNL3r5gSQ3JCmZQSULTm0DzhM";
        long chatId = -4173228500L;
    }

    interface Usage {
        String APP_BASE_URL = "https://every-terms-double.loca.lt";

        String INDIAN_CURRENCY_ISO_CODE = "INR";
    }

    interface STRING {
        String STATUS_SUCCESS = "Success, Points Added";
        String STATUS_PROCESS_FAILED = "Process failed, please contant our customer team";
        String STATUS_PAYMENT_FAILED = "Payment failed";
    }

    interface CODE {
        String PAYMENT_SUCCESS = "PAYMENT_SUCCESS";
    }

    interface KEYS {
        String razorpayKey = "rzp_live_0yoXZPeTpoL5hv";

        String razorpayTestKey = "rzp_test_BkEQB5Vvjqfd0c";
        String razorpaySecret = "jDcmFuoJNKUS28yiUdXlUToC";

        String razorpayTestSecret = "9iPTlKgJb7ZRqTjnAbmEomx0";
    }

    interface PAYTM_KEYS {
        String paytmMIdProd = "zomsNZ69103695781960";
        String paytmKeyProd = "cbeBYdhM5koQ1T1v";

        String testPaytmMId = "xrEnZj83504690328915";

        String testPaytmKey = "WG@xXSTMADb#EGEV";

        String paytmTransactionApiProdBaseUrl = "https://securegw.paytm.in/theia/api/v1/initiateTransaction?mid=%s&orderId=%s";
        String paytmTransactionApiStagingBaseUrl = "https://securegw-stage.paytm.in/theia/api/v1/initiateTransaction?mid=%s&orderId=%s";

        String PAYTM_STATUS_SUCCESS = "TXN_SUCCESS";
    }
}
