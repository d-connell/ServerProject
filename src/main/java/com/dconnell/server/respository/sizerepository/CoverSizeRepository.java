package com.dconnell.server.respository.sizerepository;

import com.dconnell.server.model.inventory.size.CoverSize;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface CoverSizeRepository extends PagingAndSortingRepository<CoverSize, BigInteger> {
}