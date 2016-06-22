package com.salutation.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * This will demonstrate a method level interceptor.
 * Created by khayapro on 2016/06/22
 */
public class MethodInterceptor {

    @AroundInvoke
    public Object methodLevel(InvocationContext context) throws Exception {
        System.out.println("MethodInterceptor - entered method : " + context.getMethod().getName());
        final Object result = context.proceed();// to invoked to targeted method.
        System.out.println("MethodInterceptor - entered method : " + context.getMethod().getName());
        return result;
    }
}
