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
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author khayapro
 */
@MessageDriven(mappedName = "jms/SalutationQueue", 
        activationConfig = {
            @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
            @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")})
public class SalutationMessageBean implements MessageListener {
    
    private static final Logger LOG = Logger.getLogger("SalutationMessageBeanLog");
    
    /**
     * Default constructor
     */
    public SalutationMessageBean(){
        super();
    }

    @Override
    public void onMessage(final Message message) {
       
        try {
            final String name = message.getStringProperty("name");
            LOG.log(Level.INFO, "Salutation processed - name = [ " + name + "]" , "");
        } catch (Exception e) {
        }
    }

}
