/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.StreamMessage;
import java.util.logging.Logger;

/**
 *
 * @author khayapro
 */
@MessageDriven(mappedName = "jms/ItemsQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationM", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class StreamMessageItemBean implements MessageListener {

    private static final Logger Log = Logger.getLogger(StreamMessageItemBean.class.getName());

    @Override
    public void onMessage(Message message) {
        final StreamMessage stream = (StreamMessage) message;
        try {
            System.out.println("Order no. " + stream.readInt());
            System.out.println("Price. " + stream.readDouble());
            System.out.println("Quantity no. " + stream.readInt());
            Log.info("message received.");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
}
