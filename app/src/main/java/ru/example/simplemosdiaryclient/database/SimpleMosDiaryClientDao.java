package ru.example.simplemosdiaryclient.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ru.example.simplemosdiaryclient.database.database_entity.Lesson;


@Dao
public interface SimpleMosDiaryClientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLessonList(List<Lesson> lessonList);

    @Query("SELECT * FROM Lesson WHERE lessonDate =:date ORDER BY lessonNum")
    LiveData<List<Lesson>> getLessonsLiveDataByDate(String date);


//    @Insert
//    void insertLessonList(List<Lesson> pets);
//
//    @Query("SELECT * FROM ScheduleItem WHERE date =:date")
//    ScheduleItem getScheduleItemByDate(String date);
//
//    @Query("SELECT * FROM Lesson WHERE scheduleItemDate =:scheduleItemDate")
//    List<Lesson> getLessonList(String scheduleItemDate);
//
//    default void insertScheduleItemWithLessons(ScheduleItem scheduleItem) {
//        List<Lesson> lessons = scheduleItem.getLessons();
//        for (int i = 0; i < lessons.size(); i++) {
//            lessons.get(i).setScheduleItemDate(scheduleItem.getDate());
//        }
//        insertLessonList(lessons);
//        insertScheduleItem(scheduleItem);
//    }
//
//    User getUserWithPets(int id) {
//        User user = getUser(id);
//        List<Pet> pets = getPetList(id);
//        user.setPetList(pets);
//        return user;
//    }


//    @Query("SELECT * FROM student_profiles WHERE id = :studentId")
//    Single<StudentProfile> getStudentProfileById(int studentId);

//    // Map
//    @Insert
//    void insertTile(MapTile mapTile);
//
//    @Query("SELECT * FROM MapTile WHERE tileKey = :key")
//    MapTile getTileByKey(String key);
//
//    @Query("DELETE FROM MapTile WHERE tileTime != :tileTime")
//    void deleteAllTile(long tileTime);
//
//    @Query("DELETE FROM MapTile")
//    void deleteAllTile( );
//
//    // Forecast and Weather
//    @Query("SELECT * FROM WeatherData WHERE dataType IS :dataType AND stationId IS :stationId")
//    List<WeatherData> getWeatherData(int dataType, long stationId);
//
//    @Query("SELECT * FROM WeatherData WHERE dataType IS :dataType AND stationId IS :stationId")
//    WeatherData getOneWeatherData(int dataType, long stationId);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertWeatherDataList(List<WeatherData> weatherDataList);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertWeatherData(WeatherData weatherData);
//
//    @Query("DELETE FROM WeatherData where stationId IS :stationId")
//    void deleteWeatherDataByStationId(long stationId);
//
//    @Query("DELETE FROM WeatherData where dataType IS :dataType AND stationId IS :stationId")
//    void deleteWeatherDataBy(int dataType, long stationId);
//
//    @Query("SELECT COALESCE(MAX(id)+1,0) FROM WeatherData")
//    Long getNextIdWeatherData();
//
//    @Query("SELECT COUNT(*) FROM WeatherData WHERE  dataType IS :dataType AND dateTime IS :timeInMillis ")
//    Long countWeatherData(int dataType,long timeInMillis);
//
//    @Query("DELETE FROM WeatherData WHERE  dataType IS :dataType AND dateTime IS :timeInMillis AND stationId IS :stationId")
//    void deleteWeatherDataByDate(int dataType,long timeInMillis,long stationId);
//
//    @Query("DELETE FROM WeatherData WHERE  dataType IS :dataType AND createDateTime <= :timeInMillis AND stationId IS :stationId")
//    void deleteWeatherDataByCreateDate(int dataType,long timeInMillis,long stationId);
//
//    @Query("DELETE FROM WeatherData WHERE  dataType IS :dataType AND createDateTime <= :createDateTime AND dateTime IS :dateTime AND stationId IS :stationId")
//    void deleteWeatherDataByCreateDate2(int dataType,long createDateTime,long dateTime,long stationId);
//
//
//    // Warnings
//    @Query("SELECT * FROM WarningsData WHERE regionId IS :regionId")
//    List<WarningsData> getWarningsData(long regionId);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertWarningsDataList(List<WarningsData> warningsDataList);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertWarningsData(WarningsData warningsData);
//
//    @Query("DELETE FROM WarningsData where regionId IS :regionId")
//    void deleteWarningsDataByRegionId(long regionId);
//
//    @Query("SELECT COALESCE(MAX(id)+1,0) FROM WarningsData")
//    Long getNextIdWarningsData();
//
//
//    // LastStationId
//    @Query("SELECT * FROM Station WHERE posInLastStationList >0")
//    List<Station> getAllLastStations();
//
//    @Query("UPDATE Station SET posInLastStationList = (SELECT MAX(posInLastStationList)+1 FROM Station) WHERE id IS :id")
//    void setPosInLastStation(Long id);
//
//    @Query("UPDATE Station SET posInLastStationList = 0 WHERE id IS :id")
//    void removeFromLastStation(Long id);
//
//    @Query("SELECT *  FROM Station WHERE posInLastStationList = (SELECT MAX(posInLastStationList) FROM Station)")
//    Station getLastStation();
//
//
//    // Station
//    @Query("DELETE FROM Station")
//    void deleteAllStation();
//
//    @Query("SELECT count(*) FROM Station")
//    long getCountStation();
//
//    @Query("SELECT DISTINCT country FROM Station")
//    List<String> getAllCountry();
//
//    @Query("SELECT DISTINCT area FROM Station WHERE Station.country IS :country")
//    List<String> getAreasByCountry(String country);
//
//    @Query("SELECT DISTINCT city FROM Station WHERE Station.country IS :country AND Station.area IS :area")
//    List<String> getCitiesByCountryAndArea(String country, String area);
//
//    @Query("SELECT * FROM Station WHERE Station.country IS :country AND Station.area IS :area AND Station.city IS :city")
//    Station getStationByPlace(String country, String area, String city);
//
//    @Query("SELECT * FROM Station WHERE Station.id IS :id")
//    Station getStationById(long id);
//
//    @Query("SELECT * FROM Station")
//    List<Station> getAllStation();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertStationsList(List<Station> stations);
}