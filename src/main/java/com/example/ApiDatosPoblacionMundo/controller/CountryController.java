package com.example.ApiDatosPoblacionMundo.controller;

import com.example.ApiDatosPoblacionMundo.domain.dto.CountryDto;
import com.example.ApiDatosPoblacionMundo.domain.raw.CountryRaw;
import com.example.ApiDatosPoblacionMundo.domain.entities.CountryEntity;
import com.example.ApiDatosPoblacionMundo.mappers.Mapper;
import com.example.ApiDatosPoblacionMundo.services.FillDbService;
import com.example.ApiDatosPoblacionMundo.services.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/data/country")
public class CountryController {
    private final Mapper<CountryEntity, CountryDto> countryMapper;
    private CountryService countryService;
    private FillDbService fillDbService;

    public CountryController(final CountryService countryService,
                             final Mapper<CountryEntity, CountryDto> countryMapper,
                             final FillDbService fillDbService) {
        this.countryMapper=countryMapper;
        this.countryService = countryService;
        this.fillDbService=fillDbService;
    }

    //ENDPOINT POST

    @PostMapping
    public ResponseEntity<CountryDto> createUpdateCountry (@RequestBody CountryDto countryDto) {

        //Convertimos el dto en entity con nuestro mapper
        CountryEntity countryEntity = countryMapper.mapFrom(countryDto);

        //Comprobamos si el dato ya existe para actualizarlo usando la Id
        if (countryService.existInDb(countryDto.getId())) {

            //Aseguramos que tenga la misma id y ahora se sobreescribe.
            countryEntity.setId(countryDto.getId());
            countryService.save(countryEntity);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }else {

            //Guardamos el objeto en la base de datos y lo recuperamos para pasarlo a dto
            CountryEntity savedCountryEntity = countryService.save(countryEntity);
            savedCountryEntity.setId(1);
            System.out.println(savedCountryEntity.getId());

            return new ResponseEntity<>(countryMapper.mapTo(savedCountryEntity), HttpStatus.CREATED);
        }

    }

    //Get endpoint
    @GetMapping
    public ResponseEntity<List<CountryDto>> getAllCountries () {

        //Si la base de datos esta vacía la rellenamos usando la Api Rest proporcionada
        ResponseEntity<List<CountryDto>> response = fillDbService.fillDataBase();

        List<CountryDto> countryDtosList = countryService.getCountries().stream().map(countryMapper::mapTo).toList();
        return new ResponseEntity<>(countryDtosList,response.getStatusCode());

    }

}
