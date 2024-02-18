package ru.example.simplemosdiaryclient.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofitDnevnik;
    private static Retrofit retrofitSchool;
    private static final String BASE_DNEVNIK_URL = "https://dnevnik.mos.ru/";
    private static final String BASE_SCHOOL_URL = "https://school.mos.ru/";


    public static Retrofit getDnevnikClient() {
        if (retrofitDnevnik == null) {
            retrofitDnevnik = new Retrofit.Builder()
                    .baseUrl(BASE_DNEVNIK_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitDnevnik;
    }

    public static Retrofit getSchoolClient() {
        if (retrofitSchool == null) {
            retrofitSchool = new Retrofit.Builder()
                    .baseUrl(BASE_SCHOOL_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitSchool;
    }
}
