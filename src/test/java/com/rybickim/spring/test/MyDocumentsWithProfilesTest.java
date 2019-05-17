package com.rybickim.spring.test;

import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;
import com.rybickim.spring.service.SearchEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-profiles-context.xml")
@ActiveProfiles("qa")
public class MyDocumentsWithProfilesTest {

    private static final Logger logger = LoggerFactory.getLogger(MyDocumentsWithProfilesTest.class);

    @Autowired
    private SearchEngine engine;
    @Autowired
    private Type webType;

    @Test
    public void testUsingSpringTestWithProfiles() {
        try {
            logger.debug("using SpringTest tools");

            List<Document> documents = engine.findByType(webType);
            assertNotNull(documents);
            assertEquals(1, documents.size());
            assertEquals(webType.getName(),documents.get(0).getType().getName());
            assertEquals(webType.getDesc(),documents.get(0).getType().getDesc());
            assertEquals(webType.getExtension(),documents.get(0).getType().getExtension());

            documents = engine.listAll();
            assertNotNull(documents);
            assertEquals(4, documents.size());
        } catch (Exception e){
            logger.error(e.getMessage());
        }

    }

}