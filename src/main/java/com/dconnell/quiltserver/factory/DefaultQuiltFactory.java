package com.dconnell.quiltserver.factory;

import com.dconnell.quiltserver.model.Quilt;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DefaultQuiltFactory implements QuiltFactory {

    @Override
    public Quilt create(String name, String size, BigDecimal price) {
        return new Quilt(name, size, price);
    }

}