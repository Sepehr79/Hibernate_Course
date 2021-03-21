package com.hibernate.crud.dao;

import com.hibernate.beans.Employee;
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

        Assert.assertEquals(employees.size(), 3);
    }

    @Test
    public void testReadUniqueById(){
        Employee employee = employeeDao.readUniqueById(1);

        Assert.assertNotNull(employee);

        System.out.println(employee);
    }

    @Test
    public void testUpdateByQuery(){
        employeeDao.updateByQuery("update Employee set name = 'kaka' where id = 1");

        Employee employee = employeeDao.readUniqueById(1);

        Assert.assertEquals(employee.getName(), "kaka");
    }

    @Test
    public void testUpdateById(){
        Employee employee = new Employee("ahmad", "ahmadi", "ahmadian");

        employeeDao.updateById(employee, 1);

        Employee editedEmployee = employeeDao.readUniqueById(1);

        Assert.assertEquals(editedEmployee.getName(), "ahmad");

        Assert.assertEquals(editedEmployee.getLastName(), "ahmadi");
    }

    @Test
    public void testDeleteById(){
        employeeDao.deleteById(1);

        Employee employee = employeeDao.readUniqueById(1);

        Assert.assertNull(employee);
    }

    @Test
    public void testDeleteByQuery(){
        employeeDao.deleteByQuery("delete Employee where name = 'sepehr'");

        List<Employee> employees = employeeDao.readAll();

        Assert.assertEquals(employees.size(), 0);
    }

    @Test
    public void testDeleteAll(){
        employeeDao.deleteAll();

        List<Employee> employees = employeeDao.readAll();

        Assert.assertEquals(employees.size(), 0);
    }

    private void addEmployee(){
        Employee employee1 = new Employee("sepehr", "mollaei", "Company", 1);
        Employee employee2 = new Employee("sepehr", "ahmadi", "ahhmad", 2);
        Employee employee3 = new Employee("sepehr", "karani", "cooo", 3);


        employeeDao.create(employee1);
        employeeDao.create(employee2);
        employeeDao.create(employee3);
    }

}
