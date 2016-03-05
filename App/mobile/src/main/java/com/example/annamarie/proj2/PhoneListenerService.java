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

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class PhoneListenerService extends WearableListenerService {

    private static final String Barbara = "/Senator Barbara Boxer";
    private static final String Dianne = "/Senator Dianne Feinstein";
    private static final String Barb = "/Representative Barbara Lee";
    private static final String shake = "/SHAKE";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
        //use the 'path' field in sendmessage to differentiate use cases
        //(here, fred vs lexy)
        System.out.println(messageEvent.getPath());

        if( messageEvent.getPath().equalsIgnoreCase( Dianne ) ) {
//            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            System.out.println(Dianne);
            Intent intent = new Intent(this, RepresentativeDetailActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            Bundle bundle = new Bundle();
            bundle.putInt("image", R.drawable.dianne);
            bundle.putString("name", "Senator Dianne Feinstein");
            bundle.putString("party", "Democrat");
            bundle.putString("bills",
                    "Senate Committee on Appropriations\n" +
                            "Ranking Member, Subcommittee on Energy and Water Development\n" +
                            "Member, Subcommittee on Agriculture, Rural Development, Food and Drug Administration, and Related Agencies\n" +
                            "Member, Subcommittee on Commerce, Justice, Science, and Related Agencies\n" +
                            "Member, Subcommittee on Department of Defense\n" +
                            "Member, Subcommittee on Department of the Interior, Environment, and Related Agencies\n" +
                            "Member, Subcommittee on Transportation, Housing and Urban Development, and Related Agencies\n" +
                            "Senate Committee on the Judiciary\n" +
                            "Member, Subcommittee on Immigration and the National Interest\n" +
                            "Member, Subcommittee on Oversight, Agency Action, Federal Rights and Federal Courts\n" +
                            "Member, Subcommittee on Privacy, Technology and the Law\n" +
                            "Senate Committee on Rules and Administration\n" +
                            "United States Senate Caucus on International Narcotics Control");
            bundle.putString("committees","S. 2568: California Desert Conservation, Off-Road Recreation, and Renewable Energy Act\n" +
                    "S. 2552: Interstate Threats Clarification Act of 2016\n" +
                    "S. 2533: California Long-Term Provisions for Water Supply and Short-Term Provisions for Emergency Drought Relief ...\n" +
                    "S. 2442: A bill to authorize the use of passenger facility charges at an airport ...\n" +
                    "S. 2422: Fiscal Year 2016 Department of Veterans Affairs Seismic Safety and Construction Authorization Act\n" +
                    "S. 2372: Requiring Reporting of Online Terrorist Activity Act\n" +
                    "S. 2337: Visa Waiver Program Security Enhancement Act");
            bundle.putString("tenure","1993-Present");
            bundle.putString("term", "January 3, 2019");
            intent.putExtras(bundle);
            Log.d("T", "about to start watch MainActivity with Representative: Dianne Feinstein");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( Barbara )) {
//            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, RepresentativeDetailActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            Bundle bundle = new Bundle();
            bundle.putInt("image", R.drawable.dianne);
            bundle.putString("name", "Senator Barbara Boxer");
            bundle.putString("party", "Democrat");
            bundle.putString("bills",
                    "Senate Committee on Appropriations\n" +
                            "Ranking Member, Subcommittee on Energy and Water Development\n" +
                            "Member, Subcommittee on Agriculture, Rural Development, Food and Drug Administration, and Related Agencies\n" +
                            "Member, Subcommittee on Commerce, Justice, Science, and Related Agencies\n" +
                            "Member, Subcommittee on Department of Defense\n" +
                            "Member, Subcommittee on Department of the Interior, Environment, and Related Agencies\n" +
                            "Member, Subcommittee on Transportation, Housing and Urban Development, and Related Agencies\n" +
                            "Senate Committee on the Judiciary\n" +
                            "Member, Subcommittee on Immigration and the National Interest\n" +
                            "Member, Subcommittee on Oversight, Agency Action, Federal Rights and Federal Courts\n" +
                            "Member, Subcommittee on Privacy, Technology and the Law\n" +
                            "Senate Committee on Rules and Administration\n" +
                            "United States Senate Caucus on International Narcotics Control");
            bundle.putString("committees","S. 2568: California Desert Conservation, Off-Road Recreation, and Renewable Energy Act\n" +
                    "S. 2552: Interstate Threats Clarification Act of 2016\n" +
                    "S. 2533: California Long-Term Provisions for Water Supply and Short-Term Provisions for Emergency Drought Relief ...\n" +
                    "S. 2442: A bill to authorize the use of passenger facility charges at an airport ...\n" +
                    "S. 2422: Fiscal Year 2016 Department of Veterans Affairs Seismic Safety and Construction Authorization Act\n" +
                    "S. 2372: Requiring Reporting of Online Terrorist Activity Act\n" +
                    "S. 2337: Visa Waiver Program Security Enhancement Act");
            bundle.putString("tenure","1993-Present");
            bundle.putString("term", "January 3, 2019");
            intent.putExtras(bundle);
            Log.d("T", "about to start watch MainActivity with Representative: Barbara Boxer");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( Barb )) {
//            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, RepresentativeDetailActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            Bundle bundle = new Bundle();
            bundle.putInt("image", R.drawable.dianne);
            bundle.putString("name", "Representative Barbara Lee");
            bundle.putString("party", "Democrat");
            bundle.putString("bills",
                    "Senate Committee on Appropriations\n" +
                            "Ranking Member, Subcommittee on Energy and Water Development\n" +
                            "Member, Subcommittee on Agriculture, Rural Development, Food and Drug Administration, and Related Agencies\n" +
                            "Member, Subcommittee on Commerce, Justice, Science, and Related Agencies\n" +
                            "Member, Subcommittee on Department of Defense\n" +
                            "Member, Subcommittee on Department of the Interior, Environment, and Related Agencies\n" +
                            "Member, Subcommittee on Transportation, Housing and Urban Development, and Related Agencies\n" +
                            "Senate Committee on the Judiciary\n" +
                            "Member, Subcommittee on Immigration and the National Interest\n" +
                            "Member, Subcommittee on Oversight, Agency Action, Federal Rights and Federal Courts\n" +
                            "Member, Subcommittee on Privacy, Technology and the Law\n" +
                            "Senate Committee on Rules and Administration\n" +
                            "United States Senate Caucus on International Narcotics Control");
            bundle.putString("committees","S. 2568: California Desert Conservation, Off-Road Recreation, and Renewable Energy Act\n" +
                    "S. 2552: Interstate Threats Clarification Act of 2016\n" +
                    "S. 2533: California Long-Term Provisions for Water Supply and Short-Term Provisions for Emergency Drought Relief ...\n" +
                    "S. 2442: A bill to authorize the use of passenger facility charges at an airport ...\n" +
                    "S. 2422: Fiscal Year 2016 Department of Veterans Affairs Seismic Safety and Construction Authorization Act\n" +
                    "S. 2372: Requiring Reporting of Online Terrorist Activity Act\n" +
                    "S. 2337: Visa Waiver Program Security Enhancement Act");
            bundle.putString("tenure","1993-Present");
            bundle.putString("term", "February 6, 2019");
            intent.putExtras(bundle);
            Log.d("T", "about to start watch MainActivity with Representative: Barbara Lee");
            startActivity(intent);
        }
        if( messageEvent.getPath().equalsIgnoreCase( shake ) ) {
            String value = new String(messageEvent.getData());
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            System.out.println("in shake");
            Toast toast = Toast.makeText(context, "New Zip Code: " + value, duration);
            toast.show();
            Intent intent = new Intent(this, RepresentativeListActivity.class);
            RepresentativeListActivity.shake = true;
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.d("T", "about to start watch MainActivity with Representative: Dianne Feinstein");
            startActivity(intent);
//            RepresentativeListActivity.shake = false;
        }else {
            super.onMessageReceived( messageEvent );
        }

    }
}
