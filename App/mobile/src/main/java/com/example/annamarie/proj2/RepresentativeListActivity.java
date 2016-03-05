package com.example.annamarie.proj2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;

import com.example.annamarie.proj2.dummy.DummyContent;

import java.util.List;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Toast;


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
    int p;
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
        setContentView(R.layout.activity_representative_list);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle(getTitle());

        final GridView gridview = (GridView) findViewById(R.id.gridView1);
        gridview.setAdapter(new ImageAdapter(this));

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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

    }



//    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
//        recyclerView.setAdapter(new ImageAdapter.SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
//    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater layoutInflater;
        private int counts;


        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return items.size();
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

//                imageView.setLayoutParams(new FrameLayout.LayoutParams(450, 450));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                imageView.setPadding(8, 8, 8, 8);
            imageView.setColorFilter(Color.argb(145, 30, 29, 29));
            imageView.setImageResource(items.get(position).image);

            final TextView name = (TextView) convertView.findViewById(R.id.textgrid);
            final TextView party = (TextView) convertView.findViewById(R.id.textgrid1);
            final TextView tweet = (TextView) convertView.findViewById(R.id.twit);
            System.out.println(shake);
            if (which == 0 && shake) {
                name.setText(items.get(1).name);
                String p = items.get(1).party;
                party.setText(p);
                String t = items.get(1).tweet;
                tweet.setText(t);
            } else if (which == 1 && shake) {
                name.setText(items.get(2).name);
                String p = items.get(2).party;
                party.setText(p);
                String t = items.get(2).tweet;
                tweet.setText(t);
            } else if (which == 2 && shake) {
                name.setText(items.get(0).name);
                String p = items.get(0).party;
                party.setText(p);
                String t = items.get(0).tweet;
                tweet.setText(t);
            } else {
                name.setText(items.get(position).name);
                String p = items.get(position).party;
                party.setText(p);
                String t = items.get(position).tweet;
                tweet.setText(t);
            }
            button = (Button) convertView.findViewById(R.id.More_button);
            final int i = items.get(position).image;
            if (button != null) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RepresentativeListActivity.this, RepresentativeDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("image", i);
                        bundle.putString("name", items.get(position).name);
                        bundle.putString("party", items.get(position).party);
                        bundle.putString("bills", items.get(position).bills);
                        bundle.putString("committees",items.get(position).committees);
                        bundle.putString("tenure",items.get(position).tenure);
                        bundle.putString("term",items.get(position).term);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                which += 1;
            }

//            final TextView txt= (TextView) convertView.findViewById(R.id.textgrid);
//
//            txt.setText(exer[position]);


//            mbutton.setOnClickListener(
//                    new View.OnClickListener() {
//                        public void onClick(View view) {
//                            String name = getResources().getStringArray(R.array.exercises)[position];
//                            Double num = map.get(name);
//                            if (num != null) {
//                                txt.setText(exer[position] + Double.toString(cal * num));
//                            }
//
//                        }
//                    });
            return convertView;
        }
    }

//    public class SimpleItemRecyclerViewAdapter
//            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
//
//        private final List<DummyContent.DummyItem> mValues;
//
//        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
//            mValues = items;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.representative_list_content, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mItem = mValues.get(position);
//            holder.mIdView.setText(mValues.get(position).id);
////            holder.mContentView.setText(mValues.get(position).content);


        }

//        @Override
//        public int getItemCount() {
//            return mValues.size();
//        }

//        public class ViewHolder extends RecyclerView.ViewHolder {
//            public final View mView;
//            public final TextView mIdView;
//            public final TextView mContentView;
//            public DummyContent.DummyItem mItem;
//
//            public ViewHolder(View view) {
//                super(view);
//                mView = view;
//                mIdView = (TextView) view.findViewById(R.id.id);
//                mContentView = (TextView) view.findViewById(R.id.content);
//            }
//
//            @Override
//            public String toString() {
//                return super.toString() + " '" + mContentView.getText() + "'";
//            }
//        }
//}
//}
