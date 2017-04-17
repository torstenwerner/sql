package com.westernacher.tutorial;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Repository {
    @Select("SELECT code, name FROM country")
    List<Country> findAllCountries();
}
