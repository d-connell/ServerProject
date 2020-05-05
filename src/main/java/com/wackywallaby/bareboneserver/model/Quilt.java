package com.wackywallaby.bareboneserver.model;

public class Quilt {

    private int id;
    private String name;
    private String size;
    private double price;

    public Quilt(String name, String size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

}