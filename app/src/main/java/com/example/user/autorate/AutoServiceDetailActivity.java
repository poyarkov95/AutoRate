package com.example.user.autorate;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class AutoServiceDetailActivity extends Activity implements View.OnClickListener{

    private ShareActionProvider shareActionProvider;
    public static final String EXTRA_SERVICENO = "pizzaNo";
    Button button;
    String geoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_service_detail);

        button = (Button)findViewById(R.id.mapButton);
        button.setOnClickListener(this);

        //getActionBar().setDisplayHomeAsUpEnabled(true);

        int autoServiceNo = (Integer)getIntent().getExtras().get(EXTRA_SERVICENO);
        String pizzaName = AutoServiceInfo.autoServices[autoServiceNo].getName();
        TextView textView = (TextView)findViewById(R.id.autoServiceDetail_text);
        textView.setText(pizzaName);

        int pizzaImage = AutoServiceInfo.autoServices[autoServiceNo].getImageResourceId();
        ImageView imageView = (ImageView)findViewById(R.id.detail_image);
        imageView.setImageDrawable(getResources().getDrawable(pizzaImage));//тут может быть затык
        imageView.setContentDescription(pizzaName);

        geoLocation = AutoServiceInfo.autoServices[autoServiceNo].getLocation().toString();
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.mapButton:
                String url = "http://maps.google.com/maps?daddr=" + geoLocation;
                Intent intent = new Intent(Intent.ACTION_VIEW,  Uri.parse(url));


                startActivity(intent);//студия пишет, что активность не может перехватить интент для открытия картыю:
                //No Activity found to handle Intent { act=android.intent.action.VIEW dat=geo:56.247915, 43.418788 }
                break;



        }
    }
}
