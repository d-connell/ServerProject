package com.dconnell.server.controller;

import com.dconnell.server.controller.controllertools.AdminAction;
import com.dconnell.server.controller.controllertools.ServiceFinder;
import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.respository.TypeRepository;
import com.dconnell.server.service.inventoryservice.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ServiceFinder serviceFinder;
    private final TypeRepository typeRepository;

    public AdminController(ServiceFinder serviceFinder, TypeRepository typeRepository) {
        this.serviceFinder = serviceFinder;
        this.typeRepository = typeRepository;
    }

    @GetMapping
    public List<AdminAction> getAdminActions() {
        List<AdminAction> actions = new ArrayList<>();
        actions.add(new AdminAction("Review deletions", "deletions"));
        return actions;
    }

    @GetMapping(path = "/types")
    public Iterable<Type> getTypes() {
        return typeRepository.findAll();
    }

    @GetMapping(path = "/deletions")
    public Iterable getAllDeleted(@RequestParam String type) {
        InventoryService service = serviceFinder.findService(type);
        return service.findByDeleteStatus(true);
    }

}