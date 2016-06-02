package com.salutation;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 * Created by khayapro on 2016/06/02
 */
@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class BeanManagedPopulationManager {

    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext(unitName = "MY_PERSISTENCE_UNIT")
    private EntityManager em;

    /**
     * Changing population for the city, and this method manages its own transaction.
     * @param cityName
     * @param counts
     */
    public void changePopulation(String cityName, long counts){
        try {
            System.out.println("about to execute change population");
            userTransaction.begin();
                final Query query = em.createQuery("UPDATE City c SET c.population = c.population+:counts WHERE c.name = :cityName");
                query.setParameter("counts", counts);
                query.setParameter("cityName", cityName);
            final int result = query.executeUpdate();//Note: must be within a transaction otherwise throws an Exception
            userTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
