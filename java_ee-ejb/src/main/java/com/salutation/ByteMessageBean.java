/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author khayapro
 */
@MessageDriven(mappedName = "jms/ByMessagePartsQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ByteMessageBean implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            BytesMessage bytesMessage = (BytesMessage) message;
            final int orderNo = bytesMessage.readInt();
            final float weight = bytesMessage.readFloat();
            final int quantity = bytesMessage.readInt();
            
            System.out.println("Message received ....");
            System.out.println("Order No." + orderNo);
            System.out.println("weight . " + weight);
            System.out.println("quality . " + quantity);
        } catch (JMSException ex) {
            Logger.getLogger(ByteMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
