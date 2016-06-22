package com.salutation.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * A default interceptor for all EJB within this module.
 * Created by khayapro on 2016/06/22
 */
public class DefaultInterceptor {

    @AroundInvoke
    public Object defaultMethod(InvocationContext context) throws Exception {
        System.out.println("DefaultInterceptor class - enter method invoked : " + context.getMethod().getName());
        final Object result = context.proceed();
        System.out.println("DefaultInterceptor class - returned from method invoked : " + context.getMethod().getName());
        return result;
    }
}
