package com.restful.smarthome.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "locations")
public class LocationInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2896282364724422441L;

    @Id
    private String id;

    private String username;
    private String userid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss SSSS")
    private Date timestamp;
    private double latitude;
    private double longitude;
    private ConnectedWifi connectedwifi;
    private String activity;
    private List<WifiInfo> wifienv = new ArrayList<WifiInfo>();

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {       
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public List<WifiInfo> getWifienv() {
        return wifienv;
    }

    public void setWifienv(List<WifiInfo> wifienv) {
        this.wifienv = wifienv;
    }

    public ConnectedWifi getConnectedwifi() {
        return connectedwifi;
    }

    public void setConnectedwifi(ConnectedWifi connectedwifi) {
        this.connectedwifi = connectedwifi;
    }
}
