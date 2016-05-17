package com.salutation;

import com.salutation.model.Order;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.logging.Logger;

/**
 * Created by khayapro on 2016/05/17
 */
@MessageDriven(mappedName = "jms/OrderQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeType", propertyValue = "Auto-Acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class OrderMessageBean implements MessageListener {

    private static final Logger Log = Logger.getLogger(OrderMessageBean.class.getName());

    /**
     * Handling the message recieved by this bean.
     * @param message - message event received.
     */
    @Override
    public void onMessage(Message message) {
        try {
            final ObjectMessage objectMessage = (ObjectMessage) message;
            final Order order = (Order) objectMessage.getObject();
            System.out.println("Order - partNumber : " + order.getPartNumber());
            System.out.println("Order - weight : " + order.getWeight());
            System.out.println("Order - quantity : " + order.getQuantity());
            Log.info("Order message successfully received...");
        } catch (JMSException e) {
            Log.info(e.getMessage());
        }

    }
}
