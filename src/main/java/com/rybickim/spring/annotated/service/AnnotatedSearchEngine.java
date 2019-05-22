package com.rybickim.spring.annotated.service;

import com.rybickim.spring.data.DocumentDAO;
import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;
import com.rybickim.spring.service.SearchEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("engine")
@Scope("prototype")
public class AnnotatedSearchEngine implements SearchEngine {

    private static final Logger logger = LoggerFactory.getLogger(AnnotatedSearchEngine.class);

    @Autowired
    private DocumentDAO documentDAO;

    public AnnotatedSearchEngine(){
        if(logger.isDebugEnabled())
            logger.debug("AnnotatedSearchEngine() was called and created: " + this);
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
        return documentDAO.getAll();
    }

    @Override
    public List<Document> findByLocation(String location) {
        return null;
    }
}
