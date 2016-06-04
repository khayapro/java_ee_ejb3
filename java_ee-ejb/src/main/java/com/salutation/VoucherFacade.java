package com.salutation;

import com.salutation.model.AbstractFacade;
import com.salutation.model.Voucher;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by khayapro on 2016/06/04
 */
@Stateless
public class VoucherFacade extends AbstractFacade<Voucher> {

    @PersistenceContext(unitName = "MY_PERSISTENCE_UNIT")
    private EntityManager em;

    public VoucherFacade(){
        super(Voucher.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
