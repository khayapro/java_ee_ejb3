package com.salutation.interceptors;

import com.salutation.interfaces.Role;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJBAccessException;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Created by khayapro on 2016/06/27
 */
@DeclareRoles(Role.EMPLOYEE)
public class SecurityInterceptor {

    @Resource
    private SessionContext sessionContext;

    @AroundInvoke
    public Object verifyAccess(InvocationContext context) throws Exception {
        System.out.println("SecurityInterceptor - entered - target method: " + context.getMethod().getName());
        //checking access for valid caller i.e Employee role.
        if(sessionContext.isCallerInRole(Role.EMPLOYEE)){
            final Object result = context.proceed();
            System.out.println("SecurityInterceptor - exit - target method: " + context.getMethod().getName());
            return result;
        } else {
            throw new EJBAccessException();
        }
    }
}
