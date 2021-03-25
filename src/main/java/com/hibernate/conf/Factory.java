package com.hibernate.conf;

import com.hibernate.advancemapping.onetomany.Course;
import com.hibernate.advancemapping.onetomany.Student;
import com.hibernate.advancemapping.onetoone.Instructor;
import com.hibernate.advancemapping.onetoone.InstructorDetail;
import com.hibernate.beans.Employee;
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
                buildSessionFactory();
    }
}
