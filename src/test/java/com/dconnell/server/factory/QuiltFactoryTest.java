package com.dconnell.server.factory;

import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.entity.Quilt;
import com.dconnell.server.model.inventory.size.CoverSize;
import com.dconnell.server.request.QuiltRequest;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuiltFactoryTest {

    String quiltName = "test quilt";
    BigDecimal price = new BigDecimal(800);
    String sizeName = "size";
    String dimensions = "dimensions";
    String makerName = "Tester";
    String filename = "fileName";

    @Test
    void ShouldPassWhenCreatingQuilt() {
        Quilt quilt = new QuiltFactory().create(createQuiltRequest(), filename, createEntityType());
        assertAll("quilt",
                () -> assertEquals(quiltName, quilt.getName()),
                () -> assertEquals(price, quilt.getPrice()),
                () -> assertEquals(sizeName, quilt.getSize().getName()),
                () -> assertEquals(dimensions, quilt.getSize().getDimensions()),
                () -> assertEquals(makerName, quilt.getMaker().getName()),
                () -> assertEquals("/uploads/" + filename, quilt.getImageUrl()),
                () -> assertFalse(quilt.isDeleted())
        );
    }

    private QuiltRequest createQuiltRequest() {
        QuiltRequest quiltRequest = new QuiltRequest();
        quiltRequest.setName(quiltName);
        quiltRequest.setPrice(price);
        quiltRequest.setSize(createSize());
        quiltRequest.setMaker(createMaker());
        return quiltRequest;
    }

    private CoverSize createSize() {
        CoverSize size = new CoverSize();
        size.setName(sizeName);
        size.setDimensions(dimensions);
        return size;
    }

    private Maker createMaker() {
        Maker maker = new Maker();
        maker.setName(makerName);
        return maker;
    }

    private Type createEntityType() {
        Type type = new Type();
        type.setName(com.dconnell.server.model.enums.Type.QUILTS.getLabel());
        return type;
    }

}