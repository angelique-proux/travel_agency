package fr.lernejo.prediction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api")
public class PingController {

    @GetMapping(path = "/ping")
    String ping() {
        return "OK";
    }

    @GetMapping(path = "/temperature")
    public @ResponseBody Object get_country_temperature(@RequestParam String country){
        try {
            TemperatureService temperatureService = new TemperatureService();
            ArrayList<OneDayTemperature> twoDaysTemperature = new ArrayList<>();
            twoDaysTemperature.add(new OneDayTemperature("2021-12-04", temperatureService.getTemperature(country)));
            twoDaysTemperature.add(new OneDayTemperature("2021-12-05", temperatureService.getTemperature(country)));
            JsonTemperature jsonTemperature = new JsonTemperature(country, twoDaysTemperature);
            return jsonTemperature;
        } catch (UnknownCountryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
