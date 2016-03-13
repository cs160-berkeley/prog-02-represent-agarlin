package com.example.annamarie.proj2;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RepresentativeDetailActivity extends AppCompatActivity {

    public String name;
    public String party;
    public String bills;
    public String committees;
    public int photo;
    public String tenure;
    public String term;
    public String bio;
    Button b1;
    Button b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_representative_detail);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
            party = extras.getString("party");
            bills = extras.getString("bills");
            committees = extras.getString("committees");
            photo = extras.getInt("image");
//            tenure = extras.getString("tenure");
            term = extras.getString("term");
            bio = extras.getString("bio");
        }

        ImageView imageView = (ImageView) findViewById(R.id.main_image);
        Picasso.with(getApplicationContext()).load("https://theunitedstates.io/images/congress/225x275/" + bio + ".jpg").into(imageView);
        final TextView name1 = (TextView) findViewById(R.id.textView4);
        final TextView party1 = (TextView) findViewById(R.id.textView5);
        final TextView bi = (TextView) findViewById(R.id.bi);
        final TextView comm = (TextView) findViewById(R.id.comm);
        final TextView term1 = (TextView) findViewById(R.id.textView9);
        b1 = (Button) findViewById(R.id.bills);
        b2 = (Button) findViewById(R.id.committees);
        name1.setText(name);
        party1.setText(party);
//        tenure1.setText(tenure);
        term1.setText(term);
        bi.setText(bills);
        comm.setText(committees);
//        comm.setVisibility(View.GONE);
//        bi.setVisibility(View.GONE);
//        final TextView bill1 = (TextView) findViewById(R.id.textView11);
//        final TextView c1 = (TextView) findViewById(R.id.textView13);
//        bill1.setText(bills);
//        c1.setText(committees);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bi.setVisibility((bi.getVisibility() == View.VISIBLE)
//                        ? View.GONE : View.VISIBLE);
//            }});
//
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                comm.setVisibility((comm.getVisibility() == View.VISIBLE)
//                        ? View.GONE : View.VISIBLE);
//            }});
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), RepresentativeListActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

}
