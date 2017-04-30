package denis.easyweather.app.model;

import java.util.List;

public class CityModel {
    public Coordinates coord;
    public SunData sys;
    public List<WeatherDescription> weather;
    public WeatherData main;
    public WindData wind;
    public RainData rain;
    public Clouds clouds;
    public Long dt;
    public Long id;
    public String name;
    public Integer cod;

    public class WeatherData {
        public Double temp;
        public Double humidity;
        public Double pressure;
        public Double temp_min;
        public Double temp_max;
    }

    public class Coordinates {
        public Double lat;
        public Double lon;
    }

    public class SunData {
        public String country;
        public Long sunrise;
        public Long sunset;
    }

    public class WeatherDescription {
        public Long id;
        public String main;
        public String description;
        public String icon;
    }

    public class WindData {
        public Double speed;
        public Double deg;
    }

    public class RainData {
        public Integer _3h;
    }

    public class Clouds {
        public Integer all;
    }
}
