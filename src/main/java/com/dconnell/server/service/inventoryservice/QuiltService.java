package com.dconnell.server.service.inventoryservice;

import com.dconnell.server.factory.QuiltFactory;
import com.dconnell.server.model.enums.Type;
import com.dconnell.server.model.inventory.entity.Quilt;
import com.dconnell.server.request.QuiltRequest;
import com.dconnell.server.response.inventoryresponse.QuiltResponse;
import com.dconnell.server.respository.TypeRepository;
import com.dconnell.server.respository.inventoryrepository.QuiltRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class QuiltService implements InventoryService<Quilt, QuiltRequest, QuiltResponse> {

    final TypeRepository typeRepository;
    final QuiltRepository quiltRepository;
    final QuiltFactory quiltFactory;

    public QuiltService(TypeRepository typeRepository,
                        QuiltRepository quiltRepository, QuiltFactory quiltFactory) {
        this.typeRepository = typeRepository;
        this.quiltRepository = quiltRepository;
        this.quiltFactory = quiltFactory;
    }

    @Transactional
    @Override
    public QuiltResponse create(String data, String imageFileName) throws JsonProcessingException {
        QuiltRequest quiltRequest = readRequestData(data);
        Quilt newQuilt = quiltFactory.create(quiltRequest, imageFileName, typeRepository.findByName(Type.QUILTS.getLabel()));
        return new QuiltResponse(quiltRepository.save(newQuilt), HttpStatus.OK);
    }

    @Transactional
    @Override
    public HttpStatus update(Quilt quilt) {
        quiltRepository.save(quilt);
        return HttpStatus.OK;
    }

    @Transactional
    @Override
    public HttpStatus delete(Quilt quilt) {
        quiltRepository.deleteById(quilt.getId());
        return HttpStatus.OK;
    }

    @Override
    public Quilt readEntityData(String data) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, Quilt.class);
    }

    @Override
    public QuiltRequest readRequestData(String data) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, QuiltRequest.class);
    }

    @Override
    public Iterable<Quilt> findByDeleteStatus(boolean isDeleted) {
        Iterable<Quilt> quilts = quiltRepository.findAll();
        return filterByDeleteStatus(quilts, isDeleted);
    }

}