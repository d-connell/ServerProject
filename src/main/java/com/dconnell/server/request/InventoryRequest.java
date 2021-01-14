package com.dconnell.server.request;

import com.dconnell.server.model.inventory.Maker;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class InventoryRequest implements Serializable {

    public abstract String getName();

    public abstract BigDecimal getPrice();

    public abstract Maker getMaker();

}