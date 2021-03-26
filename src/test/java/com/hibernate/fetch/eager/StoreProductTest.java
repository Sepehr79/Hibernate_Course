package com.hibernate.fetch.eager;

import com.hibernate.conf.Factory;
import com.hibernate.fetch.Store;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StoreProductTest {

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
    public void testInsertStoreWithEagerProducts(){

        try {
            session.beginTransaction();

            Store store = new Store("Store NNN");

            session.save(store);

            Product product1 = new Product("p1");
            Product product2 = new Product("p2");
            Product product3 = new Product("p3");
            Product product4 = new Product("p4");

            product1.setStore(store);
            product2.setStore(store);
            product3.setStore(store);
            product4.setStore(store);

            session.save(product1);
            session.save(product2);
            session.save(product3);
            session.save(product4);

            session.getTransaction().commit();
        }catch (Exception exception) {
            exception.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Test
    public void testGetProductsFromEagerStore(){

        try {
            session.beginTransaction();

            Store store = (Store) session.createQuery("from Store where storeName = 'Store NNN'").uniqueResult();

            // All products are loaded so we can use them after close session
            session.getTransaction().commit();

            session.close();

            List<Product> productList = store.getProducts();

            for (Product product: productList)
                System.out.println(product.getProductName());


        }catch (Exception exception) {
            exception.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Test
    public void resetDataBase(){
        try {
            session.beginTransaction();

            session.createQuery("delete from Product").executeUpdate();

            session.createQuery("delete from Store ").executeUpdate();

            session.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
        }
    }


}
