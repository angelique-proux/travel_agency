package fr.lernejo.prediction;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class TemperatureServiceTest {

    private final TemperatureService service = new TemperatureService();

    @Test
    void getting_temperature_of_an_unknown_country_throws() {
        Assertions.assertThatExceptionOfType(UnknownCountryException.class)
            .isThrownBy(() -> service.getTemperature("toto"))
            .withMessage("Unknown country: toto");
    }

    @ParameterizedTest
    @CsvSource({
        "france",
        "FRANCE",
        "France"
    })
    void getting_the_temperature_of_france_gives_a_believable_value(String country) {
        double temperature = service.getTemperature(country);
        assertThat(temperature).isBetween(8D, 32D);
    }

    @Test
    void test_case_insensitive_hashcode_null() {
        assertThat(new TemperatureService.CaseInsensitiveString(null).hashCode())
            .isZero();
    }

    @Test
    void test_case_insensitive_equals_null() {
        assertThat(new TemperatureService.CaseInsensitiveString("France").equals(null))
            .isFalse();
    }

    @Test
    void test_case_insensitive_equals_same() {
        assertThat(new TemperatureService.CaseInsensitiveString("France").equals(new TemperatureService.CaseInsensitiveString("France")))
            .isTrue();
    }

    @Test
    void test_case_insensitive_equals_other_class() {
        assertThat(new TemperatureService.CaseInsensitiveString("France").equals("hh"))
            .isFalse();
    }

    @Test
    void test_case_insensitive_value() {
        assertThat(new TemperatureService.CaseInsensitiveString("France").value())
            .isEqualTo("France");
    }
}
