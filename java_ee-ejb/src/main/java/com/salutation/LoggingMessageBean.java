package com.salutation;

import com.salutation.model.Availability;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by khayapro on 2016/05/18
 */
@MessageDriven(mappedName = "jms/AvailabilityTopic", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "LoggingMessageBean"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "LoggingMessageBean")
})
public class LoggingMessageBean implements MessageListener {

    /**
     * Handle a message confirm if user is available or not.
     * @param message - message to be handled.
     */
    @Override
    public void onMessage(Message message) {
        try {
            final ObjectMessage objectMessage = (ObjectMessage) message;
            final Availability availability = (Availability) objectMessage.getObject();

            if(availability.isAvailable()){
                System.out.println(availability.getName() + " is available");
            } else {
                System.out.println(availability.getName() + " is not available");
            }
            System.out.println("Message received... logging_bean");
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
