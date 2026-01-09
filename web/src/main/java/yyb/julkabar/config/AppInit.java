package yyb.julkabar.config;

public class AppInit implements jakarta.servlet.ServletContextListener {
    @Override public void contextInitialized(jakarta.servlet.ServletContextEvent e) {
        Beans.init();
        System.out.print("\nApp started at: http://localhost:8080/books\n");
    }
}
