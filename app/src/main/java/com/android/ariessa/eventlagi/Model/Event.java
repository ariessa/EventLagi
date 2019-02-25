package com.android.ariessa.eventlagi.Model;

public class Event {

    public String name;
    public String location;
    public String from_date;
    public String from_time;
    public String to_date;
    public String to_time;

    // Default constructor required for calls to
    // DataSnapshot.getValue(Event.class)
    public Event() {
    }

    public Event(String name, String location, String from_date, String from_time, String to_date, String to_time) {
        this.name = name;
        this.location= location;
        this.from_date = from_date;
        this.from_time = from_time;
        this.to_date = to_date;
        this.to_time = to_time;
    }


    // Setter Methods
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }


    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }


    // Getter Methods
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getFrom_date() {
        return from_date;
    }

    public String getFrom_time() {
        return from_time;
    }

    public String getTo_date() {
        return to_date;
    }

    public String getTo_time() {
        return to_time;
    }
}

