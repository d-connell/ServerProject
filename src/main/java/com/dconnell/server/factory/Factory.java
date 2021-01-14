package com.dconnell.server.factory;

import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.entity.InventoryEntity;
import com.dconnell.server.request.InventoryRequest;

public interface Factory <TYP extends InventoryEntity, REQTYP extends InventoryRequest> {

    TYP create(REQTYP request, String fileName, Type type);

    default InventoryEntity setValues(InventoryEntity item, InventoryRequest request, Type type, String fileName) {
        item.setName(request.getName());
        item.setType(type);
        item.setPrice(request.getPrice());
        item.setMaker(request.getMaker());
        item.setImageUrl("/uploads/" + fileName);
        item.setDeleted(false);
        return item;
    }

}