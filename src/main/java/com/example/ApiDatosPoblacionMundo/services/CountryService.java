package com.example.ApiDatosPoblacionMundo.services;

import com.example.ApiDatosPoblacionMundo.domain.entities.CountryEntity;

import java.util.List;

public interface CountryService {

    int isDbEmpty();

    List<CountryEntity> getCountries ();

    CountryEntity save (CountryEntity countryEntity);

    boolean existInDb (int id);



}
