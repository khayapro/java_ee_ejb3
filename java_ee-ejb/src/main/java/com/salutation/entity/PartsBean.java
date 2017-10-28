package com.salutation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by khayapro on 2017/10/28.
 */
@Entity
public class PartsBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int partNumber;
    private float weight;
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PartsBean)) {
            return false;
        }
        PartsBean other = (PartsBean) object;
        if ((this.id == null && other.id != null) || (this.id != null &&
                !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PartsBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", partNumber=" + partNumber +
                ", weight=" + weight +
                ", quantity=" + quantity +
                '}';
    }
}
