package ketnoi;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class KetNoiCSDL {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static String DRIVER;

    // Đọc cấu hình từ src/main/resources/.env
    static {
        loadConfig();
    }

    private static void loadConfig() {

        Properties properties = new Properties();

        try (InputStream input = KetNoiCSDL.class
                .getClassLoader()
                .getResourceAsStream("dotenv.env")) {

            if (input == null) {
                throw new RuntimeException(
                        "Không tìm thấy file dotenv.env trong src/main/resources/"
                );
            }

            properties.load(input);

            URL = properties.getProperty("DB_URL");
            USERNAME = properties.getProperty("DB_USERNAME");
            PASSWORD = properties.getProperty("DB_PASSWORD");
            DRIVER = properties.getProperty("DB_DRIVER");

            if (URL == null || USERNAME == null || PASSWORD == null) {
                throw new RuntimeException(
                        "Thiếu thông tin cấu hình database trong file dotenv.env"
                );
            }

            if (DRIVER == null || DRIVER.trim().isEmpty()) {
                DRIVER = "com.mysql.cj.jdbc.Driver";
            }

            // Nạp MySQL Driver
            Class.forName(DRIVER);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Lỗi khi đọc file dotenv.env: " + e.getMessage(), e
            );

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                    "Không tìm thấy MySQL JDBC Driver", e
            );
        }
    }

    /**
     * Tạo kết nối đến database
     */
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USERNAME,
                PASSWORD
        );
    }

    /**
     * Đóng Connection
     */
    public static void closeConnection(Connection connection) {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Kiểm tra kết nối database
     */
    public static boolean testConnection() {

        try (Connection connection = getConnection()) {

            return connection != null && !connection.isClosed();

        } catch (SQLException e) {

            System.err.println(
                    "Không thể kết nối database: "
                            + e.getMessage()
            );

            return false;
        }
    }
}