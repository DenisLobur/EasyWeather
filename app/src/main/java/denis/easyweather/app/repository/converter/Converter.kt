package denis.easyweather.app.repository.converter

interface Converter<In, Out> {
  fun convert(input: In): Out
}