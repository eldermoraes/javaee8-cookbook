package com.eldermoraes.ch10.mdb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSContext;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class Sender {
    
    @Inject
    private JMSContext context;
    
    @Resource(lookup = "jms/JmsQueue")
    private Destination queue;
    
    public void send(User user){
        context.createProducer()
                .setDeliveryMode(DeliveryMode.PERSISTENT)
                .setDisableMessageID(true)
                .setDisableMessageTimestamp(true)
                .send(queue, user);
    }
    
}
