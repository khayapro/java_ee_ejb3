package com.salutation.model;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by khayapro on 2016/05/31
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void save(T entity){
        getEntityManager().persist(entity);
    }

    public void update(T entity){
        getEntityManager().merge(entity);
    }

    /**
     * This method is also part of a transaction, if the bean extending it is transactional
     * @param entity
     */
    public void remove(T entity){
        System.out.println("--- AbstractFacade remove - " +
                this.getClass().getSimpleName());
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id){
        return getEntityManager().find(entityClass, id);
    }

    /**
     * This method is also part of a transaction, if the bean extending it is transactional
     */
    public List<T> findAll(){
        System.out.println("--- AbstractFacade findAll - " +
                this.getClass().getSimpleName());
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
