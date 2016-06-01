package com.salutation;

import com.salutation.model.City;

import javax.ejb.*;

/**
 * Created by khayapro on 2016/06/01
 */
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER) //default
@TransactionAttribute(TransactionAttributeType.REQUIRED) // default - and for all methods in this class.
public class PopulationManager {

    @EJB
    private CityFacadeBean cityFacadeBean;

    public PopulationManager(){
        super();
    }
    public void addCity(String cityName, String country, long population){
        final City city = new City(cityName, country, population);
        cityFacadeBean.save(city);
    }

    public void updatePopulation(String cityName, long population){
        cityFacadeBean.changePopulation(cityName, population);
    }

}
