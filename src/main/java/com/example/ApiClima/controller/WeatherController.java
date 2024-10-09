package com.example.ApiClima.controller;

import com.example.ApiClima.dtos.ForecastDTO;
import com.example.ApiClima.dtos.GeolocationDTO;
import com.example.ApiClima.dtos.WeatherDTO;
import com.example.ApiClima.services.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;



    @Operation(summary = "obtiene el clima actual de una ciudad o lugar", responses = {
            @ApiResponse(responseCode = "200", description = "Todo Correcto" )
    })
    @GetMapping("/{cityName}")
    public ResponseEntity<WeatherDTO> getWeatherByCityName(@PathVariable String cityName) {
        WeatherDTO weatherDTO = weatherService.getWeatherByCityName(cityName);
        return ResponseEntity.ok(weatherDTO);
    }


    @Operation(summary = "obtiene el pronostico de 5 dias de alguna ciudad", responses = {
            @ApiResponse(responseCode = "200", description = "Todo Correcto" )
    })
    @GetMapping("/forecast/{cityName}")
    public ResponseEntity<ForecastDTO> getForecast(@PathVariable String cityName) {
        ForecastDTO forecastDTO = weatherService.getExtendedForecast(cityName);
        return ResponseEntity.ok(forecastDTO);
    }



    @Operation(summary = "obtiene el clima actual de una ciudad o lugar", responses = {
            @ApiResponse(responseCode = "200", description = "Todo Correcto" )
    })
    @GetMapping("/geo/{cityName}")
    public List<GeolocationDTO> getGeolocation(@PathVariable String cityName) {
        List<GeolocationDTO> geolocationDTOList = weatherService.getGeolocationByCityName(cityName);
        return geolocationDTOList;
    }



    @Operation(summary = "Cache donde se elimina el cache del tiempo", responses = {
            @ApiResponse(responseCode = "200", description = "Todo Correcto" )
    })
    @DeleteMapping("/delete/cacheWeather")
    public ResponseEntity<String> clearAllCacheWeather(){
        weatherService.clearCacheWeather();
        return ResponseEntity.ok("Cache for current weather has been cleared.");
    }



    @Operation(summary = "Cache donde se elimina el cache el pronostico", responses = {
            @ApiResponse(responseCode = "200", description = "Todo Correcto" )
    })
    @DeleteMapping("/delete/cacheForecast")
    public ResponseEntity<String> clearAllCacheForecast(){
        weatherService.clearCacheForecast();
        return ResponseEntity.ok("Cache for current forecast has been cleared.");
    }

    

    @Operation(summary = "Cache donde se elimina el cache de la geolocalizacion", responses = {
            @ApiResponse(responseCode = "200", description = "Todo Correcto" )
    })
    @DeleteMapping("/delete/cacheGeolocation")
    public ResponseEntity<String> clearAllCacheGeolocation(){
        weatherService.clearCacheGeolocation();
        return ResponseEntity.ok("Cache for current geolocation has been cleared.");
    }
}
