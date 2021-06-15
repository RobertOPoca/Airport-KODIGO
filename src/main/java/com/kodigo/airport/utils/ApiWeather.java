package com.kodigo.airport.utils;

public class ApiWeather implements ApiConnection{
    String connection;
    @Override
    public void createConnection() {
        connection = "Connection Success";

    }

    @Override
    public String getConnection() {
        return connection;
    }
}
