package fr.lernejo.prediction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api")
public class PredictionController {

    public final TemperatureService temperatureService = new TemperatureService();

    @GetMapping(path = "/temperature")
    public @ResponseBody Object get_country_temperature(@RequestParam String country){
        try {
            ArrayList<OneDayTemperature> twoDaysTemperature = new ArrayList<>();
            twoDaysTemperature.add(new OneDayTemperature(LocalDate.now().minusDays(1).toString(), this.temperatureService.getTemperature(country)));
            twoDaysTemperature.add(new OneDayTemperature(LocalDate.now().minusDays(2).toString(), this.temperatureService.getTemperature(country)));
            JsonTemperature jsonTemperature = new JsonTemperature(country, twoDaysTemperature);
            return jsonTemperature;
        } catch (UnknownCountryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
