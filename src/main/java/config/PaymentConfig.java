package config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PaymentConfig {

    /*
     * Đọc file .env từ:
     *
     * src/main/resources/.env
     *
     * Khi build, file sẽ được đưa vào classpath.
     */
    private static final Properties properties = loadEnv();

    /*
     * In thông tin cấu hình để kiểm tra
     */
    static {
        System.out.println("====================================");
        System.out.println("PAYMENT CONFIG");

        System.out.println(
                "PAYMENT_BANK_NAME = "
                        + properties.getProperty("PAYMENT_BANK_NAME")
        );

        System.out.println(
                "PAYMENT_BANK_SHORT_NAME = "
                        + properties.getProperty("PAYMENT_BANK_SHORT_NAME")
        );

        System.out.println(
                "PAYMENT_BANK_BIN = "
                        + properties.getProperty("PAYMENT_BANK_BIN")
        );

        System.out.println(
                "PAYMENT_ACCOUNT_NUMBER = "
                        + properties.getProperty("PAYMENT_ACCOUNT_NUMBER")
        );

        System.out.println(
                "PAYMENT_ACCOUNT_NAME = "
                        + properties.getProperty("PAYMENT_ACCOUNT_NAME")
        );

        System.out.println(
                "PAYMENT_VIETQR_URL = "
                        + properties.getProperty("PAYMENT_VIETQR_URL")
        );

        System.out.println("====================================");
    }

    /*
     * Tên ngân hàng
     */
    public static final String BANK_NAME =
            getRequired("PAYMENT_BANK_NAME");

    /*
     * Tên viết tắt
     */
    public static final String BANK_SHORT_NAME =
            getRequired("PAYMENT_BANK_SHORT_NAME");

    /*
     * Mã BIN
     */
    public static final String BANK_BIN =
            getRequired("PAYMENT_BANK_BIN");

    /*
     * Số tài khoản nhận tiền
     */
    public static final String ACCOUNT_NUMBER =
            getRequired("PAYMENT_ACCOUNT_NUMBER");

    /*
     * Tên chủ tài khoản
     */
    public static final String ACCOUNT_NAME =
            getRequired("PAYMENT_ACCOUNT_NAME");

    /*
     * URL VietQR
     */
    public static final String VIETQR_URL =
            getRequired("PAYMENT_VIETQR_URL");


    /*
     * Load file .env từ resources
     */
    private static Properties loadEnv() {

        Properties props = new Properties();

        try (
                InputStream inputStream =
                        PaymentConfig.class
                                .getClassLoader()
                                .getResourceAsStream("dotenv.env")
        ) {

            if (inputStream == null) {

                throw new IllegalStateException(
                        "Không tìm thấy file .env trong src/main/resources"
                );
            }

            try (
                    InputStreamReader reader =
                            new InputStreamReader(
                                    inputStream,
                                    StandardCharsets.UTF_8
                            )
            ) {

                props.load(reader);
            }

        } catch (IOException e) {

            throw new IllegalStateException(
                    "Không thể đọc file .env",
                    e
            );
        }

        return props;
    }


    /*
     * Lấy biến bắt buộc
     */
    private static String getRequired(String key) {

        String value =
                properties.getProperty(key);

        if (value == null ||
                value.trim().isEmpty()) {

            throw new IllegalStateException(
                    "Thiếu biến môi trường: "
                            + key
            );
        }

        return value.trim();
    }


    /*
     * Không cho phép tạo đối tượng
     */
    private PaymentConfig() {
    }
}