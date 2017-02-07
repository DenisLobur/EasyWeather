package denis.easyweather.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denis on 07-Feb-17.
 */

public class CityModel {
    private Coordinates coord;
    private SunData sys;
    private List<WeatherDescription> weather;
    private WeatherData main;
    private WindData wind;
    @SerializedName("rain")
    @Expose
    private RainData rain;
    private Clouds clouds;
    private Long dt;
    private Long id;
    private String name;
    private Integer cod;

    private class WeatherData {
        private Double temp;
        private Double humidity;
        private Double pressure;
        private Double temp_min;
        private Double temp_max;

        public Double getTemp() {
            return temp;
        }

        public Double getHumidity() {
            return humidity;
        }

        public Double getPressure() {
            return pressure;
        }

        public Double getTemp_min() {
            return temp_min;
        }

        public Double getTemp_max() {
            return temp_max;
        }
    }

    private class Coordinates {
        private Double lat;
        private Double lon;

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }
    }

    private class SunData {
        private String country;
        private Long sunrise;
        private Long sunset;

        public String getCountry() {
            return country;
        }

        public long getSunrise() {
            return sunrise;
        }

        public long getSunset() {
            return sunset;
        }
    }

    private class WeatherDescription {
        private Long id;
        private String main;
        private String description;
        private String icon;

        public long getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }
    }

    private class WindData {
        private Double speed;
        private Double deg;

        public Double getSpeed() {
            return speed;
        }

        public Double getDeg() {
            return deg;
        }
    }

    private class RainData {
        @SerializedName("3h")
        @Expose
        private Integer _3h;

        public Integer get3h() {
            return _3h;
        }
    }

    private class Clouds {
        private Integer all;

        public Integer getAll() {
            return all;
        }
    }
}