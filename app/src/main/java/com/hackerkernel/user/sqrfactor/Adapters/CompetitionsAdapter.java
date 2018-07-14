package com.hackerkernel.user.sqrfactor.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.hackerkernel.user.sqrfactor.Activities.ParticipateActivity;
import com.hackerkernel.user.sqrfactor.Constants.BundleConstants;
import com.hackerkernel.user.sqrfactor.Constants.ServerConstants;
import com.hackerkernel.user.sqrfactor.Pojo.CompetitionClass;
import com.hackerkernel.user.sqrfactor.R;
import com.hackerkernel.user.sqrfactor.Storage.MySharedPreferences;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionsAdapter.MyViewHolder> {
    private static final String TAG = "CompetitionsAdapter";
    private Context mContext;
    private List<CompetitionClass> mCompetitions;

    private RequestQueue mRequestQueue;

    private MySharedPreferences mSp;


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView competitionNameTV;
        TextView startTimeAgoTV;
        TextView lastSubmissionDateTV;
        TextView lastRegistrationDateTV;
        TextView prizeTV;
        TextView competitionTypeTV;
        ImageView competitionImageView;
        Button participateButton;

        MyViewHolder(View view) {
            super(view);
            competitionNameTV = view.findViewById(R.id.comp_name);
            lastSubmissionDateTV = view.findViewById(R.id.comp_last_submission_date);
            lastRegistrationDateTV = view.findViewById(R.id.comp_last_reg_date);
            startTimeAgoTV = view.findViewById(R.id.comp_start_time_ago);
            prizeTV = view.findViewById(R.id.comp_prize);
            competitionTypeTV = view.findViewById(R.id.comp_type);
            competitionImageView = view.findViewById(R.id.comp_image);

            participateButton = view.findViewById(R.id.participate);

            participateButton.setOnClickListener(this);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: called");
            int pos = getAdapterPosition();
            CompetitionClass competition = mCompetitions.get(pos);

            if (view.getId() == R.id.participate) {
                String slug =  competition.getSlug();

                Intent i = new Intent(mContext, ParticipateActivity.class);
                i.putExtra(BundleConstants.SLUG, slug);
                mContext.startActivity(i);
            }
        }
    }

//    private void agentFarmerDetailsApi(final String farmerId) {
//        if (!NetworkUtil.isNetworkAvailable()) {
//            Toast.makeText(mContext, "No internet", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        mRequestQueue = MyVolley.getInstance().getRequestQueue();
//
//        StringRequest request = new StringRequest(Request.Method.GET, ServerConstants.AGENT_FARMER_DETAIL + farmerId, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
////                mPb.setVisibility(View.GONE);
//                Log.d(TAG, "onResponse: machine detail response = " + response);
////                try {
////                    JSONObject responseObject = new JSONObject(response);
////                    JSONObject dataObject = responseObject.getJSONObject("data");
////
////                    String machineName = dataObject.getString("machine_name");
////                    String machineCode = dataObject.getString("machine_code");
////                    String machineLocation = dataObject.getString("location");
////                    String isMaster = dataObject.getString("is_master");
////                    String role = "";
////                    if (isMaster.equals("null")) {
////                        role = "Normal";
////                    } else if (isMaster.equals("y")) {
////                        role = "Master";
////                    }
////
////                    showFarmerDetailDialog(farmerId, farmerName, farmerCode, mobileNumber);
////
////
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                mPb.setVisibility(View.GONE);
//                NetworkUtil.handleSimpleVolleyRequestError(error, mContext);
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                final Map<String, String> headers = new HashMap<>();
////                headers.put("AUTHORIZATION", mSp.getKey(SPConstants.API_KEY));
//                headers.put(mContext.getString(R.string.authorization_all_caps), mSp.getKey(SPConstants.API_KEY));
////                headers.put("Content-Type", contentType);
//                return headers;
//            }
//
////            @Override
////            protected Map<String, String> getParams() {
////                Map<String, String> params = new HashMap<>();
////
////                params.put(getString(R.string.mobile), "8517841635");
////                params.put(getString(R.string.otp), "202317");
////
////                return params;
////            }
//        };
//        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        mRequestQueue.add(request);
//    }


    public CompetitionsAdapter(Context context, List<CompetitionClass> competitions) {
        this.mContext = context;
        this.mCompetitions = competitions;

        mSp = MySharedPreferences.getInstance(mContext);

    }

    @Override
    public CompetitionsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.competition_item, parent, false);

        return new CompetitionsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        CompetitionClass competition = mCompetitions.get(position);

        holder.competitionNameTV.setText(competition.getCompetitionName());
        holder.startTimeAgoTV.setText(competition.getStartTimeAgo());
        holder.lastSubmissionDateTV.setText("Last Date of Submission: " + competition.getLastSubmissionDate());
        holder.lastRegistrationDateTV.setText("Last Date of Registration: " + competition.getLastRegistrationDate());
        holder.prizeTV.setText("Prize: " + competition.getPrize());
        holder.competitionTypeTV.setText("Type: " + competition.getCompetitionType());
        Log.d(TAG, "onBindViewHolder: image url = " + ServerConstants.BASE_URL_COMPETITION + competition.getImageUrl());

        Picasso.get().load(ServerConstants.IMAGE_BASE_URL + competition.getImageUrl()).into(holder.competitionImageView);

    }

    @Override
    public int getItemCount() {
        return mCompetitions.size();
    }

}