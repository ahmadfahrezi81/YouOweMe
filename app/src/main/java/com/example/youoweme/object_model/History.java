package com.example.youoweme.object_model;

public class History {
    String description, date, time;


    public History(String description, String date, String time) {
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public History() {
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
