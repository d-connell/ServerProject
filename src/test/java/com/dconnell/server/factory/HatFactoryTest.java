package com.dconnell.server.factory;

import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.entity.Hat;
import com.dconnell.server.model.inventory.size.HatSize;
import com.dconnell.server.request.HatRequest;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HatFactoryTest {

    String hatName = "test hat";
    BigDecimal price = new BigDecimal(800);
    String sizeName = "size";
    String dimensions = "dimensions";
    String makerName = "Tester";
    String filename = "fileName";

    @Test
    void shouldPassWhenCreatingHat() {
        Hat hat = new HatFactory().create(createHatRequest(), filename, createEntityType());
        assertAll("hat",
                () -> assertEquals(hatName, hat.getName()),
                () -> assertEquals(price, hat.getPrice()),
                () -> assertEquals(sizeName, hat.getSize().getName()),
                () -> assertEquals(dimensions, hat.getSize().getDimensions()),
                () -> assertEquals(makerName, hat.getMaker().getName()),
                () -> assertEquals("/uploads/" + filename, hat.getImageUrl()),
                () -> assertFalse(hat.isDeleted())
        );
    }

    private HatRequest createHatRequest() {
        HatRequest hatRequest = new HatRequest();
        hatRequest.setName(hatName);
        hatRequest.setPrice(price);
        hatRequest.setSize(createSize());
        hatRequest.setMaker(createMaker());
        return hatRequest;
    }

    private HatSize createSize() {
        HatSize size = new HatSize();
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
        type.setName(com.dconnell.server.model.enums.Type.HATS.getLabel());
        return type;
    }

}