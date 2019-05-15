package com.rybickim.spring.test;

import com.rybickim.spring.model.Type;
import com.rybickim.spring.service.SearchEngine;
import com.rybickim.spring.views.Menu;
import com.rybickim.spring.views.ResourceLoaderMenu;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

import static org.junit.Assert.assertNotNull;

public class MyDocumentsWithResourceLoaderInjectionTest {

    private static final Logger logger = LoggerFactory.getLogger(MyDocumentsWithResourceLoaderInjectionTest.class);

    private ClassPathXmlApplicationContext context;

    @Before
    public void setup(){
        context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-resourceloader-injection-context.xml");
    }

    @Test
    public void testMenu() {
        logger.debug("Calling menu as an injected resource loader: ");
        ResourceLoaderMenu menu = context.getBean(ResourceLoaderMenu.class);

        assertNotNull(menu);
        menu.printMenu("classpath:META-INF/data/menu.txt");
    }

}