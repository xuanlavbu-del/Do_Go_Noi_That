package config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class FlywayInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            FlywayConfig.migrate();
        } catch (Exception e) {
            System.err.println("Flyway migration failed!");
            e.printStackTrace();
        }
    }
}