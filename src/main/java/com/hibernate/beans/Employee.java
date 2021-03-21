package com.hibernate.beans;

import javax.persistence.*;

@Entity
@Table(name = "Emp")
public class Employee {
    @Id
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private String companyName;

    public Employee() {
    }

    public Employee(String name, String lastName, String companyName) {
        this.name = name;
        this.lastName = lastName;
        this.companyName = companyName;
    }

    public Employee( String name, String lastName, String companyName, int id) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
