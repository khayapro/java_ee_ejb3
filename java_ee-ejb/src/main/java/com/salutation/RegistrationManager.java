package com.salutation;

import com.salutation.interceptors.MethodInterceptor;
import com.salutation.interceptors.SimpleInterceptor;
import com.salutation.model.Attendee;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.AroundInvoke;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

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
    @ExcludeClassInterceptors //Excluding the interceptor defined at class level.
    @Interceptors({MethodInterceptor.class})
    public Attendee register(String name, String title, String company){
        System.out.println();
        attendee = new Attendee(name, title, company);
        attendeeFacade.save(attendee);
        System.out.println(name + " registered");
        return attendee;
    }

    /**
     * This is a class level interceptor declaring demo
     * @param context
     * @return
     * @throws Exception
     */
    @AroundInvoke
    public Object internalInterceptor(InvocationContext context) throws Exception {
        System.out.println("RegistrationManager - internal method invoking : " + context.getMethod().getName());
        final Object result = context.proceed(); //proceed to targeted method.
        System.out.println("RegistrationManager - internal method invoking : " + context.getMethod().getName());
        return result;
    }


}
