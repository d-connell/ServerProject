package com.dconnell.server.service;

import com.dconnell.server.model.enums.Type;
import com.dconnell.server.response.formoptionsresponse.*;
import com.dconnell.server.respository.MakerRepository;
import com.dconnell.server.respository.sizerepository.BagSizeRepository;
import com.dconnell.server.respository.sizerepository.CoverSizeRepository;
import com.dconnell.server.respository.sizerepository.HatSizeRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultFormService implements FormService {

    private final MakerRepository makerRepository;
    private final BagSizeRepository bagSizeRepository;
    private final CoverSizeRepository coverSizeRepository;
    private final HatSizeRepository hatSizeRepository;

    public DefaultFormService(MakerRepository makerRepository,
                              BagSizeRepository bagSizeRepository, CoverSizeRepository coverSizeRepository,
                              HatSizeRepository hatSizeRepository) {
        this.makerRepository = makerRepository;
        this.bagSizeRepository = bagSizeRepository;
        this.coverSizeRepository = coverSizeRepository;
        this.hatSizeRepository = hatSizeRepository;
    }

    @Override
    public FormOptionsResponse findFormOptions(String typeLabel) throws NullPointerException {
        switch (Type.findType(typeLabel)) {
            case BAGS: {
                return new BagFormOptionsResponse(bagSizeRepository.findAll(), makerRepository.findAll());
            }
            case BLANKETS:{
                return new QuiltFormOptionsResponse(coverSizeRepository.findAll(), makerRepository.findAll());
            }
            case HATS: {
                return new HatFormOptionsResponse(hatSizeRepository.findAll(), makerRepository.findAll());
            }
            case QUILTS: {
                return new BlanketFormOptionsResponse(coverSizeRepository.findAll(), makerRepository.findAll());
            }
            default: {
                throw new NullPointerException("Type " + typeLabel + " is not recognised, cannot provide form options.");
            }
        }
    }

}