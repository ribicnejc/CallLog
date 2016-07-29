package com.nejc.povio.calllog;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.InfoViewHolder> {
    private Context mContext;

    public static class InfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View.OnClickListener clickListener;

        TextView phoneNumber;
        TextView date;
        TextView duration;
        TextView type;
        ImageView icon;
        public Typeface typeface;

        InfoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            phoneNumber = (TextView) itemView.findViewById(R.id.call_card_number);
            date = (TextView) itemView.findViewById(R.id.call_card_date);
            duration = (TextView) itemView.findViewById(R.id.call_card_duration);
            type = (TextView) itemView.findViewById(R.id.call_card_type);
            icon = (ImageView) itemView.findViewById(R.id.call_card_icon);
           }

        public void setClickListener(View.OnClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v);
        }
    }

    List<Data> datas;

    public RecyclerViewAdapter(List<Data> datas, Context ctx) {
        this.datas = datas;
        this.mContext = ctx;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.call_card, viewGroup, false);
        InfoViewHolder pvh = new InfoViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(InfoViewHolder viewHolder, final int i) {
        viewHolder.type.setText(datas.get(i).call);
        viewHolder.duration.setText("Duration: "+datas.get(i).duration);
        viewHolder.date.setText(datas.get(i).date);
        viewHolder.icon.setImageResource(datas.get(i).icon);
        viewHolder.phoneNumber.setText(datas.get(i).phoneNumber);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

}
