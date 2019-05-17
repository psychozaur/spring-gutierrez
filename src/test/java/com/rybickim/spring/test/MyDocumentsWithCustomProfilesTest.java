package com.rybickim.spring.test;

import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;
import com.rybickim.spring.service.SearchEngine;
import com.rybickim.spring.test.profile.CustomProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-custom-profiles-context.xml")
@ProfileValueSourceConfiguration(CustomProfile.class)
public class MyDocumentsWithCustomProfilesTest {

    private static final Logger logger = LoggerFactory.getLogger(MyDocumentsWithCustomProfilesTest.class);

    @Autowired
    private SearchEngine engine;
    @Autowired
    private Type webType;

    @IfProfileValue(name = "environment", values = "dev")
    @Test
    public void testUsingSpringTestWithProfilesX() {
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

    @IfProfileValue(name = "os.name", values = "Unix")
    @Test
    public void testUsingSpringTestWithProfilesY() {
        try {
            logger.debug("using SpringTest tools on Unix system");

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