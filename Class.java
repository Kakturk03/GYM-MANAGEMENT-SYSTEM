package CMPE232;

import java.sql.Timestamp;

public class Class {
    private int classID;
    private String name;
    private String description;
    private Timestamp schedule;
    private int duration;
    private int capacity;

    // Constructor
    public Class(int classID, String name, String description, Timestamp schedule, int duration, int capacity) {
        this.classID = classID;
        this.name = name;
        this.description = description;
        this.schedule = schedule;
        this.duration = duration;
        this.capacity = capacity;
    }

    // Getters and Setters
    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getSchedule() {
        return schedule;
    }

    public void setSchedule(Timestamp schedule) {
        this.schedule = schedule;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

