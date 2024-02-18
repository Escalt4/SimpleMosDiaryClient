package ru.example.simplemosdiaryclient.network.network_entity.short_schedule;

import androidx.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class Lesson {
    @SerializedName("lesson_id")
    private Long lessonId;
    @SerializedName("begin_time")
    private String beginTime;
    @SerializedName("end_time")
    private String endTime;
    @SerializedName("bell_id")
    private Long bellId;
    @SerializedName("subject_name")
    private String subjectName;
    @SerializedName("lesson_type")
    private String lessonType;
    @SerializedName("group_id")
    private Long groupId;
    @SerializedName("group_name")
    private String groupName;
    @SerializedName("lesson_education_type")
    private String lessonEducationType;
    @SerializedName("evaluation")
    @Embedded
    private Object evaluation;
    @SerializedName("absence_reason_id")
    private Long absenceReasonId;
    @SerializedName("subject_id")
    private Long subjectId;
    @SerializedName("lesson_name")
    private String lessonName;
    @SerializedName("schedule_item_id")
    private Long scheduleItemId;
    @SerializedName("is_virtual")
    private boolean isVirtual;

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
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

    public Long getBellId() {
        return bellId;
    }

    public void setBellId(Long bellId) {
        this.bellId = bellId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLessonEducationType() {
        return lessonEducationType;
    }

    public void setLessonEducationType(String lessonEducationType) {
        this.lessonEducationType = lessonEducationType;
    }

    public Object getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Object evaluation) {
        this.evaluation = evaluation;
    }

    public Long getAbsenceReasonId() {
        return absenceReasonId;
    }

    public void setAbsenceReasonId(Long absenceReasonId) {
        this.absenceReasonId = absenceReasonId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Long getScheduleItemId() {
        return scheduleItemId;
    }

    public void setScheduleItemId(Long scheduleItemId) {
        this.scheduleItemId = scheduleItemId;
    }

    public boolean isVirtual() {
        return isVirtual;
    }

    public void setVirtual(boolean virtual) {
        isVirtual = virtual;
    }

}