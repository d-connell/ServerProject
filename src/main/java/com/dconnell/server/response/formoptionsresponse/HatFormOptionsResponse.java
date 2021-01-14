package com.dconnell.server.response.formoptionsresponse;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.size.HatSize;

public class HatFormOptionsResponse extends FormOptionsResponse {

    private Iterable<HatSize> sizes;
    private Iterable<Maker> makers;

    public HatFormOptionsResponse(Iterable<HatSize> hatSizes, Iterable<Maker> makers) {
        this.sizes = hatSizes;
        this.makers = makers;
    }

    public Iterable<HatSize> getSizes() {
        return sizes;
    }

    public void setSizes(Iterable<HatSize> sizes) {
        this.sizes = sizes;
    }

    public Iterable<Maker> getMakers() {
        return makers;
    }

    public void setMakers(Iterable<Maker> makers) {
        this.makers = makers;
    }

}