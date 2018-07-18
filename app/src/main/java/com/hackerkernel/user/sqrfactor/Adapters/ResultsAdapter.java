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
import com.hackerkernel.user.sqrfactor.Constants.Constants;
import com.hackerkernel.user.sqrfactor.Constants.ServerConstants;
import com.hackerkernel.user.sqrfactor.Pojo.ResultClass;
import com.hackerkernel.user.sqrfactor.R;
import com.hackerkernel.user.sqrfactor.Storage.MySharedPreferences;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ResultsAdapter";
    private Context mContext;
    private List<ResultClass> mResults;

    private RequestQueue mRequestQueue;

    private MySharedPreferences mSp;


    class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTV;
        TextView codeTV;
        TextView prizeTV;
        ImageView coverIV;
        Button likeButton;
        Button commentButton;

        DataViewHolder(View view) {
            super(view);
            titleTV = view.findViewById(R.id.result_title);
            codeTV = view.findViewById(R.id.result_code);
            coverIV = view.findViewById(R.id.result_cover);
            likeButton = view.findViewById(R.id.result_like);
            commentButton = view.findViewById(R.id.result_comment);
            prizeTV = view.findViewById(R.id.result_prize);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: called");
            int pos = getAdapterPosition();
            ResultClass result = mResults.get(pos);

//            if (view.getId() == R.id.participate) {
//                String slug =  prize.getSlug();
//
//                Intent i = new Intent(mContext, ParticipateActivity.class);
//                i.putExtra(BundleConstants.SLUG, slug);
//                mContext.startActivity(i);
//            }
        }
    }

    class HeadingViewHolder extends RecyclerView.ViewHolder {
        TextView headingTV;

        HeadingViewHolder(View view) {
            super(view);
            headingTV = view.findViewById(R.id.result_heading);
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


    public ResultsAdapter(Context context, List<ResultClass> results) {
        this.mContext = context;
        this.mResults = results;

        mSp = MySharedPreferences.getInstance(mContext);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        switch (viewType) {
            case Constants.TYPE_DATA: {
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.result_item, parent, false);

                return new DataViewHolder(itemView);
            }
            case Constants.TYPE_HEADING: {
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.result_layout_heading, parent, false);

                return new HeadingViewHolder(itemView);
            }
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        switch (mResults.get(position).getType()) {
            case 0: {
                return Constants.TYPE_HEADING;
            }
            case 1: {
                return Constants.TYPE_DATA;
            }
            default: return -1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ResultClass result = mResults.get(position);

        if (holder instanceof DataViewHolder) {
            DataViewHolder dataViewHolder = (DataViewHolder) holder;

            dataViewHolder.titleTV.setText(result.getTitle());
            dataViewHolder.codeTV.setText(result.getCode());
            dataViewHolder.prizeTV.setText(result.getPrizeTitle());

            Log.d(TAG, "onBindViewHolder: cover url = " + ServerConstants.BASE_URL + result.getCoverUrl());

            String coverUrl = result.getCoverUrl();
            Log.d(TAG, "onBindViewHolder: cover url = " + coverUrl);

            if (coverUrl != null && !coverUrl.equals("null")) {
                Picasso.get().load(ServerConstants.IMAGE_BASE_URL + result.getCoverUrl()).resize(300, 300).centerCrop().into(dataViewHolder.coverIV);
            }

        } else if (holder instanceof HeadingViewHolder) {
            ((HeadingViewHolder) holder).headingTV.setText(result.getHeading());
        }

//        switch (result.getType()) {
//            case 1: {
//                DataViewHolder dataViewHolder = (DataViewHolder) holder;
//
//                dataViewHolder.titleTV.setText(result.getTitle());
//                dataViewHolder.codeTV.setText(result.getCode());
//                dataViewHolder.prizeTV.setText(result.getPrizeTitle());
//
//                Log.d(TAG, "onBindViewHolder: cover url = " + ServerConstants.BASE_URL + result.getCoverUrl());
//
//                String coverUrl = result.getCoverUrl();
//                Log.d(TAG, "onBindViewHolder: cover url = " + coverUrl);
//
//                if (coverUrl != null && !coverUrl.equals("null")) {
//                    Picasso.get().load(ServerConstants.IMAGE_BASE_URL + result.getCoverUrl()).resize(300, 300).centerCrop().into(dataViewHolder.coverIV);
//                }
//            }
//            case 0: {
//                ((HeadingViewHolder) holder).headingTV.setText(result.getHeading());
//            }
//        }

    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

}