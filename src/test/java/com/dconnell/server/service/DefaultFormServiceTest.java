package com.dconnell.server.service;

import com.dconnell.server.model.enums.Type;
import com.dconnell.server.respository.MakerRepository;
import com.dconnell.server.respository.sizerepository.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultFormServiceTest {

    MakerRepository mockedMakerRepository = mock(MakerRepository.class);
    CoverSizeRepository mockedCoverSizeRepository = mock(CoverSizeRepository.class);
    BagSizeRepository mockedBagSizeRepository = mock(BagSizeRepository.class);
    HatSizeRepository mockedHatSizeRepository = mock(HatSizeRepository.class);
    FormService formService = new DefaultFormService(mockedMakerRepository, mockedBagSizeRepository,
            mockedCoverSizeRepository, mockedHatSizeRepository);

    @Test
    void shouldPassWhenGettingQuiltFormOptions() throws NullPointerException {
        formService.findFormOptions(Type.QUILTS.getLabel());
        verify(mockedCoverSizeRepository).findAll();
        verify(mockedMakerRepository).findAll();
    }

    @Test
    void shouldPassWhenGettingBagFormOptions() throws NullPointerException {
        formService.findFormOptions(Type.BAGS.getLabel());
        verify(mockedBagSizeRepository).findAll();
        verify(mockedMakerRepository).findAll();
    }

    @Test
    void shouldThrowErrorWhenUnknownTypeRequested() {
        assertThrows(NullPointerException.class, this::attemptToGetFormOptionsForUnknownType);
    }

    private void attemptToGetFormOptionsForUnknownType() throws NullPointerException {
        formService.findFormOptions("gibberish");
    }

}