package com.dconnell.server.factory;

import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.entity.Hat;
import com.dconnell.server.request.HatRequest;
import org.springframework.stereotype.Component;

@Component
public class HatFactory implements Factory<Hat, HatRequest> {

    @Override
    public Hat create(HatRequest hatRequest, String fileName, Type type) {
        Hat hat = new Hat();
        hat.setSize(hatRequest.getSize());
        setValues(hat, hatRequest, type, fileName);
        return hat;
    }

}