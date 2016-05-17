package com.salutation;

import com.salutation.model.Order;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
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

    @Resource(mappedName = "jms/OrderQueueFactory")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName = "jms/OrderQueue")
    private Queue queue;

    /**
     * Handling the message recieved by this bean.
     * @param message - message event received.
     */
    @Override
    public void onMessage(Message message) {
        try {
            final ObjectMessage objectMessage = (ObjectMessage) message;
            final Order order = (Order) objectMessage.getObject();

            // Send out a thank you order
            if(order.getQuantity() > 40) {
                //1. create connection
                final QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
                //2. create session
                final Session session = queueConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                //3. create producer
                final MessageProducer producer = session.createProducer(queue);
                //4. create object message
                final ObjectMessage objMessage = session.createObjectMessage();
                objMessage.setObject(new Order(54321,5.5f,1));

                //5. sending message.
                producer.send(objMessage);
            }
            System.out.println("Order - partNumber : " + order.getPartNumber());
            System.out.println("Order - weight : " + order.getWeight());
            System.out.println("Order - quantity : " + order.getQuantity());
            Log.info("Order message successfully received...");
        } catch (JMSException e) {
            Log.info(e.getMessage());
        }

    }
}
