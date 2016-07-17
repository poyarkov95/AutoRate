package com.example.user.autorate;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;

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

    String[] data = {"Выберите услугу", "Шиномонтаж","Кузовные работы","Диагностика"};
    Spinner spinner;


    String[] from;
    int[] to;
    ArrayAdapter<String>adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_service_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle= getIntent().getExtras();
        if(bundle != null){
            Object o = bundle.get(EXTRA_SERVICE_NO);
            autoServiceNo = (Integer.parseInt(String.valueOf(o)));
        }
        //all buttons
        button = (Button)findViewById(R.id.mapButton);
        button.setOnClickListener(this);

        webButton = (Button)findViewById(R.id.webButton);
        webButton.setOnClickListener(this);

        servicesButton = (Button)findViewById(R.id.servicesButton);
        servicesButton.setOnClickListener(this);

        callButton = (Button)findViewById(R.id.callButton);
        callButton.setOnClickListener(this);

        //data from information class for CardView
        geoLocation = AutoServiceInfo.autoServices[autoServiceNo].getLocation();
        webAddress = AutoServiceInfo.autoServices[autoServiceNo].getWebAddress();
        tableName = AutoServiceInfo.autoServices[autoServiceNo].getName();
        callNumber = AutoServiceInfo.autoServices[autoServiceNo].getCallNumber();

        String pizzaName = AutoServiceInfo.autoServices[autoServiceNo].getName();
        TextView textView = (TextView)findViewById(R.id.autoServiceDetail_text);
        textView.setText(pizzaName);

        int pizzaImage = AutoServiceInfo.autoServices[autoServiceNo].getImageResourceId();
        ImageView imageView = (ImageView)findViewById(R.id.detail_image);
        imageView.setImageDrawable(getResources().getDrawable(pizzaImage));
        imageView.setContentDescription(pizzaName);

        //database
        dataBase = new MyDataBase(this);
        from = new String[]{MyDataBase.NAME,MyDataBase.DESCRIPTION,MyDataBase.PRICE};
        to = new int[]{R.id.tvName, R.id.tvDescription, R.id.tvPrise};

        //Object spinner
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (spinner.getSelectedItem().toString()){
                    case "Выберите услугу":
                        break;
                    case "Шиномонтаж":
                        intent = new Intent(getBaseContext(),ServiceDetailActivity.class);
                        intent.putExtra(ServiceDetailActivity.SERVICE_NAME,tableName);
                        intent.putExtra(ServiceDetailActivity.SERVICE_SERVICE, spinner.getSelectedItem().toString());
                        startActivity(intent);
                        break;
                    case "Кузовные работы":
                        intent = new Intent(getBaseContext(),ServiceDetailActivity.class);
                        intent.putExtra(ServiceDetailActivity.SERVICE_NAME,tableName);
                        intent.putExtra(ServiceDetailActivity.SERVICE_SERVICE, spinner.getSelectedItem().toString());
                        startActivity(intent);
                        break;
                    case "Диагностика":
                        intent = new Intent(getBaseContext(),ServiceDetailActivity.class);
                        intent.putExtra(ServiceDetailActivity.SERVICE_NAME,tableName);
                        intent.putExtra(ServiceDetailActivity.SERVICE_SERVICE, spinner.getSelectedItem().toString());
                        startActivity(intent);
                        break;
                    default:
                        dataBase.close();
                        cursor.close();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
                intent = new Intent(getBaseContext(), ServiceDetailActivity.class);
                intent.putExtra(ServiceDetailActivity.SERVICE_NAME, tableName);
                intent.putExtra(ServiceDetailActivity.SERVICE_SERVICE,servicesButton.getText());
                startActivity(intent);
                break;
            case R.id.callButton:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+callNumber));
                startActivity(intent);
                break;
            default:
                dataBase.close();
                cursor.close();
                break;
        }
    }
}
