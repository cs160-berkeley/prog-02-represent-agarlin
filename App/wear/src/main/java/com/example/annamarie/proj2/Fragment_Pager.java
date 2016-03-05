package com.example.annamarie.proj2;

/**
 * Created by AnnaMarie on 3/3/2016.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.wearable.view.CardFrame;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

public class Fragment_Pager extends FragmentPagerAdapter {
    private static Context context;
    public static Button b;
    private ShakeListener mShaker;
    final int PAGE_COUNT = 3;
    public static boolean x;
    public static String z;
    public Fragment_Pager(FragmentManager fm, Context context) {
        super(fm);
        // TODO Auto-generated constructor stub
        this.context=context;
    }



    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // Your current main fragment showing how to send arguments to fragment
//                Fragment_control myFragment = new Fragment_control();
//                Bundle data = new Bundle();
//                data.putInt("current_page", position+1);
//                myFragment.setArguments(data);
                return new MyFirstFragment();
            case 1:
                // Calling a Fragment without sending arguments
                return new MySecondFragment();
            case 2:
                return new MyThirdFragment();
            default:
                return null;
        }
    }

    public static class MyFirstFragment extends Fragment {
        private ShakeListener mShaker;
        @Override
        public View onCreateView( LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.first_fragment, null);
            View view2 = inflater.inflate(R.layout.activity_voting, null);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView v = (TextView) view.findViewById(R.id.text_3);
//                    String text = v.getText().toString();
//                    if ( !TextUtils.isEmpty(text) ) {
//                        mAdapter.add(text);
//                        mAdapter.notifyDataSetChanged();
                    System.out.println("sent message");
                    Intent i = new Intent(context, WatchToPhoneService.class);
                    context.startService(i);

//                    }
                }
            });

            b = (Button) view.findViewById(R.id.vote);
            System.out.println(b);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyFirstFragment.this.getActivity(), VotingActivity.class);
                    startActivity(intent);
                }
            });
            mShaker = new ShakeListener(context);
            Boolean x = false;
            final List<View> t = new ArrayList<View>();
            t.add(view);
            t.add(view2);
            final List<String> zi = new ArrayList<String>();
            mShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {

                public void onShake() {
                    String[] zips = {"07042", "00000", "87777", "34344", "33442"};
                    String z = zips[new Random().nextInt(zips.length)];
                    zi.add(z);
                    change(t.get(0), t.get(1), zi);
                    Toast.makeText(context, "New Zip Code: " + z, Toast.LENGTH_SHORT).show();
                }

            });
            return view;

        }
        public void change(View view, View view2, List zi) {
            TextView zip = (TextView) view.findViewById(R.id.textView5);
            TextView name = (TextView) view.findViewById(R.id.text_1);
            ImageView image = (ImageView) view.findViewById(R.id.image_1);
            name.setText("Senator Barbara Boxer");
            x = true;
            System.out.println(x);
            image.setImageResource(R.drawable.bboxer);
            zip.setText((String) zi.get(0));
            z = (String)zi.get(0);
            Intent i = new Intent(context, WatchToPhoneService.class);
            context.startService(i);
        }
    }


    public static class MySecondFragment extends Fragment {
        private ShakeListener mShaker;
        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.second_fragment, null);
            b = (Button) view.findViewById(R.id.vote);
            System.out.println(b);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MySecondFragment.this.getActivity(), VotingActivity.class);
                    startActivity(intent);
                }
            });
            return view;
        }

    }

    public static class MyThirdFragment extends Fragment {
        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.third_fragment, null);
            b = (Button) view.findViewById(R.id.vote);
            System.out.println(b);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyThirdFragment.this.getActivity(), VotingActivity.class);
                    startActivity(intent);
                }
            });
            return view;
        }

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return PAGE_COUNT;
    }

}
