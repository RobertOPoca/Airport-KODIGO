package com.kodigo.airport.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MyFormatDate {

    private MyFormatDate(){}

    private static String findMatch(String regex, String str){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find()? matcher.group() : "";
    }
    public static String splitDate(String date){

        String regex = "[0-9]{4}(\\-)[0-9]{2}(\\-)[0-9]{2}";
        return findMatch(regex, date);
    }

    public static String splitTime(String time){

        String regex = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
        return findMatch(regex, time);
    }
}