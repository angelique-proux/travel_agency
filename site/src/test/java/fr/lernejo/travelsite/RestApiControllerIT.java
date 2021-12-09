package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class RestApiControllerIT {

    ApiRestController apiRestController;

    @ParameterizedTest
    @CsvSource({
        "first, first@f.fr, France, WARMER, 30",
        "try, try@t.de, Germany, COLDER, 2"
    })
    public void register_an_user(String userName, String userEmail, String userCountry, String weatherExpectation, Integer minimumTemperatureDistance, @Autowired MockMvc mockMvc) throws Exception {
        ApiService mockService = Mockito.mock(ApiService.class);
        ArrayList<JsonRegistry> mockRegistry = new ArrayList<>();
        JsonRegistry newUser = new JsonRegistry(userEmail, userName, userCountry, weatherExpectation, minimumTemperatureDistance);
        mockRegistry.add(newUser);
        Mockito.when(mockService.register_user(newUser))
            .thenReturn(mockRegistry);
        Assertions.assertThat(mockService.register_user(newUser)).isEqualTo(mockRegistry);

        this.apiRestController = new ApiRestController(mockService);

        Assertions.assertThat(this.apiRestController.register_new_user(newUser))
            .contains(newUser);
    }

    @ParameterizedTest
    @CsvSource({
        "thisOne",
        "thisOtherOne"
    })
    void destinations_return_error_when_unknown_username(String username, @Autowired MockMvc mockMvc) throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/travels?username=" + username))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void destinations_return_a_list(@Autowired MockMvc mockMvc) throws Exception {
        ApiService mockService = Mockito.mock(ApiService.class);
        ArrayList<JsonCountry> jsonCountries = new ArrayList<>();
        jsonCountries.add(new JsonCountry("France", 2));
        Mockito.when(mockService.get_destinations("hey"))
            .thenReturn(jsonCountries);

        this.apiRestController = new ApiRestController(mockService);

        Assertions.assertThat(this.apiRestController.get_potential_destinations("hey"))
            .toString().contains("France");
    }
}
