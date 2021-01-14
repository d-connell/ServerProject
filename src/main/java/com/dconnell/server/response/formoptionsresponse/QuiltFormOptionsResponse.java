package com.dconnell.server.response.formoptionsresponse;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.size.CoverSize;

public class QuiltFormOptionsResponse extends FormOptionsResponse {

    private Iterable<CoverSize> sizes;
    private Iterable<Maker> makers;

    public QuiltFormOptionsResponse(Iterable<CoverSize> quiltSizes, Iterable<Maker> makers) {
        this.sizes = quiltSizes;
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