package com.salutation.model;

import java.io.Serializable;

/**
 * Created by khayapro on 2016/05/18
 */
public class Availability implements Serializable {

    private String name;
    private boolean available;

    /**
     * Constructor to create the Availability instance.
     * @param name - person available
     * @param available - boolean value
     */
    public Availability(final String name, final boolean available){
        this.name = name;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }
}
