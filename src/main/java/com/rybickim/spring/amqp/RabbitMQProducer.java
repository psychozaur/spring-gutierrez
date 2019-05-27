package com.rybickim.spring.amqp;

import com.rybickim.spring.model.Document;
import com.rybickim.spring.utils.XmlUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("rabbitmqProducer")
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Document document){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("text/xml");
        byte[] body = XmlUtils.toXML(document).getBytes();
        Message message = new Message(body, messageProperties);
        rabbitTemplate.send(message);
    }
}
