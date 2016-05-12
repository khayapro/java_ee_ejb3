package com.salutation;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by khayapro on 2016/05/12
 */
@MessageDriven(mappedName = "jms/MapMessageQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "acknowledgeType", propertyValue = "Auto-Acknowledge")
})
public class MapMessageBean implements MessageListener {

    private static final Logger LOG = Logger.getLogger(MapMessageBean.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            final MapMessage msg = (MapMessage) message;
            System.out.println("PartNumber received: " + msg.getInt("PartNumber"));
            System.out.println("Weight received: " + msg.getDouble("Weight"));
            System.out.println("Quantity received: " + msg.getInt("Quantity"));
            LOG.info("Message received successfully...");
        } catch (JMSException e) {
            LOG.log(Level.SEVERE, null, e);
        }
    }
}
