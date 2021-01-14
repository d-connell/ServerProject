package com.dconnell.server.service.inventoryservice;

import com.dconnell.server.factory.BlanketFactory;
import com.dconnell.server.model.enums.Type;
import com.dconnell.server.model.inventory.entity.Blanket;
import com.dconnell.server.request.BlanketRequest;
import com.dconnell.server.response.inventoryresponse.BlanketResponse;
import com.dconnell.server.respository.TypeRepository;
import com.dconnell.server.respository.inventoryrepository.BlanketRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BlanketService implements InventoryService<Blanket, BlanketRequest, BlanketResponse> {

    final TypeRepository typeRepository;
    final BlanketRepository blanketRepository;
    final BlanketFactory blanketFactory;

    public BlanketService(TypeRepository typeRepository,
                          BlanketRepository blanketRepository, BlanketFactory blanketFactory) {
        this.typeRepository = typeRepository;
        this.blanketRepository = blanketRepository;
        this.blanketFactory = blanketFactory;
    }

    @Transactional
    @Override
    public BlanketResponse create(String data, String fileName) throws JsonProcessingException {
        BlanketRequest blanketRequest = readRequestData(data);
        Blanket blanket = blanketFactory.create(blanketRequest, fileName, typeRepository.findByName(Type.BLANKETS.getLabel()));
        return new BlanketResponse(blanketRepository.save(blanket), HttpStatus.OK);
    }

    @Transactional
    @Override
    public HttpStatus update(Blanket blanket) {
        blanketRepository.save(blanket);
        return HttpStatus.OK;
    }

    @Transactional
    @Override
    public HttpStatus delete(Blanket blanket) {
        blanketRepository.deleteById(blanket.getId());
        return HttpStatus.OK;
    }

    @Override
    public Blanket readEntityData(String data) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, Blanket.class);
    }

    @Override
    public BlanketRequest readRequestData(String data) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, BlanketRequest.class);
    }

    @Override
    public Iterable<Blanket> findByDeleteStatus(boolean isDeleted) {
        Iterable<Blanket> blankets = blanketRepository.findAll();
        return filterByDeleteStatus(blankets, isDeleted);
    }

}