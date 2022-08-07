package com.danamon.challengehibermart.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    private static SessionFactory sessionFactory ;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure("Hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }


}
