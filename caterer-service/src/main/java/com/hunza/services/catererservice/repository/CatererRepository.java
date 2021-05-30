package com.hunza.services.catererservice.repository;

import com.hunza.services.catererservice.entity.Caterer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatererRepository extends MongoRepository<Caterer, Long> {

    Optional<Caterer> findByName(String name);

    Page<Caterer> findByLocationCityName(String cityName, Pageable pageable);

}
