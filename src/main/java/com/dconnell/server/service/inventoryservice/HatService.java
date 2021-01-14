package com.dconnell.server.service.inventoryservice;

import com.dconnell.server.factory.HatFactory;
import com.dconnell.server.model.enums.Type;
import com.dconnell.server.model.inventory.entity.Hat;
import com.dconnell.server.request.HatRequest;
import com.dconnell.server.response.inventoryresponse.HatResponse;
import com.dconnell.server.respository.TypeRepository;
import com.dconnell.server.respository.inventoryrepository.HatRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class HatService implements InventoryService<Hat, HatRequest, HatResponse> {

    final TypeRepository typeRepository;
    final HatRepository hatRepository;
    final HatFactory hatFactory;

    public HatService(TypeRepository typeRepository,
                      HatRepository hatRepository, HatFactory hatFactory) {
        this.typeRepository = typeRepository;
        this.hatRepository = hatRepository;
        this.hatFactory = hatFactory;
    }

    @Transactional
    @Override
    public HatResponse create(String data, String imageFileName) throws JsonProcessingException {
        HatRequest hatRequest = readRequestData(data);
        Hat newHat = hatFactory.create(hatRequest, imageFileName, typeRepository.findByName(Type.HATS.getLabel()));
        return new HatResponse(hatRepository.save(newHat), HttpStatus.OK);
    }

    @Transactional
    @Override
    public HttpStatus update(Hat hat) {
        hatRepository.save(hat);
        return HttpStatus.OK;
    }

    @Transactional
    @Override
    public HttpStatus delete(Hat hat) {
        hatRepository.deleteById(hat.getId());
        return HttpStatus.OK;
    }

    @Override
    public Hat readEntityData(String data) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, Hat.class);
    }

    @Override
    public HatRequest readRequestData(String data) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, HatRequest.class);
    }

    @Override
    public Iterable<Hat> findByDeleteStatus(boolean isDeleted) {
        Iterable<Hat> hats = hatRepository.findAll();
        return filterByDeleteStatus(hats, isDeleted);
    }

}