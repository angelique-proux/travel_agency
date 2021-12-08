package fr.lernejo.travelsite;

import java.util.ArrayList;

public class JsonTemperature {

    public final String country;
    public final ArrayList<OneDayTemperature> temperatures;

    public JsonTemperature() {
        this.country = null;
        this.temperatures = null;
    }

    public JsonTemperature(String country, ArrayList<OneDayTemperature> twoDaysTemperature) {
        this.country = country;
        this.temperatures = twoDaysTemperature;
    }

    @Override
    public String toString() {
        return "{" +
            "country='" + country + '\'' +
            ", twoDaysTemperature=" + temperatures +
            '}';
    }
}
