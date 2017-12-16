package com.eldermoraes.ch06.jms;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

/**
 *
 * @author eldermoraes
 */
public class QueueReceiver {

    @Resource(mappedName = "jms/JmsFactory")
    private ConnectionFactory jmsFactory;
    
    @Resource(mappedName = "jms/JmsQueue")
    private Queue jmsQueue;

    public void receive() {
        Connection conn;
        MessageConsumer consumer;
        
        try {
            conn = jmsFactory.createConnection();
            try (Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
                
                consumer = session.createConsumer(jmsQueue);
                consumer.setMessageListener(new QueueListener());
                conn.start();
                
                consumer.close();
            }
            conn.close();
        } catch (JMSException e) {
            System.err.println(e.getMessage());
        }
    }
}
