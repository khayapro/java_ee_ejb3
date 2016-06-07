/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation;

import javax.ejb.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khayapro
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class ConcurrencyBean {
    
    private static final Logger LOG = Logger.getLogger(ConcurrencyBean.class.getName());
    public enum State {PAUSED, RUNNING, TERMINATED}
    
    private volatile State state; //volatile guarantees this value - BeanManagedConcurrency.

    @Lock(LockType.READ)
    public State getState() {
        return state;
    }

    /**
     * Default write protected for all singleton.
     * @param state 
     */
    @Lock(LockType.WRITE)
    @AccessTimeout(value = 2000) //how long the client should wait, otherwise throws exception
    @Asynchronous
    public void setState(State state) {
        try {
            this.state = state;
            Thread.sleep(5000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ConcurrencyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
