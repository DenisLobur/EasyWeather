package denis.easyweather.app.repository.converter

import denis.easyweather.app.data.remote.weatherModel.*
import denis.easyweather.app.network.response.WeatherResponseDTO

class WeatherConverter : Converter<WeatherResponseDTO, WeatherResponse> {
  override fun convert(input: WeatherResponseDTO) = with(input) {
    WeatherResponse(
      dt = dt,
      coord = Coord(coord.lon, coord.lat),
      main = Main(main.temp, main.pressure, main.humidity, main.tempMin, main.tempMax),
      clouds = Clouds(clouds.all),
      wind = Wind(wind.speed, wind.deg),
      weather = weather.map { it -> Weather(it.id, it.main, it.description, it.icon) },
      sys = Sys(sys.country, sys.sunrise, sys.sunset),
      name = name
    )
  }
}