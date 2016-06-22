package com.salutation.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Created by khayapro on 2016/06/22
 */
public class ValidationInterceptor {

    @AroundInvoke
    public Object validateParameters(InvocationContext context) throws Exception {
        System.out.println("ValidationInterceptor - entered method invoked : " + context.getMethod().getName());

        final Object[] parameters = context.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            System.out.println("Before mod : [" + parameters[i] + "]"); //implicit cast
            parameters[i] = ((String) parameters[i]).trim();
            System.out.println("After mod: [" + parameters[i] + "]");
        }

        //setting back modified parameter
        context.setParameters(parameters);
        final Object result = context.proceed();
        System.out.println("ValidationInterceptor - exit method invoked : " + context.getMethod().getName());
        return  result;
    }
}
