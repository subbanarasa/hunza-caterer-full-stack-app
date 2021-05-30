package com.hunza.services.catererservice.controller;

import com.hunza.services.catererservice.entity.Caterer;
import com.hunza.services.catererservice.service.CatererService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/caterers")
@Slf4j
@CrossOrigin("*")
public class CatererController {

    private CatererService catererService;

    public CatererController(CatererService catererService) {
        this.catererService = catererService;
    }

    @ApiOperation(value = "Save Caterers")
    @PostMapping
    public ResponseEntity<?> saveCaterer(@Valid  @RequestBody Caterer caterer) throws Exception {
        log.info("Saving Caterer request :"+ caterer);
        Caterer savedCaterer = catererService.saveCaterer(caterer);
        return new ResponseEntity<>(savedCaterer, HttpStatus.OK);
    }

    @ApiOperation(value = "Fetching the Caterers based on the Caterer Id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCatererById(@PathVariable Long id) {
        log.info("Get Caterer by Id :"+ id);
        Caterer caterer = catererService.findById(id);
        return new ResponseEntity<>(caterer, HttpStatus.OK);
    }

    @ApiOperation(value = "Fetching the Caterers based on the Caterer Name")
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getCatererByName(@PathVariable String name) {
        log.info("Get Caterer by Name :"+ name);
        Caterer caterer = catererService.findByName(name);
        return new ResponseEntity<>(caterer, HttpStatus.OK);

    }

    @ApiOperation(value = "Fetching the Caterers based on the City Name")
    @GetMapping("/location/{city-name}")
    public ResponseEntity<?> getCaterersByCityName(@PathVariable(name="city-name") String cityName, @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size) {
        log.info("Get Caterer List by City Name :"+ cityName);
        Map<String, Object> response = catererService.findByLocationCityName(cityName, page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
