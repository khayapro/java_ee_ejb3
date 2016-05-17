package com.salutation.model;

import java.io.Serializable;

/**
 * Created by khayapro on 2016/05/17
 */
public class Order implements Serializable {

    private int partNumber;
    private float weight;
    private int quantity;

    /**
     * Constructor for order
     * @param partNumber
     * @param weight
     * @param quantity
     */
    public Order(final int partNumber, final float weight, final int quantity){
        this.partNumber = partNumber;
        this.weight = weight;
        this.quantity = quantity;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public float getWeight() {
        return weight;
    }

    public int getQuantity() {
        return quantity;
    }
}
