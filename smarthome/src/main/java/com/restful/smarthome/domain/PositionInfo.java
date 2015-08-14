package com.restful.smarthome.domain;

import java.io.Serializable;
import java.util.Date;

public class PositionInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private double latitude;
    private double longitude;

    public PositionInfo(Date timestamp, double latitude, double longitude) {
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
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
}
