package com.example.ApiDatosPoblacionMundo.services;

import com.example.ApiDatosPoblacionMundo.domain.entities.CountryEntity;
import com.example.ApiDatosPoblacionMundo.domain.raw.CountryRaw;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FillDbService {

    ResponseEntity fillDataBase();

    List<CountryRaw> getCountriesRawsFromJson ();

    String sendToDataBase (List<CountryEntity> countryEntity);
}
