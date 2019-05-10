package com.rybickim.spring.test;

import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;
import com.rybickim.spring.service.MySearchEngine;
import com.rybickim.spring.service.SearchEngine;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class MyDocumentsTest {

    private ClassPathXmlApplicationContext context;
    private SearchEngine engine;
    private Type webType;

    @Before
    public void setup(){
        context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-context.xml");
        engine = context.getBean(SearchEngine.class);
        webType = context.getBean("webType",Type.class);
    }

    @Test
    public void testWithSpringFindByType() {
        List<Document> documents = engine.findByType(webType);
        assertNotNull(documents);
        assertTrue(documents.size() == 1);
        assertEquals(webType.getName(),documents.get(0).getType().getName());
        assertEquals(webType.getDesc(),documents.get(0).getType().getDesc());
        assertEquals(webType.getExtension(),documents.get(0).getType().getExtension());
    }

    @Test
    public void testWithSpringListAll() {
        List<Document> documents = engine.listAll();
        assertNotNull(documents);
        assertTrue(documents.size() == 4);
    }

}