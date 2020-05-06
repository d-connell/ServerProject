package com.dconnell.quiltserver.factory;

import com.dconnell.quiltserver.model.Quilt;

import java.math.BigDecimal;

public interface QuiltFactory {

    Quilt create(String name, String size, BigDecimal price);

}