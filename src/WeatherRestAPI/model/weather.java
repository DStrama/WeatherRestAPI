package WeatherRestAPI.model;


import javafx.scene.image.Image;

public class weather {

    private Image icon;
    private String description;
    private String temperature;
    private String pressure;
    private String humidity;
    private String windspeed;
    private String winddeg;

    @Override
    public String toString() {
        return "Description= " + description + "\n" +
                "Temperature= " + temperature + "\u00b0 C \n" +
                "Pressure= " + pressure + "hPa \n" +
                "Humidity= " + humidity + "% \n" +
                "Windspeed= " + windspeed + "m/s \n" +
                "Winddeg= " + winddeg + "\u00b0 C \n";
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getWinddeg() {
        return winddeg;
    }

    public void setWinddeg(String winddeg) {
        this.winddeg = winddeg;
    }
}
