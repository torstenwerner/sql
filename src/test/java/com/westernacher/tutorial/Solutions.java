package com.westernacher.tutorial;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Solutions {

    @Autowired
    private WorldRepository worldRepository;

    @Test
    public void hundredPercent() throws Exception {
        worldRepository.findAllLanguages().stream()
                .collect(toMap(Language::getCountrycode, Language::getPercentage, (sum, value) -> sum + value))
                .forEach((countrycode, percentage) -> System.out.printf("%s %.1f\n", countrycode, percentage));
    }

    @Test
    public void speakersPerCountry() throws Exception {
        final Map<String, Country> countries = worldRepository.findAllCountries().stream()
                .collect(toMap(Country::getCode, Function.identity()));

        class ResultRow {
            String name;
            long speakers;
            String language;

            ResultRow(Language language) {
                final Country country = countries.get(language.getCountrycode());
                this.name = country.getName();
                this.speakers = Math.round(country.getPopulation() * language.getPercentage());
                this.language = language.getLanguage();
            }

            void print() {
                System.out.printf("%40s %10d %30s\n", name, speakers, language);
            }
        }

        worldRepository.findAllLanguages().stream()
                .map(language -> new ResultRow(language))
                .forEach(ResultRow::print);
    }
}
