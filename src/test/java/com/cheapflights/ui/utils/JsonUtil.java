package com.cheapflights.ui.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonUtil {
    static Gson gson = new Gson();

    public static <T> T readJson(String fileName, Class<T> c) {
        try {
            JsonReader jsonReader = new JsonReader(new FileReader(fileName));
            return gson.fromJson(jsonReader, c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }




}
