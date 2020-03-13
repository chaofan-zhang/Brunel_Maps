package com.example.brunel_maps.ui.events.bean;

/**
 * Author by linkaikai
 * Date on 2020/3/13
 */
public class EventBean {

    private String title;
    private String time;
    private String place;
    private String path;

    public EventBean(String title, String time, String place, String path) {
        this.title = title;
        this.time = time;
        this.place = place;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
