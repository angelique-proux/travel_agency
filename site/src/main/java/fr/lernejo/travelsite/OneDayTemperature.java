package fr.lernejo.travelsite;

public class OneDayTemperature {
    public final String date;
    public final Number temperature;

    public OneDayTemperature(){
        this.date = null;
        this.temperature = null;
    }

    public OneDayTemperature(String date, Number temperature) {
        this.date = date;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "{" +
            "date='" + date + '\'' +
            ", temperature=" + temperature +
            "°C}";
    }
}
