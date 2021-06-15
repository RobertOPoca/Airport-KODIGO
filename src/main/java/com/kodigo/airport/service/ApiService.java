package com.kodigo.airport.service;

import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.utils.ApiConnection;
import com.kodigo.airport.utils.ApiWeather;
import org.springframework.stereotype.Service;

public class ApiService implements IApiService{
    ApiConnection apiConnection;
    public ApiService(ApiConnection apiConnection){
        this.apiConnection = apiConnection;
    }

    public ResponseApi<String> getWeather(){
        apiConnection.createConnection();
        return new ResponseApi<>(true, "Success", "cloudy");
    }
}
