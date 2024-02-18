package ru.example.simplemosdiaryclient.network.network_entity;

import ru.example.simplemosdiaryclient.network.network_entity.short_schedule.ScheduleItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShortSchedule {
    @SerializedName("payload")
    private List<ScheduleItem> payload;

    public ShortSchedule() {
    }

    public ShortSchedule(List<ScheduleItem> payload) {
        this.payload = payload;
    }

    public List<ScheduleItem> getPayload() {
        return payload;
    }

    public void setPayload(List<ScheduleItem> payload) {
        this.payload = payload;
    }
}

