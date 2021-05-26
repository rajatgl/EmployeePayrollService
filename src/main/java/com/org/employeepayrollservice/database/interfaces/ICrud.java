package com.org.employeepayrollservice.database.interfaces;

public interface ICrud<T>{
    void Create(T obj);
    T Retrieve(String key);
    void Update(String key);
    void Delete(String key);
}
