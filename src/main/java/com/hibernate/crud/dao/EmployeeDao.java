package com.hibernate.crud.dao;

import com.hibernate.beans.Employee;
import com.hibernate.conf.Factory;
import org.hibernate.Session;

import java.util.List;

public class EmployeeDao implements DAO<Employee> {

    @Override
    public void create(Employee employee) {
        Session session = Factory.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            session.save(employee);

            session.getTransaction().commit();
        }catch (Exception exception){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public List<Employee> readAll() {
        Session session = Factory.getSessionFactory().openSession();

        return session.createQuery("from Employee").getResultList();
    }

    @Override
    public List<Employee> readByQuery(String query) {
        Session session = Factory.getSessionFactory().openSession();

        return session.createQuery(query).getResultList();
    }

    @Override // this method gets unique result
    public Employee readUniqueById(int id) {
        Session session = Factory.getSessionFactory().openSession();

        return (Employee) session.createQuery("from Employee where id = :id").
                setParameter("id", id).
                uniqueResult();

    }

    @Override
    public void updateByQuery(String query) {

    }

    @Override
    public void updateBySampleObject(Employee object) {

    }

    @Override
    public void deleteBySampleObject(Employee object) {

    }

    @Override
    public void deleteByQuery(String query) {

    }

    @Override
    public void deleteAll() {
        Session session = Factory.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            session.createQuery("delete from Employee").executeUpdate();

            session.getTransaction().commit();
        }catch (Exception exception){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
}
