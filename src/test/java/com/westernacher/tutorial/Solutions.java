package com.westernacher.tutorial;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Solutions {

    @Autowired
    private WorldRepository worldRepository;
    private Map<String, Country> countries;

    @Before
    public void setUp() throws Exception {
        countries = worldRepository.findAllCountries().stream()
                .collect(toMap(Country::getCode, Function.identity()));
    }

    @Test
    public void hundredPercent() throws Exception {
        worldRepository.findAllLanguages().stream()
                .collect(toMap(Language::getCountrycode, Language::getPercentage, (sum, value) -> sum + value))
                .forEach((countrycode, percentage) -> System.out.printf("%s %.1f\n", countrycode, percentage));
    }

    @Test
    public void printSpeakersPerCountry() throws Exception {
        speakersPerCountry(System.out::print);
    }

    @Test
    public void testSpeakersPerCountry() throws Exception {
        final List<String> speakers = new ArrayList<>();
        speakersPerCountry(speakers::add);
        assertThat(speakers, hasSize(100));
        assertThat(speakers.get(0),
                is("                             Afghanistan 1190528034                         Pashto\n"));
    }

    public void speakersPerCountry(Consumer<String> consumer) throws Exception {

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

            String format() {
                return String.format("%40s %10d %30s\n", name, speakers, language);
            }
        }

        worldRepository.findAllLanguages().stream()
                .limit(100)
                .map(language -> new ResultRow(language))
                .map(ResultRow::format)
                .forEach(consumer);
    }

    @Test
    public void speakersInWorld() throws Exception {
        worldRepository.findAllLanguages().stream()
                .collect(Collectors.toMap(
                        Language::getLanguage,
                        language -> language.getPercentage() * countries.get(language.getCountrycode()).getPopulation(),
                        (sum, value) -> sum + value,
                        TreeMap::new
                ))
                .forEach((language, speakers) -> System.out.printf("%30s %12d\n", language, Math.round(speakers)));
    }

    @Test
    public void worldPopulation() throws Exception {
        final long worldPopulation = worldRepository.findAllCountries().stream()
                .mapToLong(Country::getPopulation)
                .sum();
        System.out.println(worldPopulation);
    }
}
