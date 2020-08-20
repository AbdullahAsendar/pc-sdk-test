package com.pc.android.program.sdk.utils;

import com.google.gson.Gson;

public class JsonUtils {
    private static Gson GSON = new Gson();

    public static <T> String encode(T object) {
        return GSON.toJson(object);
    }

    public static <T> T decode(String json, Class<T> type) {
        return GSON.fromJson(json, type);
    }
}
