package org.javeriana.sd.streaming.app.util;

import com.google.gson.Gson;

public class GsonUtils {

    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static Object toObject(String json, Class aClass) {
        Gson gson = new Gson();
        return gson.fromJson(json, aClass);
    }
}
