package com.example.annamarie.proj2;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v4.view.ViewPager;
import android.support.wearable.view.CardFrame;
import android.util.Log;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainView extends FragmentActivity {
    String tag = this.getClass().getSimpleName();
    Button b;
    private ShakeListener mShaker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        Log.i(tag, "onCreate");
        /** Getting a reference to the ViewPager defined the layout file */
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        /** Getting fragment manager */
        FragmentManager fm =  getSupportFragmentManager();


        /** Instantiating FragmentPagerAdapter */
        Fragment_Pager pagerAdapter = new Fragment_Pager(fm, getBaseContext());

        /** Setting the pagerAdapter to the pager object */
        pager.setAdapter(pagerAdapter);
        final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//
//        mShaker = new ShakeListener(this);
//        System.out.println("mshake:" + mShaker);
//        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
//            public void onShake() {
//                System.out.println("aacel");
//                TextView county = (TextView) findViewById(R.id.county);
//                TextView red = (TextView) findViewById(R.id.red);
//                TextView blue = (TextView) findViewById(R.id.blue);
//                CardFrame c1 = (CardFrame) findViewById(R.id.cardFrame1);
//                CardFrame c2 = (CardFrame) findViewById(R.id.cardFrame2);
//                CardFrame c3 = (CardFrame) findViewById(R.id.cardFrame3);
//                blue.setText("58.0%");
//                red.setText("40.9%");
//                county.setText("Essex County");
//                int i = R.id.cardFrame1;
//                int j = R.id.cardFrame2;
//                int k = R.id.cardFrame3;
//                c1.setId(j);
//                c2.setId(k);
//                c3.setId(i);
//                String[] zips = {"07042", "00000", "87777", "34344", "33442"};
//                Toast.makeText(getApplicationContext(), "New Zip Code: " + zips[new Random().nextInt(zips.length)], Toast.LENGTH_SHORT).show();
//            }
//        });
        }

    }


