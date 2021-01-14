package com.dconnell.server.response.formoptionsresponse;

import com.dconnell.server.model.inventory.size.BagSize;
import com.dconnell.server.model.inventory.Maker;

public class BagFormOptionsResponse extends FormOptionsResponse {

    private Iterable<BagSize> sizes;
    private Iterable<Maker> makers;

    public BagFormOptionsResponse(Iterable<BagSize> bagSizes, Iterable<Maker> makers) {
        this.sizes = bagSizes;
        this.makers = makers;
    }

    public Iterable<BagSize> getSizes() {
        return sizes;
    }

    public void setSizes(Iterable<BagSize> sizes) {
        this.sizes = sizes;
    }

    public Iterable<Maker> getMakers() {
        return makers;
    }

    public void setMakers(Iterable<Maker> makers) {
        this.makers = makers;
    }

}