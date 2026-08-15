package service;

import config.PaymentConfig;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class VietQRService {

    /**
     * Tạo URL ảnh QR thanh toán.
     *
     * @param amount số tiền
     * @param content nội dung chuyển khoản
     * @return URL ảnh QR
     */
    public String taoQR(
            double amount,
            String content) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Số tiền không hợp lệ"
            );
        }


        if (content == null ||
                content.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Nội dung thanh toán không hợp lệ"
            );
        }


        /*
         * Làm tròn số tiền về số nguyên.
         */
        long amountLong =
                Math.round(amount);


        /*
         * Encode nội dung để đưa vào URL.
         */
        String encodedContent =
                URLEncoder.encode(
                        content,
                        StandardCharsets.UTF_8
                );


        /*
         * Format:
         *
         * https://img.vietqr.io/image/
         * BANK-ACCOUNT-compact2.png
         *
         * ?amount=...
         * &addInfo=...
         * &accountName=...
         */

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

                + URLEncoder.encode(
                PaymentConfig.ACCOUNT_NAME,
                StandardCharsets.UTF_8
        );
    }
}