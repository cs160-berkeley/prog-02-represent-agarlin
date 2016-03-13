package com.example.annamarie.proj2;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;



import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class PhoneListenerService extends WearableListenerService {

    private static final String rep = "/REP";
//    private static final String Dianne = "/Senator Dianne Feinstein";
//    private static final String Barb = "/Representative Barbara Lee";
    private static final String shake = "/SHAKE";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
        //use the 'path' field in sendmessage to differentiate use cases
        //(here, fred vs lexy)
        System.out.println(messageEvent.getPath());

        if( messageEvent.getPath().equalsIgnoreCase( rep ) ) {
//            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            System.out.println(rep);
            Intent intent = new Intent(this, RepresentativeDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            String name = new String(messageEvent.getData());
            System.out.println(name);
            ArrayList<String> array = RepresentativeListActivity.hash.get(name);
            Bundle bundle = new Bundle();
            bundle.putString("bio", array.get(3));
            bundle.putString("name", name);
            String party;
            if (array.get(2).equals("D")) {
                party = "Democrat";
            }  else if (array.get(2).equals("R")) {
                party = "Republican";
            } else {
                party = "Independent";
            }
            bundle.putString("party", party);
            bundle.putString("bills", array.get(0));
            bundle.putString("committees", array.get(1));
            bundle.putString("tenure", "1993-Present");
            bundle.putString("term", array.get(4));
            intent.putExtras(bundle);
            Log.d("T", "about to start watch MainActivity with Representative: " + name);
            startActivity(intent);
        }
        if( messageEvent.getPath().equalsIgnoreCase( shake ) ) {
            String value = new String(messageEvent.getData());
            Context context = getApplicationContext();

            System.out.println("in shake");

            Intent intent = new Intent(this, RepresentativeListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("zipcode", value);
            RepresentativeListActivity.shake = true;
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            System.out.println("value: " + value);
            Log.d("T", "about to start watch MainActivity with shake");
            intent.putExtras(bundle);
            startActivity(intent);
//            RepresentativeListActivity.shake = false;
        }else {
            super.onMessageReceived( messageEvent );
        }

    }
}
