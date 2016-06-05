package com.example.akshithreddy.b15_weatherfragment.Parser;

import com.google.gson.Gson;

/**
 * Created by akshithreddy on 9/29/2015.
 */
public class ParserUtil {
    public static Info getParsedData(String rawJson){
        Gson gson=new Gson();
        Info info=gson.fromJson(rawJson,Info.class);
        return info;

    }
}
