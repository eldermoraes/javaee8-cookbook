package com.eldermoraes.ch06.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class QueueReceiver {

    @Resource(mappedName = "jms/JmsFactory")
    private ConnectionFactory jmsFactory;

    @Resource(mappedName = "jms/JmsQueue")
    private Queue jmsQueue;

    public void receive() throws JMSException {
        MessageConsumer consumer;
        Connection conn;
        boolean received = false;
        TextMessage message;

        conn = jmsFactory.createConnection();
        try (Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE)) {

            consumer = session.createConsumer(jmsQueue);
            conn.start();
            System.out.println("Will get messages");

            int counter = 0;
            while (!received) {
                message = (TextMessage) consumer.receive();
                if (message != null) {
                    System.out.print("Got this message: ");
                    System.out.println(message.getText());
                    System.out.println("");
                    counter = 0;
                }
                counter++;

                if (counter > 100) {
                    received = true;
                }
            }
            consumer.close();
            session.close();
            conn.close();
        } 
    }

}
