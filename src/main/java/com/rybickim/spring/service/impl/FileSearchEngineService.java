package com.rybickim.spring.service.impl;

import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;
import com.rybickim.spring.service.SearchEngine;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("qa")
public class FileSearchEngineService implements SearchEngine {

    public List<Document> findByType(Type documentType) {
        throw new UnsupportedOperationException("QA Environment. Not yet implemented operation.");
    }

    public List<Document> listAll() {
        throw new UnsupportedOperationException("QA Environment. Not yet implemented operation.");
    }

    @Override
    public List<Document> findByLocation(String location) {
        return null;
    }

    @Override
    public Document findById(String id) {
        return null;
    }
}