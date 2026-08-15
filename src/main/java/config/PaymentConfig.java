package config;

public class PaymentConfig {

    /*
     * Mã ngân hàng dùng để tạo VietQR.
     *
     * Bạn cần thay giá trị này bằng mã BIN
     * của tài khoản NHCSXH thực tế của cửa hàng.
     */
    public static final String BANK_BIN =
            "970428";


    /*
     * Số tài khoản NHCSXH nhận tiền.
     *
     * THAY bằng số tài khoản thật.
     */
    public static final String ACCOUNT_NUMBER =
            "1234567890";


    /*
     * Tên chủ tài khoản.
     */
    public static final String ACCOUNT_NAME =
            "TEN CUA HANG";


    /*
     * URL tạo ảnh QR VietQR.
     */
    public static final String VIETQR_URL =
            "https://img.vietqr.io/image/";


    private PaymentConfig() {
    }
}
