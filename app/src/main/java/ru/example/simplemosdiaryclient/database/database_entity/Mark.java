package ru.example.simplemosdiaryclient.database.database_entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Mark {
    @PrimaryKey
    private long id;

    private int weight;
    private long scheduleLessonId;
    private double value;

    public Mark() {
    }

    public Mark(long id, int weight, long scheduleLessonId, double value) {
        this.id = id;
        this.weight = weight;
        this.scheduleLessonId = scheduleLessonId;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getScheduleLessonId() {
        return scheduleLessonId;
    }

    public void setScheduleLessonId(long scheduleLessonId) {
        this.scheduleLessonId = scheduleLessonId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
