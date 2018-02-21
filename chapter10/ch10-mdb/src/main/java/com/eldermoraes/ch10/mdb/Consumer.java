package com.eldermoraes.ch10.mdb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author eldermoraes
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/JmsQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class Consumer  implements MessageListener{

    @Override
    public void onMessage(Message msg) {
        try {
            User user = msg.getBody(User.class);
            System.out.println("User: " + user);
        } catch (JMSException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
