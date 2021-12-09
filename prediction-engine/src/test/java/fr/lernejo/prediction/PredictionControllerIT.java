package fr.lernejo.prediction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class PredictionControllerIT {

    @Test
    void temperature_return_two_temperatures(@Autowired MockMvc mockMvc) throws Exception {

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/temperature?country=" + "France"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void temperature_return_error_when_unknown_temperature(@Autowired MockMvc mockMvc) throws Exception {

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/temperature?country=" + "country"))
            .andExpect(MockMvcResultMatchers.status().isExpectationFailed())
            .andExpect(MockMvcResultMatchers.content().string("Unknown country: " + "country"));
    }
}
