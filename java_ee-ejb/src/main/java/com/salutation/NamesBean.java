/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 *
 * @author khayapro
 */
@Stateful
@LocalBean
public class NamesBean {
    
    private List<String> names;
    
    @PostConstruct
    public void init(){
        names = new ArrayList<String>();
    }
    
    public void addName(final String name){
        if(name == null)
            throw new IllegalArgumentException("Name cannot be null");
        
        names.add(name);
    }
    
    public List<String> getNames(){
        return names;
    }

    
}
