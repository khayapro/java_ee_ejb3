package com.salutation;

import com.salutation.model.AbstractFacade;
import com.salutation.model.Attendee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by khayapro on 2016/06/21
 */
@Stateless
public class AttendeeFacade extends AbstractFacade<Attendee> {

    @PersistenceContext(name = "MY_PERSISTENCE_UNIT")
    private EntityManager em;

    /**
     * default constructor
     */
    public AttendeeFacade(){
        super(Attendee.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
