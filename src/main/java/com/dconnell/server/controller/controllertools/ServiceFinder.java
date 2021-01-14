package com.dconnell.server.controller.controllertools;

import com.dconnell.server.model.enums.Type;
import com.dconnell.server.service.inventoryservice.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ServiceFinder {

    private final HashMap<String, InventoryService> services = new HashMap<>();

    public ServiceFinder(BagService bagService, BlanketService blanketService,
                         HatService hatService, QuiltService quiltService) {
        services.put(Type.BAGS.getLabel(), bagService);
        services.put(Type.BLANKETS.getLabel(), blanketService);
        services.put(Type.HATS.getLabel(), hatService);
        services.put(Type.QUILTS.getLabel(), quiltService);
    }

    public InventoryService findService(String typeLabel) throws NullPointerException {
        if (!services.containsKey(typeLabel)) {
            throw new NullPointerException("Type " + typeLabel + " is not recognised.");
        }
        return services.get(typeLabel);
    }

}