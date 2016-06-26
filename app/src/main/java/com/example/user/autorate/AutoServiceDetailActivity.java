package com.example.user.autorate;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ShareActionProvider;

import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;


public class AutoServiceDetailActivity extends Activity implements View.OnClickListener{

    public static final String EXTRA_SERVICE_NO = "serviceNo";
    Button button;
    String geoLocation;
    String tableName;


    String callNumber;
    Button callButton;

    Button webButton;
    String webAddress;

    Button servicesButton;

    int autoServiceNo;
    MyDataBase dataBase;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_service_detail);
        Bundle bundle= getIntent().getExtras();
        if(bundle != null){
            Object o = bundle.get(EXTRA_SERVICE_NO);
            autoServiceNo = (Integer.parseInt(String.valueOf(o)));
        }



        geoLocation = AutoServiceInfo.autoServices[autoServiceNo].getLocation();
        webAddress = AutoServiceInfo.autoServices[autoServiceNo].getWebAddress();
        tableName = AutoServiceInfo.autoServices[autoServiceNo].getName();
        callNumber = AutoServiceInfo.autoServices[autoServiceNo].getCallNumber();

        button = (Button)findViewById(R.id.mapButton);
        button.setOnClickListener(this);

        webButton = (Button)findViewById(R.id.webButton);
        webButton.setOnClickListener(this);

        servicesButton = (Button)findViewById(R.id.servicesButton);
        servicesButton.setOnClickListener(this);

        callButton = (Button)findViewById(R.id.callButton);
        callButton.setOnClickListener(this);

        //Initialize dataBase






        String pizzaName = AutoServiceInfo.autoServices[autoServiceNo].getName();
        TextView textView = (TextView)findViewById(R.id.autoServiceDetail_text);
        textView.setText(pizzaName);

        int pizzaImage = AutoServiceInfo.autoServices[autoServiceNo].getImageResourceId();
        ImageView imageView = (ImageView)findViewById(R.id.detail_image);
        imageView.setImageDrawable(getResources().getDrawable(pizzaImage));//тут может быть затык
        imageView.setContentDescription(pizzaName);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.mapButton:
                String url = "http://maps.google.com/maps?daddr=" + geoLocation;
                intent = new Intent(Intent.ACTION_VIEW,  Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.webButton:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webAddress));
                startActivity(intent);
                break;
            case R.id.servicesButton:
                dataBase = new MyDataBase(this);
                cursor = dataBase.getAllItems(tableName);
                String[]from = new String[]{MyDataBase.NAME,MyDataBase.DESCRIPTION,MyDataBase.PRICE};
                int[] to = new int[]{R.id.tvName, R.id.tvDescription, R.id.tvPrise};
                SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to, 0);
                ListView listView = (ListView) findViewById(R.id.listViewPrise);
                listView.setAdapter(cursorAdapter);
                break;
            case R.id.callButton:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+callNumber));
                startActivity(intent);
                break;
            default:
                dataBase.close();
                break;
        }
    }
}
