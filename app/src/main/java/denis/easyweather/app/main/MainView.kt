package denis.easyweather.app.main

import denis.easyweather.app.model.CityModel

interface MainView {

    fun showTodayWeather(weatherData: Array<String>)

    fun showWeatherRx(cityModel: CityModel)
}
