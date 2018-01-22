package com.cheapflights.utils;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonReader {
    static Gson gson = new Gson();


    public static <T> T readJson(String fileName, Class<T> c) {
        try {
            com.google.gson.stream.JsonReader jsonReader = new com.google.gson.stream.JsonReader(new FileReader(fileName));
            return gson.fromJson(jsonReader, c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
