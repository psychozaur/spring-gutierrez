package com.rybickim.spring.test;

import com.rybickim.spring.amqp.RabbitMQProducer;
import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;
import com.rybickim.spring.service.SearchEngine;
import com.rybickim.spring.utils.XmlUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-context.xml")
public class MyDocumentsTest {

    private static final Logger log = LoggerFactory.getLogger(MyDocumentsTest.class);
    //Based on the META-INF/data/jms.txt - only one record
    private static final int MAX_ALL_DOCS = 5;
    private static final int MAX_WEB_DOCS = 2;
    private static final String DOCUMENT_ID = "df569fa4-a513-4252-9810-818cade184ca";
    @Autowired
    private SearchEngine engine;

    @Test
    @Ignore
    public void testXmlUtils(){
        log.debug("Testing XML Utils...");
        Type type = new Type();
        type.setTypeId("4980d2e4-a424-4ff4-a0b2-476039682f43");
        type.setName("WEB");
        type.setDesc("Web Link");
        type.setExtension(".url");


        Document document = new Document();
        document.setDocumentID(UUID.randomUUID().toString());
        document.setName("Apress Books");
        document.setLocation("http://www.apress.com");
        document.setDescription("Apress Books");
        document.setType(type);
        document.setCreated(new Date());
        document.setModified(new Date());

        String string = XmlUtils.toXML(document);
        log.debug("\n" + string);

        Document other = XmlUtils.fromXML(string,Document.class);
        assertNotNull(other);
    }


    @Autowired
    RabbitMQProducer rabbitmqProducer;

    @Test
    public void testSpringRabbitMQ_1(){
        log.debug("Testing RabbitMQ producer...");
        assertNotNull(rabbitmqProducer);

        Document document = engine.findById(DOCUMENT_ID);
        assertNotNull(document);
        rabbitmqProducer.send(document);
    }


    @Test
    public void testSpringRabbitMQ_2() throws InterruptedException{
        log.debug("Testing RabbitMQ Consumer...");
        //Just wait for the RabbitMQ consumer...
        Thread.sleep(5000);
    }

}