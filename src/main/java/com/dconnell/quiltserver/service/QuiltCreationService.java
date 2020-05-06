package com.dconnell.quiltserver.service;

import com.dconnell.quiltserver.response.NewQuiltResponse;
import java.math.BigDecimal;

public interface QuiltCreationService {

    NewQuiltResponse create(String name, String size, BigDecimal price);

}
