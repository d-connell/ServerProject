package com.dconnell.server.controller.controllertools;

import com.dconnell.server.model.enums.Type;
import com.dconnell.server.service.inventoryservice.*;
import org.springframework.stereotype.Component;

@Component
public class ServiceFinder {

    private final BagService bagService;
    private final BlanketService blanketService;
    private final HatService hatService;
    private final QuiltService quiltService;

    public ServiceFinder(BagService bagService, BlanketService blanketService,
                         HatService hatService, QuiltService quiltService) {
        this.bagService = bagService;
        this.blanketService = blanketService;
        this.hatService = hatService;
        this.quiltService = quiltService;
    }

    public InventoryService findService(String typeLabel) throws NullPointerException {
        switch (Type.findService(typeLabel)) {
            case BAGS: {
                return bagService;
            }
            case BLANKETS: {
                return blanketService;
            }
            case HATS: {
                return hatService;
            }
            case QUILTS: {
                return quiltService;
            }
            default: {
                throw new NullPointerException("Type " +  typeLabel + " is not recognised.");
            }
        }
    }

}