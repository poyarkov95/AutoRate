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

    public static final String EXTRA_SERVICENO = "pizzaNo";
    Button button;
    String geoLocation;
    String tableName;


    Button webButton;
    String webAddress;

    Button servicesButton;


    //Work with DataBase
    private Cursor cursor;
    private SimpleCursorAdapter cursorAdapter;
    private ListView listView;

    private MyDataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_service_detail);



        button = (Button)findViewById(R.id.mapButton);
        button.setOnClickListener(this);

        webButton = (Button)findViewById(R.id.webButton);
        webButton.setOnClickListener(this);

        servicesButton = (Button)findViewById(R.id.servicesButton);
        servicesButton.setOnClickListener(this);

        int autoServiceNo = (Integer)getIntent().getExtras().get(EXTRA_SERVICENO);
        geoLocation = AutoServiceInfo.autoServices[autoServiceNo].getLocation().toString();
        webAddress = AutoServiceInfo.autoServices[autoServiceNo].getWebAddress().toString();
        //У меня имеется имя для таблицы из класса AutoServiceInfo.java
        //Как мне его использовать, чтобы выводить информацию в ListView из нужной базы данных?
        //Я не могу понять за что уцепиться с этим tableName
        tableName = AutoServiceInfo.autoServices[autoServiceNo].getName().toString();


        //Initialize dataBase

        MyDataBase dataBase = new MyDataBase(this);
        cursor = dataBase.getAllItems();

        String[]from = new String[]{MyDataBase.NAME, MyDataBase.DESCRIPTION, MyDataBase.PRICE};
        int[] to = new int[]{R.id.tvName, R.id.tvDescription, R.id.tvPrise};


        cursorAdapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to,0);
        listView = (ListView)findViewById(R.id.listViewPrise);





        String serviceName = AutoServiceInfo.autoServices[autoServiceNo].getName();
        final TextView textView = (TextView)findViewById(R.id.autoServiceDetail_text);
        textView.setText(serviceName);

        int serviceImage = AutoServiceInfo.autoServices[autoServiceNo].getImageResourceId();
        ImageView imageView = (ImageView)findViewById(R.id.detail_image);
        imageView.setImageDrawable(getResources().getDrawable(serviceImage));//тут может быть затык
        imageView.setContentDescription(serviceName);






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
                listView.setAdapter(cursorAdapter);
                break;
            default:
                dataBase.close();
        }
    }
}
