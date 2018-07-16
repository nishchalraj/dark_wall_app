package com.hackerkernel.user.sqrfactor.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.hackerkernel.user.sqrfactor.Constants.ServerConstants;
import com.hackerkernel.user.sqrfactor.Pojo.QuestionCommentClass;
import com.hackerkernel.user.sqrfactor.R;
import com.hackerkernel.user.sqrfactor.Storage.MySharedPreferences;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuestionCommentsAdapter extends RecyclerView.Adapter<QuestionCommentsAdapter.MyViewHolder> {
    private static final String TAG = "QuestionCommentsAdapter";
    private Context mContext;
    private List<QuestionCommentClass> mComments;

    private RequestQueue mRequestQueue;

    private MySharedPreferences mSp;


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView askedByTV;
        TextView descriptionTV;
        ImageView imageIV;

        MyViewHolder(View view) {
            super(view);
            askedByTV = view.findViewById(R.id.comment_asked_by);
            descriptionTV = view.findViewById(R.id.comment_description);
            imageIV = view.findViewById(R.id.comment_image);

        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: called");
            int pos = getAdapterPosition();
            QuestionCommentClass comment = mComments.get(pos);

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


    public QuestionCommentsAdapter(Context context, List<QuestionCommentClass> comments) {
        this.mContext = context;
        this.mComments = comments;

        mSp = MySharedPreferences.getInstance(mContext);

    }

    @Override
    public QuestionCommentsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_comment_item, parent, false);

        return new QuestionCommentsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        QuestionCommentClass comment = mComments.get(position);

        holder.askedByTV.setText(comment.getAskedBy());
        holder.descriptionTV.setText(comment.getDescription());
        Log.d(TAG, "onBindViewHolder: image url = " + ServerConstants.IMAGE_BASE_URL + comment.getImageUrl());
//        Log.d(TAG, "onBindViewHolder: jury image url = " + ServerConstants.IMAGE_URL + jury.getImageUrl());
        Picasso.get().load(ServerConstants.IMAGE_BASE_URL + comment.getImageUrl()).into(holder.imageIV);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

}