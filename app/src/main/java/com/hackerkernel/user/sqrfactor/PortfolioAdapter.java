package com.hackerkernel.user.sqrfactor;

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

import java.util.ArrayList;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.MyViewHolder> {
    private ArrayList<PortfolioClass> portfolioClassArrayList;
    private Context context;

    public PortfolioAdapter(ArrayList<PortfolioClass> portfolioClassArrayList, Context context) {
        this.portfolioClassArrayList = portfolioClassArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.portfolio_adapter_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
       PortfolioClass portfolioClass=portfolioClassArrayList.get(position);
        holder.articleName.setText(portfolioClass.getArticleName());
        holder.articleWriter.setText(portfolioClass.articleWirterName);
        holder.likes.setText(portfolioClass.getLikes());
        holder.comment.setText(portfolioClass.getComments());
        holder.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, holder.menuButton);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_portfolio);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.RemoveFromFeatures:
                                //handle case for RemoveFromFeatures here
                                break;
                            case R.id.EditPost:
                                //handle case for EditPost here
                                break;

                        }
                        return false;
                    }
                });

            }
        });
        //todo
        //set profile image using glide libaray fromserver
    }

    @Override
    public int getItemCount() {
        return portfolioClassArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView articleName,articleWriter,likes,comment;
        ImageView articleImage;
        ImageView menuButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            articleName=(TextView)itemView.findViewById(R.id.articleTitle_portfolio);
            articleWriter=(TextView)itemView.findViewById(R.id.articleWriter_portfolio);
            likes=(TextView)itemView.findViewById(R.id.like_portfolio);
            comment=(TextView)itemView.findViewById(R.id.Comments_portfolio);
            articleImage=(ImageView)itemView.findViewById(R.id.imageProfile);
            menuButton=(ImageView)itemView.findViewById(R.id.more_portfolio);
        }
    }
}
