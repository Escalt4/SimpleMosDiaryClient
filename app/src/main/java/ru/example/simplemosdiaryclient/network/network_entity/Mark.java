package ru.example.simplemosdiaryclient.network.network_entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mark {
    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("id")
    private long id;

    @SerializedName("student_profile_id")
    private long studentProfileId;

    @SerializedName("weight")
    private int weight;

    @SerializedName("teacher_id")
    private long teacherId;

    @SerializedName("name")
    private String name;

    @SerializedName("comment")
    private String comment;

    @SerializedName("control_form_id")
    private long controlFormId;

    @SerializedName("deleted_by")
    private Object deletedBy;

    @SerializedName("grade_id")
    private long gradeId;

    @SerializedName("schedule_lesson_id")
    private long scheduleLessonId;

    @SerializedName("is_exam")
    private boolean isExam;

    @SerializedName("group_id")
    private long groupId;

    @SerializedName("date")
    private String date;

    @SerializedName("is_point")
    private boolean isPoint;

    @SerializedName("point_date")
    private Object pointDate;

    @SerializedName("subject_id")
    private long subjectId;

    @SerializedName("grade_system_id")
    private long gradeSystemId;

    @SerializedName("grade_system_type")
    private String gradeSystemType;

    @SerializedName("values")
    private List<Value> values;

    @SerializedName("course_lesson_topic_id")
    private long courseLessonTopicId;

    @SerializedName("theme_frame_integration_id")
    private Object themeFrameIntegrationId;

    @SerializedName("project_id")
    private Object projectId;

    @SerializedName("is_control_once")
    private Object isControlOnce;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentProfileId() {
        return studentProfileId;
    }

    public void setStudentProfileId(long studentProfileId) {
        this.studentProfileId = studentProfileId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getControlFormId() {
        return controlFormId;
    }

    public void setControlFormId(long controlFormId) {
        this.controlFormId = controlFormId;
    }

    public Object getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Object deletedBy) {
        this.deletedBy = deletedBy;
    }

    public long getGradeId() {
        return gradeId;
    }

    public void setGradeId(long gradeId) {
        this.gradeId = gradeId;
    }

    public long getScheduleLessonId() {
        return scheduleLessonId;
    }

    public void setScheduleLessonId(long scheduleLessonId) {
        this.scheduleLessonId = scheduleLessonId;
    }

    public boolean isExam() {
        return isExam;
    }

    public void setExam(boolean exam) {
        isExam = exam;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPoint() {
        return isPoint;
    }

    public void setPoint(boolean point) {
        isPoint = point;
    }

    public Object getPointDate() {
        return pointDate;
    }

    public void setPointDate(Object pointDate) {
        this.pointDate = pointDate;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getGradeSystemId() {
        return gradeSystemId;
    }

    public void setGradeSystemId(long gradeSystemId) {
        this.gradeSystemId = gradeSystemId;
    }

    public String getGradeSystemType() {
        return gradeSystemType;
    }

    public void setGradeSystemType(String gradeSystemType) {
        this.gradeSystemType = gradeSystemType;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public long getCourseLessonTopicId() {
        return courseLessonTopicId;
    }

    public void setCourseLessonTopicId(long courseLessonTopicId) {
        this.courseLessonTopicId = courseLessonTopicId;
    }

    public Object getThemeFrameIntegrationId() {
        return themeFrameIntegrationId;
    }

    public void setThemeFrameIntegrationId(Object themeFrameIntegrationId) {
        this.themeFrameIntegrationId = themeFrameIntegrationId;
    }

    public Object getProjectId() {
        return projectId;
    }

    public void setProjectId(Object projectId) {
        this.projectId = projectId;
    }

    public Object getIsControlOnce() {
        return isControlOnce;
    }

    public void setIsControlOnce(Object isControlOnce) {
        this.isControlOnce = isControlOnce;
    }

    public static class Value {
        @SerializedName("grade_system_id")
        private long gradeSystemId;

        @SerializedName("name")
        private String name;

        @SerializedName("nmax")
        private double nmax;

        @SerializedName("grade_system_type")
        private String gradeSystemType;

        @SerializedName("grade")
        private Grade grade;

        public long getGradeSystemId() {
            return gradeSystemId;
        }

        public void setGradeSystemId(long gradeSystemId) {
            this.gradeSystemId = gradeSystemId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getNmax() {
            return nmax;
        }

        public void setNmax(double nmax) {
            this.nmax = nmax;
        }

        public String getGradeSystemType() {
            return gradeSystemType;
        }

        public void setGradeSystemType(String gradeSystemType) {
            this.gradeSystemType = gradeSystemType;
        }

        public Grade getGrade() {
            return grade;
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }
    }

    public static class Grade {
        @SerializedName("origin")
        private String origin;

        @SerializedName("five")
        private double five;

        @SerializedName("hundred")
        private double hundred;

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public double getFive() {
            return five;
        }

        public void setFive(double five) {
            this.five = five;
        }

        public double getHundred() {
            return hundred;
        }

        public void setHundred(double hundred) {
            this.hundred = hundred;
        }
    }
}