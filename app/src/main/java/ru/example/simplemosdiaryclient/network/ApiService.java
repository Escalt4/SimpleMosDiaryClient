package ru.example.simplemosdiaryclient.network;

import ru.example.simplemosdiaryclient.network.network_entity.Mark;
import ru.example.simplemosdiaryclient.network.network_entity.Schedule;
import ru.example.simplemosdiaryclient.network.network_entity.ShortSchedule;
import ru.example.simplemosdiaryclient.network.network_entity.StudentProfile;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {
    @GET("core/api/student_profiles/")
    Call<List<StudentProfile>> getStudentProfiles(@Header("Auth-Token") String authToken);

    @GET("api/family/mobile/v1/schedule/")
    Observable<Schedule> getSchedule(@Header("Auth-Token") String authToken, @Header("x-mes-subsystem") String familymp, @Query("student_id") int studentId, @Query("date") String date);

    @GET("api/family/mobile/v1/schedule/short/")
    Observable<ShortSchedule> getShortSchedule(@Header("Auth-Token") String authToken, @Header("x-mes-subsystem") String familymp, @Query("student_id") int studentId, @Query("dates") String dates);

    @GET("core/api/marks/")
    Observable<List<Mark>> getMarks(@Header("Cookie") String cookie, @Query("created_at_from") String createdAtFrom, @Query("created_at_to") String createdAtTo, @Query("student_id") int studentId);
}