package com.example.ApiDatosPoblacionMundo.services.impl;


import com.example.ApiDatosPoblacionMundo.domain.dto.CountryDto;
import com.example.ApiDatosPoblacionMundo.domain.entities.CountryEntity;
import com.example.ApiDatosPoblacionMundo.domain.raw.CountryRaw;
import com.example.ApiDatosPoblacionMundo.mappers.Mapper;
import com.example.ApiDatosPoblacionMundo.repository.CountryRepository;
import com.example.ApiDatosPoblacionMundo.services.CountryService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    public CountryServiceImpl( CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public int isDbEmpty() {
        return countryRepository.countRows();
    }

    @Override
    public List<CountryEntity> getCountries() {
       List<CountryEntity> countryEntityList = StreamSupport.stream(countryRepository
               .findAll()
               .spliterator(),
               false)
               .toList();
       return countryEntityList;
    }

    @Override
    public CountryEntity save(CountryEntity countryEntity) {

        return countryRepository.save(countryEntity);
    }

    public boolean existInDb (int id) {
        return countryRepository.existsById(id);
    }


}
