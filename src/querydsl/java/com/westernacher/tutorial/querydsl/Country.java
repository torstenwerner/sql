package com.westernacher.tutorial.querydsl;

import javax.annotation.Generated;

/**
 * Country is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class Country {

    private Integer capital;

    private String code;

    private String code2;

    private String continent;

    private java.math.BigDecimal gnp;

    private java.math.BigDecimal gnpold;

    private String governmentform;

    private String headofstate;

    private Short indepyear;

    private Float lifeexpectancy;

    private String localname;

    private String name;

    private Integer population;

    private String region;

    private Float surfacearea;

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public java.math.BigDecimal getGnp() {
        return gnp;
    }

    public void setGnp(java.math.BigDecimal gnp) {
        this.gnp = gnp;
    }

    public java.math.BigDecimal getGnpold() {
        return gnpold;
    }

    public void setGnpold(java.math.BigDecimal gnpold) {
        this.gnpold = gnpold;
    }

    public String getGovernmentform() {
        return governmentform;
    }

    public void setGovernmentform(String governmentform) {
        this.governmentform = governmentform;
    }

    public String getHeadofstate() {
        return headofstate;
    }

    public void setHeadofstate(String headofstate) {
        this.headofstate = headofstate;
    }

    public Short getIndepyear() {
        return indepyear;
    }

    public void setIndepyear(Short indepyear) {
        this.indepyear = indepyear;
    }

    public Float getLifeexpectancy() {
        return lifeexpectancy;
    }

    public void setLifeexpectancy(Float lifeexpectancy) {
        this.lifeexpectancy = lifeexpectancy;
    }

    public String getLocalname() {
        return localname;
    }

    public void setLocalname(String localname) {
        this.localname = localname;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Float getSurfacearea() {
        return surfacearea;
    }

    public void setSurfacearea(Float surfacearea) {
        this.surfacearea = surfacearea;
    }

    @Override
    public String toString() {
        return "capital = " + capital + ", code = " + code + ", code2 = " + code2 + ", continent = " + continent + ", gnp = " + gnp + ", gnpold = " + gnpold + ", governmentform = " + governmentform + ", headofstate = " + headofstate + ", indepyear = " + indepyear + ", lifeexpectancy = " + lifeexpectancy + ", localname = " + localname + ", name = " + name + ", population = " + population + ", region = " + region + ", surfacearea = " + surfacearea;
    }

}

