package com.hibernate.advancemapping.onetomany;

import com.hibernate.conf.Factory;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UniDirectionalTests {

    @Before
    public void resetDataBase(){
        Session session = Factory.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            session.createQuery("delete from Dependency ").executeUpdate();
            session.createQuery("delete from Project ").executeUpdate();

            session.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }

    }

    @Test
    public void insertNewProject(){
        Session session = Factory.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            Project project = new Project(1, "Android project");

            Dependency dependency1 = new Dependency("sqlite driver");
            Dependency dependency2 = new Dependency("sqlite driver");
            Dependency dependency3 = new Dependency("sqlite driver");

            project.addDependency(dependency1);
            project.addDependency(dependency2);
            project.addDependency(dependency3);

            session.save(project);

            session.save(dependency1);
            session.save(dependency2);
            session.save(dependency3);

            Project savedProject = session.get(Project.class, 1);

            Assert.assertEquals(savedProject.getDependencies().size(), 3);

            System.out.println(savedProject);

            System.out.println(savedProject.getDependencies());

            session.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

}
