package com.hunza.services.catererservice.controller;

import com.hunza.services.catererservice.entity.Capacity;
import com.hunza.services.catererservice.entity.Caterer;
import com.hunza.services.catererservice.entity.ContactInfo;
import com.hunza.services.catererservice.entity.Location;
import com.hunza.services.catererservice.service.CatererServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CatererController.class)
class CatererControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CatererServiceImpl catererService;

    @Test
    void saveCaterer() {

    }

    @Test
    void getCatererById() throws Exception {
        Caterer caterer = new Caterer();
        caterer.setName("Mom's Kitchen");
        caterer.setLocation(new Location("Stockholm", "Nybrogatan 40", "11440"));
        caterer.setCapacity(new Capacity(10,50));
        caterer.setContactInfo(new ContactInfo("1234567890","08-6612727", "test@mail.com"));

        when(catererService.findById(anyLong())).thenReturn(caterer);

        mvc.perform(get("/api/caterers/1234"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Mom's Kitchen")));

    }



    @Test
    void getCatererByName() throws Exception {

        Caterer caterer = new Caterer();
        caterer.setName("Mom's Kitchen");
        caterer.setLocation(new Location("Stockholm", "Nybrogatan 40", "11440"));
        caterer.setCapacity(new Capacity(10,50));
        caterer.setContactInfo(new ContactInfo("1234567890","08-6612727", "test@mail.com"));

        when(catererService.findByName(anyString())).thenReturn(caterer);

        mvc.perform(get("/api/caterers/name/Mom's Kitchen"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Mom's Kitchen")));

    }

    @Test
    void getCaterersByCityName() throws Exception {

        Caterer caterer = new Caterer();
        caterer.setName("Mom's Kitchen");
        caterer.setLocation(new Location("Stockholm", "Nybrogatan 40", "11440"));
        caterer.setCapacity(new Capacity(10,50));
        caterer.setContactInfo(new ContactInfo("1234567890","08-6612727", "test@mail.com"));

        List<Caterer> caterers = Arrays.asList(caterer);

        Map<String, Object> response = new HashMap<>();
        response.put("caterers", caterer);

        when(catererService.findByLocationCityName(anyString(), anyInt(), anyInt())).thenReturn(response);

        mvc.perform(get("/api/caterers/location/Stockholm"))
                .andExpect(status().isOk())
                .andDo(print());
               // .andExpect(jsonPath("$", Matchers.hasSize(1)))
                //.andExpect(jsonPath("$[0].name", Matchers.is("Mom's Kitchen")));

    }
}