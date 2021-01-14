package com.dconnell.server.service.inventoryservice;

import com.dconnell.server.factory.BagFactory;
import com.dconnell.server.model.enums.Type;
import com.dconnell.server.model.inventory.entity.Bag;
import com.dconnell.server.request.BagRequest;
import com.dconnell.server.response.inventoryresponse.BagResponse;
import com.dconnell.server.respository.TypeRepository;
import com.dconnell.server.respository.inventoryrepository.BagRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BagService implements InventoryService<Bag, BagRequest, BagResponse> {

    final TypeRepository typeRepository;
    final BagRepository bagRepository;
    final BagFactory bagFactory;

    public BagService(TypeRepository typeRepository,
                      BagRepository bagRepository, BagFactory bagFactory) {
        this.typeRepository = typeRepository;
        this.bagRepository = bagRepository;
        this.bagFactory = bagFactory;
    }

    @Transactional
    @Override
    public BagResponse create(String data, String fileName) throws JsonProcessingException {
        BagRequest bagRequest = readRequestData(data);
        Bag newBag = bagFactory.create(bagRequest, fileName, typeRepository.findByName(Type.BAGS.getLabel()));
        return new BagResponse(bagRepository.save(newBag), HttpStatus.OK);
    }

    @Transactional
    @Override
    public HttpStatus update(Bag bag) {
        bagRepository.save(bag);
        return HttpStatus.OK;
    }

    @Transactional
    @Override
    public HttpStatus delete(Bag bag) {
        bagRepository.deleteById(bag.getId());
        return HttpStatus.OK;
    }

    @Override
    public Bag readEntityData(String data) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, Bag.class);
    }

    @Override
    public BagRequest readRequestData(String data) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, BagRequest.class);
    }

    @Override
    public Iterable<Bag> findByDeleteStatus(boolean isDeleted) {
        Iterable<Bag> bags = bagRepository.findAll();
        return filterByDeleteStatus(bags, isDeleted);
    }

}