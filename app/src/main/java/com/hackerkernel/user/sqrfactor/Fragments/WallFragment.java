package com.hackerkernel.user.sqrfactor.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hackerkernel.user.sqrfactor.Adapters.WallQuestionsAdapter;
import com.hackerkernel.user.sqrfactor.Constants.BundleConstants;
import com.hackerkernel.user.sqrfactor.Constants.Constants;
import com.hackerkernel.user.sqrfactor.Constants.SPConstants;
import com.hackerkernel.user.sqrfactor.Constants.ServerConstants;
import com.hackerkernel.user.sqrfactor.Network.MyVolley;
import com.hackerkernel.user.sqrfactor.Pojo.WallQuestionClass;
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

public class WallFragment extends Fragment {
    private static final String TAG = "WallFragment";

    private RecyclerView mWallRecyclerView;

    List<WallQuestionClass> mWallQuestions;
    WallQuestionsAdapter mWallQuestionsAdapter;

    ProgressBar mPb;
    LinearLayout mContentLayout;
    MySharedPreferences mSp;
    RequestQueue mRequestQueue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_wall, container, false);

        mPb = view.findViewById(R.id.pb);
        mContentLayout = view.findViewById(R.id.content_layout);
        mSp = MySharedPreferences.getInstance(getActivity());
        mRequestQueue = MyVolley.getInstance().getRequestQueue();

        mWallRecyclerView = view.findViewById(R.id.wall_rv);

        mWallRecyclerView.setHasFixedSize(true);
        mWallRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mWallRecyclerView.setNestedScrollingEnabled(false);

        mWallQuestions = new ArrayList<>();
        mWallQuestionsAdapter = new WallQuestionsAdapter(getActivity(), mWallQuestions);
        mWallRecyclerView.setAdapter(mWallQuestionsAdapter);

        Intent i = getActivity().getIntent();
        String slug = i.getStringExtra(BundleConstants.SLUG);

        competitionWallApi(slug);

        return view;
    }

    private void competitionWallApi(String slug) {
        mPb.setVisibility(View.VISIBLE);
        mContentLayout.setVisibility(View.GONE);
        if (!NetworkUtil.isNetworkAvailable()) {
            Toast.makeText(getActivity(), "No internet", Toast.LENGTH_SHORT).show();
            mPb.setVisibility(View.GONE);
            mContentLayout.setVisibility(View.VISIBLE);
            return;
        }

        mRequestQueue = MyVolley.getInstance().getRequestQueue();

        Log.d(TAG, "competitionWallApi: slug  = " + slug);
        Log.d(TAG, "competitionDetailApi: url = " + ServerConstants.COMPETITION_DETAIL + slug + "/wall");
        StringRequest request = new StringRequest(Request.Method.GET, ServerConstants.COMPETITION_DETAIL + slug + "/wall", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mPb.setVisibility(View.GONE);
                mContentLayout.setVisibility(View.VISIBLE);

                Log.d(TAG, "onResponse: competition wall response = " + response);

                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONObject competitionWallObj = responseObject.getJSONObject("competition_wall");


                    JSONArray questionsArray = competitionWallObj.getJSONArray("user_competition_wall_question");

                    if (questionsArray.length() > 0) {

                        for (int i = 0; i < questionsArray.length(); i++) {
                            JSONObject singleObject = questionsArray.getJSONObject(i);

                            String description = singleObject.getString("subject");
                            String id = singleObject.getString("id");


                            WallQuestionClass wallQuestion = new WallQuestionClass(description, "No One", id);
                            mWallQuestions.add(wallQuestion);
                            mWallQuestionsAdapter.notifyDataSetChanged();

                        }

                    } else {
                        Toast.makeText(getActivity(), "No prize found", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPb.setVisibility(View.GONE);
                mContentLayout.setVisibility(View.VISIBLE);
                NetworkUtil.handleSimpleVolleyRequestError(error, getActivity());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                final Map<String, String> headers = new HashMap<>();
                headers.put(getString(R.string.accept), getString(R.string.application_json));
                headers.put(getString(R.string.authorization), Constants.AUTHORIZATION_HEADER + mSp.getKey(SPConstants.API_KEY));
//                headers.put("Content-Type", contentType);
                return headers;
            }

//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//
//                params.put(getString(R.string.mobile), "8517841635");
//                params.put(getString(R.string.otp), "202317");
//
//                return params;
//            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(request);
    }

}
