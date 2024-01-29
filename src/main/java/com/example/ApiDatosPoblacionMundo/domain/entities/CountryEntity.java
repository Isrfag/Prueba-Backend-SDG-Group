package com.example.ApiDatosPoblacionMundo.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="countries")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "country_id_seq")
    private Integer id;
    private String country;
    private Integer population;
}
