package com.nejc.povio.calllog;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.InfoViewHolder> {
    private Context mContext;

    public static class InfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View.OnClickListener clickListener;

        TextView backGroundTemp;
        ImageView weatherPhoto;
        public Typeface typeface;

        InfoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //weatherPhoto = (ImageView) itemView.findViewById(R.id.weatherIcon);
            //backGroundTemp = (TextView) itemView.findViewById(R.id.card_view_background_temp);
            typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "openSansLight.ttf");
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
    public void onBindViewHolder(InfoViewHolder weatherViewHolder, final int i) {

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

}
