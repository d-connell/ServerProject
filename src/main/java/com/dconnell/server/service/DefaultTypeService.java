package com.dconnell.server.service;

import com.dconnell.server.model.inventory.Category;
import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.respository.CategoryRepository;
import com.dconnell.server.respository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultTypeService implements TypeService {

    private final TypeRepository typeRepository;
    private final CategoryRepository categoryRepository;

    public DefaultTypeService(TypeRepository typeRepository, CategoryRepository categoryRepository) {
        this.typeRepository = typeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Iterable<Type> findTypesForCategory(String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);
        return typeRepository.findAllByCategory(category);
    }

}