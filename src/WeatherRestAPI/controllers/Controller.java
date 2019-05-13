package WeatherRestAPI.controllers;

import WeatherRestAPI.model.weather;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private TextField idCityCurrent;

    @FXML
    private TextField idCountryCurrent;

    @FXML
    private Button idGetWeatherCurrent;

    @FXML
    private ImageView isCurrentImage;


    @FXML
    private Label idCurrentLabel;

    @FXML
    private TextField idCityForecast;

    @FXML
    private TextField idCountryForecast;

    @FXML
    private Button isGetWeatherForecast;

    @FXML
    private ImageView idForecastOne;

    @FXML
    private ImageView idForecastTwo;

    @FXML
    private ImageView idForecastThree;

    @FXML
    private ImageView idForecastFour;

    @FXML
    private ImageView idForecastFive;

    @FXML
    private Label idForecastLabelOne;

    @FXML
    private Label idForecastLabelTwo;

    @FXML
    private Label idForecastLabelThree;

    @FXML
    private Label idForecastLabelFour;

    @FXML
    private Label idForecastLabelFive;

    @FXML
    void getWeatherCurrent(ActionEvent event) {

        String City = idCityCurrent.getText();
        String Country = idCountryCurrent.getText();
        if((City.length() == 0 && (Country.length() == 0))){
            System.out.println("You forgot to pass City or Country!");
        }

        String urlAddress = "http://api.openweathermap.org/data/2.5/weather?q=" + City.toLowerCase()+ "," + Country.toLowerCase() + "&units=metric&appid=f49aa2424e81a818bcdf08a8a72600fc";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEnity = restTemplate.getForEntity(urlAddress, String.class);
        if( responseEnity.getStatusCode() == HttpStatus.OK ){
            String response = responseEnity.getBody();
            System.out.println(response);
            JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
            assign(jsonObject);
            String imageName = response.substring(response.lastIndexOf("\"icon\":\"")+ 8,response.lastIndexOf("\"icon\":\"")+11);
            String imageId = "http://openweathermap.org/img/w/" + imageName + ".png";
            Image image = new Image(imageId);
            isCurrentImage.setImage(image);
            idCurrentLabel.setText(assign(jsonObject).toString());
        }



    }

    public static weather assign(JsonObject jsonObject){
        JsonArray weatherArray = (JsonArray) jsonObject.get("weather");
        JsonObject weatherEntity = (JsonObject) weatherArray.get(0);
        JsonObject main = (JsonObject) jsonObject.get("main");
        JsonObject wind = (JsonObject) jsonObject.get("wind");
        weather weather = new weather();
        weather.setDescription(weatherEntity.get("description").getAsString());
        weather.setHumidity(main.get("humidity").getAsString());
        weather.setPressure(main.get("pressure").getAsString());
        weather.setTemperature(main.get("temp").getAsString());
        weather.setWinddeg(wind.get("deg").getAsString());
        weather.setWindspeed(wind.get("speed").getAsString());

        String iconUrl = "http://openweathermap.org/img/w/" + weatherEntity.get("icon").getAsString() + ".png";
        Image image = new Image(iconUrl);
        weather.setIcon(image);
        return weather;

    }

    @FXML
    void getWeatherForecast(ActionEvent event) {
        String City = idCityForecast.getText();
        String Country = idCountryForecast.getText();
        String urlAddress = "http://api.openweathermap.org/data/2.5/forecast?q=" + City.toLowerCase()+ "," + Country.toLowerCase() + "&units=metric&appid=f49aa2424e81a818bcdf08a8a72600fc";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEnity = restTemplate.getForEntity(urlAddress, String.class);
        if( responseEnity.getStatusCode() == HttpStatus.OK ){
            String response = responseEnity.getBody();
            System.out.println(response);
            JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
            List<weather> weatherList = new ArrayList<weather>();
            JsonArray jsonArray = (JsonArray) jsonObject.get("list");
            for(int i =0; i< 40 ; i +=8 ){
                JsonObject arrayEnity = (JsonObject) jsonArray.get(i);
                weatherList.add(assign(arrayEnity));
            }
            //idCurrentLabel.setText(assign(jsonObject).toString());

            idForecastLabelOne.setText(weatherList.get(0).toString());
            idForecastLabelTwo.setText(weatherList.get(1).toString());
            idForecastLabelThree.setText(weatherList.get(2).toString());
            idForecastLabelFour.setText(weatherList.get(3).toString());
            idForecastLabelFive.setText(weatherList.get(4).toString());

            idForecastOne.setImage(weatherList.get(0).getIcon());
            idForecastTwo.setImage(weatherList.get(1).getIcon());
            idForecastThree.setImage(weatherList.get(2).getIcon());
            idForecastFour.setImage(weatherList.get(3).getIcon());
            idForecastFive.setImage(weatherList.get(4).getIcon());
        }

    }

    @FXML
    void writincCountryForecast(ActionEvent event) {

    }

    @FXML
    void writingCityCurrent(ActionEvent event) {

    }

    @FXML
    void writingCityForecast(ActionEvent event) {

    }

    @FXML
    void writingCountryCurrent(ActionEvent event) {

    }

    private ArrayList<Label> containerOfLabel;

    private ArrayList<ImageView> containerOfImageView;
}
