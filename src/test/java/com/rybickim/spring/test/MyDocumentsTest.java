package com.rybickim.spring.test;

import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;
import com.rybickim.spring.service.SearchEngine;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MyDocumentsTest {

    private static final Logger logger = LoggerFactory.getLogger(MyDocumentsTest.class);

    private ClassPathXmlApplicationContext context;
    private SearchEngine engine;
    private Type webType;

    @Before
    public void setup(){
        context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-context.xml");
    }

    @Test
    public void testMenu() {
        logger.debug("Reading resource file: menu.txt");
        Resource resource = context.getResource("classpath:META-INF/data/menu.txt");
        try {
            InputStream stream = resource.getInputStream();
            Scanner scanner = new Scanner(stream);
            while (scanner.hasNext()){
                System.out.println(scanner.nextLine());
            }
            scanner.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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