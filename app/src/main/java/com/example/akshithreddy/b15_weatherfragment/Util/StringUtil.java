package com.example.akshithreddy.b15_weatherfragment.Util;

/**
 * Created by akshithreddy on 9/29/2015.
 */
public class StringUtil {
    public static String getValidString(String city){
        if(city!=null&&city.length()>0){
            city=city.trim();
            city=city.replace(" ","_");
        }
        return city;
    }
}
