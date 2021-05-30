package com.hunza.services.catererservice.repository;

import com.hunza.services.catererservice.entity.Capacity;
import com.hunza.services.catererservice.entity.Caterer;
import com.hunza.services.catererservice.entity.ContactInfo;
import com.hunza.services.catererservice.entity.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class CatererRepositoryTest {

    @Autowired
    CatererRepository catererRepository;

    @BeforeEach
    void setup() {
        Caterer caterer1 = new Caterer();
        caterer1.setId(123451l);
        caterer1.setName("Mom's Kitchen");
        caterer1.setLocation(new Location("Stockholm", "Nybrogatan 40", "11440"));
        caterer1.setCapacity(new Capacity(10,50));
        caterer1.setContactInfo(new ContactInfo("1234567890","08-6612727", "test@mail.com"));

        Caterer caterer2 = new Caterer();
        caterer2.setId(123452l);
        caterer2.setName("Coco & Carmen");
        caterer2.setLocation(new Location("Stockholm", "Banérgatan 7", "11456"));
        caterer2.setCapacity(new Capacity(5,20));
        caterer2.setContactInfo(new ContactInfo("1234567890","08-6601105", "test@mail.com"));

        catererRepository.deleteAll();
        catererRepository.save(caterer1);
        catererRepository.save(caterer2);
    }

    @AfterEach
    void clean() {
        catererRepository.deleteAll();
    }

    @Test
    public void testFindAllCaterers(){
        List<Caterer> caterers =  catererRepository.findAll();
        assertEquals(2, caterers.size());
    }

    @Test
    public void testFindById(){
        Optional<Caterer> caterers =  catererRepository.findById(123451l);
        assertEquals(123451l, caterers.get().getId());
    }

    @Test
    public void testFindByName(){
        Optional<Caterer> caterers =  catererRepository.findByName("Mom's Kitchen");
        assertEquals("Mom's Kitchen", caterers.get().getName());
    }

    @Test
    public void testFindByCityName(){
        Page<Caterer> caterersPage =  catererRepository.findByLocationCityName("Stockholm", PageRequest.of(0,1));
        assertEquals(2, caterersPage.getTotalElements());
    }

    @Test
    public void testFindByEmptyCityName(){
        Page<Caterer> caterersPage =  catererRepository.findByLocationCityName("",PageRequest.of(0,2));
        assertEquals(0, caterersPage.getTotalElements());
    }

}