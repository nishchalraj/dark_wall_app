package com.hackerkernel.user.sqrfactor.Adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hackerkernel.user.sqrfactor.R;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    String names[], desc[];
    Drawable drawables[];

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout ll = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_layout, parent, false);
        ViewHolder v = new ViewHolder(ll);

        return v;
    }

    public MessageAdapter(String name[], String desc[], Drawable image[]) {

        names = name;
        this.desc = desc;
        drawables = image;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        holder.tv1.setText(names[position]);
        holder.tv2.setText(desc[position]);
        holder.img.setImageDrawable(drawables[position]);

    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class ViewHolder extends MyAdapter.ViewHolder {

        TextView tv1, tv2;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView)itemView.findViewById(R.id.tv1);
            tv2 = (TextView)itemView.findViewById(R.id.tv2);
            img = (ImageView)itemView.findViewById(R.id.img);
        }
    }
}
