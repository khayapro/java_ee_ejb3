package com.salutation;

import com.salutation.interceptors.SimpleInterceptor;
import com.salutation.model.Attendee;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;

/**
 * Created by khayapro on 2016/06/21
 */
@Stateful
/**
 * Targeted class to intercept and only the business method
 * will be intercepted and handled by SimpleInterceptor class.
 */
@Interceptors(SimpleInterceptor.class)
public class RegistrationManager {

    @EJB
    private AttendeeFacade attendeeFacade;
    private Attendee attendee;

    /**
     * Register attendee
     * @param name - name of attendee
     * @param title - title of attendee
     * @param company -
     * @return Attendee
     */
    public Attendee register(String name, String title, String company){
        System.out.println();
        attendee = new Attendee(name, title, company);
        attendeeFacade.save(attendee);
        System.out.println(name + " registered");
        return attendee;
    }


}
