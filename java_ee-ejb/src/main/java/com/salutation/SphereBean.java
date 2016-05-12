/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author khayapro
 */
@Stateless
@LocalBean //default for session beans
public class SphereBean {
    
    private static final Logger LOG = Logger.getLogger("SphereBeanLog");
    
    
    @PostConstruct
    public void init(){
        LOG.info("@PostConstuction init");
    }
    
    /**
     * Compute the volume.
     * @param radius
     * @return 
     */
    public double computeVolume(final double radius){
        return (4.0 / 3.0) * Math.PI * (radius * radius * radius);
    }
    
    
    @PreDestroy
    public void cleanUp(){
         LOG.info("@PreDestroy init");
    }
    
    
    
    
}
