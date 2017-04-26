package com.westernacher.tutorial.querydsl;

import javax.annotation.Generated;

/**
 * Countrylanguage is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class Countrylanguage {

    private String countrycode;

    private Boolean isofficial;

    private String language;

    private Float percentage;

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public Boolean getIsofficial() {
        return isofficial;
    }

    public void setIsofficial(Boolean isofficial) {
        this.isofficial = isofficial;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "countrycode = " + countrycode + ", isofficial = " + isofficial + ", language = " + language + ", percentage = " + percentage;
    }

}

