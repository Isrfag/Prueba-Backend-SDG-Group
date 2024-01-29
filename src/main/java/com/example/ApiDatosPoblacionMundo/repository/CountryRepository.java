package com.example.ApiDatosPoblacionMundo.repository;

import com.example.ApiDatosPoblacionMundo.domain.entities.CountryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<CountryEntity,Integer> {

    @Query("SELECT COUNT(*) from CountryEntity c")
    int countRows ();


}
