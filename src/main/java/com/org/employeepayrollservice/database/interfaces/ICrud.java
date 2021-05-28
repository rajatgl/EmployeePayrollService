package com.org.employeepayrollservice.database.interfaces;

import java.util.List;

public interface ICrud<T>{
    void create(T obj);
    List<T> retrieve(int key);
    void update(int key, T value);
    void delete(int key);
}
