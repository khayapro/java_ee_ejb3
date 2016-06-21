package com.salutation;

import com.salutation.model.Attendee;

import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * Created by khayapro on 2016/06/21
 */
@Stateful
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
        attendee = new Attendee(name, title, company);
        attendeeFacade.save(attendee);
        return attendee;
    }


}
