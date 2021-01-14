package com.dconnell.server.response.formoptionsresponse;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.size.CoverSize;

public class BlanketFormOptionsResponse extends FormOptionsResponse {

    private Iterable<CoverSize> sizes;
    private Iterable<Maker> makers;

    public BlanketFormOptionsResponse(Iterable<CoverSize> blanketSizes, Iterable<Maker> makers) {
        this.sizes = blanketSizes;
        this.makers = makers;
    }

    public Iterable<CoverSize> getSizes() {
        return sizes;
    }

    public void setSizes(Iterable<CoverSize> sizes) {
        this.sizes = sizes;
    }

    public Iterable<Maker> getMakers() {
        return makers;
    }

    public void setMakers(Iterable<Maker> makers) {
        this.makers = makers;
    }

}