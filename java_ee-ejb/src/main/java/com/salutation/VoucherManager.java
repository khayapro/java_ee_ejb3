package com.salutation;

import com.salutation.model.Voucher;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.math.BigDecimal;

/**
 * Created by khayapro on 2016/06/04
 */
@Stateful
public class VoucherManager {

    @EJB
    private VoucherFacade voucherFacade;
    private Voucher voucher;

    public void createVoucher(String name, String destination, BigDecimal amount){
        voucher = new Voucher(name, destination, amount);
        voucherFacade.save(voucher);
    }

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
    public void submit(){
        System.out.println("voucher submitted");
    }

    /**
     * This method is used by a Manager to approve a voucher.
     */
    public void approve(){
        System.out.println("voucher approved");
        voucher.setApproved(true);
    }

    /**
     * This method is used by a manager to reject a voucher.
     */
    public void reject(){
        System.out.println("voucher rejected");
        voucher.setApproved(false);
    }
}
