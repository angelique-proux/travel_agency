package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OneDayTemperatureTest {

    @Test
    public void create_a_empty_temperature_prediction(){
        Assertions.assertThat(new OneDayTemperature())
            .isInstanceOf(OneDayTemperature.class);
    }

    @ParameterizedTest
    @CsvSource({
        "2020-02-12, 23",
        "2016-04-07, 18"
    })
    public void create_a_temperature_prediction(String date, Integer temperaturePrediction){
        Assertions.assertThat(new OneDayTemperature(date, temperaturePrediction))
            .isInstanceOf(OneDayTemperature.class)
            .isNotNull();
    }

    @ParameterizedTest
    @CsvSource({
        "2022-11-30, 3",
        "2011-09-01, 40"
    })
    public void test_the_to_string_method(String date, Integer temperaturePrediction){
        Assertions.assertThat(new OneDayTemperature(date, temperaturePrediction).toString())
            .isEqualTo("{" +
                "date='" + date + '\'' +
                ", temperature=" + temperaturePrediction +
                "°C}");
    }
}
