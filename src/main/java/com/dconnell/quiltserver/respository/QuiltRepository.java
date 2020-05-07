package com.dconnell.quiltserver.respository;

import com.dconnell.quiltserver.model.Quilt;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface QuiltRepository extends PagingAndSortingRepository<Quilt, BigInteger> {

    List<Quilt> findAll();

    boolean existsByName(String name);

    Quilt findByName(String name);

}