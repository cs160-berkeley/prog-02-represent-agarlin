package com.example.annamarie.proj2;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "FSC0ehVZRYCkYWkfdrK8k3I5O";
    private static final String TWITTER_SECRET = "9lLbmMg4poq8SNKqKtP26WLcFJAI7HMIRtLSGXl0yUeopN9sOj";


    Button b1,b2;
    EditText ed1;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    String lat;
    String longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        b1=(Button)findViewById(R.id.button1);
        ed1=(EditText)findViewById(R.id.editText1);
        b2 = (Button) findViewById(R.id.button2);
        ed1.setHint("Enter zip code here");
        ed1.setTextColor(Color.WHITE);


        ed1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ed1.setText("");
                return false;
            }
        });

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zip = ed1.getText().toString();
                if(zip != null) { //make sure to add a check that the zipcode is in this country too
                    Toast.makeText(getApplicationContext(), "Looking for Representatives...", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("zipcode", zip);
                    Intent intent = new Intent(MainActivity.this, RepresentativeListActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
//                    Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                    sendIntent.putExtra("GO", RepresentativeListActivity.n);
//                    startService(sendIntent);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Sorry, we don't have the representatives for that area yet!",Toast.LENGTH_SHORT).show();


                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Looking for Representatives...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, RepresentativeListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("latitude", lat);
                bundle.putString("longitude", longi);
                intent.putExtras(bundle);
                startActivity(intent);
//                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntent.putExtra("GO", RepresentativeListActivity.n);
//                startService(sendIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}

    @Override
    public void onConnected(Bundle bundle) {
        System.out.println("in map connected");
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            System.out.println("last location");
            lat = String.valueOf(mLastLocation.getLatitude());
            longi = String.valueOf(mLastLocation.getLongitude());
        }
    }
}
