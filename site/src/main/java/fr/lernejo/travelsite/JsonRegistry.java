package fr.lernejo.travelsite;

public class JsonRegistry {
    public final String userEmail;
    public final String userName;
    public final String userCountry;
    public final WeatherExpectation weatherExpectation;
    public final Integer minimumTemperatureDistance;

    public JsonRegistry(){
        this.userEmail = null;
        this.userName = null;
        this.userCountry = null;
        this.weatherExpectation = null;
        this.minimumTemperatureDistance = null;
    }

    public JsonRegistry(String userEmail, String userName, String userCountry, String weatherExpectation, Integer minimumTemperatureDistance) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userCountry = userCountry;
        this.weatherExpectation = WeatherExpectation.valueOf(weatherExpectation);
        this.minimumTemperatureDistance = minimumTemperatureDistance;
    }

    @Override
    public String toString() {
        return "JsonRegistry{" +
            "userEmail='" + userEmail + '\'' +
            ", userName='" + userName + '\'' +
            ", userCountry='" + userCountry + '\'' +
            ", weatherExpectation=" + weatherExpectation +
            ", minimumTemperatureDistance=" + minimumTemperatureDistance +
            '}';
    }
}
