package com.salutation.interceptors;

import com.salutation.ApplicationStatistics;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Map;

/**
 * Created by khayapro on 2016/07/04
 */
public class HitCounterInterceptor {

    @EJB
    private ApplicationStatistics applicationStatistics;

    @AroundInvoke
    public Object incrementCount(InvocationContext context) throws Exception {
        System.out.println("HitCounterInterceptor - entered method");
        final Map<String, Object> data = context.getContextData();
        data.put("count", applicationStatistics.getCount());
        applicationStatistics.incrementCount();
        final Object result = context.proceed();
        System.out.println("HitCounterInterceptor - exit method");
        return result;
    }
}
