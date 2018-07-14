package com.hackerkernel.user.sqrfactor.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hackerkernel.user.sqrfactor.R;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    String text;

    public MyAdapter(String s) {
        text = s;
    }

    @NonNull

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout ll = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_layout, parent, false);

        ViewHolder v = new ViewHolder(ll);
        return v;

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.tv.setText(Html.fromHtml(text));
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv;

        public ViewHolder(View itemView) {

            super(itemView);

            tv = (TextView)itemView.findViewById(R.id.tv);

        }
    }

}
