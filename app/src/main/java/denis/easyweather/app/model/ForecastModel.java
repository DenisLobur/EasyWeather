package denis.easyweather.app.model;

import java.util.List;

public class ForecastModel {
    private City city;
    private List<ForecastData> list;

    private class City {
        private Long id;
        private String name;
        private Coordinates coord;
        private String country;
        private Integer cod;

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Coordinates getCoord() {
            return coord;
        }

        public String getCountry() {
            return country;
        }

        public Integer getCod() {
            return cod;
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
    }

    private class ForecastData {
        private Long dt;
        private Main main;
        private List<Weather> weathers;
        private Clouds clouds;
        private Wind wind;
        private String dt_txt;

        public Main getMain() {
            return main;
        }

        public List<Weather> getWeathers() {
            return weathers;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public Wind getWind() {
            return wind;
        }

        public Long getDt() {
            return dt;
        }

        public String getDt_txt() {
            return dt_txt;
        }

        private class Main {
            private Double temp;
            private Double temp_min;
            private Double temp_max;
            private Double pressure;
            private Double sea_level;
            private Double grnd_level;
            private Integer humidity;
            private Double temp_kf;

            public Double getTemp() {
                return temp;
            }

            public Double getTemp_min() {
                return temp_min;
            }

            public Double getTemp_max() {
                return temp_max;
            }

            public Double getPressure() {
                return pressure;
            }

            public Double getSea_level() {
                return sea_level;
            }

            public Double getGrnd_level() {
                return grnd_level;
            }

            public Integer getHumidity() {
                return humidity;
            }

            public Double getTemp_kf() {
                return temp_kf;
            }
        }

        private class Weather {
            private Long id;
            private String main;
            private String description;
            private String icon;

            public Long getId() {
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

        private class Clouds {
            private Integer all;

            public Integer getAll() {
                return all;
            }
        }

        private class Wind {
            private Double speed;
            private Double deg;

            public Double getSpeed() {
                return speed;
            }

            public Double getDeg() {
                return deg;
            }
        }
    }

    public City getCity() {
        return city;
    }

    public List<ForecastData> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "ForecastModel{" +
                "city=" + city.getCountry() +
                ", list=" + list.get(0).wind.getDeg() +
                ", dt_txt='" + list.get(0).dt_txt + '\'' +
                '}';
    }
}
