package com.hackerkernel.user.sqrfactor.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.hackerkernel.user.sqrfactor.Constants.ServerConstants;
import com.hackerkernel.user.sqrfactor.Pojo.SubmissionClass;
import com.hackerkernel.user.sqrfactor.R;
import com.hackerkernel.user.sqrfactor.Storage.MySharedPreferences;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubmissionsAdapter extends RecyclerView.Adapter<SubmissionsAdapter.MyViewHolder> {
    private static final String TAG = "SubmissionsAdapter";
    private Context mContext;
    private List<SubmissionClass> mSubmissions;

    private RequestQueue mRequestQueue;

    private MySharedPreferences mSp;


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTV;
        TextView codeTV;
        ImageView coverIV;
        Button likeButton;
        Button commentButton;

        MyViewHolder(View view) {
            super(view);
            titleTV = view.findViewById(R.id.submission_title);
            codeTV = view.findViewById(R.id.submission_code);
            coverIV = view.findViewById(R.id.submission_cover);
            likeButton = view.findViewById(R.id.submission_like);
            commentButton = view.findViewById(R.id.submission_comment);

        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: called");
            int pos = getAdapterPosition();
            SubmissionClass submission = mSubmissions.get(pos);

//            if (view.getId() == R.id.participate) {
//                String slug =  prize.getSlug();
//
//                Intent i = new Intent(mContext, ParticipateActivity.class);
//                i.putExtra(BundleConstants.SLUG, slug);
//                mContext.startActivity(i);
//            }
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


    public SubmissionsAdapter(Context context, List<SubmissionClass> submissions) {
        this.mContext = context;
        this.mSubmissions = submissions;

        mSp = MySharedPreferences.getInstance(mContext);

    }

    @Override
    public SubmissionsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.submission_item, parent, false);

        return new SubmissionsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        SubmissionClass submission = mSubmissions.get(position);

        holder.titleTV.setText(submission.getTitle());
        holder.codeTV.setText(submission.getCode());

        Log.d(TAG, "onBindViewHolder: cover url = " + ServerConstants.BASE_URL + submission.getCoverUrl());

        String coverUrl = submission.getCoverUrl();
        Log.d(TAG, "onBindViewHolder: cover url = " + coverUrl);

        if (coverUrl != null && !coverUrl.equals("null")) {
            Picasso.get().load(ServerConstants.IMAGE_BASE_URL + submission.getCoverUrl()).into(holder.coverIV);
        }


    }

    @Override
    public int getItemCount() {
        return mSubmissions.size();
    }

}