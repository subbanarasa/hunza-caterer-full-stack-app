package com.hunza.services.catererservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hunza.services.catererservice.entity.Caterer;
import com.hunza.services.catererservice.entity.DbSequence;
import com.hunza.services.catererservice.exception.CatererAlreadyExistsException;
import com.hunza.services.catererservice.exception.CatererNotFoundException;
import com.hunza.services.catererservice.repository.CatererRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Slf4j
@Service
public class CatererServiceImpl implements CatererService{

    private MongoOperations mongoOperations;

    private CatererRepository catererRepository;

    private KafkaMsgSendService kafkaMsgSendService;

    @Value("${spring.kafka.producer.topic:caterer-topic}")
    private String topicName;

    public CatererServiceImpl(MongoOperations mongoOperations, CatererRepository catererRepository, KafkaMsgSendService kafkaMsgSendService) {
        this.mongoOperations = mongoOperations;
        this.catererRepository = catererRepository;
        this.kafkaMsgSendService = kafkaMsgSendService;
    }

    @Override
    public Caterer saveCaterer(Caterer caterer) throws JsonProcessingException {
        Optional<Caterer> catererOptional = catererRepository.findByName(caterer.getName());
        if (catererOptional.isPresent()){
            throw new CatererAlreadyExistsException("Caterer is already exists. For Name: " + caterer.getName());
        }
        caterer.setId(getSequenceNumber(Caterer.SEQUENCE_NAME));
        log.info("Saving caterer - {}", caterer);
        Caterer savedCaterer = catererRepository.save(caterer);
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaMsgSendService.sendMessage(topicName, objectMapper.writeValueAsString(savedCaterer));
        return savedCaterer;
    }

    @Override
    @Cacheable(value = "caterer", key = "#id")
    public Caterer findById(Long id) {
        log.info("Find Caterer by Id {}", id);
        Optional<Caterer> catererOptional =  catererRepository.findById(id);
        if (!catererOptional.isPresent()){
            throw new CatererNotFoundException("Caterer Not Found. For ID value: " + id);
        }
        return catererOptional.get();
    }

    @Override
    @Cacheable(value = "caterer", key = "#name")
    public Caterer findByName(String name) {
        log.info("Find Caterer by Name {}", name);
        Optional<Caterer> catererOptional =  catererRepository.findByName(name);
        if (!catererOptional.isPresent()){
            throw new CatererNotFoundException("Caterer Not Found. For Name value: " + name);
        }
        return catererOptional.get();
    }

    @Override
    public Map<String, Object> findByLocationCityName(String cityName, int page, int size) {
        Page<Caterer> caterersPage =  catererRepository.findByLocationCityName(cityName, PageRequest.of(page,size));
        if (caterersPage.isEmpty()) {
            throw new CatererNotFoundException("Caterers Not Found. For city name value: " + cityName);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("caterers", caterersPage.getContent());
        response.put("total", caterersPage.getTotalElements());
        response.put("currentPage", caterersPage.getNumber());
        response.put("totalPages", caterersPage.getTotalPages());
        log.info("Caterers by CityName::"+response);
        return response;
    }

    public Long getSequenceNumber(String sequenceName) {
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq", 1);
        DbSequence counter = mongoOperations.findAndModify(query, update,
                options().returnNew(true).upsert(true), DbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
