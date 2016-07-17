package com.example.user.autorate;
//TODO: disable the divice revolution.

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    //Network connection
    Socket socket = null;
    public String debuggingString = "DEBUG";
    // We should use here Ethernet adapter VMware Network Adapter VMnet1: IpV4
    public String hostname = "192.168.253.1"; // <-- paste your IPv4 here
    public int portNumber = 60123;
    BufferedReader bufferedReader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.servicesButton);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        button.setOnClickListener(this);
        new Thread() {
            @Override
            public void run() {
                try {
                    //connecting
                    Log.e(debuggingString, "Attempting to connect to server");
                    socket = new Socket(hostname, portNumber);

                    //Send message to server
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    bw.write("this is a message from the client");
                    bw.newLine();
                    bw.flush();

                    //Receive message from server
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String s = "";
                    while((s = bufferedReader.readLine()) != null)
                    {
                        s = bufferedReader.readLine();
                        updateDiscount(s);
                        MyDataBase dataBase = new MyDataBase(getBaseContext());
                        dataBase.updateDB(s);
                    }
                } catch (Exception e)
                {
                    Log.e(debuggingString, e.getMessage());
                }
            }

        }.start();
    }
    public void updateDiscount(String text){
        String[] message = text.split("#");
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra(MyIntentService.EXTRA_MESSAGE, message[0] + " " + message[1]);
        startService(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        if(getFragmentManager().popBackStackImmediate()){
            getFragmentManager().popBackStack();
        }
        else
            super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.servicesButton:
                Fragment fragment = new AutoServiceFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame_container, fragment, "visible_fragment");
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
                break;
            default:
                break;
        }
    }
}
