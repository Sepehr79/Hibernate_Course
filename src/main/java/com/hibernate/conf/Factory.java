package com.hibernate.conf;

import com.hibernate.beans.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Factory {

    private Factory(){

    }

    public static SessionFactory getSessionFactory(){
        return new Configuration().configure("config/hibernate.cfg.xml").
                addAnnotatedClass(Employee.class).buildSessionFactory();
    }
}
