package com.example.annamarie.proj2;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v4.view.ViewPager;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.CardFrame;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.text.Layout;
import android.util.Log;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainView extends FragmentActivity {
    static String x;
    static Boolean yes =false;
    ArrayList<String> zipcodes;
    String tag = this.getClass().getSimpleName();
    static String county;
    private ShakeListener mShaker;
    static ArrayList<String> arr1 = new ArrayList<>();
    static ArrayList<String> arr2 = new ArrayList<>();
    public static String rep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        Log.i(tag, "onCreate");
        /** Getting a reference to the ViewPager defined the layout file */
//        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            arr1 = extras.getStringArrayList("names");
            arr2 = extras.getStringArrayList("parties");

        }
        System.out.println("creating");
        county = arr2.get(0) + ", " + arr1.get(0);
        System.out.println(county);
        System.out.println("arr2: " + arr2);
        arr2.remove(arr2.get(0));
        arr1.remove(arr1.get(0));
//        arr1.remove(arr1.get(0));
        System.out.println("arr1: " + arr1);
        /** Getting fragment manager */
//        FragmentManager fm = getSupportFragmentManager();
//        final TextView zi = (TextView) findViewById(R.id.textView5);
        zipcodes = new ArrayList<>();
        x = "";
        InputStream is = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
        yes = false;
        try {
            is = getInput();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            int x = 0;
            while ((line = reader.readLine()) != null) {
                String[] RowData = line.split(",");
//                System.out.println(line);
                if (x > 0 & RowData.length > 0) {
                    String zip = RowData[0];
                    zipcodes.add(zip);
                }
                x += 1;
                // do something with "data" and "value"
            }
        }
        catch (IOException ex) {
            // handle exception
            ex.printStackTrace();
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(zipcodes);
        mShaker = new ShakeListener(getApplicationContext());
        final String z = zipcodes.get(new Random().nextInt(zipcodes.size()));
        x = z;
//        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
//
//            public void onShake() {
////                zi.setText(z);
//                yes = true;
////                Toast.makeText(getApplicationContext(), "New Zip Code: " + z, Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(getApplicationContext(), WatchToPhoneService.class);
//                getApplicationContext().startService(i);
//            }
//
//        });

        final GridViewPager pager1 = (GridViewPager) findViewById(R.id.pager1);
        pager1.setAdapter(new SampleGridPagerAdapter(this, getFragmentManager()));
        Button b = (Button) findViewById(R.id.detail);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("sent message");
                Intent i = new Intent(getApplicationContext(), WatchToPhoneService.class);
                getApplicationContext().startService(i);
            }
        });

        /** Instantiating FragmentPagerAdapter */
//        Fragment_Pager pagerAdapter = new Fragment_Pager(fm, getBaseContext());

        /** Setting the pagerAdapter to the pager object */
//        pager.setAdapter(pagerAdapter);
        Button b1 =  (Button) findViewById(R.id.vote);
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainView.this, VotingActivity.class);
                startActivity(intent);
            }
        });
    }
    public InputStream getInput()
            throws FileNotFoundException {
        getResources().getIdentifier("zip",
                "raw", getPackageName());
//        File initialFile = new File("wear/src/main/res/raw/zip");
//        System.out.println(initialFile);
//        InputStream targetStream = new FileInputStream(initialFile);
        InputStream ins = getResources().openRawResource(
                getResources().getIdentifier("zip",
                        "raw", getPackageName()));
        return ins;
    }


    public class SampleGridPagerAdapter extends FragmentGridPagerAdapter {

        private final Context mContext;
        private List mRows;

        public SampleGridPagerAdapter(Context ctx, FragmentManager f) {

            super(f);
            mContext = ctx;
        }

        final int[] BG_IMAGES = new int[]{
//                R.drawable.bboxer;
//                R.drawable.barbaralee;
        };

        // A simple container for static data in each page
        private class Page {
            // static resources
            String titleRes;
            String textRes;
            int iconRes = 1;

        }

        @Override
        public Fragment getFragment(int row, int col) {
            Page page = PAGES[row][col];
            System.out.println(col);
            System.out.println(arr1);
            String title = arr2.get(col);
            String text = arr1.get(col);
//            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            if (text.equals("D")) {
                text = "Democrat";
            }  else if (text.equals("R")) {
                text = "Republican";
            } else {
                text = "Independent";
            }
            CardFragment fragment = CardFragment.create(title, text);
//            fragment.setCardMarginTop(10);
//            fragment.setContentPadding(0,5,0,5);
//            fragment.setExpansionDirection(CardFragment.EXPAND_UP);
            // Advanced settings (card gravity, card expansion/scrolling)
//            fragment.setCardGravity(page.cardGravity);
//            fragment.setExpansionEnabled(page.expansionEnabled);
//            fragment.setExpansionDirection(page.expansionDirection);
//            fragment.setExpansionFactor(page.expansionFactor);
            rep = title;
//            View view = inflater.inflate(R.layout.activity_main_view, null);


            return fragment;
        }
//        @Override
//        public Drawable getBackgroundForRow(int row) {
//            return mContext.getResources().getDrawable(
//                    (BG_IMAGES[row % BG_IMAGES.length]), null);
//        }

        // Create a static set of pages in a 2D array

        private final Page[][] PAGES = new Page[1][arr1.size()];

        @Override
        public int getRowCount() {
            return PAGES.length;
        }
// Obtain the number of pages (horizontal)

        public int getColumnCount(int rowNum) {
            return PAGES[rowNum].length;
        }

    }
}



