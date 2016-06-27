package com.salutation.interceptors;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.transaction.UserTransaction;

/**
 * Created by khayapro on 2016/06/27
 */
public class TransactionInterceptor {

    @Resource
    private UserTransaction userTransaction;

    @AroundInvoke
    public Object verifyAccess(InvocationContext context) throws Exception {
        userTransaction.begin();
        System.out.println("TransactionInterceptor - entered transaction begin - method : " + context.getMethod().getName());
        final Object result = context.proceed();
        userTransaction.commit();
        System.out.println("TransactionInterceptor - exit transaction committed - method : " + context.getMethod().getName());
        return result;
    }
}
