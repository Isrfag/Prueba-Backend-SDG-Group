package com.example.ApiDatosPoblacionMundo.mappers.impl;

import com.example.ApiDatosPoblacionMundo.domain.dto.CountryDto;
import com.example.ApiDatosPoblacionMundo.domain.entities.CountryEntity;
import com.example.ApiDatosPoblacionMundo.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityMapperImp implements Mapper<CountryEntity, CountryDto> {

    ModelMapper modelMapper;

    public EntityMapperImp(ModelMapper modelMapper) {this.modelMapper=modelMapper;}
    @Override
    public CountryDto mapTo(CountryEntity countryEntity) {
        return modelMapper.map(countryEntity,CountryDto.class);
    }

    @Override
    public CountryEntity mapFrom(CountryDto countryDto) {
        return modelMapper.map(countryDto,CountryEntity.class);
    }
}
