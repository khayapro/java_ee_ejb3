/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation.interfaces;

import javax.ejb.Local;

/**
 *
 * @author khayapro
 */
@Local
public interface AccountBeanLocal {
    
    public float getCorporateDiscount();
    public void setCorporateDiscount(float corporateDiscount);
    public float getNonProfitDiscount();
    public void setNonProfitDiscount(float nonProfitDiscount);
    
}
