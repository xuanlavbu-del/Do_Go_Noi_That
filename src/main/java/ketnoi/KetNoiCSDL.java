package ketnoi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KetNoiCSDL {

    // Thông tin kết nối MySQL
    private static final String URL =
            "jdbc:mysql://127.0.0.1:3306/DoGoNoiThat?useSSL=false&serverTimezone=Asia/Ho_Chi_Minh&characterEncoding=UTF-8";

    private static final String USER = "root";

    private static final String PASSWORD = "1008";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Đã nạp Driver MySQL thành công.");
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy Driver MySQL.");
            e.printStackTrace();
        }
    }

    /**
     * Lấy kết nối đến CSDL
     */
    public static Connection getConnection() {

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {

            System.out.println("Không thể kết nối CSDL.");

            e.printStackTrace();

        }

        return connection;
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
     * Kiểm tra kết nối
     */
    public static void main(String[] args) {

        Connection connection = getConnection();

        if (connection != null) {

            System.out.println("Kết nối CSDL thành công!");

            closeConnection(connection);

        } else {

            System.out.println("Kết nối thất bại!");

        }

    }

}