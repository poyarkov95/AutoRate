package com.example.user.autorate;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ServiceDetailActivity extends AppCompatActivity {
    public static final String SERVICE_NAME = "name";
    public static final String SERVICE_SERVICE = "service";
    TextView textView1,textView2;
    ListView listView;
    MyDataBase dataBase;
    String name;
    String service;
    SimpleCursorAdapter cursorAdapter;
    Cursor cursor;
    String[] from;
    int[] to;

    String[] newFrom;
    int[] newTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = (String)getIntent().getExtras().get(SERVICE_NAME);
        service = (String)getIntent().getExtras().get(SERVICE_SERVICE);
        setContentView(R.layout.activity_service_deteil);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        listView = (ListView)findViewById(R.id.listViewData);
        textView1 = (TextView)findViewById(R.id.servName);
        textView2 = (TextView)listView.findViewById(R.id.tvServise);

        dataBase = new MyDataBase(this);
        from = new String[]{MyDataBase.DESCRIPTION,MyDataBase.PRICE};
        to = new int[]{R.id.tvDescription, R.id.tvPrise};

        newFrom = new String[]{MyDataBase.DESCRIPTION, MyDataBase.PRICE, MyDataBase.NAME};
        newTo = new int[]{R.id.tvDescription, R.id.tvPrise, R.id.tvServise};



        switch (service){
            case "Шиномонтаж":
                cursor = dataBase.getTireItems(name);
                textView1.setText(name + " " + service);
                cursorAdapter = new SimpleCursorAdapter(this, R.layout.listviewitem, cursor, from,to);
                listView.setAdapter(cursorAdapter);
                break;
            case "Кузовные работы":
                cursor = dataBase.getBodyItems(name);
                textView1.setText(name + " " + service);
                cursorAdapter = new SimpleCursorAdapter(this, R.layout.listviewitem, cursor, from,to);
                listView.setAdapter(cursorAdapter);
                break;
            case "Диагностика":
                cursor = dataBase.getDiagnostic(name);
                textView1.setText(name + " " + service);
                cursorAdapter = new SimpleCursorAdapter(this, R.layout.listviewitem, cursor, from,to);
                listView.setAdapter(cursorAdapter);
                break;
            case "Просмотреть все услуги":
                cursor = dataBase.getAllItems(name);
                textView1.setText(name + " " + service);
                cursorAdapter = new SimpleCursorAdapter(this, R.layout.listviewitem2,cursor,newFrom,newTo);
                listView.setAdapter(cursorAdapter);

                break;
            default:
                dataBase.close();
                cursor.close();
        }

    }
}
