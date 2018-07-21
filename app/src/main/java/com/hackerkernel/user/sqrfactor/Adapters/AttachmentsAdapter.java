package com.hackerkernel.user.sqrfactor.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.hackerkernel.user.sqrfactor.Activities.W;
import com.hackerkernel.user.sqrfactor.Constants.ServerConstants;
import com.hackerkernel.user.sqrfactor.Pojo.AttachmentClass;
import com.hackerkernel.user.sqrfactor.R;
import com.hackerkernel.user.sqrfactor.Storage.MySharedPreferences;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AttachmentsAdapter extends RecyclerView.Adapter<AttachmentsAdapter.MyViewHolder> {
    private static final String TAG = "AttachmentsAdapter";
    private Context mContext;
    private List<AttachmentClass> mAttachments;

    private RequestQueue mRequestQueue;

    private MySharedPreferences mSp;
    AlertDialog dialog;

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTV, download_link;
        ImageView imageIV;

        MyViewHolder(View view) {
            super(view);
            nameTV = view.findViewById(R.id.attachment_url);
            download_link = view.findViewById(R.id.download_link);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: called");
            int pos = getAdapterPosition();
            AttachmentClass attachment = mAttachments.get(pos);

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


    public AttachmentsAdapter(Context context, List<AttachmentClass> attachments) {
        this.mContext = context;
        this.mAttachments = attachments;

        mSp = MySharedPreferences.getInstance(mContext);

    }

    @Override
    public AttachmentsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attachment_item, parent, false);

        return new AttachmentsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        AttachmentClass attachment = mAttachments.get(position);

        holder.nameTV.setText("Download Breif");
        holder.download_link.setText(attachment.getAttachmentUrl());
        holder.nameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(ServerConstants.IMAGE_BASE_URL + holder.download_link.getText().toString()));
                Intent chooser = Intent.createChooser(intent, "Select Browser");
                mContext.startActivity(chooser);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mAttachments.size();
    }


}