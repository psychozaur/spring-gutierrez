package com.rybickim.spring.test;

import com.rybickim.spring.service.Login;
import com.rybickim.spring.views.ResourceLoaderMenu;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class MyDocumentsWithLoginTest {

    private static final Logger logger = LoggerFactory.getLogger(MyDocumentsWithLoginTest.class);

    private static final String EMAIL = "test@mydocuments.com";
    private static final String PASS = "test123";
    private static final String SUCCESS = "This user is authorized!";
    private static final String FAILURE = "WARNING! This user isn't authorized!";

    private ClassPathXmlApplicationContext context;

    @Before
    public void setup(){
        context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-login-context.xml");
    }

    @Test
    public void testLogin() {
        logger.debug("Testing login: ");
        Login login = context.getBean(Login.class);

        assertNotNull(login);
        if (login.isAuthorized(EMAIL, PASS)){
            System.out.println(SUCCESS);
        } else {
            System.out.println(FAILURE);
        }
    }

}