package com.kodigo.airport.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyFormatDate {

    private String format;

    public MyFormatDate(String format){
        this.format = format;
    }

    public String splitDate(String date){

        String found = "";

        if(format.equals("yyyy-mm-dd") || format.equals("yyyy/mm/dd")){
            String regex = "[0-9]{4}(\\-|\\/)[0-9]{2}(\\-|\\/)[0-9]{2}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(date);
            while(matcher.find()){
                found = matcher.group();
            }
        }
        return found;
    }

    public String splitTime(String time){

        String found = "";

        if(format.equals("hh:mm:ss")){
            String regex = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(time);
            while(matcher.find()){
                found = matcher.group();
            }
        }
        return found;
    }
}
