package com.restful.smarthome.domain;

public class ConnectedWifi {
    private double rssi;
    private String ssid;
    private String bssid;
    
    public double getRssi() {
        return rssi;
    }
    public void setRssi(double rssi) {
        this.rssi = rssi;
    }
    public String getSsid() {
        return ssid;
    }
    public void setSsid(String ssid) {
        this.ssid = ssid;
    }
    public String getBssid() {
        return bssid;
    }
    public void setBssid(String bssid) {
        this.bssid = bssid;
    }  
}
