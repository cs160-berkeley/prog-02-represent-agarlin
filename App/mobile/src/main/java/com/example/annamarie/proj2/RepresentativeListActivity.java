package com.example.annamarie.proj2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

import com.example.annamarie.proj2.dummy.DummyContent;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import java.util.List;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import io.fabric.sdk.android.Fabric;


/**
 * An activity representing a list of Representatives. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DummyContent} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RepresentativeListActivity extends AppCompatActivity {
    Button button;
    static String n = " ";
    private TwitterLoginButton loginButton;
    int p;
    ProgressBar progressBar;
    String zipcode;
    public static ArrayList<String> names;
    public static ArrayList<String> parties;
    private static ArrayList<String> addresses;
    private static ArrayList<String> twitters;
    private static ArrayList<String> emails;
    private static ArrayList<String> bio_ids;
    private static ArrayList<String> committees;
    private static ArrayList<String> bills;
    private static ArrayList<String> tenure;
    String bio;
    ImageAdapter adapter;
    String lon;
    String lat;
    String tweet;
    static Boolean once;
    static Boolean send = false;
    Boolean comm = false;
    Boolean re = false;
    private static final String TWITTER_KEY = "key";
    private static final String TWITTER_SECRET = "secret";
    Boolean success = false;
    static String county;
    static HashMap<String, ArrayList<String>> hash;
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    public int which = 0;
    public static Boolean shake=false;
    List<DummyContent.DummyItem> items = DummyContent.ITEMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new Twitter(authConfig));

//        if (shake) {
//            finish();
//            startActivity(getIntent());
//        }
        setContentView(R.layout.activity_representative_list);
        Bundle extras = this.getIntent().getExtras();
        names = new ArrayList<>();
        parties = new ArrayList<>();
        addresses = new ArrayList<>();
        twitters = new ArrayList<>();
        emails = new ArrayList<>();
        bio_ids = new ArrayList<>();
        committees = new ArrayList<>();
        bills = new ArrayList<>();
        tweet = new String();
        county = new String();
        hash = new HashMap<>();
        tenure = new ArrayList<>();

        if (extras != null) {
            System.out.println("extras not null");
            zipcode = extras.getString("zipcode");
            lat = extras.getString("latitude");
            lon = extras.getString("longitude");
        }
//        if (shake) {
//            Toast toast = Toast.makeText(getApplicationContext(), "New Zip Code: " + zipcode, Toast.LENGTH_SHORT);
//            toast.show();
//        }
        System.out.println("zipcode:" + zipcode);
        new RetrieveCounty().execute();
        final GridView gridview = (GridView) findViewById(R.id.gridView1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new RetrieveRepresentatives().execute();
        adapter = new ImageAdapter(this);
        gridview.setAdapter(adapter);

        View recyclerView = findViewById(R.id.representative_list);
//        assert recyclerView != null;
//        setupRecyclerView((RecyclerView) recyclerView);


        if (findViewById(R.id.representative_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        once = true;
        if (shake) {
            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
            sendIntent.putExtra("GO", n);
            startService(sendIntent);
        }
//        finish();
//        startActivity(getIntent());




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    public class RetrieveCounty extends AsyncTask<Void, Void, String> {

        private Exception exception;
        //        static final String API_KEY = "c14e376c6746485391bc36de8f61cf60";

        static final String API_URL = "http://maps.googleapis.com/maps/api/geocode/json?address=";
        static final String LAT_URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=";

        protected void onPreExecute() {
            System.out.println("got to pre execute");
//            progressBar.setVisibility(View.VISIBLE);
//            responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
            // Do some validation here

            try {
                URL url;
                if (lat != null) {
                    url = new URL(LAT_URL +  lat + "," + lon + "&sensor=false");

                } else {
                    url = new URL(API_URL + zipcode + "&region=us");
                }

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    System.out.println("got to background stuff");
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            System.out.println("got to post");
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
//            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
//            responseView.setText(response);

            try {

                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
//                String requestID = object.getString("requestId");
//                int likelihood = object.getInt("likelihood");
                String all = "";
                JSONArray reps = object.getJSONArray("results");
                JSONObject r = reps.getJSONObject(0);
                JSONArray address_components = r.getJSONArray("address_components");
                String coun = "";
                String state = "";
                for (int i = 0; i < address_components.length(); i++) {
                    JSONObject zero2 = address_components.getJSONObject(i);
                    JSONArray mtypes = zero2.getJSONArray("types");
                    String t = mtypes.getString(0);

                    if (t.equalsIgnoreCase("administrative_area_level_2")) {
                        coun = zero2.getString("short_name");
                    }
                    if (t.equalsIgnoreCase("administrative_area_level_1")) {
                        state = zero2.getString("short_name");
                    }
                    System.out.println("zero: " + zero2);
                    System.out.println(county);

//                adapter.notifyDataSetChanged();
                }
                county = coun + "," + state;
                System.out.println(county);
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
//            }
            }
        }}


    public class RetrieveRepresentatives extends AsyncTask<Void, Void, String> {

        private Exception exception;
        static final String API_KEY = "c14e376c6746485391bc36de8f61cf60";
        static final String API_URL = "congress.api.sunlightfoundation.com/legislators/locate?";
        static final String COMM_URL = "congress.api.sunlightfoundation.com/committees?member_ids=";
        static final String BILL_URL = "congress.api.sunlightfoundation.com/bills?sponsor_id=";

        protected void onPreExecute() {
            System.out.println("got to pre execute");
//            progressBar.setVisibility(View.VISIBLE);
//            responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
            // Do some validation here

            try {
                URL url;
                if (lat != null) {
                    url = new URL("http://" + API_URL + "latitude=" + lat + "&longitude=" + lon + "&apikey=" + API_KEY);
                } else {
                    url = new URL("http://" + API_URL + "zip=" + zipcode + "&apikey=" + API_KEY);
                }
//                if (comm) {
//                    url = new URL("http://" + COMM_URL + bio + "&apikey=" + API_KEY);
//                }
//                if (bill) {
//                    url = new URL("http://" + BILL_URL + bio + "&apikey=" + API_KEY);
//                }
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    System.out.println("got to background stuff");
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            System.out.println("got to post");
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
//            progressBar.setVisibility(View.GONE);

            Log.i("INFO", response);
//            responseView.setText(response);

            try {

                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                    JSONArray reps = object.getJSONArray("results");
                    for (int i = 0; i < reps.length(); i++) {
                        JSONObject r = reps.getJSONObject(i);
                        String name = r.getString("title") + " " + r.getString("first_name") + " " + r.getString("last_name");
                        System.out.println(name);
                        String email = r.getString("oc_email");
                        String website = r.getString("website");
                        String party = r.getString("party");
                        String twitter_id = r.getString("twitter_id");
                        String bio_id = r.getString("bioguide_id");
                        String ten = r.getString("term_end");
                        names.add(name);
                        emails.add(email);
                        addresses.add(website);
                        parties.add(party);
                        twitters.add(twitter_id);
                        bio_ids.add(bio_id);
                        tenure.add(ten);
                    }

                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
//            }
            }
        }
    }
    public class GetComm extends AsyncTask<String, Void, String> {

        private Exception exception;
        static final String API_KEY = "c14e376c6746485391bc36de8f61cf60";
        static final String API_URL = "congress.api.sunlightfoundation.com/legislators/locate?";
        static final String COMM_URL = "congress.api.sunlightfoundation.com/committees?member_ids=";

        protected void onPreExecute() {
            System.out.println("got to pre execute");
//            progressBar.setVisibility(View.VISIBLE);
//            responseView.setText("");
        }

        protected String doInBackground(String... args) {
            // Do some validation here
            String a = args[0];
            try {
                URL url;
                url = new URL("http://" + COMM_URL + a + "&apikey=" + API_KEY);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    System.out.println("got to background stuff");
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            System.out.println("got to post");
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
//            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
//            responseView.setText(response);

            try {

                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
//                String requestID = object.getString("requestId");
//                int likelihood = object.getInt("likelihood");
                    String all = "";
                    JSONArray reps = object.getJSONArray("results");
                    System.out.println("bio " + bio);
                    System.out.println(reps);
                    for (int i = 0; i < reps.length(); i++) {
                        JSONObject r = reps.getJSONObject(i);
                        all += "\n" + r.getString("name");
                    }
                    committees.add(all);

                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
//            }
            }
        }
    }

    public class GetBills extends AsyncTask<String, Void, String> {

        private Exception exception;
        static final String API_KEY = "c14e376c6746485391bc36de8f61cf60";
//        static final String API_URL = "congress.api.sunlightfoundation.com/legislators/locate?";
//        static final String COMM_URL = "congress.api.sunlightfoundation.com/committees?member_ids=";
        static final String BILL_URL = "congress.api.sunlightfoundation.com/bills?sponsor_id=";

        protected void onPreExecute() {
            System.out.println("got to pre execute");
//            progressBar.setVisibility(View.VISIBLE);
//            responseView.setText("");
        }

        protected String doInBackground(String... args) {
            // Do some validation here
            bio = args[0];
            try {
                URL url;
                url = new URL("http://" + BILL_URL + bio + "&apikey=" + API_KEY);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    System.out.println("got to background stuff");
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            System.out.println("got to post");
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
//            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
//            responseView.setText(response);

            try {

                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
//                String requestID = object.getString("requestId");
//                int likelihood = object.getInt("likelihood");
                    String all = "";
                    JSONArray reps = object.getJSONArray("results");
                    for (int i = 0; i < 5; i++) {
                        JSONObject r = reps.getJSONObject(i);
                        if (r.getString("short_title").equals("null")) {
                            all += "\n" + r.getString("official_title") + "\nIntroduced: " + r.getString("introduced_on");
                        } else {
                            all += "\n" + r.getString("short_title") + " \n" + "Introduced: " + r.getString("introduced_on");
                        }

                    }
                    bills.add(all);

                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
//            }
            }
        }
    }


    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater layoutInflater;
        private int counts;


        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return names.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }


        // create a new ImageView for each item referenced by the Adapter
        public View getView(final int position, View convertView, ViewGroup parent) {
            layoutInflater = LayoutInflater.from(getApplicationContext());
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.representative_list_content, null);
                // if it's not recycled, initialize some attributes
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image);

//            imageView.setLayoutParams(new FrameLayout.LayoutParams(1100, 1000));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                imageView.setPadding(8, 8, 8, 8);
            imageView.setColorFilter(Color.argb(145, 30, 29, 29));
//            imageView.setImageResource(items.get(position).image);

            final TextView name = (TextView) convertView.findViewById(R.id.textgrid);
            final TextView party = (TextView) convertView.findViewById(R.id.textgrid1);
            final TextView twee = (TextView) convertView.findViewById(R.id.twit);
            final TextView e = (TextView) convertView.findViewById(R.id.textgrid4);
            final TextView web  = (TextView) convertView.findViewById(R.id.textgrid3);
            web.setClickable(true);
            web.setMovementMethod(LinkMovementMethod.getInstance());
            e.setClickable(true);
            e.setMovementMethod(LinkMovementMethod.getInstance());
            TwitterCore.getInstance().logInGuest(new Callback<AppSession>() {
                @Override
                public void success(Result<AppSession> appSessionResult) {
                    AppSession session = appSessionResult.data;
                    TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient(session);
                    twitterApiClient.getStatusesService().userTimeline(null,twitters.get(position), 1, null, null, false, false, false, true, new Callback<List<Tweet>>() {
                        @Override
                        public void success(Result<List<Tweet>> listResult) {
                            for(Tweet tweet: listResult.data) {
                                twee.setText(tweet.text);
                            }
                        }
                        @Override
                        public void failure(TwitterException e) {
                            e.printStackTrace();
                        }
                    });
                }
                @Override
                public void failure(TwitterException e) {
                    e.printStackTrace();
                }
            });
            if (names.size() == 0) {
                name.setText(" ");
            } else {
                System.out.println(names.get(position));
                name.setText(names.get(position));
            }
            if (parties.size() == 0) {
                party.setText(" ");
            } else {
                String p = parties.get(position);
                if (p.equals("D")) {
                    p = "Democrat";
                } else if (p.equals("R")) {
                    p = "Republican";
                }
                party.setText(p);
            }
            if (bio_ids.size() == names.size() & !re) {
                for (int i = 0; i < bio_ids.size(); i++) {
                    new GetBills().execute(bio_ids.get(i));
                    new GetComm().execute(bio_ids.get(i));
                }
                re = true;
            }
            System.out.println(emails);
            if (emails.size() > 0) {
                String a = "<a href='" + emails.get(position) + "'> Email </a>";
                String x = a;
                e.setText(Html.fromHtml(x));
            }
            if (addresses.size() > 0) {
                String a = "<a href='" + addresses.get(position) + "'> Website </a>";
                System.out.println("href:" + a);
                String x = a;
                web.setText(Html.fromHtml(x));

            }
            if (bio_ids.size() > 0) {
                Picasso.with(mContext).load("https://theunitedstates.io/images/congress/225x275/" + bio_ids.get(position) + ".jpg").into(imageView);
            }
            if (committees.size() >= 3) {
                ArrayList<String> a = new ArrayList<>();
                a.add(bills.get(position));
                a.add(committees.get(position));
                a.add(parties.get(position));
                a.add(bio_ids.get(position));
                a.add(tenure.get(position));
                hash.put(names.get(position),a);
            }

            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);

            if (RepresentativeListActivity.names.size() >= 3 & once) {
                System.out.println("will send");
                n = county;
                for (int i = 0; i < RepresentativeListActivity.names.size(); i++) {
                    n += "-" + RepresentativeListActivity.names.get(i) + ",";
                    n +=  RepresentativeListActivity.parties.get(i);
                }
                System.out.println("n with county:" + n);
                once = false;
                send = true;
                startService(sendIntent);
            }


            button = (Button) convertView.findViewById(R.id.More_button);
//            final int i = items.get(position).image;
            if (button != null) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RepresentativeListActivity.this, RepresentativeDetailActivity.class);
                        Bundle bundle = new Bundle();
//                        bundle.putInt("image", i);
                        bundle.putString("name", names.get(position));
                        String p = parties.get(position);
                        if (p.equals("D")) {
                            p = "Democrat";
                        } else if (p.equals("R")) {
                            p = "Republican";
                        }
                        bundle.putString("party", p);
                        if (committees.size() > position) {
                            bundle.putString("committees", committees.get(position));
                        } else {
                            bundle.putString("committees", " ");
                        }
                        if (bills.size() > position) {
                            bundle.putString("bills", bills.get(position));
                        } else {
                            bundle.putString("bills", " ");
                        }
                        if (bio_ids.size() > position) {
                            bundle.putString("bio", bio_ids.get(position));
                        }
                        if (tenure.size() > position) {
                            bundle.putString("term", tenure.get(position));
                        }


//                        bundle.putString("tenure", items.get(position).tenure);
//                        bundle.putString("term", items.get(position).term);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                which += 1;
            }
            return convertView;
        }
    }




        }


