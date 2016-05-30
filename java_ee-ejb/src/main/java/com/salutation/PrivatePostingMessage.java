package com.salutation;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by khayapro on 2016/05/30
 */
@MessageDriven(mappedName = "jms/PostingQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "PostingType = 'private'")
})
public class PrivatePostingMessage implements MessageListener {

    /**
     * Handling all private messages received.
     * @param message - private message
     */
    @Override
    public void onMessage(Message message) {
        try {
            final TextMessage privateMessage = (TextMessage) message;
            System.out.println("received private message - " + privateMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
