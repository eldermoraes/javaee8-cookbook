package com.eldermoraes.ch06.jms;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

/**
 *
 * @author eldermoraes
 */
public class TopicReceiver {

    @Resource(mappedName = "jms/JmsFactory")
    private ConnectionFactory jmsFactory;
    
    @Resource(mappedName = "jms/JmsTopic")
    private Topic jmsTopic;

    public void receive() {
        Connection conn;
        MessageConsumer consumer;
        
        try {
            conn = jmsFactory.createConnection();
            try (Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
                
                consumer = session.createConsumer(jmsTopic);
                consumer.setMessageListener(new TopicListener());
                conn.start();
                
                consumer.close();
            }
            conn.close();
        } catch (JMSException e) {
            System.err.println(e.getMessage());
        }
    }
}
