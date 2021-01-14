package com.dconnell.server.service;

import com.dconnell.server.model.inventory.Type;

public interface TypeService {

    Iterable<Type> findTypesForCategory(String categoryName);

}