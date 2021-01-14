package com.dconnell.server.respository;

import com.dconnell.server.model.inventory.Category;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface CategoryRepository extends PagingAndSortingRepository<Category, BigInteger> {

    Optional<Category> findByName(String name);

}