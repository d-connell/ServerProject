package com.dconnell.server.model.inventory.entity;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.Type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class InventoryEntity implements Serializable  {

    public abstract Type getType();

    public abstract void setType(Type type);

    public abstract BigInteger getId();

    public abstract String getImageUrl();

    public abstract void setImageUrl(String imageUrl);

    public abstract boolean isDeleted();

    public abstract void setDeleted(boolean isDeleted);

    public abstract void setPrice(BigDecimal price);

    public abstract void setMaker(Maker maker);

    public abstract void setName(String name);

}