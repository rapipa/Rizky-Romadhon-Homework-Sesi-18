package com.example;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.logging.Logger;
import java.io.BufferedWriter;

public class Example implements HttpFunction {
  private static final Logger logger = Logger.getLogger(Example.class.getName());

  private static final String API_KEY = "9cb3f072f6f04386a2455239230504";
  private static final String WEATHER_API_ENDPOINT = "https://api.weatherapi.com/v1/current.json";

  private final OkHttpClient httpClient = new OkHttpClient();
  private final Gson gson = new Gson();
  @Override
  public void service(HttpRequest request, HttpResponse response) throws Exception {
    String city = request.getFirstQueryParameter("city").orElse("");
    if (city.isEmpty()) {
      response.setStatusCode(400);
      response.getWriter().write("City parameter is missing");
      return;
    }

    String weatherApiResponse = getWeatherData(city);
    WeatherApiResponse weatherResponse = gson.fromJson(weatherApiResponse, WeatherApiResponse.class);

     WeatherData weatherData = new WeatherData(
        weatherResponse.getLocation().getName(),
        weatherResponse.getCurrent().getTempC(),
        weatherResponse.getCurrent().getCondition().getText(),
        weatherResponse.getCurrent().getHumidity(),
        weatherResponse.getCurrent().getWindKph()
    );
    String jsonResponse = gson.toJson(weatherData);
    response.getWriter().write(jsonResponse);

  }
  private String getWeatherData(String city) throws IOException {
    Request httpRequest = new Request.Builder()
        .url(WEATHER_API_ENDPOINT + "?key=" + API_KEY + "&q=" + city + "&aqi=no")
        .build();
    Response httpResponse = httpClient.newCall(httpRequest).execute();
    return httpResponse.body().string();
  }

  private static class WeatherApiResponse {
    private Location location;
    private Current current;

    public Location getLocation() {
      return location;
    }

    public void setLocation(Location location) {
      this.location = location;
    }

    public Current getCurrent() {
      return current;
    }

    public void setCurrent(Current current) {
      this.current = current;
    }
  }

  private static class Location {
    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  private static class Current {
    private double temp_c;
    private Condition condition;
    private double humidity;
    private double wind_kph;

    public double getTempC() {
      return temp_c;
    }

    public void setTempC(double temp_c) {
      this.temp_c = temp_c;
    }

    public Condition getCondition() {
      return condition;
    }

    public void setCondition(Condition condition) {
      this.condition = condition;
    }

    public double getHumidity() {
      return humidity;
    }

    public void setHumidity(double humidity) {
      this.humidity = humidity;
    }

    public double getWindKph() {
      return wind_kph;
    }

    public void setWindKph(double wind_kph) {
      this.wind_kph = wind_kph;
    }
  }

  private static class Condition {
    private String text;

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }
  }

  private static class WeatherData {
    private String location;
    private double temp_c;
    private String condition;
    private double humidity;
    private double wind_kph;

    public WeatherData(String location, double temp_c, String condition, double humidity, double wind_kph) {
      this.location = location;
      this.temp_c = temp_c;
      this.condition = condition;
      this.humidity = humidity;
      this.wind_kph = wind_kph;
    }

    public String getLocation() {
      return location;
    }

    public void setLocation(String location) {
      this.location = location;
    }

    public double getTempC() {
      return temp_c;
    }

    public void setTempC(double temp_c) {
      this.temp_c = temp_c;
    }

    public String getCondition() {
      return condition;
    }

    public void setCondition(String condition) {
      this.condition = condition;
    }

    public double getHumidity() {
      return humidity;
    }

    public void setHumidity(double humidity) {
      this.humidity = humidity;
    }

    public double getWindKph() {
      return wind_kph;
    }

    public void setWindKph(double wind_kph) {
      this.wind_kph = wind_kph;
    }
  }
}
