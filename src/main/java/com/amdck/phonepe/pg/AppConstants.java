package com.amdck.phonepe.pg;

public class AppConstants {

    interface Endpoints {
        String INITIATE_PHONEPE_TXN = "/initiate_payment";
        String INITIATE_PHONEPE_TXN_INTENT = "/initiate_payment_intent";

        String PHONEPE_CALLBACK = "/phonepe-callback";

        String INITIATE_PHONEPE_TXN_SDK = "/initiate_payment_sdk";

        String REDIRECT_URL = "/ui-redirect-url";

        String REQUEST_MAPPING = "/api";
    }

    interface Telegram {
        String botToken = "5644977891:AAGYiDBwBeBNL3r5gSQ3JCmZQSULTm0DzhM";
        long chatId = -4003148302L;
    }
    interface Usage {
        String APP_BASE_URL = "https://happy-pans-cheat.loca.lt";
    }

    interface STRING {
        String STATUS_SUCCESS = "Success, Points Added";
        String STATUS_PROCESS_FAILED = "Process failed, please contant our customer team";
        String STATUS_PAYMENT_FAILED = "Payment failed";
    }

    interface CODE{
        String PAYMENT_SUCCESS = "PAYMENT_SUCCESS";
    }
}
