package com.rybickim.spring.test;

import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;
import com.rybickim.spring.service.SearchEngine;
import com.rybickim.spring.views.Menu;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MyDocumentsWithResourceInjectionTest {

    private static final Logger logger = LoggerFactory.getLogger(MyDocumentsWithResourceInjectionTest.class);

    private ClassPathXmlApplicationContext context;
    private SearchEngine engine;
    private Type webType;

    @Before
    public void setup(){
        context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-resource-injection-context.xml");
    }

    @Test
    public void testMenu() {
        logger.debug("Calling menu as a injected resource: ");
        Menu menu = context.getBean(Menu.class);

        assertNotNull(menu);
        menu.printMenu();
    }

}