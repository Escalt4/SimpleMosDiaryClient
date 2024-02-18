package ru.example.simplemosdiaryclient.network.network_entity;

import ru.example.simplemosdiaryclient.network.network_entity.schedule.Activity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Schedule {
    @SerializedName("summary")
    private String summary;
    @SerializedName("date")
    private String date;
    @SerializedName("activities")
    private List<Activity> activities;
    @SerializedName("has_homework")
    private boolean hasHomework;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public boolean isHasHomework() {
        return hasHomework;
    }

    public void setHasHomework(boolean hasHomework) {
        this.hasHomework = hasHomework;
    }

}

