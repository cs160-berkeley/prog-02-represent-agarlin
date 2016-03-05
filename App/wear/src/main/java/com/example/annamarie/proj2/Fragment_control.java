package com.example.annamarie.proj2;

/**
 * Created by AnnaMarie on 3/3/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_control extends Fragment {
    Button b;
    String tag = this.getClass().getSimpleName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(tag, "onCreate");
        super.onCreate(savedInstanceState);
        /** Getting the arguments to the Bundle object */
        Bundle data = getArguments();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(tag, "onCreateView");

        View view = inflater.inflate(R.layout.activity_main_view,container, false );


        return view;
    }

}