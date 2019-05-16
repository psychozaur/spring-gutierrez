package com.rybickim.spring.data.impl;

import com.rybickim.spring.data.DocumentDAO;
import com.rybickim.spring.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DocumentRepository implements DocumentDAO {

    private static final Logger logger = LoggerFactory.getLogger(DocumentRepository.class);

    private List<Document> documents = new ArrayList<>();

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public Document[] getAll() {
        if(logger.isDebugEnabled()) logger.debug("starting getAll() method from DocumentRepository");

        Document[] result = documents.toArray(new Document[documents.size()]);

        if(logger.isDebugEnabled()) logger.debug("ending getAll() method from DocumentRepository: " + result);


        return result;

    }
}
