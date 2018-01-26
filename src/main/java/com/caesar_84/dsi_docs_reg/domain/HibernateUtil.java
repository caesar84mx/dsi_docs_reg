package com.caesar_84.dsi_docs_reg.domain;

import com.caesar_84.dsi_docs_reg.domain.model.HandedDocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration config = getConfig();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

            config.setSessionFactoryObserver(new SessionFactoryObserver() {
                private static final long serialVersionUID = 1L;

                @Override
                public void sessionFactoryCreated(SessionFactory factory) {
                }

                @Override
                public void sessionFactoryClosed(SessionFactory factory) {
                    StandardServiceRegistryBuilder.destroy(serviceRegistry);
                }
            });

            sessionFactory = config.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

    private static Configuration getConfig() {
        Configuration config = new Configuration();
        config.addAnnotatedClass(HandedDocument.class);
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url","jdbc:h2:~/dsi");
        config.setProperty("hibernate.connection.username", "");
        config.setProperty("hibernate.connection.password", "");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "update");
        config.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");
        config.setProperty("hibernate.current_session_context_class", "thread");

        return config;
    }
}
