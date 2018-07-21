package com.hackerkernel.user.sqrfactor.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hackerkernel.user.sqrfactor.Adapters.AttachmentsAdapter;
import com.hackerkernel.user.sqrfactor.Adapters.JuryAdapter;
import com.hackerkernel.user.sqrfactor.Adapters.PartnersAdapter;
import com.hackerkernel.user.sqrfactor.Adapters.PrizesAdapter;
import com.hackerkernel.user.sqrfactor.Constants.BundleConstants;
import com.hackerkernel.user.sqrfactor.Constants.Constants;
import com.hackerkernel.user.sqrfactor.Constants.SPConstants;
import com.hackerkernel.user.sqrfactor.Constants.ServerConstants;
import com.hackerkernel.user.sqrfactor.Network.MyVolley;
import com.hackerkernel.user.sqrfactor.Pojo.AttachmentClass;
import com.hackerkernel.user.sqrfactor.Pojo.JuryClass;
import com.hackerkernel.user.sqrfactor.Pojo.PartnerClass;
import com.hackerkernel.user.sqrfactor.Pojo.PrizeClass;
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

public class InfoFragment extends Fragment {
    private static final String TAG = "InfoFragment";

    Button mRegisterButton;
    RecyclerView mPrizesRecyclerView;
    RecyclerView mJuryRecyclerView;
    RecyclerView mPartnersRecyclerView;
    RecyclerView mAttachmentsRecyclerview;

    List<PrizeClass> mPrizes;
    PrizesAdapter mPrizesAdapter;

    List<JuryClass> mJuryList;
    JuryAdapter mJuryAdapter;

    List<PartnerClass> mPartners;
    PartnersAdapter mPartnersAdapter;

    List<AttachmentClass> mAttachments;
    AttachmentsAdapter mAttachmentsAdapter;


    private WebView mWebView;

    private ProgressBar mPb;
    private LinearLayout mContentLayout;
    private MySharedPreferences mSp;
    private RequestQueue mRequestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_info, container, false);

        mWebView = view.findViewById(R.id.info_webview);
        mPrizesRecyclerView = view.findViewById(R.id.info_prize_rv);
        mJuryRecyclerView = view.findViewById(R.id.info_jury_rv);
        mPartnersRecyclerView = view.findViewById(R.id.info_partner_rv);
        mAttachmentsRecyclerview = view.findViewById(R.id.info_attachments_rv);
        mRegisterButton = view.findViewById(R.id.register_btn);

        mPrizesRecyclerView.setHasFixedSize(true);
        mPrizesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPrizesRecyclerView.setNestedScrollingEnabled(false);

        mPrizes = new ArrayList<>();
        mPrizesAdapter = new PrizesAdapter(getActivity(), mPrizes);
        mPrizesRecyclerView.setAdapter(mPrizesAdapter);

        mJuryRecyclerView.setHasFixedSize(true);
        mJuryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mJuryRecyclerView.setNestedScrollingEnabled(false);

        mJuryList = new ArrayList<>();
        mJuryAdapter = new JuryAdapter(getActivity(), mJuryList);
        mJuryRecyclerView.setAdapter(mJuryAdapter);

        mPartnersRecyclerView.setHasFixedSize(true);
        mPartnersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPartnersRecyclerView.setNestedScrollingEnabled(false);

        mPartners = new ArrayList<>();
        mPartnersAdapter = new PartnersAdapter(getActivity(), mPartners);
        mPartnersRecyclerView.setAdapter(mPartnersAdapter);


        mAttachmentsRecyclerview.setHasFixedSize(true);
        mAttachmentsRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAttachmentsRecyclerview.setNestedScrollingEnabled(false);

        mAttachments = new ArrayList<>();
        mAttachmentsAdapter = new AttachmentsAdapter(getActivity(), mAttachments);
        mAttachmentsRecyclerview.setAdapter(mAttachmentsAdapter);

        mPb = view.findViewById(R.id.pb);
        mContentLayout = view.findViewById(R.id.content_layout);
        mSp = MySharedPreferences.getInstance(getActivity());
        mRequestQueue = MyVolley.getInstance().getRequestQueue();

        Intent i = getActivity().getIntent();
        String slug = i.getStringExtra(BundleConstants.SLUG);

        competitionDetailApi(slug);


        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RegistrationDialog().show(getFragmentManager(), "");
            }
        });

        return view;

    }

    private void competitionDetailApi(String slug) {
        mPb.setVisibility(View.VISIBLE);
        mContentLayout.setVisibility(View.GONE);
        if (!NetworkUtil.isNetworkAvailable()) {
            Toast.makeText(getActivity(), "No internet", Toast.LENGTH_SHORT).show();
            mPb.setVisibility(View.GONE);
            mContentLayout.setVisibility(View.VISIBLE);
            return;
        }

        mRequestQueue = MyVolley.getInstance().getRequestQueue();
        Log.d(TAG, "SHIVANI: url = " + ServerConstants.COMPETITION_DETAIL + slug);
        StringRequest request = new StringRequest(Request.Method.GET, ServerConstants.COMPETITION_DETAIL + slug, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mPb.setVisibility(View.GONE);
                mContentLayout.setVisibility(View.VISIBLE);
                Log.d(TAG, "onResponse: competition detail response = " + response);
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONObject competitionDetailObj = responseObject.getJSONObject("competition_detail");


                    String brief = competitionDetailObj.getString("brief");
                    Log.d("brief: ", brief);
                    initWebView(brief);
                    JSONArray prizesArray = competitionDetailObj.getJSONArray("users_competitions_award");

                    if (prizesArray.length() > 0) {

                        for (int i = 0; i < prizesArray.length(); i++) {
                            JSONObject singleObject = prizesArray.getJSONObject(i);

                            String id = singleObject.getString("id");
                            String type = singleObject.getString("award_type");
                            String amount = singleObject.getString("award_amount");
                            String currency = singleObject.getString("award_currency");
                            String extra = singleObject.getString("award_extra");

                            PrizeClass prize = new PrizeClass(id, type, amount, currency, extra);
                            mPrizes.add(prize);
                            mPrizesAdapter.notifyDataSetChanged();

                        }

                    } else {
                        Toast.makeText(getActivity(), "No prize found", Toast.LENGTH_SHORT).show();
                    }

                    JSONArray juryArray = competitionDetailObj.getJSONArray("user_competition_jury");
                    Log.d("juryArray: ", juryArray + "");

                    if (juryArray.length() != 0) {

                        for (int i = 0; i < juryArray.length(); i++) {
                            JSONObject singleObject = juryArray.getJSONObject(i);
                            if (singleObject.has("user")) {
                                Log.d(TAG, "singleObject.: " + singleObject.getString("user"));
                                if (!singleObject.getString("user").toString().equals("null")) {
                                    JSONObject userObject = singleObject.getJSONObject("user");
                                    Log.d(TAG, "userObject: " + userObject.toString());


                                    String id = singleObject.getString("id");
                                    String fullName = singleObject.getString("jury_fullname");
                                    String imageUrl = userObject.getString("profile");


                                    JuryClass jury = new JuryClass(id, fullName, imageUrl);
                                    mJuryList.add(jury);
                                    mJuryAdapter.notifyDataSetChanged();
                                }


                            }


                        }

                    } else {
                        Toast.makeText(getActivity(), "No Jury", Toast.LENGTH_SHORT).show();
                    }

                    JSONArray partnersArray = competitionDetailObj.getJSONArray("user_competition_partner");

                    if (partnersArray.length() > 0) {

                        for (int i = 0; i < partnersArray.length(); i++) {
                            JSONObject singleObject = partnersArray.getJSONObject(i);

                            if (singleObject.has("user")) {
                                Log.d(TAG, "singleObject.: " + singleObject.getString("user"));
                                if (!singleObject.getString("user").toString().equals("null")) {

                                    JSONObject userObject = singleObject.getJSONObject("user");

                                    String id = singleObject.getString("id");
                                    String name = singleObject.getString("partner_name");
                                    String imageUrl = userObject.getString("profile");


                                    PartnerClass partner = new PartnerClass(id, name, imageUrl);
                                    mPartners.add(partner);
                                    mPartnersAdapter.notifyDataSetChanged();
                                }
                            }
                        }

                    } else {
                        Toast.makeText(getActivity(), "No Report found", Toast.LENGTH_SHORT).show();
                    }

                    JSONArray attachmentsArray = competitionDetailObj.getJSONArray("user_competition_attachment");

                    if (attachmentsArray.length() > 0) {

                        for (int i = 0; i < attachmentsArray.length(); i++) {
                            JSONObject singleObject = attachmentsArray.getJSONObject(i);

                            String id = singleObject.getString("id");
                            String attachmentUrl = singleObject.getString("attach_documents");


                            AttachmentClass attachment = new AttachmentClass(id, attachmentUrl);
                            mAttachments.add(attachment);
                            mAttachmentsAdapter.notifyDataSetChanged();
                        }

                    } else {
                        Toast.makeText(getActivity(), "No Report found", Toast.LENGTH_SHORT).show();
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
                headers.put(getString(R.string.authorization), "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjYyODI4YjM3MjJjMzcyN2ZiNThiOGU0YWRjMDg3ZDgzNzFhYzdhNGFlNzhlODc4NWY2NzI3Y2EyM2JmMTEyOGYzNGVkODA4OGU4MjQ5MjFmIn0.eyJhdWQiOiIzIiwianRpIjoiNjI4MjhiMzcyMmMzNzI3ZmI1OGI4ZTRhZGMwODdkODM3MWFjN2E0YWU3OGU4Nzg1ZjY3MjdjYTIzYmYxMTI4ZjM0ZWQ4MDg4ZTgyNDkyMWYiLCJpYXQiOjE1MzIwNzM0NjQsIm5iZiI6MTUzMjA3MzQ2NCwiZXhwIjoxNTYzNjA5NDY0LCJzdWIiOiIxMDYiLCJzY29wZXMiOltdfQ.kGgIH4IStNn0mkjj3pXs85YfpUNH37vKM8v3QUn3Vbljwds0eXQXacX76-r_uA7pbT_1b0HQmdOxxqdOGHPJ8ahweNe6wEbSgsSvWuhJ-niHb2Hf6y4KWlIK0AI6ktDvKfNFKKlAXbH-mML0N9Te6tge_MEkMtzhoy8RptlDrycJR1aoLr5UR0z3KUuynRqmh5hind79OD5vM6zq_4I2-VPvFe_WNRRsb63HDLZaGunZwTUPCE0SSW3Xo30zDPsgLBjZpwkt_8fIJzd9N1GPPEmjwFqHuggDlthh81zR3r-dGN9GdGpzWtIsLouyt-b0Rel2olgAjFkrR8kIjvShv1S4sgdFMlP-90gpdHBTbQ6rL4gsBkcPxC1veaxTGFxvGds81N3jQidjZh2_wnZ-OSBBYcxndDlxuQt5WceDQA7M4muX1y714gAJEL698qX9tXPFPXhlKBzP5KvPEfxRg_neIhEWf5KnuX40dnWAkD5EDdwJWi2n5BqBBMqjvt57EpPIlHovgSP2HT3bp8Y-rCbvdwGTfb-d2cItofndZfxzHTstRqzrgsKgNuI1ZsJudLIo_9fpulsoga9-88eJXzaOJMhHf0nmymuxOfaB3m0HHWw1wLpVbS7dOpN0H_z5a1HFqugBpDfXECsiPlJtZ8D8wJ5zhbKLYer_89OtYrc");
                //headers.put(getString(R.string.authorization), Constants.AUTHORIZATION_HEADER + mSp.getKey(SPConstants.API_KEY));
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

    private void initWebView(String brief) {

        mWebView.loadData(brief, "text/html", null);

    }

}
