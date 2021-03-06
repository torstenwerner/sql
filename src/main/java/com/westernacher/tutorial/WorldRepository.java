package com.westernacher.tutorial;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WorldRepository {
    @Select("SELECT code, name, population, continent FROM country")
    List<Country> findAllCountries();

    @Select("SELECT countrycode, language, isofficial, percentage FROM countrylanguage")
    List<Language> findAllLanguages();

    @Select("SELECT id, name, countrycode FROM city")
    List<City> findAllCities();
}
