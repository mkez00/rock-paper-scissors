package com.mkez00.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkez00.model.Action;

import java.io.IOException;
import java.util.Date;

/**
 * Created by michaelkezele on 2017-10-25.
 */
public class GeneralHelper {
    /**
     * Calculates the distance in km between two lat/long points
     * using the haversine formula
     */
    public static double distanceBetweenGeoPoints(
            double lat1, double lng1, double lat2, double lng2) {
        int r = 6371; // average radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = r * c;
        return d;
    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static long absoluteMillisecondsBetweenDates(long date1, long date2){
        return Math.abs(date1-date2);
    }

    public static long getCurrentTime(){
        return new Date().getTime();
    }

    public static long addSecondsToCurrentTime(int seconds) {
        seconds = seconds * 1000;
        return getCurrentTime()+seconds;
    }

    public static String serialize(Action action){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(action);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Action deserialize(String string){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(string, Action.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
