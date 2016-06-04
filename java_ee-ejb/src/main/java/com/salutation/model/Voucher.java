package com.salutation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by khayapro on 2016/06/04
 */
@Entity
public class Voucher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String destination;
    private BigDecimal amount;
    private boolean approved;

    /**
     * default constructor
     */
    public Voucher(){
        super();
    }

    public Voucher(String name, String destination, BigDecimal amount){
        this.name = name;
        this.destination = destination;
        this.amount = amount;
        approved = false;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
