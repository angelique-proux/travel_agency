package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.ArrayList;

class JsonTemperatureTest {

    private final OneDayTemperature temperatures = Mockito.mock(OneDayTemperature.class);

    @Test
    public void create_a_empty_temperature_prediction(){
        Assertions.assertThat(new JsonTemperature())
            .isInstanceOf(JsonTemperature.class);
    }

    @ParameterizedTest
    @CsvSource({
        "Fiji",
        "Dominica"
    })
    public void create_a_temperature_prediction(String country){
        ArrayList<OneDayTemperature> twoDaysTemperatures = new ArrayList<>();
        twoDaysTemperatures.add(this.temperatures);
        twoDaysTemperatures.add(this.temperatures);
        Assertions.assertThat(new JsonTemperature(country, twoDaysTemperatures))
            .isInstanceOf(JsonTemperature.class)
            .isNotNull();
    }

    @ParameterizedTest
    @CsvSource({
        "Greece",
        "Japan"
    })
    public void test_the_to_string_method(String country){
        ArrayList<OneDayTemperature> twoDaysTemperatures = new ArrayList<>();
        twoDaysTemperatures.add(this.temperatures);
        twoDaysTemperatures.add(this.temperatures);
        Assertions.assertThat(new JsonTemperature(country, twoDaysTemperatures).toString())
            .isEqualTo("{" +
                "country='" + country + '\'' +
                ", twoDaysTemperature=" + twoDaysTemperatures +
                '}');
    }
}
