package com.rybickim.spring.test;

import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;
import com.rybickim.spring.service.SearchEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-jdbc-embedded-context.xml")
public class MyDocumentsJDBCEmbeddedAnnotatedTest {

    private static final Logger logger = LoggerFactory.getLogger(MyDocumentsJDBCEmbeddedAnnotatedTest.class);

    @Autowired
    private SearchEngine engine;

    private Type webType = new Type("WEB",".url");

    @Test
    public void testUsingSpringJDBCEmbedded() {
        logger.debug("using embedded JDBC mechanisms from Spring");

        List<Document> documents = engine.findByType(webType);
        assertNotNull(documents);
        assertEquals(1, documents.size());

        assertEquals(webType.getName(),documents.get(0).getType().getName());
        assertEquals(webType.getExtension(),documents.get(0).getType().getExtension());

        documents = engine.listAll();
        assertNotNull(documents);
        assertEquals(4, documents.size());

        logger.debug("Web document found: " + documents.get(0));
    }

}