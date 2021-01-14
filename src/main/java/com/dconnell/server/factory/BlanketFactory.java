package com.dconnell.server.factory;

import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.entity.Blanket;
import com.dconnell.server.request.BlanketRequest;
import org.springframework.stereotype.Component;

@Component
public class BlanketFactory implements Factory<Blanket, BlanketRequest> {

    @Override
    public Blanket create(BlanketRequest blanketRequest, String imageFileName, Type type) {
        Blanket blanket = new Blanket();
        blanket.setSize(blanketRequest.getSize());
        setValues(blanket, blanketRequest, type, imageFileName);
        return blanket;
    }

}