package com.example.ApiDatosPoblacionMundo;

import com.example.ApiDatosPoblacionMundo.domain.dto.CountryDto;
import com.example.ApiDatosPoblacionMundo.domain.entities.CountryEntity;

public final class TestDataUtil {

    public static CountryEntity createCountryEntityA () {
        return CountryEntity.builder()
            .id(1)
            .country("España")
            .population(47000000)
            .build();
    }

    public static CountryEntity createCountryEntityB () {
        return CountryEntity.builder()
                .id(2)
                .country("Francia")
                .population(67000000)
                .build();
    }

    public static CountryEntity createCountryEntityC () {
        return CountryEntity.builder()
                .id(3)
                .country("Alemania")
                .population(83000000)
                .build();
    }

    public static CountryDto createCountryDto () {
        return CountryDto.builder()
                .id(1)
                .country("España")
                .population(47000000)
                .build();
    }
}
