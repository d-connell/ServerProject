package com.dconnell.quiltserver.service;

import com.dconnell.quiltserver.response.NewQuiltResponse;
import com.dconnell.quiltserver.response.RemoveQuiltResponse;

import java.math.BigDecimal;

public interface InventoryAmendmentService {

    NewQuiltResponse create(String name, String size, BigDecimal price);

    RemoveQuiltResponse remove(String name);

}
