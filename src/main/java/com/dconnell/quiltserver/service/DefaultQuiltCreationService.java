package com.dconnell.quiltserver.service;

import com.dconnell.quiltserver.factory.QuiltFactory;
import com.dconnell.quiltserver.model.Quilt;
import com.dconnell.quiltserver.response.NewQuiltResponse;
import com.dconnell.quiltserver.respository.QuiltRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class DefaultQuiltCreationService implements QuiltCreationService {

    @Autowired
    QuiltRepository quiltRepository;

    @Autowired
    QuiltFactory quiltFactory;

    @Transactional
    @Override
    public NewQuiltResponse create(String name, String size, BigDecimal price) {
        Quilt newQuilt = quiltRepository.save(quiltFactory.create(name, size, price));
        return new NewQuiltResponse(newQuilt, HttpStatus.OK);
    }

}
