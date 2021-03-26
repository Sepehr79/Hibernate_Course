package com.hibernate.fetch.lazy;

import com.hibernate.conf.Factory;
import com.hibernate.fetch.Store;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StoreCustomerTest {

    private Session session;

    @Before
    public void openSession(){
        session = Factory.getSessionFactory().openSession();
    }

    @After
    public void closeSession(){
        session.close();
    }

    @Test
    public void testInsertStoreWithLazyCustomer(){
        try {
            session.beginTransaction();

            Store store = new Store("Store BBB");

            session.save(store);

            Customer customer1 = new Customer("sepehr1", "mollaei1", 21);
            Customer customer2 = new Customer("sepehr2", "mollaei2", 22);
            Customer customer3 = new Customer("sepehr3", "mollaei3", 23);
            Customer customer4 = new Customer("sepehr4", "mollaei4", 24);

            customer1.setStore(store);
            customer2.setStore(store);
            customer3.setStore(store);
            customer4.setStore(store);

            session.save(customer1);
            session.save(customer2);
            session.save(customer3);
            session.save(customer4);

            session.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Test
    public void testWrongGetProductsFromLazyStore() {
        try {
            session.beginTransaction();

            Store store = (Store) session.createQuery("from Store where storeName = 'Store BBB'").uniqueResult();

            // Customers are still unloaded so we cant use them after closing session
            session.getTransaction().commit();

            session.close();

            List<Customer> customers = store.getCustomers();

            for (Customer customer:customers)
                System.out.println(customer.getCustomerName());

            Assert.fail();

        } catch (LazyInitializationException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void testTrueGetProductsFromLazyStore1() {
        try {
            session.beginTransaction();

            Store store = (Store) session.createQuery("from Store where storeName = 'Store BBB'").uniqueResult();

            // Customers are still unloaded so we cant use them after closing session
            // so we can load them before close session

            System.out.println(store.getCustomers());

            session.getTransaction().commit();

            session.close();

            List<Customer> customers = store.getCustomers();

            for (Customer customer:customers)
                System.out.println(customer.getCustomerName());

        } catch (Exception exception) {
            exception.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Test
    public void testTrueGetProductsFromLazyStore2() {
        try {
            session.beginTransaction();

            Store store = (Store) session.createQuery("from Store where storeName = 'Store BBB'").uniqueResult();

            // Customers are still unloaded so we cant use them after closing session
            // also we can use HQL query to solve this problem
            List<Customer> customers = session.createQuery("from Customer").getResultList();

            session.getTransaction().commit();

            session.close();

            for (Customer customer:customers)
                System.out.println(customer.getCustomerName());

        } catch (Exception exception) {
            exception.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Test
    public void resetDataBase(){
        try {
            session.beginTransaction();

            session.createQuery("delete from Customer").executeUpdate();

            session.createQuery("delete from Store ").executeUpdate();

            session.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
        }
    }

}
