package com.hackerkernel.user.sqrfactor.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.hackerkernel.user.sqrfactor.Activities.QuestionDetailActivity;
import com.hackerkernel.user.sqrfactor.Constants.BundleConstants;
import com.hackerkernel.user.sqrfactor.Pojo.WallQuestionClass;
import com.hackerkernel.user.sqrfactor.R;
import com.hackerkernel.user.sqrfactor.Storage.MySharedPreferences;

import org.json.JSONArray;

import java.util.List;

public class WallQuestionsAdapter extends RecyclerView.Adapter<WallQuestionsAdapter.MyViewHolder> {
    private static final String TAG = "WallQuestionsAdapter";
    private Context mContext;
    private List<WallQuestionClass> mWallQuestions;

    private RequestQueue mRequestQueue;

    private MySharedPreferences mSp;


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView announcedByTV;
        TextView subjectTV;

        MyViewHolder(View view) {
            super(view);
            announcedByTV = view.findViewById(R.id.wall_announced_by);
            subjectTV = view.findViewById(R.id.wall_subject);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: called");
            int pos = getAdapterPosition();
            WallQuestionClass wallQuestion = mWallQuestions.get(pos);

            JSONArray commentsArray = wallQuestion.getCommentsArray();
            String commentsArrayString = commentsArray.toString();
            Log.d(TAG, "onClick: comments array string = " + commentsArrayString);

            String questionDesc = wallQuestion.getDescription();
            String questionId = wallQuestion.getId();

            Intent i = new Intent(mContext, QuestionDetailActivity.class);
            i.putExtra(BundleConstants.QUESTION_ID, questionId);
            i.putExtra(BundleConstants.QUESTION_DESCRIPTION, questionDesc);
            i.putExtra(BundleConstants.COMMENTS_ARRAY, commentsArrayString);

            mContext.startActivity(i);

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


    public WallQuestionsAdapter(Context context, List<WallQuestionClass> wallQuestions) {
        this.mContext = context;
        this.mWallQuestions = wallQuestions;

        mSp = MySharedPreferences.getInstance(mContext);

    }

    @Override
    public WallQuestionsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wall_item, parent, false);

        return new WallQuestionsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        WallQuestionClass wallQuestion = mWallQuestions.get(position);

        holder.announcedByTV.setText("announced by " + wallQuestion.getAnnouncedBy());
        holder.subjectTV.setText(wallQuestion.getSubject());
    }

    @Override
    public int getItemCount() {
        return mWallQuestions.size();
    }

}