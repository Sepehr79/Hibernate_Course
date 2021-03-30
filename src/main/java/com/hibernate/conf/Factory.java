package com.hibernate.conf;

import com.hibernate.advancemapping.manytomany.Person;
import com.hibernate.advancemapping.manytomany.Work;
import com.hibernate.advancemapping.manytoone.Course;
import com.hibernate.advancemapping.onetomany.Dependency;
import com.hibernate.advancemapping.onetomany.Project;
import com.hibernate.advancemapping.onetomany.Student;
import com.hibernate.advancemapping.onetoone.Instructor;
import com.hibernate.advancemapping.onetoone.InstructorDetail;
import com.hibernate.beans.Employee;
import com.hibernate.fetch.Store;
import com.hibernate.fetch.eager.Product;
import com.hibernate.fetch.lazy.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Factory {

    private Factory(){

    }

    public static SessionFactory getSessionFactory(){
        return new Configuration().configure("config/hibernate.cfg.xml").
                addAnnotatedClass(Employee.class).
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                addAnnotatedClass(Student.class).
                addAnnotatedClass(Course.class).
                addAnnotatedClass(Store.class).
                addAnnotatedClass(Product.class).
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Project.class).
                addAnnotatedClass(Dependency.class).
                addAnnotatedClass(Person.class).
                addAnnotatedClass(Work.class).
                buildSessionFactory();
    }
}
