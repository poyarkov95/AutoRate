package com.example.user.autorate;

/**
 * Created by User on 10.06.2016.
 */
public class AutoServiceInfo {
    private String name;
    private String description;
    private int imageResourceId;
    private String location;
    private String webAddress;

    public AutoServiceInfo(String name, String description, int imageResourceId, String location, String webAddress){
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.location = location;
        this.webAddress = webAddress;
    }



    public static final AutoServiceInfo[] autoServices = { new AutoServiceInfo("AUTOCOLOR","Auto service with color",R.drawable.autocolor,"56.247915, 43.418788","http://www.colorservice.eu/"),
                                                           new AutoServiceInfo("Auto-servis","Service servis bla vtoroy servis",R.drawable.autoserv,"56.245883, 43.427562","http://www.autoservice.fo/"),
                                                           new AutoServiceInfo("Dils-auto","Servis dils bla bla",R.drawable.dilsauto,"56.242578, 43.407931","http://dils-auto.ru/")};




    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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
}
