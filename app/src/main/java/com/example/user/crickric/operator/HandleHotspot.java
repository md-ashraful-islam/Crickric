package com.example.user.crickric.operator;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import java.lang.reflect.Method;

/**
 * Created by User on 15-Mar-18.
 */

public class HandleHotspot {

    public static void createHotspot(Context context){
        if(isWifiOn(context))
            return;
        toggleHotspot(context);
    }
    public static void closeHotspot(Context context){
        if(isWifiOn(context)){
            toggleHotspot(context);
            System.out.println("Closing wifi");
        }
    }


    //check whether wifi hotspot on or off
    public static boolean isWifiOn(Context context) {
        WifiManager wifimanager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        try {
            Method method = wifimanager.getClass().getDeclaredMethod("isWifiOn");
            method.setAccessible(true);
            return (Boolean) method.invoke(wifimanager);
        } catch (Throwable ignored) {
        }
        return false;
    }

    // toggle wifi hotspot on or off
    public static boolean toggleHotspot(Context context) {
        WifiManager wifimanager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        WifiConfiguration wificonfiguration = null;
        try {
            // if WiFi is on, turn it off
            if (isWifiOn(context)) {
                wifimanager.setWifiEnabled(false);
            }
            Method method = wifimanager.getClass().getMethod("setWifiApEnabled",
                    WifiConfiguration.class, boolean.class);
            method.invoke(wifimanager, wificonfiguration, !isWifiOn(context));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}