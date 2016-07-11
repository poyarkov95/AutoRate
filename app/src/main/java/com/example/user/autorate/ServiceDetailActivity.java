package com.example.user.autorate;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ServiceDetailActivity extends AppCompatActivity {
    public static final String SERVICE_NAME = "service";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String s = (String)getIntent().getExtras().get(SERVICE_NAME);
        setContentView(R.layout.activity_service_deteil);
        textView = (TextView)findViewById(R.id.serviceTextView);
        textView.setText(s);


    }

}
