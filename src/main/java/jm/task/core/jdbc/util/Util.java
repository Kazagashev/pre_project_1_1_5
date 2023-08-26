package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:/dbbase";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;
    private static SessionFactory sessionFactory;


    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL,NAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/dbbase");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "root");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

            settings.put(Environment.SHOW_SQL, "true");

            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            settings.put(Environment.HBM2DDL_AUTO, "");

            configuration.setProperties(settings);

            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = (SessionFactory) configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

}