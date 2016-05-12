/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.AccessTimeout;
import javax.ejb.Asynchronous;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;

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
