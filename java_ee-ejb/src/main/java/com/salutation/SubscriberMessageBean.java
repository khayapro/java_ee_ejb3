package com.salutation;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by khayapro on 2016/05/18
 */
@MessageDriven(mappedName = "jms/AvailabilityTopic", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-Acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "SubscriberMessageBean"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "SubscriberMessageBean")
})
public class SubscriberMessageBean implements MessageListener {


    @Override
    public void onMessage(Message message) {
        System.out.println("Message received ---> SubscriberMessageBean");
    }
}
