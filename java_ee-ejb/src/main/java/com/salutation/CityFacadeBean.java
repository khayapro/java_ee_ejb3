package com.salutation;

import com.salutation.model.AbstractFacade;
import com.salutation.model.City;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by khayapro on 2016/05/31
 */
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CityFacadeBean extends AbstractFacade<City> {

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

    public void changePopulation(String cityName, long count){
        Query query = em.createQuery("UPDATE City c SET c.polulation = c.population+:count WHERE c.name = :cityName");
        query.setParameter("count", count);
        query.setParameter("cityName", cityName);
        final int result = query.executeUpdate();
        System.out.println("result = " + result);
        System.out.println("--- end changePopulation");
    }
}
