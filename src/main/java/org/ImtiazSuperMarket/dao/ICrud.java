package org.ImtiazSuperMarket.dao;

import org.ImtiazSuperMarket.Domain.User;

import java.util.List;


    public interface ICrud<T> {
        void insert(T obj);

        List<T> getAll();
        T getById(Integer id);
        void update(T obj,long id);

        void delteById(Integer id);
    }

