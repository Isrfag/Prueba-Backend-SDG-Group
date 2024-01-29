package com.example.ApiDatosPoblacionMundo.mappers.impl;

import com.example.ApiDatosPoblacionMundo.domain.dto.CountryDto;
import com.example.ApiDatosPoblacionMundo.domain.raw.CountryRaw;
import com.example.ApiDatosPoblacionMundo.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoMapperImp implements Mapper<CountryDto, CountryRaw> {

    ModelMapper modelMapper;

    //Inyectamos el bean modelmapper anteriormente creado
    public DtoMapperImp(ModelMapper modelMapper) {
        this.modelMapper=modelMapper;
    }

    @Override
    public CountryRaw mapTo(CountryDto countryDto) {
        return modelMapper.map(countryDto, CountryRaw.class);
    }

    @Override
    public CountryDto mapFrom (CountryRaw countryRaw) {
        return modelMapper.map(countryRaw, CountryDto.class);
    }
}
