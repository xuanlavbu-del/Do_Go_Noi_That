import org.mindrot.jbcrypt.BCrypt;

public class Main {
    public static void main(String[] args) {
        String matKhau = "123456";
        String hash = BCrypt.hashpw(matKhau, BCrypt.gensalt());
        System.out.println("Mat khau: " + matKhau);
        System.out.println("BCrypt: " + hash);
    }
}