package com.salutation.interceptors;

import com.salutation.ApplicationStatistics;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Created by khayapro on 2016/07/04
 */
public class TimeInMethodInterceptor {

    @EJB
    private ApplicationStatistics applicationStatistics;

    /**
     * The following method record the total time within a method.
     * @param context
     * @return
     * @throws Exception
     */
    @AroundInvoke
    public Object recordTime(InvocationContext context) throws Exception {
        System.out.println("TimeInMethodInterceptor - entered");
        System.out.println("Context data - count = " + context.getContextData().get("count"));
        final long startTime = System.currentTimeMillis(); //begin
        final Object result = context.proceed();
        final long endTime = System.currentTimeMillis(); //end
        applicationStatistics.increaseTotalTime(endTime - startTime);
        System.out.println("TimeInMethodInterceptor - exit");
        return result;
    }
}
