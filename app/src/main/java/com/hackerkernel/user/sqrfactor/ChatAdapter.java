package com.hackerkernel.user.sqrfactor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout ll = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.chatroom_layout, parent, false);
        ViewHolder view = new ViewHolder(ll);

        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
