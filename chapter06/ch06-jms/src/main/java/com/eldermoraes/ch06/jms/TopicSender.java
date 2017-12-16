package com.eldermoraes.ch06.jms;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class TopicSender {

    @Resource(mappedName = "jms/JmsFactory")
    private ConnectionFactory jmsFactory;
    
    @Resource(mappedName = "jms/JmsTopic")
    private Topic jmsTopic;

    public void send() throws JMSException {
        MessageProducer producer;
        TextMessage message;

        try (Connection connection = jmsFactory.createConnection(); 
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
            
            producer = session.createProducer(jmsTopic);
            message = session.createTextMessage();

            String msg = "Now it is " + new Date();
            message.setText(msg);
            System.out.println("Message sent to topic: " + msg);
            producer.send(message);

            producer.close();
        }
    }
}
