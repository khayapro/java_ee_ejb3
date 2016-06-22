package com.salutation.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Created by khayapro on 2016/06/22
 */
public class SimpleInterceptor {

    /**
     * Declaring an Interceptor method with its signature,
     * <code>@AroundInvoke</code> designate this method as an interceptor method.
     * @param context - hold a state and target method
     * @return Object
     * @throws Exception
     */
    @AroundInvoke
    public Object simpleMethod(InvocationContext context) throws Exception {
        System.out.println("SimpleInterceptor class - target method entered: " + context.getMethod().getName());
        final Object outcome = context.proceed();
        System.out.println("SimpleInterceptor class - target method exited: " + context.getMethod().getName());
        return outcome;
    }
}
