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
@ContextConfiguration("classpath:META-INF/spring/mydocuments-aop-annotated-context.xml")
public class MyDocumentsAOPTest {

    private static final Logger logger = LoggerFactory.getLogger(MyDocumentsAOPTest.class);

    @Autowired
    private SearchEngine engineProxy;
    @Autowired
    private Type webType;

    @Test
    public void testUsingSpringAOP() {
        logger.debug("test using AOP techniques");

        List<Document> documents = engineProxy.findByType(webType);
        assertNotNull(documents);
        assertEquals(1, documents.size());
        assertEquals(webType.getName(),documents.get(0).getType().getName());
        assertEquals(webType.getDesc(),documents.get(0).getType().getDesc());
        assertEquals(webType.getExtension(),documents.get(0).getType().getExtension());

        documents = engineProxy.listAll();
        assertNotNull(documents);
        assertEquals(4, documents.size());

        try{
            engineProxy.findByLocation("some/path/");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Test
    public void testUsingSpringAOPCaching(){
        logger.debug("Testing buffering module...");

        List<Document> documents = engineProxy.findByType(webType);
        assertNotNull(documents);
        int count = documents.size();

        for(int i = 0; i < 2; i++){
            logger.debug("The object should already be in the buffer!");
            documents = engineProxy.findByType(webType);
            assertNotNull(documents);
            assertEquals(count, documents.size());
        }

    }

}