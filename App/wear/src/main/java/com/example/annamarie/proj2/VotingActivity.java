package com.example.annamarie.proj2;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

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
        if (Fragment_Pager.x) {
            b.setText("33.3%");
            r.setText("65.5%");
            c.setText("Hamburger County");
        }
    }

}
