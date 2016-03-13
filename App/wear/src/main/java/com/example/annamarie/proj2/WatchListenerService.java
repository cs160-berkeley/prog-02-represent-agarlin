package com.example.annamarie.proj2;

/**
 * Created by AnnaMarie on 3/3/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;


import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class WatchListenerService extends WearableListenerService {
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watch messages
    private static final String go = "/GO";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
        //use the 'path' field in sendmessage to differentiate use cases
        //(here, fred vs lexy)
        System.out.println(messageEvent.getPath());
        if( messageEvent.getPath().equalsIgnoreCase( go ) ) {
            String n = new String(messageEvent.getData());
            ArrayList<String> arr1 = new ArrayList<>();
            ArrayList<String> arr2 = new ArrayList<>();
            System.out.println(n);
            for (String each: n.split("-")) {
                int i = 0;
                for (String t: each.split(",")) {
                    if (i % 2 != 0) {
                        arr1.add(t);
                    } else {
                        arr2.add(t);
                    }
                    i += 1;
                }
            }
            System.out.println("arr1: " + arr1);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("names", arr1);
            bundle.putStringArrayList("parties", arr2);
            Intent intent = new Intent(this, MainView.class );
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            Log.d("T", "about to start watch MainView");
            startActivity(intent);
        }
        else {
            super.onMessageReceived( messageEvent );
        }

    }
}