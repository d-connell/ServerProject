package com.dconnell.server.factory;

import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.entity.Quilt;
import com.dconnell.server.request.QuiltRequest;
import org.springframework.stereotype.Component;

@Component
public class QuiltFactory implements Factory<Quilt, QuiltRequest> {

    @Override
    public Quilt create(QuiltRequest quiltRequest, String imageFileName, Type type) {
        Quilt quilt = new Quilt();
        quilt.setSize(quiltRequest.getSize());
        setValues(quilt, quiltRequest, type, imageFileName);
        return quilt;
    }

}