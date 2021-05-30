package com.hunza.services.catererservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hunza.services.catererservice.entity.Capacity;
import com.hunza.services.catererservice.entity.Caterer;
import com.hunza.services.catererservice.entity.ContactInfo;
import com.hunza.services.catererservice.entity.Location;
import com.hunza.services.catererservice.exception.CatererNotFoundException;
import com.hunza.services.catererservice.repository.CatererRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatererServiceImplTest {

    @InjectMocks
    private CatererServiceImpl catererService;

    @Mock
    private CatererRepository catererRepository;

    @Mock
    private MongoOperations mongoOperations;
    @Mock
    private KafkaMsgSendServiceImpl kafkaMsgSendService;

    @Test
    void saveCaterer() throws JsonProcessingException {
        Caterer caterer = new Caterer();
        caterer.setName("Mom's Kitchen");
        caterer.setLocation(new Location("Stockholm", "Nybrogatan 40", "11440"));
        caterer.setCapacity(new Capacity(10,50));
        caterer.setContactInfo(new ContactInfo("1234567890","08-6612727", "test@mail.com"));

        when(catererRepository.save(any())).thenReturn(caterer);

        Caterer catererSaved = catererService.saveCaterer(caterer);
        assertEquals(caterer.getName(), catererSaved.getName());
        verify(catererRepository, times(1)).save(caterer);
    }

    @Test
    void findById() {
        Caterer caterer = new Caterer();
        caterer.setName("Mom's Kitchen");
        caterer.setLocation(new Location("Stockholm", "Nybrogatan 40", "11440"));
        caterer.setCapacity(new Capacity(10,50));
        caterer.setContactInfo(new ContactInfo("1234567890","08-6612727", "test@mail.com"));

        when(catererRepository.findById(any())).thenReturn(Optional.of(caterer));

        Caterer catererDb = catererService.findById(1l);
        assertEquals(caterer.getName(), catererDb.getName());
        verify(catererRepository, times(1)).findById(1l);
    }

    @Test
    void findByIdNotFound() {
        Optional<Caterer> recipeOptional = Optional.empty();
        when(catererRepository.findById(anyLong())).thenReturn(recipeOptional);
        Assertions.assertThrows(CatererNotFoundException.class, () -> {
            catererService.findById(123l);
        });
    }

    @Test
    void findByName() {
        Caterer caterer = new Caterer();
        caterer.setName("Mom's Kitchen");
        caterer.setLocation(new Location("Stockholm", "Nybrogatan 40", "11440"));
        caterer.setCapacity(new Capacity(10,50));
        caterer.setContactInfo(new ContactInfo("1234567890","08-6612727", "test@mail.com"));

        when(catererRepository.findByName(any())).thenReturn(Optional.of(caterer));

        Caterer catererDb = catererService.findByName("Mom's Kitchen");
        assertEquals(caterer.getName(), catererDb.getName());
        verify(catererRepository, times(1)).findByName("Mom's Kitchen");
    }

    @Test
    void findByNameNotFound() {
        Optional<Caterer> recipeOptional = Optional.empty();
        when(catererRepository.findByName(anyString())).thenReturn(recipeOptional);
        Assertions.assertThrows(CatererNotFoundException.class, () -> {
            catererService.findByName("Test");
        });
    }

    @Test
    void getCaterersByCityName() {
        Caterer caterer = new Caterer();
        caterer.setName("Mom's Kitchen");
        caterer.setLocation(new Location("Stockholm", "Nybrogatan 40", "11440"));
        caterer.setCapacity(new Capacity(10,50));
        caterer.setContactInfo(new ContactInfo("1234567890","08-6612727", "test@mail.com"));

        List<Caterer> list = new ArrayList<>();
        list.add(caterer);
        Page<Caterer> pagedCaterers = new PageImpl(list);

        when(catererRepository.findByLocationCityName(any(),any())).thenReturn(pagedCaterers);

        Map<String, Object> response = catererService.findByLocationCityName("Stockholm", 0, 5);
        List<Caterer> caterers = (List<Caterer>)response.get("caterers");
        assertEquals(1, caterers.size());
        verify(catererRepository, times(1)).findByLocationCityName("Stockholm", PageRequest.of(0, 5));
    }

    @Test
    void getCaterersByCityNameEmptyResult() {
        Page<Caterer> pagedCaterers = new PageImpl(new ArrayList<>());
        when(catererRepository.findByLocationCityName(any(), any())).thenReturn(pagedCaterers);
        Assertions.assertThrows(CatererNotFoundException.class, () -> {
            catererService.findByLocationCityName("Test",0,5);
        });
    }

}
