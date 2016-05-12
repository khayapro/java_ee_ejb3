/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;

/**
 *
 * @author khayapro
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER) //default.
public class PlayerBean {
    
    private static final Logger LOG = Logger.getLogger("PlayerBeanLog");
    
    private String name;
    
    @PostConstruct
    public void init(){
        name = "Init Value";
        LOG.info("name initialized...");
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @PreDestroy
    public void destroy(){
        LOG.info("about to destroy the bean.");
    }
    
    
}
