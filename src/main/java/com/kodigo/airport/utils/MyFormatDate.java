package com.kodigo.airport.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MyFormatDate {
<<<<<<< HEAD

    public static String splitDate(String date){

        String found = "";

        String regex = "[0-9]{4}(\\-)[0-9]{2}(\\-)[0-9]{2}";
=======
    private MyFormatDate(){}
    public static String splitDate(String date){
        String found = "";
        String regex = "[0-9]{4}(-)[0-9]{2}(-)[0-9]{2}";
>>>>>>> alpha
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        while(matcher.find()) {
            found = matcher.group();
        }
        return found;
    }
<<<<<<< HEAD

    public static String splitTime(String time){

        String found = "";

=======
    public static String splitTime(String time){
        String found = "";
>>>>>>> alpha
        String regex = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        while(matcher.find()){
            found = matcher.group();
        }
        return found;
    }
}