package com.westernacher.tutorial;

public class Language {
    private String countrycode;
    private String language;
    private Boolean isofficial;
    private double percentage;

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

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Language{" +
                "countrycode='" + countrycode + '\'' +
                ", language='" + language + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
