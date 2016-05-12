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
import javax.jms.TextMessage;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khayapro
 */
@MessageDriven(mappedName = "jms/TextMessageQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class TextMessageBean implements MessageListener {
    
    private static final Logger Log = Logger.getLogger(TextMessageBean.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            final TextMessage textMessage = (TextMessage) message;
            final Scanner sc = new Scanner(textMessage.getText());
            
            System.out.println("Order No. received... - " + sc.nextInt());
            System.out.println("Order Weight. received... - " + sc.nextFloat());
            System.out.println("Order Qty. received... - " + sc.nextInt());
        } catch (JMSException ex) {
            Logger.getLogger(TextMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
