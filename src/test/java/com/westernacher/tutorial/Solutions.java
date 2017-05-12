package com.westernacher.tutorial;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.String.format;
import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.*;
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

    private void speakersPerCountry(Consumer<String> consumer) throws Exception {

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
                        this::getSpeakers,
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

    @Test
    public void countriesNoOfficialLanguages() throws Exception {
        worldRepository.findAllLanguages().stream()
                .filter(language -> FALSE.equals(language.getIsofficial()))
                .map(Language::getCountrycode)
                .sorted()
                .distinct()
                .map(countries::get)
                .map(Country::getName)
                .forEach(name -> System.out.printf("%s\n", name));
    }

    @Test
    public void variousAggregates() throws Exception {
        final LongSummaryStatistics statistics = countries.values().stream()
                .mapToLong(Country::getPopulation)
                .summaryStatistics();
        final long sum = statistics.getSum();
        final double average = statistics.getAverage();
        final long max = statistics.getMax();
        final double median = calculateMedian(countries.values());
        final long sumEurope = countries.values().stream()
                .filter(country -> "Europe".equals(country.getContinent()))
                .mapToLong(Country::getPopulation)
                .sum();
        final long sumBelowMedian = countries.values().stream()
                .mapToLong(Country::getPopulation)
                .filter(population -> population < median)
                .sum();
        System.out.printf("sum: %d\n", sum);
        System.out.printf("average: %.0f\n", average);
        System.out.printf("max: %d\n", max);
        System.out.printf("median: %.0f\n", median);
        System.out.printf("sum europe: %d\n", sumEurope);
        System.out.printf("sum below median: %d\n", sumBelowMedian);
    }

    private double calculateMedian(Collection<Country> values) {
        final List<Country> sortedCountries = new ArrayList<>(values);
        sortedCountries.sort(comparing(Country::getPopulation));
        final int size = sortedCountries.size();

        if (size % 2 == 1) {
            return sortedCountries.get((size - 1) / 2).getPopulation();
        } else {
            final long belowMedian = sortedCountries.get(size / 2 - 1).getPopulation();
            final long aboveMedian = sortedCountries.get(size / 2).getPopulation();
            return 0.5 * (belowMedian + aboveMedian);
        }
    }

    @Test
    public void cities() throws Exception {
        worldRepository.findAllCities().stream()
                .collect(groupingBy(City::getCountrycode, counting()))
                .entrySet().stream()
                .sorted(comparing(Map.Entry::getValue, reverseOrder()))
                .map(entry -> format("%-20s %3d\n", countries.get(entry.getKey()).getName(), entry.getValue()))
                .limit(10)
                .forEach(System.out::print);
    }

    @Test
    public void jsonCountries() throws Exception {
        final List<Country> countries = worldRepository.findAllCountries().stream()
                .limit(10)
                .collect(toList());
        System.out.println(toJsonString(countries));
    }

    @Test
    public void jsonCountryLanguages() throws Exception {
        class ResultRow {
            String name;
            List<Language> languages;

            ResultRow(Country country, List<Language> languages) {
                this.name = country.getName();
                this.languages = languages;
            }
        }

        final Map<String, List<Language>> languageMap = worldRepository.findAllLanguages().stream()
                .collect(groupingBy(Language::getCountrycode));

        final List<ResultRow> countries = worldRepository.findAllCountries().stream()
                .limit(2)
                .map(country -> new ResultRow(country, languageMap.get(country.getCode())))
                .collect(toList());
        System.out.println(toJsonString(countries));
    }

    private <T> String toJsonString(T object) {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json serialization failed", e);
        }
    }

    @Test
    public void languagePercentages() throws Exception {
        final long worldPopulation = worldRepository.findAllCountries().stream()
                .mapToLong(Country::getPopulation)
                .sum();
        worldRepository.findAllLanguages().stream()
                .collect(groupingBy(Language::getLanguage, summingDouble(this::getSpeakers)))
                .entrySet().stream()
                .sorted(comparing(Map.Entry::getValue, reverseOrder()))
                .map(entry -> format("%-20s %.6f\n", entry.getKey(), entry.getValue() / worldPopulation))
                .limit(10)
                .forEach(System.out::print);
    }

    @Test
    public void languagePercentagesWithRank() throws Exception {
        final long worldPopulation = worldRepository.findAllCountries().stream()
                .mapToLong(Country::getPopulation)
                .sum();
        worldRepository.findAllLanguages().stream()
                .collect(groupingBy(Language::getLanguage, summingDouble(this::getSpeakers)))
                .entrySet().stream()
                .sorted(comparing(Map.Entry::getValue, reverseOrder()))
                .map(new IndexAdder<>())
                .map(entry -> format("%3d %-20s %.6f\n", entry.index() + 1, entry.get().getKey(), entry.get().getValue() / worldPopulation))
                .limit(10)
                .forEach(System.out::print);
    }

    private double getSpeakers(Language language) {
        return .01 * language.getPercentage() * countries.get(language.getCountrycode()).getPopulation();
    }

    @Test
    public void countriesByPopulation() throws Exception {
        final AtomicInteger rank = new AtomicInteger();
        worldRepository.findAllCountries().stream()
                .sorted(comparing(Country::getPopulation, reverseOrder()))
                .map(country -> format("%-20s %10d\n", country.getName(), country.getPopulation()))
                .limit(10)
                .forEach(System.out::print);
    }
}
