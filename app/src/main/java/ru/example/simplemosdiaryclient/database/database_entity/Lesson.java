package ru.example.simplemosdiaryclient.database.database_entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity
public class Lesson {
    private String lessonDate;

    @PrimaryKey
    private Long scheduleItemId;
    private Long scheduleItemId2;

    private String subject;
    private String cabinetNum;
    private Integer lessonNum;
    private String groupName;

    private String teacherLastName;
    private String teacherFirstName;
    private String teacherMiddleName;

    private Long absenceReasonId;

    private String timeStartString;

    private String timeEndString;

    private Double markValue;

    public Lesson(String lessonDate, Long scheduleItemId, String subject, String groupName, String timeStartString, String timeEndString,Long absenceReasonId) {
        this.lessonDate = lessonDate;
        this.scheduleItemId = scheduleItemId;
        this.subject = subject;
        this.groupName = groupName;
        this.timeStartString = timeStartString;
        this.timeEndString = timeEndString;
        this.absenceReasonId = absenceReasonId;
    }

    public Double getMarkValue() {
        return markValue;
    }

    public void setMarkValue(Double markValue) {
        this.markValue = markValue;
    }

    public Long getAbsenceReasonId() {
        return absenceReasonId;
    }

    public void setAbsenceReasonId(Long absenceReasonId) {
        this.absenceReasonId = absenceReasonId;
    }

    public Long getScheduleItemId2() {
        return scheduleItemId2;
    }

    public void setScheduleItemId2(Long scheduleItemId2) {
        this.scheduleItemId2 = scheduleItemId2;
    }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public Long getScheduleItemId() {
        return scheduleItemId;
    }

    public void setScheduleItemId(Long scheduleItemId) {
        this.scheduleItemId = scheduleItemId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCabinetNum() {
        return cabinetNum;
    }

    public void setCabinetNum(String cabinetNum) {
        this.cabinetNum = cabinetNum;
    }

    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherMiddleName() {
        return teacherMiddleName;
    }

    public void setTeacherMiddleName(String teacherMiddleName) {
        this.teacherMiddleName = teacherMiddleName;
    }

    public String getTimeStartString() {
        return timeStartString;
    }

    public void setTimeStartString(String timeStartString) {
        this.timeStartString = timeStartString;
    }

    public String getTimeEndString() {
        return timeEndString;
    }

    public void setTimeEndString(String timeEndString) {
        this.timeEndString = timeEndString;
    }
}
