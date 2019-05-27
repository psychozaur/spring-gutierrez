package com.rybickim.spring.service;

import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;

import java.util.List;

public interface SearchEngine {

    public List<Document> findByType (Type documentType);
    public List<Document> listAll();
    public List<Document> findByLocation(String location);
    public Document findById(String id);
}
