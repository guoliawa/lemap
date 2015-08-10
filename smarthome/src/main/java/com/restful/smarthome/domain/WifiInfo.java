package com.restful.smarthome.domain;

import java.io.Serializable;

public class WifiInfo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -5989119063135915429L;
    private double level;
    private String ssid;
    private String bssid;

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
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
