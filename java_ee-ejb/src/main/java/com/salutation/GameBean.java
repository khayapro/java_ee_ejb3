/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author khayapro
 */
@Singleton
@Startup
@DependsOn(value = "PlayerBean")
public class GameBean {
    
    private static final Logger LOG = Logger.getLogger("GameBeanLog");
    
    private String state;
    
    @PostConstruct
    public void init(){
        state = "Initializing GameBean";
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public void destroy(){
        LOG.info("about to destroy GameBean - closing resouces.");
    }
    
    
    
}
