package com.dconnell.server.model.inventory.entity;

import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.size.HatSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "hats")
public class Hat extends InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @ManyToOne
    private Type type;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @JoinColumn(name = "size_id", referencedColumnName = "id")
    @ManyToOne
    private HatSize size;

    @JoinColumn(name = "maker_id", referencedColumnName = "id")
    @ManyToOne
    private Maker maker;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    @Column(name = "is_deleted")
    private boolean isDeleted;

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

}