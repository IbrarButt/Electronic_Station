package com.example.electronicstation.screens.modals;

import java.io.Serializable;

public class Station implements Serializable {
    private String name;
    private String stationLevel;
    private String startTime;
    private String endTime;
    private String location;

    public Station(String name, String stationLevel, String startTime, String endTime, String location) {
        this.name = name;
        this.stationLevel = stationLevel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
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
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
