package denis.easyweather.app.model;

import java.util.List;

public class ForecastModel {
    public City city;
    public List<ForecastData> list;

    public class City {
        public Long id;
        public String name;
        public Coordinates coord;
        public String country;
        public Integer cod;

        public class Coordinates {
            public Double lat;
            public Double lon;
        }
    }

    public class ForecastData {
        public Long dt;
        public Main main;
        public List<Weather> weathers;
        public Clouds clouds;
        public Wind wind;
        public String dt_txt;

        public class Main {
            public Double temp;
            public Double temp_min;
            public Double temp_max;
            public Double pressure;
            public Double sea_level;
            public Double grnd_level;
            public Integer humidity;
            public Double temp_kf;
        }

        public class Weather {
            public Long id;
            public String main;
            public String description;
            public String icon;
        }

        public class Clouds {
            public Integer all;
        }

        public class Wind {
            public Double speed;
            public Double deg;
        }
    }

    @Override
    public String toString() {
        return "ForecastModel{" +
                "city=" + city.country +
                ", list=" + list.get(0).wind.deg +
                ", dt_txt='" + list.get(0).dt_txt + '\'' +
                '}';
    }
}
