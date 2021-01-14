package com.dconnell.server.factory;

import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.entity.Bag;
import com.dconnell.server.request.BagRequest;
import org.springframework.stereotype.Component;

@Component
public class BagFactory implements Factory<Bag, BagRequest> {

    @Override
    public Bag create(BagRequest bagRequest, String fileName, Type type) {
        Bag bag = new Bag();
        bag.setSize(bagRequest.getSize());
        setValues(bag, bagRequest, type, fileName);
        return bag;
    }

}