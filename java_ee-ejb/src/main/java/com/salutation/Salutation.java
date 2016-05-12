/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author khayapro
 */
@Stateless(mappedName = "salutationEJB")
@LocalBean
public class Salutation {

    public String getFormalSalutation(final String name){
        return "Dear " + name;
    }
    
    public String getInformalSalutation(final String name){
        return "Hi " + name;
    }
}
