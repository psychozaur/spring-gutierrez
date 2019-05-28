package com.rybickim.spring.jms;

import com.rybickim.spring.data.DocumentDAO;
import com.rybickim.spring.model.Document;
import com.rybickim.spring.utils.XmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class JMSConsumer implements MessageListener {

    @Autowired
    DocumentDAO documentDAO;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            Document document = XmlUtils.fromXML(textMessage.getText(), Document.class);
            documentDAO.save(document);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
