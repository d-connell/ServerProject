package com.dconnell.quiltserver.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "quilts")
public class Quilt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "size")
    private String size;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    public Quilt() {
    }

    public Quilt(String name, String size, BigDecimal price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}