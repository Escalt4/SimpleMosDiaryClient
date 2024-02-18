package ru.example.simplemosdiaryclient.network.network_entity;

import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class StudentProfile {
    private int id;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("deleted_at")
    private String deletedAt;
    @SerializedName("person_id")
    private String personId;
    private boolean transferred;
    @SerializedName("school_id")
    private int schoolId;
    @SerializedName("organization_id")
    private String organizationId;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("study_mode_id")
    private int studyModeId;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("short_name")
    private String shortName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("middle_name")
    private String middleName;
    @SerializedName("change_password_required")
    private boolean changePasswordRequired;
    @SerializedName("birth_date")
    private String birthDate;
    @SerializedName("enlisted_on")
    private String enlistedOn;
    @SerializedName("gusoev_login")
    private String gusoevLogin;
    private int age;
    private String sex;
    private boolean deleted;
    private String email;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("email_ezd")
    private String emailEzd;
    @SerializedName("phone_number_ezd")
    private String phoneNumberEzd;
    @SerializedName("class_unit")
    @Embedded
    private ClassUnit classUnit;
    @SerializedName("previously_class_unit")
    @Embedded
    private Object previouslyClassUnit;
    @Embedded
    private Curricula curricula;
    @SerializedName("non_attendance")
    private int nonAttendance;
    @Embedded
    private List<Mentor> mentors;
    @SerializedName("ispp_account")
    private int isppAccount;
    @SerializedName("previously_profile_id")
    @Embedded
    private Object previouslyProfileId;
    @SerializedName("student_viewed")
    @Embedded
    private Object studentViewed;
    @SerializedName("migration_date")
    @Embedded
    private Object migrationDate;
    @SerializedName("education_level")
    @Embedded
    private Object educationLevel;
    @SerializedName("class_level")
    @Embedded
    private Object classLevel;
    private String snils;
    @SerializedName("last_sign_in_at")
    private String lastSignInAt;
    @Embedded
    private List<Object> groups;
    @Embedded
    private List<Parent> parents;
    @Embedded
    private List<Object> marks;
    @SerializedName("final_marks")
    @Embedded
    private List<Object> finalMarks;
    @Embedded
    private List<Object> attendances;
    @SerializedName("lesson_comments")
    @Embedded
    private List<Object> lessonComments;
    @SerializedName("home_based_periods")
    @Embedded
    private List<Object> homeBasedPeriods;
    @Embedded
    private List<Object> subjects;
    @SerializedName("ae_attendances")
    @Embedded
    private List<Object> aeAttendances;
    @SerializedName("ec_attendances")
    @Embedded
    private List<Object> ecAttendances;
    @Embedded
    private List<Object> assignments;
    @SerializedName("ae_groups")
    @Embedded
    private List<Object> aeGroups;
    @SerializedName("left_on_registry")
    @Embedded
    private Object leftOnRegistry;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public boolean isTransferred() {
        return transferred;
    }

    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStudyModeId() {
        return studyModeId;
    }

    public void setStudyModeId(int studyModeId) {
        this.studyModeId = studyModeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public boolean isChangePasswordRequired() {
        return changePasswordRequired;
    }

    public void setChangePasswordRequired(boolean changePasswordRequired) {
        this.changePasswordRequired = changePasswordRequired;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEnlistedOn() {
        return enlistedOn;
    }

    public void setEnlistedOn(String enlistedOn) {
        this.enlistedOn = enlistedOn;
    }

    public String getGusoevLogin() {
        return gusoevLogin;
    }

    public void setGusoevLogin(String gusoevLogin) {
        this.gusoevLogin = gusoevLogin;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailEzd() {
        return emailEzd;
    }

    public void setEmailEzd(String emailEzd) {
        this.emailEzd = emailEzd;
    }

    public String getPhoneNumberEzd() {
        return phoneNumberEzd;
    }

    public void setPhoneNumberEzd(String phoneNumberEzd) {
        this.phoneNumberEzd = phoneNumberEzd;
    }

    public ClassUnit getClassUnit() {
        return classUnit;
    }

    public void setClassUnit(ClassUnit classUnit) {
        this.classUnit = classUnit;
    }

    public Object getPreviouslyClassUnit() {
        return previouslyClassUnit;
    }

    public void setPreviouslyClassUnit(Object previouslyClassUnit) {
        this.previouslyClassUnit = previouslyClassUnit;
    }

    public Curricula getCurricula() {
        return curricula;
    }

    public void setCurricula(Curricula curricula) {
        this.curricula = curricula;
    }

    public int getNonAttendance() {
        return nonAttendance;
    }

    public void setNonAttendance(int nonAttendance) {
        this.nonAttendance = nonAttendance;
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(List<Mentor> mentors) {
        this.mentors = mentors;
    }

    public int getIsppAccount() {
        return isppAccount;
    }

    public void setIsppAccount(int isppAccount) {
        this.isppAccount = isppAccount;
    }

    public Object getPreviouslyProfileId() {
        return previouslyProfileId;
    }

    public void setPreviouslyProfileId(Object previouslyProfileId) {
        this.previouslyProfileId = previouslyProfileId;
    }

    public Object getStudentViewed() {
        return studentViewed;
    }

    public void setStudentViewed(Object studentViewed) {
        this.studentViewed = studentViewed;
    }

    public Object getMigrationDate() {
        return migrationDate;
    }

    public void setMigrationDate(Object migrationDate) {
        this.migrationDate = migrationDate;
    }

    public Object getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(Object educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Object getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(Object classLevel) {
        this.classLevel = classLevel;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getLastSignInAt() {
        return lastSignInAt;
    }

    public void setLastSignInAt(String lastSignInAt) {
        this.lastSignInAt = lastSignInAt;
    }

    public List<Object> getGroups() {
        return groups;
    }

    public void setGroups(List<Object> groups) {
        this.groups = groups;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public List<Object> getMarks() {
        return marks;
    }

    public void setMarks(List<Object> marks) {
        this.marks = marks;
    }

    public List<Object> getFinalMarks() {
        return finalMarks;
    }

    public void setFinalMarks(List<Object> finalMarks) {
        this.finalMarks = finalMarks;
    }

    public List<Object> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Object> attendances) {
        this.attendances = attendances;
    }

    public List<Object> getLessonComments() {
        return lessonComments;
    }

    public void setLessonComments(List<Object> lessonComments) {
        this.lessonComments = lessonComments;
    }

    public List<Object> getHomeBasedPeriods() {
        return homeBasedPeriods;
    }

    public void setHomeBasedPeriods(List<Object> homeBasedPeriods) {
        this.homeBasedPeriods = homeBasedPeriods;
    }

    public List<Object> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Object> subjects) {
        this.subjects = subjects;
    }

    public List<Object> getAeAttendances() {
        return aeAttendances;
    }

    public void setAeAttendances(List<Object> aeAttendances) {
        this.aeAttendances = aeAttendances;
    }

    public List<Object> getEcAttendances() {
        return ecAttendances;
    }

    public void setEcAttendances(List<Object> ecAttendances) {
        this.ecAttendances = ecAttendances;
    }

    public List<Object> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Object> assignments) {
        this.assignments = assignments;
    }

    public List<Object> getAeGroups() {
        return aeGroups;
    }

    public void setAeGroups(List<Object> aeGroups) {
        this.aeGroups = aeGroups;
    }

    public Object getLeftOnRegistry() {
        return leftOnRegistry;
    }

    public void setLeftOnRegistry(Object leftOnRegistry) {
        this.leftOnRegistry = leftOnRegistry;
    }

    public static class ClassUnit {
        private int id;
        @SerializedName("class_level_id")
        private int classLevelId;
        private String name;
        @SerializedName("home_based")
        private boolean homeBased;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getClassLevelId() {
            return classLevelId;
        }

        public void setClassLevelId(int classLevelId) {
            this.classLevelId = classLevelId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isHomeBased() {
            return homeBased;
        }

        public void setHomeBased(boolean homeBased) {
            this.homeBased = homeBased;
        }
    }


    public static class Curricula {
        private int id;
        private String name;
        @SerializedName("class_level_id")
        @Embedded
        private Object classLevelId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getClassLevelId() {
            return classLevelId;
        }

        public void setClassLevelId(Object classLevelId) {
            this.classLevelId = classLevelId;
        }
    }

    public static class Mentor {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Parent {
        private int id;
        @SerializedName("user_id")
        private int userId;
        private String type;
        @SerializedName("gusoev_login")
        private Object gusoevLogin;
        private String name;
        @SerializedName("phone_number_ezd")
        private Object phoneNumberEzd;
        @SerializedName("email_ezd")
        private Object emailEzd;
        @SerializedName("phone_number")
        private String phoneNumber;
        private String email;
        private Object snils;
        @SerializedName("last_sign_in_at")
        private String lastSignInAt;
        private boolean hidden;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getGusoevLogin() {
            return gusoevLogin;
        }

        public void setGusoevLogin(Object gusoevLogin) {
            this.gusoevLogin = gusoevLogin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getPhoneNumberEzd() {
            return phoneNumberEzd;
        }

        public void setPhoneNumberEzd(Object phoneNumberEzd) {
            this.phoneNumberEzd = phoneNumberEzd;
        }

        public Object getEmailEzd() {
            return emailEzd;
        }

        public void setEmailEzd(Object emailEzd) {
            this.emailEzd = emailEzd;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getSnils() {
            return snils;
        }

        public void setSnils(Object snils) {
            this.snils = snils;
        }

        public String getLastSignInAt() {
            return lastSignInAt;
        }

        public void setLastSignInAt(String lastSignInAt) {
            this.lastSignInAt = lastSignInAt;
        }

        public boolean isHidden() {
            return hidden;
        }

        public void setHidden(boolean hidden) {
            this.hidden = hidden;
        }
    }
}

