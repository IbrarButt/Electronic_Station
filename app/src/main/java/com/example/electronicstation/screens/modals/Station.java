package com.example.electronicstation.screens.modals;

import java.io.Serializable;

public class Station implements Serializable {
    private String name;
    private String stationLevel;
    private String startTime;
    private String endTime;
    private String locationUrl;
    private String locationDistance;
    private String userName;



    public Station(String name, String stationLevel, String startTime, String endTime, String locationUrl, String locationDistance, String userName) {
        this.name = name;
        this.stationLevel = stationLevel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.locationUrl = locationUrl;
        this.locationDistance = locationDistance;
        this.userName = userName;
    }

    public Station(String name, String stationLevel, String startTime, String endTime, String location, String locationDistance) {
        this.name = name;
        this.stationLevel = stationLevel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.locationUrl = location;
        this.locationDistance = locationDistance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getLocationDistance() {
        return locationDistance;
    }

    public void setLocationDistance(String locationDistance) {
        this.locationDistance = locationDistance;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStationLevel() {
        return stationLevel;
    }

    public void setStationLevel(String stationLevel) {
        this.stationLevel = stationLevel;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return locationUrl;
    }

    public void setLocation(String location) {
        this.locationUrl = location;
    }
}
