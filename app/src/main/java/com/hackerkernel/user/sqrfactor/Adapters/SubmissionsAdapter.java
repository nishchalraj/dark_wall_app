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
            int pos = getAdapterPosition();
            SubmissionClass submission = mSubmissions.get(pos);
        }
    }


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
        String coverUrl = submission.getCoverUrl();

        if (coverUrl != null && !coverUrl.equals("null")) {
            Picasso.get().load("https://cfvod.kaltura.com/p/1836881/sp/183688100/thumbnail/entry_id/1_87thk8o6/version/100001/width/600/height/400").into(holder.coverIV);
            Picasso.get().load(ServerConstants.IMAGE_BASE_URL + submission.getCoverUrl()).resize(350, 200).into(holder.coverIV);
        }


    }

    @Override
    public int getItemCount() {
        return mSubmissions.size();
    }

}