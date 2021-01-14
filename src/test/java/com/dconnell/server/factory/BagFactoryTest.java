package com.dconnell.server.factory;

import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.entity.Bag;
import com.dconnell.server.model.inventory.size.BagSize;
import com.dconnell.server.request.BagRequest;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BagFactoryTest {

    String bagName = "test bag";
    BigDecimal price = new BigDecimal(800);
    String sizeName = "size";
    String dimensions = "dimensions";
    String makerName = "Tester";
    String filename = "fileName";

    @Test
    void shouldPassWhenCreatingBag() {
        Bag bag = new BagFactory().create(createBagRequest(), filename, createEntityType());
        assertAll("bag",
                () -> assertEquals(bagName, bag.getName()),
                () -> assertEquals(price, bag.getPrice()),
                () -> assertEquals(sizeName, bag.getSize().getName()),
                () -> assertEquals(dimensions, bag.getSize().getDimensions()),
                () -> assertEquals(makerName, bag.getMaker().getName()),
                () -> assertEquals("/uploads/" + filename, bag.getImageUrl()),
                () -> assertFalse(bag.isDeleted())
        );
    }

    private BagRequest createBagRequest() {
        BagRequest bagRequest = new BagRequest();
        bagRequest.setName(bagName);
        bagRequest.setPrice(price);
        bagRequest.setSize(createSize());
        bagRequest.setMaker(createMaker());
        return bagRequest;
    }

    private BagSize createSize() {
        BagSize size = new BagSize();
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
        type.setName(com.dconnell.server.model.enums.Type.BAGS.getLabel());
        return type;
    }

}