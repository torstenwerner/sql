package com.westernacher.tutorial;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    private WorldRepository worldRepository;

    @Test
    public void countries() throws Exception {
        final List<Country> allCountries = worldRepository.findAllCountries();
        assertThat(allCountries, hasSize(239));
        final Country afghanistan = allCountries.get(0);
        assertThat(afghanistan.getCode(), is("AFG"));
        assertThat(afghanistan.getName(), is("Afghanistan"));
        assertThat(afghanistan.getPopulation(), is(22720000L));
    }

    @Test
    public void languages() throws Exception {
        final List<Language> allLanguages = worldRepository.findAllLanguages();
        assertThat(allLanguages, hasSize(984));
        final Language pashto = allLanguages.get(0);
        assertThat(pashto.getCountrycode(), is("AFG"));
        assertThat(pashto.getLanguage(), is("Pashto"));
        assertThat(pashto.getPercentage(), closeTo(52.4, 1e-5));
    }

    @Test
    public void cities() throws Exception {
        final List<City> allCities = worldRepository.findAllCities();
        assertThat(allCities, hasSize(4079));
        final City kabul = allCities.get(0);
        assertThat(kabul.getId(), is(1));
        assertThat(kabul.getName(), is("Kabul"));
        assertThat(kabul.getCountrycode(), is("AFG"));
    }
}
