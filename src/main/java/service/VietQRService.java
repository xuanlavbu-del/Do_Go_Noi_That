package service;

import config.PaymentConfig;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class VietQRService {

    public String taoQR(double amount, String content) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Số tiền không hợp lệ"
            );
        }

        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nội dung thanh toán không hợp lệ"
            );
        }

        long amountLong = Math.round(amount);

        String encodedContent =
                URLEncoder.encode(
                        content.trim(),
                        StandardCharsets.UTF_8
                );

        String encodedAccountName =
                URLEncoder.encode(
                        PaymentConfig.ACCOUNT_NAME,
                        StandardCharsets.UTF_8
                );

        return PaymentConfig.VIETQR_URL
                + PaymentConfig.BANK_BIN
                + "-"
                + PaymentConfig.ACCOUNT_NUMBER
                + "-compact2.png"
                + "?amount="
                + amountLong
                + "&addInfo="
                + encodedContent
                + "&accountName="
                + encodedAccountName;
    }
}