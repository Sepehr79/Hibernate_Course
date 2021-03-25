package com.hibernate.advancemapping.onetomany;

import com.hibernate.conf.Factory;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

public class StudentCourseTests {
    private Session session;

    @Test
    public void saveStudentAndCourse(){
        deleteStudentAndCourse();
        session = Factory.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Student student = new Student("sepehr", "mollaei", 20, Gender.MALE);

            Course math = new Course("AL");
            Course chemistry = new Course("AR");
            Course computerSystem = new Course("PH");

            student.addCourse(math);
            student.addCourse(chemistry);
            student.addCourse(computerSystem);

            session.save(student);

            session.save(math);
            session.save(chemistry);
            session.save(computerSystem);

            Student sepehr = (Student) session.createQuery("from Student where name = 'sepehr'").uniqueResult();

            Assert.assertEquals(sepehr.getName(), "sepehr");

            session.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
            session.close();
        }
    }

    @Test
    public void deleteCourse(){
        saveStudentAndCourse();
        session = Factory.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Course course = (Course) session.createQuery("from Course where course_name = 'AR'").uniqueResult();

            session.remove(course);

            session.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
            session.close();
        }
    }

    @Test
    public void getStudentWithCoursesList(){
        deleteCourse();
        session = Factory.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Student student = (Student) session.createQuery("from Student where name = 'sepehr'").uniqueResult();

            Assert.assertEquals(student.getCourseList().size(), 2);

            for (Course course: student.getCourseList()){
                System.out.println(course.getCourseName());
            }

            session.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
            session.close();
        }
    }

    @Test
    public void deleteStudentAndCourse(){
        session = Factory.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            session.createQuery("delete from Course").executeUpdate();
            session.createQuery("delete from Student").executeUpdate();

            session.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
            session.close();
        }
    }
}
