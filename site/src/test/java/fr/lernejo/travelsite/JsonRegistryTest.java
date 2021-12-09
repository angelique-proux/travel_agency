package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class JsonRegistryTest {

    @Test
    public void create_a_empty_registry(){
        Assertions.assertThat(new JsonRegistry())
            .isInstanceOf(JsonRegistry.class);
    }

    @ParameterizedTest
    @CsvSource({
        "abc, a@b.cr, Belgium, WARMER, 30",
        "try, try@t.rr, Andorra, COLDER, 2"
    })
    public void create_a_registry(String userName, String userEmail, String userCountry, String weatherExpectation, Integer minimumTemperatureDistance){
        Assertions.assertThat(new JsonRegistry(userEmail, userName, userCountry, weatherExpectation, minimumTemperatureDistance))
            .isInstanceOf(JsonRegistry.class)
            .isNotNull();
    }

    @ParameterizedTest
    @CsvSource({
        "second, second@s.com, Côte d’Ivoire, WARMER, 15",
        "none, none@no.fr, Ethiopia, COLDER, 6"
    })
    public void test_the_to_string_method(String userName, String userEmail, String userCountry, String weatherExpectation, Integer minimumTemperatureDistance){
        Assertions.assertThat(new JsonRegistry(userEmail, userName, userCountry, weatherExpectation, minimumTemperatureDistance).toString())
            .isEqualTo("JsonRegistry{" +
                "userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userCountry='" + userCountry + '\'' +
                ", weatherExpectation=" + weatherExpectation +
                ", minimumTemperatureDistance=" + minimumTemperatureDistance +
                '}');
    }

    @ParameterizedTest
    @CsvSource({
        "second, second^s.e, Belgium, WARMER, 15",
        "none, email, South Korea, COLDER, 6"
    })
    public void test_not_valid_email(String userName, String userEmail, String userCountry, String weatherExpectation, Integer minimumTemperatureDistance){
        Assertions.assertThatThrownBy(() -> new JsonRegistry(userEmail, userName, userCountry, weatherExpectation, minimumTemperatureDistance))
            .isInstanceOf(NotValidException.class)
            .hasMessageStartingWith("Email is not valid.");
    }

}
