package com.example.ApiDatosPoblacionMundo.controller;

import com.example.ApiDatosPoblacionMundo.TestDataUtil;
import com.example.ApiDatosPoblacionMundo.domain.dto.CountryDto;
import com.example.ApiDatosPoblacionMundo.domain.entities.CountryEntity;
import com.example.ApiDatosPoblacionMundo.services.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CountryControllerIntegrationTest {

    private CountryService testCService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public CountryControllerIntegrationTest(CountryService testCService, MockMvc mockMvc, ObjectMapper objectMapper) {

        this.testCService=testCService;
        this.mockMvc=mockMvc;
        this.objectMapper=objectMapper;
    }

    @Test
    public void TestThatCreateCountryReturnsAn201Response () {
        CountryEntity countryEntityA = TestDataUtil.createCountryEntityA();
        countryEntityA.setId(null);

        try{

            String testJson = objectMapper.writeValueAsString(countryEntityA);

            mockMvc.perform(

                    MockMvcRequestBuilders.post("/api/v1/data/country")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(testJson)
            ).andExpect(
                    MockMvcResultMatchers.status().isCreated()
            );

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestThatUpdateCountryReturnsAn202Response () {
        CountryEntity countryEntityA = TestDataUtil.createCountryEntityA();
        testCService.save(countryEntityA);

        testCService.existInDb(countryEntityA.getId());
        try{

            String testJson = objectMapper.writeValueAsString(countryEntityA);

            mockMvc.perform(

                    MockMvcRequestBuilders.post("/api/v1/data/country")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(testJson)
            ).andExpect(
                    MockMvcResultMatchers.status().isAccepted()
            );

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThatCountryCanBeCreated () {

        CountryEntity countryEntityA = TestDataUtil.createCountryEntityA();
        countryEntityA.setId(null);

        try{

            String testJson = objectMapper.writeValueAsString(countryEntityA);

            mockMvc.perform(

                    MockMvcRequestBuilders.post("/api/v1/data/country")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(testJson)
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.id").value(countryEntityA.getId())
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.country").value(countryEntityA.getCountry())
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.population").value((countryEntityA.getPopulation()))
            );

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testThatGetAllCountriesRetrieves200StatusCodeIfDataBaseHaveRows () {

        CountryEntity countryEntityA = TestDataUtil.createCountryEntityA();
        testCService.save(countryEntityA);

        try{
            mockMvc.perform(

                    MockMvcRequestBuilders.get("/api/v1/data/country")
                            .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(
                    MockMvcResultMatchers.status().isOk()
            );

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testThatGetAllCountriesRetrieves201StatusCodeIfDataIsEmpty () {

        try{
            mockMvc.perform(

                    MockMvcRequestBuilders.get("/api/v1/data/country")
                            .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(
                    MockMvcResultMatchers.status().isCreated()
            );

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testThatGetAllRetrievesAllCountriesInDataBase () throws Exception{

        CountryEntity cA = TestDataUtil.createCountryEntityA();
        CountryEntity cB = TestDataUtil.createCountryEntityB();
        CountryEntity cC = TestDataUtil.createCountryEntityC();

        CountryEntity savedA = testCService.save(cA);
        CountryEntity savedB = testCService.save(cB);
        CountryEntity savedC = testCService.save(cC);

        Iterable<CountryEntity> saved = List.of(savedA,savedB,savedC);

        testCService.getCountries();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/data/country").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].country").value("España")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("[0].population").isNumber()
        );

    }

    @Test
    public void testThatGetAllRetrievesDataFromExternalApiRest () throws Exception {

        testCService.getCountries();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/data/country").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].country").value("Andorra")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("[0].population").isNumber()
        );
    }

}
