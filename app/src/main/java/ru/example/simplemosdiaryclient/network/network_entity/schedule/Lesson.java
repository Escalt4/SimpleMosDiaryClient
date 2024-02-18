package ru.example.simplemosdiaryclient.network.network_entity.schedule;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Lesson {
    @SerializedName("schedule_item_id")
    private long scheduleItemId;
    @SerializedName("subject_id")
    private long subjectId;
    @SerializedName("subject_name")
    private String subjectName;
    @SerializedName("course_lesson_type")
    private Object courseLessonType;
    @SerializedName("teacher")
    private Teacher teacher;
    @SerializedName("marks")
    private List<Object> marks;
    @SerializedName("homework")
    private String homework;
    @SerializedName("lesson_type")
    private String lessonType;
    @SerializedName("lesson_education_type")
    private String lessonEducationType;
    @SerializedName("evaluation")
    private Object evaluation;
    @SerializedName("absence_reason_id")
    private int absenceReasonId;
    @SerializedName("nonattendance_reason_id")
    private Object nonattendanceReasonId;
    @SerializedName("disease_status_type")
    private Object diseaseStatusType;
    @SerializedName("bell_id")
    private long bellId;
    @SerializedName("replaced")
    private boolean replaced;
    @SerializedName("homework_count")
    private HomeworkCount homeworkCount;
    @SerializedName("esz_field_id")
    private Object eszFieldId;
    @SerializedName("is_cancelled")
    private boolean isCancelled;
    @SerializedName("is_missed_lesson")
    private boolean isMissedLesson;
    @SerializedName("is_virtual")
    private boolean isVirtual;

    public long getScheduleItemId() {
        return scheduleItemId;
    }

    public void setScheduleItemId(long scheduleItemId) {
        this.scheduleItemId = scheduleItemId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Object getCourseLessonType() {
        return courseLessonType;
    }

    public void setCourseLessonType(Object courseLessonType) {
        this.courseLessonType = courseLessonType;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Object> getMarks() {
        return marks;
    }

    public void setMarks(List<Object> marks) {
        this.marks = marks;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
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

    public int getAbsenceReasonId() {
        return absenceReasonId;
    }

    public void setAbsenceReasonId(int absenceReasonId) {
        this.absenceReasonId = absenceReasonId;
    }

    public Object getNonattendanceReasonId() {
        return nonattendanceReasonId;
    }

    public void setNonattendanceReasonId(Object nonattendanceReasonId) {
        this.nonattendanceReasonId = nonattendanceReasonId;
    }

    public Object getDiseaseStatusType() {
        return diseaseStatusType;
    }

    public void setDiseaseStatusType(Object diseaseStatusType) {
        this.diseaseStatusType = diseaseStatusType;
    }

    public long getBellId() {
        return bellId;
    }

    public void setBellId(long bellId) {
        this.bellId = bellId;
    }

    public boolean isReplaced() {
        return replaced;
    }

    public void setReplaced(boolean replaced) {
        this.replaced = replaced;
    }

    public HomeworkCount getHomeworkCount() {
        return homeworkCount;
    }

    public void setHomeworkCount(HomeworkCount homeworkCount) {
        this.homeworkCount = homeworkCount;
    }

    public Object getEszFieldId() {
        return eszFieldId;
    }

    public void setEszFieldId(Object eszFieldId) {
        this.eszFieldId = eszFieldId;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public boolean isMissedLesson() {
        return isMissedLesson;
    }

    public void setMissedLesson(boolean missedLesson) {
        isMissedLesson = missedLesson;
    }

    public boolean isVirtual() {
        return isVirtual;
    }

    public void setVirtual(boolean virtual) {
        isVirtual = virtual;
    }
}
