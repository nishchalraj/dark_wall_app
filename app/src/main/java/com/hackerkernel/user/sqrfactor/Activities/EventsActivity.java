package com.hackerkernel.user.sqrfactor.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hackerkernel.user.sqrfactor.Adapters.EventsAdapter;
import com.hackerkernel.user.sqrfactor.Constants.Constants;
import com.hackerkernel.user.sqrfactor.Constants.SPConstants;
import com.hackerkernel.user.sqrfactor.Constants.ServerConstants;
import com.hackerkernel.user.sqrfactor.Network.MyVolley;
import com.hackerkernel.user.sqrfactor.Pojo.EventClass;
import com.hackerkernel.user.sqrfactor.R;
import com.hackerkernel.user.sqrfactor.Storage.MySharedPreferences;
import com.hackerkernel.user.sqrfactor.Utils.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventsActivity extends AppCompatActivity {
    private static final String TAG = "EventsActivity";

    Toolbar mToolbar;
    FloatingActionButton mFab;
    RecyclerView mEventsRecyclerView;

    private List<EventClass> mEvents;

    private EventsAdapter mAdapter;

    private ProgressBar mPb;
    private MySharedPreferences mSp;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.back_arrow);

        mToolbar.setNavigationIcon(R.drawable.back_arrow);
        mPb = findViewById(R.id.pb);
        mRequestQueue = MyVolley.getInstance().getRequestQueue();
        mSp = MySharedPreferences.getInstance(this);

        mEvents = new ArrayList<>();

        mEventsRecyclerView = findViewById(R.id.events_rv);

        mEventsRecyclerView.setHasFixedSize(true);
        mEventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new EventsAdapter(this, mEvents);

        mEventsRecyclerView.setAdapter(mAdapter);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

        eventsApi();



        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PostEventActivity.class);
                startActivity(i);
            }
        });
    }

    private void eventsApi() {

        mPb.setVisibility(View.VISIBLE);
        mEventsRecyclerView.setVisibility(View.GONE);

        if (!NetworkUtil.isNetworkAvailable()) {
            Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
            mPb.setVisibility(View.GONE);
            mEventsRecyclerView.setVisibility(View.VISIBLE);
            return;
        }

        mRequestQueue = MyVolley.getInstance().getRequestQueue();

        StringRequest request = new StringRequest(Request.Method.GET, ServerConstants.EVENT_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mPb.setVisibility(View.GONE);
                mEventsRecyclerView.setVisibility(View.VISIBLE);
                Log.d(TAG, "onResponse: Event list response = " + response);

                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONArray eventsArray = responseObject.getJSONArray("events");

                    for (int i = 0; i < eventsArray.length(); i++) {
                        JSONObject singleObject = eventsArray.getJSONObject(i);
                        String id = singleObject.getString("id");
                        String slug = singleObject.getString("slug");
                        String coverUrl = singleObject.getString("cover_image");
                        String title = singleObject.getString("event_title");
                        String description = singleObject.getString("description");
                        String venue = singleObject.getString("venue");
                        String startTimeAgo = singleObject.getString("created_at");

                        EventClass event = new EventClass(id, slug, coverUrl, title, description, venue, startTimeAgo);
                        mEvents.add(event);
                        mAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPb.setVisibility(View.GONE);
                mEventsRecyclerView.setVisibility(View.VISIBLE);
                NetworkUtil.handleSimpleVolleyRequestError(error, EventsActivity.this);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                final Map<String, String> headers = new HashMap<>();
                headers.put(getString(R.string.accept), getString(R.string.application_json));
                headers.put(getString(R.string.authorization), "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjYyODI4YjM3MjJjMzcyN2ZiNThiOGU0YWRjMDg3ZDgzNzFhYzdhNGFlNzhlODc4NWY2NzI3Y2EyM2JmMTEyOGYzNGVkODA4OGU4MjQ5MjFmIn0.eyJhdWQiOiIzIiwianRpIjoiNjI4MjhiMzcyMmMzNzI3ZmI1OGI4ZTRhZGMwODdkODM3MWFjN2E0YWU3OGU4Nzg1ZjY3MjdjYTIzYmYxMTI4ZjM0ZWQ4MDg4ZTgyNDkyMWYiLCJpYXQiOjE1MzIwNzM0NjQsIm5iZiI6MTUzMjA3MzQ2NCwiZXhwIjoxNTYzNjA5NDY0LCJzdWIiOiIxMDYiLCJzY29wZXMiOltdfQ.kGgIH4IStNn0mkjj3pXs85YfpUNH37vKM8v3QUn3Vbljwds0eXQXacX76-r_uA7pbT_1b0HQmdOxxqdOGHPJ8ahweNe6wEbSgsSvWuhJ-niHb2Hf6y4KWlIK0AI6ktDvKfNFKKlAXbH-mML0N9Te6tge_MEkMtzhoy8RptlDrycJR1aoLr5UR0z3KUuynRqmh5hind79OD5vM6zq_4I2-VPvFe_WNRRsb63HDLZaGunZwTUPCE0SSW3Xo30zDPsgLBjZpwkt_8fIJzd9N1GPPEmjwFqHuggDlthh81zR3r-dGN9GdGpzWtIsLouyt-b0Rel2olgAjFkrR8kIjvShv1S4sgdFMlP-90gpdHBTbQ6rL4gsBkcPxC1veaxTGFxvGds81N3jQidjZh2_wnZ-OSBBYcxndDlxuQt5WceDQA7M4muX1y714gAJEL698qX9tXPFPXhlKBzP5KvPEfxRg_neIhEWf5KnuX40dnWAkD5EDdwJWi2n5BqBBMqjvt57EpPIlHovgSP2HT3bp8Y-rCbvdwGTfb-d2cItofndZfxzHTstRqzrgsKgNuI1ZsJudLIo_9fpulsoga9-88eJXzaOJMhHf0nmymuxOfaB3m0HHWw1wLpVbS7dOpN0H_z5a1HFqugBpDfXECsiPlJtZ8D8wJ5zhbKLYer_89OtYrc");
                //headers.put(getString(R.string.authorization), Constants.AUTHORIZATION_HEADER + mSp.getKey(SPConstants.API_KEY));
                Log.d(TAG, "getHeaders: api key = " + mSp.getKey(SPConstants.API_KEY));
//                headers.put("Content-Type", contentType);
                return headers;
            }

        };
        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(request);
    }
}
