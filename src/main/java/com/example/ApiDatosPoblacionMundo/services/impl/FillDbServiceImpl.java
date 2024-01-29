package com.example.ApiDatosPoblacionMundo.services.impl;

import com.example.ApiDatosPoblacionMundo.domain.dto.CountryDto;
import com.example.ApiDatosPoblacionMundo.domain.entities.CountryEntity;
import com.example.ApiDatosPoblacionMundo.domain.raw.CountryRaw;
import com.example.ApiDatosPoblacionMundo.mappers.Mapper;
import com.example.ApiDatosPoblacionMundo.repository.CountryRepository;
import com.example.ApiDatosPoblacionMundo.services.FillDbService;
import com.example.ApiDatosPoblacionMundo.services.CountryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FillDbServiceImpl implements FillDbService {


    private Mapper<CountryEntity,CountryDto> countryMapper;
    private Mapper<CountryDto,CountryRaw> rawMapper;
    private CountryService countryService;
    private RestTemplate restTemplate;
    private CountryRepository countryRepository;
    private ObjectMapper objectMapper;
    public FillDbServiceImpl(final CountryService countryService,
                             final RestTemplate restTemplate,
                             final ObjectMapper objectMapper,
                             final CountryRepository countryRepository,
                             final Mapper<CountryEntity,CountryDto> countryMapper,
                             final Mapper<CountryDto,CountryRaw> rawMapper) {

        this.objectMapper=objectMapper;
        this.restTemplate=restTemplate;
        this.countryService = countryService;
        this.countryRepository=countryRepository;
        this.countryMapper=countryMapper;
        this.rawMapper=rawMapper;
    }

    @Value("${spring.external.service.base-url}") //Traemos la base url de la API
    private String pathUrl;
    @Override
    public ResponseEntity fillDataBase() {
        List<String > countryNames = new ArrayList<>();

        List<CountryRaw> countryListRaw = getCountriesRawsFromJson();

        if(countryListRaw==null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }else {

            for (CountryRaw c : countryListRaw) {

                countryNames.add(c.getName().getCommon());
            }

            List<CountryDto> countryDtosList = countryListRaw.stream().map(rawMapper::mapFrom).toList();


            for (int i = 0; i < 250; i++) {
                countryDtosList.get(i).setCountry(countryNames.get(i));
            }

            List<CountryEntity> countryEntityList = countryDtosList.stream().map(countryMapper::mapFrom).toList();

            //Comprobamos si la base de datos esta vacía, si lo esta la rellenamos con el json
            if (countryService.isDbEmpty()!=0){
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                sendToDataBase(countryEntityList);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
    }

        public List<CountryRaw> getCountriesRawsFromJson () {

            List <String > countryNames = new ArrayList<>();

            //Obtenemos la informacion de la API

            ResponseEntity<String> response = restTemplate.exchange(
                    pathUrl, HttpMethod.GET,
                    new HttpEntity<>(gethttpHeaders()),
                    String.class);

            try {
                List<CountryRaw> countryListRaw = objectMapper.readValue(
                        response.getBody(),
                        (new TypeReference<List<CountryRaw>>() {}));

                return countryListRaw;

            }catch (Exception e){

                System.out.println(e + "Failed to return the json values");

                return null;
            }

        }

        private HttpHeaders gethttpHeaders (){
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            return headers;
        }


        public String sendToDataBase (List<CountryEntity> countryEntity) {

            countryRepository.saveAll(countryEntity);

            return "Enviado";

        }

}
