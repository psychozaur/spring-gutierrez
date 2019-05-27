package com.rybickim.spring.service.impl;

import com.rybickim.spring.data.DocumentDAO;
import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rybickim.spring.service.SearchEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchEngineService implements SearchEngine {

    private DocumentDAO documentDAO;

    public SearchEngineService() {
    }

    public DocumentDAO getDocumentDAO() {
        return documentDAO;
    }

    public void setDocumentDAO(DocumentDAO documentDAO){

        this.documentDAO = documentDAO;
    }

    @Override
    public List<Document> findByType(Type documentType) {
        List<Document> result = new ArrayList<>();
        for(Document document : listAll()){
            if (document.getType().getName()
                    .equals(documentType.getName()))
                result.add(document);
        }

        return result;
    }

    @Override
    public List<Document> listAll() {
        List<Document> result = documentDAO.getAll();
        return result;
    }

    @Override
    public List<Document> findByLocation(String location) {
        throw new UnsupportedOperationException("Method findByLocation() not yet implemented");
    }

    @Override
    public Document findById(String id) {
        return documentDAO.findById(id);
    }
}
