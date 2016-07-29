package com.nejc.povio.calllog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.InfoViewHolder> {
    private Context mContext;

    public static class InfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View.OnClickListener clickListener;
        TextView phoneNumber;
        TextView date;
        TextView duration;
        TextView type;
        ImageView icon;
        ImageView icon2;

        InfoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            phoneNumber = (TextView) itemView.findViewById(R.id.call_card_number);
            date = (TextView) itemView.findViewById(R.id.call_card_date);
            duration = (TextView) itemView.findViewById(R.id.call_card_duration);
            type = (TextView) itemView.findViewById(R.id.call_card_type);
            icon = (ImageView) itemView.findViewById(R.id.call_card_icon);
            icon2 = (ImageView) itemView.findViewById(R.id.add_button);
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

    public RVAdapter(List<Data> datas, Context ctx) {
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
    public void onBindViewHolder(final InfoViewHolder viewHolder, final int i) {
        viewHolder.type.setText(datas.get(i).call);
        viewHolder.duration.setText("Duration: " + datas.get(i).duration);
        viewHolder.date.setText(datas.get(i).date);
        viewHolder.icon.setImageResource(datas.get(i).icon);
        viewHolder.phoneNumber.setText(datas.get(i).phoneNumber);
        viewHolder.icon2.setVisibility(View.VISIBLE);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                String phoneNumber = viewHolder.phoneNumber.getText().toString();
                                String date = viewHolder.date.getText().toString();
                                String duration = viewHolder.duration.getText().toString();
                                String type = viewHolder.type.getText().toString();
                                int id = R.drawable.received_call;
                                if (type.equals("MISSED"))
                                    id = R.drawable.missed_call;
                                if (type.equals("OUTGOING"))
                                    id = R.drawable.called_call;
                                if (type.equals("INCOMING"))
                                    id = R.drawable.received_call;

//                                MainActivity.remove(i);
//                                notifyDataSetChanged();
//                                notifyItemRangeChanged(0, getItemCount());
                                MainActivity.addCity(id, type, phoneNumber, date, duration.substring(9));
                                Toast.makeText(mContext, "Phone call added", Toast.LENGTH_SHORT).show();
                                mContext.startActivity(new Intent(mContext, MainActivity.class));
                                ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Add call to list?").setPositiveButton("Add", dialogClickListener)
                        .setNegativeButton("Cancel", dialogClickListener).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

}
