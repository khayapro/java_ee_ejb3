package com.salutation;

import com.salutation.model.AbstractFacade;
import com.salutation.model.City;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khayapro on 2016/05/31
 */
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER) //default
@TransactionAttribute(TransactionAttributeType.REQUIRED) // default - and for all methods in this class.
public class CityFacadeBean extends AbstractFacade<City> implements SessionSynchronization {

    @PersistenceContext(unitName = "MY_PERSISTENCE_UNIT")
    private EntityManager em;

    public CityFacadeBean() {
        super(City.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void save(City city){
        getEntityManager().persist(city);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void changePopulation(String cityName, long count){
        Query query = em.createQuery("UPDATE City c SET c.population = c.population + :counts WHERE c.name = :cityName");
        query.setParameter("counts", count);
        query.setParameter("cityName", cityName);
        final int result = query.executeUpdate();
        System.out.println("result = " + result);
        System.out.println("--- end changePopulation");
    }

    @Override
    public void afterBegin() throws EJBException, RemoteException {
        System.out.println("**********\n - changePopulation() - CityFacadeBean afterBegin");
    }

    @Override
    public void beforeCompletion() throws EJBException, RemoteException {
        System.out.println("********** - changePopulation() - CityFacadeBean beforeCompletion");
    }

    @Override
    public void afterCompletion(boolean b) throws EJBException, RemoteException {
        System.out.println("*********** - changePopulation() - CityFacadeBean afterCompletion\n");
    }
}
