package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;

class ApiServiceTest {

    private final PredictionEngineClient predictionEngineClientMock = Mockito.mock(PredictionEngineClient.class);
    private final ApiService apiService = new ApiService(this.predictionEngineClientMock);


    @ParameterizedTest
    @CsvSource({
        "first, first@f.fr, France, WARMER, 30",
        "try, try@t.de, Germany, COLDER, 2"
    })
    public void register_user(String userName, String userEmail, String userCountry, String weatherExpectation, Integer minimumTemperatureDistance){
        JsonRegistry newUser = new JsonRegistry(userEmail, userName, userCountry, weatherExpectation, minimumTemperatureDistance);
        Iterable<JsonRegistry> registries = this.apiService.register_user(newUser);

        Assertions.assertThat(registries)
            .contains(newUser);
    }

    @ParameterizedTest
    @CsvSource({
        "first, first@f.fi, France, WARMER, 30",
        "try, try@t.it, Germany, COLDER, 2"
    })
    public void do_not_register_user_twice(String userName, String userEmail, String userCountry, String weatherExpectation, Integer minimumTemperatureDistance){
        JsonRegistry newUser = new JsonRegistry(userEmail, userName, userCountry, weatherExpectation, minimumTemperatureDistance);
        this.apiService.register_user(newUser);
        ArrayList<JsonRegistry> registries = (ArrayList<JsonRegistry>) this.apiService.register_user(newUser);

        Assertions.assertThat(registries.size())
            .isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource({
        "first, France",
        "try, Germany"
    })
    public void get_error_when_bad_user(String userName, String country){
        Object object = this.apiService.get_destinations(userName);

        Assertions.assertThat(object)
            .isEqualTo(new ResponseEntity<>("The userName doesn't match.", HttpStatus.EXPECTATION_FAILED));
    }

    @ParameterizedTest
    @CsvSource({
        "first, first@f.us, France, WARMER, 30",
        "try, try@t.ro, Germany, COLDER, 2"
    })
    public void get_destination_when_good_user(String userName, String userEmail, String userCountry, String weatherExpectation, Integer minimumTemperatureDistance){
        JsonRegistry newUser = new JsonRegistry(userEmail, userName, userCountry, weatherExpectation, minimumTemperatureDistance);
        this.apiService.register_user(newUser);

        Object object = this.apiService.get_destinations(userName);

        Assertions.assertThat(object)
            .isInstanceOf(ArrayList.class);
    }
}
