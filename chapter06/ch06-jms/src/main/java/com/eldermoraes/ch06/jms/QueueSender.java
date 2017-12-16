package com.eldermoraes.ch06.jms;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class QueueSender {

    @Resource(mappedName = "jms/JmsFactory")
    private ConnectionFactory jmsFactory;
    
    @Resource(mappedName = "jms/JmsQueue")
    private Queue jmsQueue;

    public void send() throws JMSException {
        MessageProducer producer;
        TextMessage message;

        try (Connection connection = jmsFactory.createConnection(); 
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
            
            producer = session.createProducer(jmsQueue);
            message = session.createTextMessage();

            String msg = "Message sent to queue at " + new Date();
            message.setText(msg);
            System.out.println(msg);
            producer.send(message);

            producer.close();
        }
    }
}
