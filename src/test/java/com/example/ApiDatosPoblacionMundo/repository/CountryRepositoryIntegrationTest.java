package com.example.ApiDatosPoblacionMundo.repository;

import com.example.ApiDatosPoblacionMundo.TestDataUtil;
import com.example.ApiDatosPoblacionMundo.domain.entities.CountryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CountryRepositoryIntegrationTest {


    public CountryRepository underTest;

    @Autowired
    public CountryRepositoryIntegrationTest(CountryRepository underTest) {
        this.underTest=underTest;
    }

    @Test
    public void testThatDbIsEmptyWorksCountDbRows () {

        Integer rows = underTest.countRows();
        assertThat(rows).isNotNull();

    }

    @Test
    public void testThatListOfCountriesAreReturnedFromDb () {

        CountryEntity testcountryEntityA = TestDataUtil.createCountryEntityA();
        CountryEntity testcountryEntityB = TestDataUtil.createCountryEntityB();
        CountryEntity testcountryEntityC = TestDataUtil.createCountryEntityC();

        Iterable <CountryEntity> testList = List.of(testcountryEntityA,testcountryEntityB,testcountryEntityC);

        underTest.saveAll(testList);
        Iterable<CountryEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(testcountryEntityA,testcountryEntityB,testcountryEntityC);
    }

    @Test
    public void testThatACountryCanBeSaved () {

        CountryEntity countryEntityA = TestDataUtil.createCountryEntityA();

        CountryEntity result = underTest.save(countryEntityA);

        Optional<CountryEntity> res = underTest.findById(countryEntityA.getId());

        assertThat(res).isPresent();
        assertThat(res.get()).isEqualTo(result);


    }

    @Test
    public void testThatACountryCanBeUpdated () {

        CountryEntity countryEntityA = TestDataUtil.createCountryEntityA();


        underTest.save(countryEntityA);
        countryEntityA.setCountry("Narnia");
        underTest.save(countryEntityA);

        Optional<CountryEntity> res = underTest.findById(countryEntityA.getId());

        assertThat(res).isPresent();
        assertThat(res.get()).isEqualTo(countryEntityA);


    }

    @Test
    public void testThatExistInDbWorks () {

        CountryEntity countryEntityA = TestDataUtil.createCountryEntityA();
        underTest.save(countryEntityA);

        boolean res = underTest.existsById(countryEntityA.getId());

        assertThat(res).isEqualTo(true);

    }

}
