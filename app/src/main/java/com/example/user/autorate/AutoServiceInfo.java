package com.example.user.autorate;

/**
 * Created by User on 10.06.2016.
 */
public class AutoServiceInfo {
    private String name;
    private int imageResourceId;
    private String location;
    private String webAddress;
    private String callNumber;

    public AutoServiceInfo(String name, int imageResourceId, String location, String webAddress, String callNumber){
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.location = location;
        this.webAddress = webAddress;
        this.callNumber = callNumber;
    }



    public static final AutoServiceInfo[] autoServices = { new AutoServiceInfo("AUTOCOLOR",R.drawable.autocolor,"56.247915, 43.418788","http://www.colorservice.eu/","89524590561"),
                                                           new AutoServiceInfo("AUTORATE",R.drawable.autoserv,"56.245883, 43.427562","http://www.autoservice.fo/","88313332372"),
                                                           new AutoServiceInfo("DILSAUTO",R.drawable.dilsauto,"56.242578, 43.407931","http://dils-auto.ru/","123321123321")};
    public String getName() {
        return name;
    }


    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getLocation() {
        return location;
    }



    public String getWebAddress() {
        return webAddress;
    }

    public String getCallNumber() {
        return callNumber;
    }
}
