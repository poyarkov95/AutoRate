package com.example.user.autorate;

/**
 * Created by User on 10.06.2016.
 */
public class AutoServiceInfo {
    private String name;
    private String description;
    private int imageResourceId;
    private String location;

    public AutoServiceInfo(String name, String description, int imageResourceId, String location){
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.location = location;
    }

    public static final AutoServiceInfo[] autoServices = { new AutoServiceInfo("Auto-color","Auto service with color",R.drawable.autocolor,"geo:56.247915, 43.418788"),
                                                           new AutoServiceInfo("Auto-servis","Service servis bla vtoroy servis",R.drawable.autoserv,"geo:56.245883, 43.427562"),
                                                           new AutoServiceInfo("Dils-auto","Servis dils bla bla",R.drawable.dilsauto,"geo:56.242578, 43.407931")};

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
}
