package com.salutation.Exception;

import javax.ejb.ApplicationException;

/**
 * Created by khayapro on 2016/06/04
 */
@ApplicationException(rollback = true)
public class IllegalPopulationException extends Exception {

    public IllegalPopulationException(){
        super();
    }

    public IllegalPopulationException(String message){
        super("IllegalPopulationException");
    }
}
