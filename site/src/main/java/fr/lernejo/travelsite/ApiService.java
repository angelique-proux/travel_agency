package fr.lernejo.travelsite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApiService {

    public final PredictionEngineClient predictionEngineClient;
    public final ArrayList<JsonRegistry> jsonRegistries = new ArrayList<>();
    public final ArrayList<JsonCountry> jsonCountries = new ArrayList<>();
    public final List<String> countries;

    public ApiService(PredictionEngineClient predictionEngineClient){
        this.predictionEngineClient = predictionEngineClient;
        InputStream inputStream = ApiService.class.getClassLoader().getResourceAsStream("countries.txt");
        try {
            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            countries = content.lines().collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new IllegalStateException("No file named countries.txt in classPath.");
        }
    }

    public Iterable<JsonRegistry> register_user(JsonRegistry client){
        if(!this.jsonRegistries.contains(client)) this.jsonRegistries.add(client);
        return this.jsonRegistries;
    }

    public Object get_destinations(String userName){
        JsonRegistry userFounded = null;
        for (JsonRegistry jsonRegistry : this.jsonRegistries)
            if (jsonRegistry.userName.equals(userName)) userFounded = jsonRegistry;
        if(userFounded != null) {
            Queue LL_queue = new LinkedList();
            for (String country : this.countries) LL_queue.add(country);
            Iterator iterator = LL_queue.iterator();
            while(iterator.hasNext()){
                get_mean_temperature((String) iterator.next());
            }
            return get_countries_that_satisfy_expectations(userFounded);
        }
        else return new ResponseEntity<>("The userName doesn't match.", HttpStatus.EXPECTATION_FAILED);
    }

    private void get_mean_temperature(String country) {
        Call<JsonTemperature> jsonTemperatureCall = this.predictionEngineClient.listTemperature(country);
        if (jsonTemperatureCall != null) {
            try {
                Response<JsonTemperature> response = jsonTemperatureCall.execute();
                int meanTemperature = (response.body().temperatures.get(0).temperature.intValue() + response.body().temperatures.get(1).temperature.intValue())/2;
                jsonCountries.add(new JsonCountry(country, meanTemperature));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Iterable<JsonCountry> get_countries_that_satisfy_expectations(JsonRegistry client){
        ArrayList<JsonCountry> countriesMatches = new ArrayList<>();
        int clientCountryTemperature = 0;
        for (JsonCountry jsonCountry : this.jsonCountries)
            if (jsonCountry.country.equals(client.userCountry)) clientCountryTemperature = jsonCountry.temperature.intValue();
        for (JsonCountry jsonCountry : this.jsonCountries) {
            if (!jsonCountry.country.equals(client.userCountry)
                && ((jsonCountry.temperature.intValue() < (clientCountryTemperature - client.minimumTemperatureDistance) && client.weatherExpectation.equals(WeatherExpectation.COLDER))
                    || (jsonCountry.temperature.intValue() > (client.minimumTemperatureDistance + clientCountryTemperature) && client.weatherExpectation.equals(WeatherExpectation.WARMER)))) {
                countriesMatches.add(jsonCountry);
            }
        }
        this.jsonCountries.clear();
        return countriesMatches;
    }
}
