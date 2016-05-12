/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation;

import com.salutation.interfaces.AccountBeanLocal;
import com.salutation.interfaces.AccountBeanRemote;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author khayapro
 */
@Stateless
@Named(value = "account")
public class AccountBean implements AccountBeanLocal, AccountBeanRemote {
    
    private static final Logger LOG = Logger.getLogger(AccountBean.class.getName());
    
    private float corporateDiscount;
    private float nonProfitDiscount;  
    
    @PostConstruct
    public void init(){
        corporateDiscount = 0.15f;
        nonProfitDiscount = 0.25f;
    }

    @Override
    public float getCorporateDiscount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCorporateDiscount(float corporateDiscount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getNonProfitDiscount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNonProfitDiscount(float nonProfitDiscount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
