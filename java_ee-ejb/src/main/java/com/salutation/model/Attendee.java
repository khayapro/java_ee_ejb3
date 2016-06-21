package com.salutation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by khayapro on 2016/06/21
 */
@Entity
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String title;
    private String companyName;

    /**
     * Default constructor
     */
    public Attendee(){
        super();
    }

    public Attendee(final String name, final String title, final String companyName){
        this.name = name;
        this.title = title;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getCompanyName() {
        return companyName;
    }
}
