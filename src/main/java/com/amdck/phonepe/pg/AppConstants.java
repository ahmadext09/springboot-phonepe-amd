package com.amdck.phonepe.pg;

public class AppConstants {

    interface Endpoints {
        String INITIATE_PHONEPE_TXN = "/initiate_payment";

        String PHONEPE_CALLBACK = "/phonepe-callback";
    }

    interface Telegram {
        String botToken = "5644977891:AAGYiDBwBeBNL3r5gSQ3JCmZQSULTm0DzhM";
        long chatId = -4003148302L;
    }
}
