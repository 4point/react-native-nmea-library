package com.reactlibrary;

import android.location.OnNmeaMessageListener;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import javax.annotation.Nullable;

public class NmeaMessageListener implements OnNmeaMessageListener {

    private ReactApplicationContext reactContext;

    public NmeaMessageListener(ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
    }

    public void onNmeaMessage(String message, long timestamp) {
        WritableMap params = Arguments.createMap();
        params.putString("message", message);
        params.putDouble("timestamp", timestamp);

        this.sendEvent(reactContext, "onNmeaReceive", params);
    }

    private void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }
}