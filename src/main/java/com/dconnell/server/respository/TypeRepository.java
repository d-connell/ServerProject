package com.dconnell.server.respository;

import com.dconnell.server.model.inventory.Category;
import com.dconnell.server.model.inventory.Type;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface TypeRepository extends PagingAndSortingRepository<Type, BigInteger> {

    Type findByName(String type);

    Iterable<Type> findAllByCategory(Optional<Category> category);

}