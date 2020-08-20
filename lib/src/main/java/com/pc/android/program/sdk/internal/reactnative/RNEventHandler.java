package com.pc.android.program.sdk.internal.reactnative;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.pc.android.program.sdk.internal.event.EventManager;
import com.pc.android.program.sdk.utils.JsonUtils;

import java.util.Map;

public class RNEventHandler extends ReactContextBaseJavaModule {

    private static ReactApplicationContext reactContext;


    RNEventHandler(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "RNEventHandler";
    }

    @ReactMethod
    public void onEvent(String event) {
        Map<String, String> map = JsonUtils.decode(event, Map.class);
        EventManager.instance.fireEvent(map.get("event"), map.get("data"));
    }
}
