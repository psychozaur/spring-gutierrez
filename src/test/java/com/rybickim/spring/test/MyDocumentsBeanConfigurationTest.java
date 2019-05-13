package com.rybickim.spring.test;

import com.rybickim.spring.config.MyDocumentsContext;
import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;
import com.rybickim.spring.service.SearchEngine;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class MyDocumentsBeanConfigurationTest {

    private ApplicationContext context;
    private SearchEngine engine;
    private Type webType;

    @Before
    public void setup(){
        context = new AnnotationConfigApplicationContext(MyDocumentsContext.class);
    }

    @Test
    public void testAll() {
        engine = context.getBean(SearchEngine.class);
        webType = context.getBean("webType",Type.class);

        List<Document> documents = engine.findByType(webType);
        assertNotNull(documents);
        assertEquals(1, documents.size());
        assertEquals(webType.getName(),documents.get(0).getType().getName());
        assertEquals(webType.getDesc(),documents.get(0).getType().getDesc());
        assertEquals(webType.getExtension(),documents.get(0).getType().getExtension());

        engine = context.getBean(SearchEngine.class);

        documents = engine.listAll();
        assertNotNull(documents);
        assertEquals(4, documents.size());
    }

}
