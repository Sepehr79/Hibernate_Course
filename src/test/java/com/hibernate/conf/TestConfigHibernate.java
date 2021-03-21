package com.hibernate.conf;

import com.hibernate.beans.Employee;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class TestConfigHibernate {

    private Session session;

    @Before
    public void openSession(){
        session = Factory.getSessionFactory().openSession();
    }

    @Test
    public void testConfigurationHibernate(){
        session.beginTransaction();

        List<Employee> employees = session.createQuery("from Employee").getResultList();

        System.out.println(employees.size());

        session.getTransaction().commit();
    }

    @After
    public void closeSession(){
        session.close();
    }

}
