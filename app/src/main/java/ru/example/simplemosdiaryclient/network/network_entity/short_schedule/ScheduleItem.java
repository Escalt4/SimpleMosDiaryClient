package ru.example.simplemosdiaryclient.network.network_entity.short_schedule;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleItem {
    @SerializedName("date")
    private String date;

    @SerializedName("lessons")
    private List<Lesson> lessons;

    public ScheduleItem() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
