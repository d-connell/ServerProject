package com.dconnell.server.factory;

import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.entity.Blanket;
import com.dconnell.server.model.inventory.size.CoverSize;
import com.dconnell.server.request.BlanketRequest;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BlanketFactoryTest {

    String hatName = "test blanket";
    BigDecimal price = new BigDecimal(800);
    String sizeName = "size";
    String dimensions = "dimensions";
    String makerName = "Tester";
    String filename = "fileName";

    @Test
    void shouldPassWhenCreatingBlanket() {
        Blanket blanket = new BlanketFactory().create(createBlanketRequest(), filename, createEntityType());
        assertAll("blanket",
                () -> assertEquals(hatName, blanket.getName()),
                () -> assertEquals(price, blanket.getPrice()),
                () -> assertEquals(sizeName, blanket.getSize().getName()),
                () -> assertEquals(dimensions, blanket.getSize().getDimensions()),
                () -> assertEquals(makerName, blanket.getMaker().getName()),
                () -> assertEquals("/uploads/" + filename, blanket.getImageUrl()),
                () -> assertFalse(blanket.isDeleted())
        );
    }

    private BlanketRequest createBlanketRequest() {
        BlanketRequest blanketRequest = new BlanketRequest();
        blanketRequest.setName(hatName);
        blanketRequest.setPrice(price);
        blanketRequest.setSize(createSize());
        blanketRequest.setMaker(createMaker());
        return blanketRequest;
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
        type.setName(com.dconnell.server.model.enums.Type.BLANKETS.getLabel());
        return type;
    }

}