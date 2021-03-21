package com.hibernate.crud.dao;

import com.hibernate.beans.Employee;
import com.hibernate.conf.Factory;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestEmployeeDao {

    private final EmployeeDao employeeDao = new EmployeeDao();

    @Before
    public void addEmployees(){
        addEmployee();
    }

    @After
    public void deleteAllRecords(){
        employeeDao.deleteAll();
    }

    @Test
    public void testSaveEmployee(){
        addEmployee();
    }

    @Test
    public void testReadAll(){
        List<Employee> employees = employeeDao.readAll();

        Assert.assertEquals(employees.size(), 3);
    }

    @Test
    public void testReadByQuery(){
        List<Employee> employees = employeeDao.readByQuery("from Employee where name = 'sepehr'");

        Assert.assertEquals(employees.size(), 1);
    }

    @Test
    public void testReadUniqueById(){
        Employee employee = employeeDao.readUniqueById(5555);

        Assert.assertEquals(employee.getName(), "karan");
    }

    private void addEmployee(){
        Employee employee1 = new Employee("sepehr", "mollaei", "Company");
        Employee employee2 = new Employee("ahmad", "ahmadi", "ahhmad");
        Employee employee3 = new Employee("karan", "karani", "cooo");


        employeeDao.create(employee1);
        employeeDao.create(employee2);
        employeeDao.create(employee3);
    }
}
