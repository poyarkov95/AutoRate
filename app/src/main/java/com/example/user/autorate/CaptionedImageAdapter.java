package com.example.user.autorate;

import android.graphics.drawable.Drawable;
import android.location.GpsStatus;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;



/**
 * Created by User on 10.06.2016.
 */
public class CaptionedImageAdapter extends RecyclerView.Adapter<CaptionedImageAdapter.ViewHolder> {

    private String[] captions;
    private int[] imageIds;
    private Listener listener;

    public CaptionedImageAdapter(String[] captions, int[]imageIds){
        this.captions = captions;
        this.imageIds = imageIds;
    }

    public static interface Listener{
        public void onClick(int position);
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    public CaptionedImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(CaptionedImageAdapter.ViewHolder holder,final int position) {
        CardView cardView = holder.cardView;

        ImageView imageView = (ImageView)cardView.findViewById(R.id.info_image);

        Drawable drawable = cardView.getResources().getDrawable(imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView = (TextView)cardView.findViewById(R.id.info_text);
        textView.setText(captions[position]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return captions.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
    }
}
