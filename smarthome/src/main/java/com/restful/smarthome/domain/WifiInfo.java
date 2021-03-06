package com.restful.smarthome.domain;

import java.io.Serializable;

public class WifiInfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5989119063135915429L;
    private int level;
    private String ssid;
    private String bssid;
    private boolean locked;

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
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
