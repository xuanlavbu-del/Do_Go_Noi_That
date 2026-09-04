package config;

import io.github.cdimascio.dotenv.Dotenv;
import org.flywaydb.core.Flyway;

public class FlywayConfig {

    private static final Dotenv dotenv = Dotenv.configure()
            .filename("dotenv.env")
            .ignoreIfMissing()
            .load();

    private FlywayConfig() {
        // Không cho phép tạo object
    }

    public static void migrate() {

        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        if (url == null || url.isBlank()) {
            throw new RuntimeException("Thiếu DB_URL trong file .env");
        }

        if (username == null || username.isBlank()) {
            throw new RuntimeException("Thiếu DB_USERNAME trong file .env");
        }

        if (password == null) {
            throw new RuntimeException("Thiếu DB_PASSWORD trong file .env");
        }

        System.out.println("========== FLYWAY ==========");
        System.out.println("Database: " + url);
        System.out.println("Username: " + username);

        Flyway flyway = Flyway.configure()
                .dataSource(url, username, password)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .baselineVersion("1")
                .load();

        flyway.migrate();

        System.out.println("Flyway migration completed!");
        System.out.println("============================");
    }
}