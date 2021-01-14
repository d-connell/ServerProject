package com.dconnell.server.controller;

import com.dconnell.server.controller.controllertools.ServiceFinder;
import com.dconnell.server.model.enums.Type;
import com.dconnell.server.model.inventory.Category;
import com.dconnell.server.respository.CategoryRepository;
import com.dconnell.server.respository.inventoryrepository.BagRepository;
import com.dconnell.server.respository.inventoryrepository.BlanketRepository;
import com.dconnell.server.respository.inventoryrepository.HatRepository;
import com.dconnell.server.respository.inventoryrepository.QuiltRepository;
import com.dconnell.server.service.TypeService;
import com.dconnell.server.service.inventoryservice.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("/view")
public class ViewController {

    private final ServiceFinder serviceFinder;
    private final CategoryRepository categoryRepository;
    private final TypeService typeService;
    private final BagRepository bagRepository;
    private final BlanketRepository blanketRepository;
    private final HatRepository hatRepository;
    private final QuiltRepository quiltRepository;

    public ViewController(ServiceFinder serviceFinder,
                          CategoryRepository categoryRepository, TypeService typeService,
                          BagRepository bagRepository, BlanketRepository blanketRepository,
                          HatRepository hatRepository, QuiltRepository quiltRepository) {
        this.serviceFinder = serviceFinder;
        this.categoryRepository = categoryRepository;
        this.typeService = typeService;
        this.bagRepository = bagRepository;
        this.blanketRepository = blanketRepository;
        this.hatRepository = hatRepository;
        this.quiltRepository = quiltRepository;
    }

    @GetMapping
    public Iterable<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/category")
    public Iterable<com.dconnell.server.model.inventory.Type> getTypesForCategory(@RequestParam String categoryName) {
        return typeService.findTypesForCategory(categoryName);
    }

    @GetMapping(path = "/type")
    public Iterable getAllItemsForType(@RequestParam String typeName) {
        InventoryService service = serviceFinder.findService(typeName);
        return service.findByDeleteStatus(false);
    }

    @GetMapping(path = "/details/{typeLabel}")
    public Optional getDetails(@PathVariable String typeLabel, @RequestParam String id)
            throws NullPointerException {
        Type type = Type.findType(typeLabel);
        switch (type) {
            case BAGS: {
                return bagRepository.findById(new BigInteger(id));
            }
            case BLANKETS: {
                return blanketRepository.findById(new BigInteger(id));
            }
            case HATS: {
                return hatRepository.findById(new BigInteger(id));
            }
            case QUILTS: {
                return quiltRepository.findById(new BigInteger(id));
            }
            default: {
                throw new NullPointerException("Type " + type + "is not recognised, item details could mot be retrieved.");
            }
        }
    }

}