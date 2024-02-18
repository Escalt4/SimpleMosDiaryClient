package ru.example.simplemosdiaryclient.network.network_entity.schedule;

import com.google.gson.annotations.SerializedName;

public  class Activity {
    @SerializedName("type")
    private String type;
    @SerializedName("info")
    private String info;
    @SerializedName("begin_utc")
    private long beginUtc;
    @SerializedName("end_utc")
    private long endUtc;
    @SerializedName("duration")
    private int duration;
    @SerializedName("begin_time")
    private String beginTime;
    @SerializedName("end_time")
    private String endTime;
    @SerializedName("room_number")
    private String roomNumber;
    @SerializedName("room_name")
    private String roomName;
    @SerializedName("building_name")
    private String buildingName;
    @SerializedName("lesson")
    private Lesson lesson;
    @SerializedName("homework_presence_status_id")
    private int homeworkPresenceStatusId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getBeginUtc() {
        return beginUtc;
    }

    public void setBeginUtc(long beginUtc) {
        this.beginUtc = beginUtc;
    }

    public long getEndUtc() {
        return endUtc;
    }

    public void setEndUtc(long endUtc) {
        this.endUtc = endUtc;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public int getHomeworkPresenceStatusId() {
        return homeworkPresenceStatusId;
    }

    public void setHomeworkPresenceStatusId(int homeworkPresenceStatusId) {
        this.homeworkPresenceStatusId = homeworkPresenceStatusId;
    }
}
