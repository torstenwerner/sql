package com.westernacher.tutorial.querydsl;

import javax.annotation.Generated;

/**
 * City is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class City {

    private String countrycode;

    private String district;

    private Integer id;

    private String name;

    private Integer population;

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public String toString() {
         return "countrycode = " + countrycode + ", district = " + district + ", id = " + id + ", name = " + name + ", population = " + population;
    }

}

