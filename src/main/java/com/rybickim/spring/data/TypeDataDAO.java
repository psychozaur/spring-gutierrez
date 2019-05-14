package com.rybickim.spring.data;

import com.rybickim.spring.model.Type;

public interface TypeDataDAO {
    public Type[] getAll();
    public Type findById(String id);
}
