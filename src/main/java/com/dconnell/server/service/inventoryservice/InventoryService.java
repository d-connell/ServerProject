package com.dconnell.server.service.inventoryservice;

import com.dconnell.server.model.inventory.entity.InventoryEntity;
import com.dconnell.server.request.InventoryRequest;
import com.dconnell.server.response.inventoryresponse.InventoryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Iterator;

public interface InventoryService <TYP extends InventoryEntity, REQTYP extends InventoryRequest, RESTYP extends InventoryResponse> {

    RESTYP create(String data, String imageFileName) throws IOException;

    HttpStatus update(TYP inventoryEntity) throws IOException;

    HttpStatus delete(TYP inventoryEntity) throws IOException;

    TYP readEntityData(String data) throws JsonProcessingException;

    REQTYP readRequestData(String data) throws JsonProcessingException;

    Iterable<TYP> findByDeleteStatus(boolean isDeleted);

    default Iterable<TYP> filterByDeleteStatus(Iterable<TYP> items, boolean isDeleted) {
        Iterator iterator = items.iterator();
        while (iterator.hasNext()) {
            InventoryEntity item = (InventoryEntity) iterator.next();
            if (item.isDeleted() != isDeleted) {
                iterator.remove();
            }
        }
        return items;
    }

}