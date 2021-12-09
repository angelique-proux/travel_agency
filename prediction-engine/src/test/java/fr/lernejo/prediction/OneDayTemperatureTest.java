package fr.lernejo.prediction;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OneDayTemperatureTest {

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
}
