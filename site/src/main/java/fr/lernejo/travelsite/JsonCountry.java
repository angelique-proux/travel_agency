package fr.lernejo.travelsite;

public class JsonCountry {
    public final String country;
    public final Number temperature;

    public JsonCountry(String country, Number temperature) {
        this.country = country;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "JsonCountry{" +
            "country='" + country + '\'' +
            ", temperature=" + temperature +
            '}';
    }
}
