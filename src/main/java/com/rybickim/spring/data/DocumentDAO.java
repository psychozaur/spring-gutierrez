package com.rybickim.spring.data;

import com.rybickim.spring.model.Document;

import java.util.List;

public interface DocumentDAO {
    public List<Document> getAll();
}
