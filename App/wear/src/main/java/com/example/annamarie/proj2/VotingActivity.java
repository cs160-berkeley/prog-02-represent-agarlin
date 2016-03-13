package com.example.annamarie.proj2;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class VotingActivity extends Activity {

    public static TextView b;
    public static TextView r;
    public static TextView c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);
        b = (TextView) findViewById(R.id.blue);
        r = (TextView) findViewById(R.id.red);
        c = (TextView) findViewById(R.id.county);
        System.out.println(MainView.county);
        c.setText(MainView.county);
        double obama=0;
        double romney=0;

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray arr = obj.getJSONArray("results");

//            for (int i = 0; i < arr.length(); i++) {
                JSONObject j_inside = arr.getJSONObject(0);
//                String yes = j_inside.toString();
                JSONObject arr_in = j_inside.getJSONObject(MainView.county);
//                System.out.println(yes);
                if (arr_in != null) {
                    System.out.println("got county");
                     obama = arr_in.getDouble("obama");
                     romney = arr_in.getDouble("romney");
                }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(obama);
        r.setText(Double.toString(romney) + "%");
        b.setText(Double.toString(obama) + "%");
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("county_data.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

}
