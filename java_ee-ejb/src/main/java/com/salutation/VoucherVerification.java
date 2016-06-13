package com.salutation;

import com.salutation.interfaces.Role;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.security.Principal;

/**
 * Created by khayapro on 2016/06/13
 */
@Stateless
@DeclareRoles(Role.MANAGER)
@RunAs(Role.MANAGER)
public class VoucherVerification {

    @Resource
    private SessionContext sessionContext;

    public void submit(){
        final Principal principal = sessionContext.getCallerPrincipal();
        System.out.println("Current principal name : " + principal.getName());
    }
}
