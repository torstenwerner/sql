package com.westernacher.tutorial;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
