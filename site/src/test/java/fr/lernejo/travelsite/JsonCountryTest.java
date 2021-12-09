package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class JsonCountryTest {

    @ParameterizedTest
    @CsvSource({
        "France, 2",
        "Germany, 33"
    })
    public void create_a_country(String country, Integer temperature){
        Assertions.assertThat(new JsonCountry(country,temperature))
            .isInstanceOf(JsonCountry.class)
            .isNotNull();
    }

    @ParameterizedTest
    @CsvSource({
        "Spain, 10",
        "Finland, 5"
    })
    public void test_the_to_string_method(String country, Integer temperature){
        Assertions.assertThat(new JsonCountry(country,temperature).toString())
            .isEqualTo("JsonCountry{" +
                "country='" + country + '\'' +
                ", temperature=" + temperature + "}");
    }

}
