package com.rybickim.spring.service;

import com.rybickim.spring.data.DocumentDAO;
import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchEngineService implements SearchEngine {

    private DocumentDAO documentDAO;

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
        return Arrays.asList(documentDAO.getAll());
    }
}
