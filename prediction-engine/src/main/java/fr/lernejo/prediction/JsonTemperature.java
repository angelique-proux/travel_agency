package fr.lernejo.prediction;

import java.util.ArrayList;

public class JsonTemperature {

    public final String country;
    public final ArrayList<OneDayTemperature> temperatures;

    public JsonTemperature(String country, ArrayList<OneDayTemperature> twoDaysTemperature) {
        this.country = country;
        this.temperatures = twoDaysTemperature;
    }

    @Override
    public String toString() {
        return "JsonTemperature{" +
            "country='" + country + '\'' +
            ", twoDaysTemperature=" + temperatures +
            '}';
    }
}
