package com.example.annamarie.proj2;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    EditText ed1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString().equals("94709")) {
                    Toast.makeText(getApplicationContext(), "Looking for Representatives...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, RepresentativeListActivity.class);
                    startActivity(intent);
                    Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                    sendIntent.putExtra("GO", "go");
                    startService(sendIntent);
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
                startActivity(intent);
                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                sendIntent.putExtra("GO", "go");
                startService(sendIntent);
            }
        });
    }
//    public void repList(View view) {
//        Intent intent = new Intent(this, RepresentativeListActivity.class);
//        startActivity(intent);
//    }

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
}
