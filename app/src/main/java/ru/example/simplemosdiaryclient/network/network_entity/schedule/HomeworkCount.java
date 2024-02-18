package ru.example.simplemosdiaryclient.network.network_entity.schedule;

import com.google.gson.annotations.SerializedName;

public  class HomeworkCount {
    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("ready_count")
    private int readyCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getReadyCount() {
        return readyCount;
    }

    public void setReadyCount(int readyCount) {
        this.readyCount = readyCount;
    }
}
