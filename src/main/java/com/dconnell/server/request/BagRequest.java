package com.dconnell.server.request;

import com.dconnell.server.model.inventory.size.BagSize;
import com.dconnell.server.model.inventory.Maker;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BagRequest extends InventoryRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("size")
    private BagSize size;

    @JsonProperty("maker")
    private Maker maker;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BagSize getSize() {
        return size;
    }

    public void setSize(BagSize size) {
        this.size = size;
    }

    public Maker getMaker() {
        return maker;
    }

    public void setMaker(Maker maker) {
        this.maker = maker;
    }

}