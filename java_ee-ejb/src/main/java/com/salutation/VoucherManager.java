package com.salutation;

import com.salutation.interfaces.Role;
import com.salutation.model.Voucher;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import java.math.BigDecimal;

/**
 * Created by khayapro on 2016/06/04
 */
@Stateful
@DeclareRoles({Role.MANAGER, Role.EMPLOYEE, Role.AUDITOR})
public class VoucherManager {

    @Resource
    private SessionContext sessionContext;

    @EJB
    private VoucherFacade voucherFacade;
    @EJB
    private VoucherVerification voucherVerification;
    private Voucher voucher;

    public void createVoucher(String name, String destination, BigDecimal amount){
        voucher = new Voucher(name, destination, amount);
        voucherFacade.save(voucher);
    }

    /**
     * Allowing all roles to access this method.
     * @return
     */
    @PermitAll
    public String getName(){
        return voucher.getName();
    }

    public String getDestination(){
        return voucher.getDestination();
    }

    public BigDecimal getAmount(){
        return voucher.getAmount();
    }

    /**
     * This method is used by employee to submit a voucher to a Manager.
     */
    @RolesAllowed(Role.EMPLOYEE)
    public void submit(){
        System.out.println("voucher submitted");
        voucherVerification.submit();
    }

    /**
     * This method is used by a Manager to approve a voucher.
     */
    @RolesAllowed(Role.MANAGER)
    public boolean approve(){
        if(sessionContext.isCallerInRole(Role.MANAGER)) { //more efficient than comparing principal.getName()
            final boolean validated = validateAllowance(voucher.getAmount());
            if(validated)
                voucher.setApproved(true);
            else
                return false;
            System.out.println("voucher approved successfully");
            return true;
        } else {
            System.out.println("voucher failed to be approved");
            return  false;
        }
    }

    /**
     * This method is used by a manager to reject a voucher.
     */
    @RolesAllowed(Role.MANAGER)
    public boolean reject(){
        System.out.println("voucher rejected");
        voucher.setApproved(false);
        return false;
    }

    /**
     * checking allowed allowance based on the roles. Controlling security programmatically.
     * @param allowance
     * @return
     */
    public boolean validateAllowance(final BigDecimal allowance){
        if(allowance == null)
            throw new IllegalArgumentException("Allowance cannot be null");

        if(sessionContext.isCallerInRole(Role.MANAGER))
            return allowance.compareTo(BigDecimal.valueOf(2500)) <= 0;
        else if(sessionContext.isCallerInRole(Role.EMPLOYEE))
            return allowance.compareTo(BigDecimal.valueOf(1500)) <= 0;
        else if(sessionContext.isCallerInRole(Role.AUDITOR))
            return allowance.compareTo(BigDecimal.valueOf(1000)) <= 0;

        return false;
    }
}
