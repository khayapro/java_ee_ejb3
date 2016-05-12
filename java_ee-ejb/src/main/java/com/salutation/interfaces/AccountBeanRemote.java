/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salutation.interfaces;

import javax.ejb.Remote;

/**
 *
 * @author khayapro
 */
@Remote
public interface AccountBeanRemote {
    
    public float getCorporateDiscount();
    public float getNonProfitDiscount();
    
}
