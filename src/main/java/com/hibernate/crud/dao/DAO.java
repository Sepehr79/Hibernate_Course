package com.hibernate.crud.dao;

import com.hibernate.beans.Employee;

import java.util.List;

public interface DAO<DT> {

    public void create(DT object);

    public List<DT> readAll();

    public List<DT> readByQuery(String query);

    public Employee readUniqueById(int id);

    public void updateByQuery(String query);

    public void updateById(DT newObject, int id);

    public void deleteById(int id);

    public void deleteByQuery(String query);

    public void deleteAll();

}
