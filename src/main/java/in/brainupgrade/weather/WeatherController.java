package in.brainupgrade.weather;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.brainupgrade.weather.model.currentWeather.City;
import in.brainupgrade.weather.model.currentWeather.WeatherForecast;

@Controller
public class WeatherController {

	@Autowired
	private RemoteApiFetcher remoteApiFetcher;

	@RequestMapping(value = "/get-weather", method = { RequestMethod.PUT, RequestMethod.POST })
	public Optional<WeatherForecast> fetchWeatherForecastFromRemoteApi(@RequestBody City city) {
		return remoteApiFetcher.fetchWeatherForecastFromRemoteApi(city);
	}

	@RequestMapping(value = "/get-cities", method = { RequestMethod.PUT, RequestMethod.POST })
	public Optional<City[]> fetchCitiesFromRemoteApi(@RequestBody String cityInput) {
		return remoteApiFetcher.fetchCitiesFromRemoteApi(cityInput);
	}
}
