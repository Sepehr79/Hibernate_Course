package com.hibernate.advancemapping.manytomany;

import com.hibernate.conf.Factory;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PersonWorkTest {

    @After
    public void resetDataBase(){
        Session session = Factory.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            session.createQuery("delete from Person").executeUpdate();
            session.createQuery("delete from Work").executeUpdate();

            session.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    @Test
    public void addPersonAndWork(){
        Session session = Factory.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            Person person = new Person("sepehr", "mollaei", 20);

            Work work1 = new Work("Programmer", 20000);
            Work work2 = new Work("Engineer", 15000);
            Work work3 = new Work("Database manager", 18000);

            session.save(work1);
            session.save(work2);
            session.save(work3);

            person.addWork(work1);
            person.addWork(work2);
            person.addWork(work3);

            session.save(person);

            // Assertions
            Person insertedPerson = (Person) session.createQuery("from Person where name = 'sepehr'").uniqueResult();

            Assert.assertNotNull(insertedPerson);

            Assert.assertEquals(insertedPerson.getWorkList().size(), 3);

            session.delete(insertedPerson);

            Person deletedPerson =  (Person) session.createQuery("from Person where name = 'sepehr'").uniqueResult();

            Assert.assertNull(deletedPerson);

            // Works should not delete!
            List<Work> workList = session.createQuery("from Work").getResultList();

            Assert.assertEquals(workList.size(), 3);

            session.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }



}
