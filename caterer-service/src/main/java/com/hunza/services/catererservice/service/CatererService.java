package com.hunza.services.catererservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hunza.services.catererservice.entity.Caterer;

import java.util.List;
import java.util.Map;

public interface CatererService {


    Caterer saveCaterer(Caterer caterer) throws JsonProcessingException;

    Caterer findById(Long id);

    Caterer findByName(String name);

    Map<String, Object> findByLocationCityName(String cityName, int page, int size);

}
