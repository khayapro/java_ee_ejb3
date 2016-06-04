package com.salutation;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Status;
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
            System.out.println("Transaction state before begin(): " + getTransactionStateString(userTransaction.getStatus()));
            userTransaction.begin();
            System.out.println("Transaction state after begin(): " + getTransactionStateString(userTransaction.getStatus()));
                final Query query = em.createQuery("UPDATE City c SET c.population = c.population+:counts WHERE c.name = :cityName");
                query.setParameter("counts", counts);
                query.setParameter("cityName", cityName);
            final int result = query.executeUpdate();//Note: must be within a transaction otherwise throws an Exception
            System.out.println("Transaction state before commit(): " + getTransactionStateString(userTransaction.getStatus()));
            userTransaction.commit();
            System.out.println("Transaction state after commit(): " + getTransactionStateString(userTransaction.getStatus()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method gives us insight into a UserTransaction by using the getStatus() method
     * with that class.
     * @param state
     * @return String
     */
    private String getTransactionStateString(int state){
        switch (state){
            case Status.STATUS_ACTIVE:
                return "STATUS_ACTIVE: transaction is active";
            case Status.STATUS_COMMITTED:
                return "STATUS_COMMITTED: transaction is committed";
            case Status.STATUS_COMMITTING:
                return "STATUS_COMMITTING: transaction is committing...";
            case Status.STATUS_MARKED_ROLLBACK:
                return "STATUS_MARKED_ROLLBACK: transaction marked for rollback";
            case Status.STATUS_NO_TRANSACTION:
                return "STATUS_NO_TRANSACTION: there is no transaction";
            case Status.STATUS_PREPARED:
                return "STATUS_PREPARED: transaction is in prepared state, ready to commit";
            case Status.STATUS_PREPARING:
                return "STATUS_PREPARING: transaction is preparing to commit";
            case Status.STATUS_ROLLING_BACK:
                return "STATUS_ROLLING_BACK: transaction is being rolledback";
            case Status.STATUS_UNKNOWN:
                return "STATUS_UNKNOWN: transaction in a unknown state";
            default:
                return "status not available";
        }
    }
}
