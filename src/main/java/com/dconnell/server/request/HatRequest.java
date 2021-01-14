package com.dconnell.server.request;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.size.HatSize;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class HatRequest extends InventoryRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("size")
    private HatSize size;

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

    public HatSize getSize() {
        return size;
    }

    public void setSize(HatSize size) {
        this.size = size;
    }

    public Maker getMaker() {
        return maker;
    }

    public void setMaker(Maker maker) {
        this.maker = maker;
    }

}