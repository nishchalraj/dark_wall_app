package com.hackerkernel.user.sqrfactor.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.hackerkernel.user.sqrfactor.Pojo.FollowerClass;
import com.hackerkernel.user.sqrfactor.R;

import java.util.ArrayList;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.MyViewAdapter> {
   private ArrayList<FollowerClass> followerClassArrayList;
   private Context context;

    public FollowingAdapter(ArrayList<FollowerClass> followerClassArrayList, Context context) {
        this.followerClassArrayList = followerClassArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.following_adapter_row, parent, false);
        return new MyViewAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewAdapter holder, int position) {
        FollowerClass followerClass=followerClassArrayList.get(position);
        holder.name.setText(followerClass.getName());
        holder.place.setText(followerClass.getPlace());
        holder.post.setText(followerClass.getPost());
        holder.portfolio.setText(followerClass.getPortfolio());
        holder.moreImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, holder.moreImage);
                //inflating menu from xml resource
                popup.inflate(R.menu.followers_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.ReportProfile:
                                //handle case for reportprofile here
                                break;
                            case R.id.BlockProfile:
                                //handle case for blockprofile here
                                break;
                            case R.id.TurnOfNotification:
                                //handle case for TurnOfNotification here
                                break;
                        }
                        return false;
                    }
                });
                popup.show();

            }
        });
        //todo
        //set profile image using glide libaray fromserver
    }

    @Override
    public int getItemCount() {
        return followerClassArrayList.size();
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder {
        TextView name,place,post,portfolio;
        ImageView moreImage;
        ImageView prfileImage;

        public MyViewAdapter(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.name_following);
            place=(TextView)itemView.findViewById(R.id.place_following);
            post=(TextView)itemView.findViewById(R.id.post_following);
            portfolio=(TextView)itemView.findViewById(R.id.portfolio_following);
            prfileImage=(ImageView)itemView.findViewById(R.id.imageProfile_following);
            moreImage=(ImageView)itemView.findViewById(R.id.more_following);

        }
    }
}
