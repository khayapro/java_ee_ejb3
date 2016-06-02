package com.salutation;

import com.salutation.model.City;

import javax.ejb.*;
import java.rmi.RemoteException;

/**
 * Created by khayapro on 2016/06/01
 */
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER) //default
@TransactionAttribute(TransactionAttributeType.REQUIRED) // default - and for all methods in this class.
public class PopulationManager implements SessionSynchronization {

    @EJB
    private CityFacadeBean cityFacadeBean;

    public PopulationManager(){
        super();
    }
    public void addCity(String cityName, String country, long population){
        final City city = new City(cityName, country, population);
        cityFacadeBean.save(city);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updatePopulation(String cityName, long population){
        cityFacadeBean.changePopulation(cityName, population);
    }

    @Override
    public void afterBegin() throws EJBException, RemoteException {
        System.out.println("***********\n - updatePopulation() - PopulationManager afterBegin");
    }

    @Override
    public void beforeCompletion() throws EJBException, RemoteException {
        System.out.println("************\n - updatePopulation() - PopulationManager beforeCompletion");
    }

    @Override
    public void afterCompletion(boolean b) throws EJBException, RemoteException {
        System.out.println("************\n - updatePopulation() - PopulationManager afterCompletion");
    }
}
