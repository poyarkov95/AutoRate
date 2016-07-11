package com.example.user.autorate;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by User on 10.07.2016.
 */
class CaptionedAdapter extends RecyclerView.Adapter<CaptionedAdapter.ViewHolder> {
    private String[] names;
    private String[] services;
    private String[] costs;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned, parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView nameTextView = (TextView)cardView.findViewById(R.id.name);
        nameTextView.setText(names[position]);
        TextView serviceTextView = (TextView)cardView.findViewById(R.id.service);
        serviceTextView.setText(services[position]);
        TextView costTextView = (TextView)cardView.findViewById(R.id.cost);
        costTextView.setText(costs[position]);

    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }
}
