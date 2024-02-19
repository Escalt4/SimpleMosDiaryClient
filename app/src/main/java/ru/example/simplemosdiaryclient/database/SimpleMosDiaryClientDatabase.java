package ru.example.simplemosdiaryclient.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.example.simplemosdiaryclient.database.database_entity.Lesson;
import ru.example.simplemosdiaryclient.database.database_entity.Mark;


@Database(entities = {Lesson.class}, version = 4, exportSchema = false)
public abstract class SimpleMosDiaryClientDatabase extends RoomDatabase {
    public abstract SimpleMosDiaryClientDao simpleMosDiaryClientDao();

    private static volatile SimpleMosDiaryClientDatabase instance;

    public static synchronized SimpleMosDiaryClientDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (SimpleMosDiaryClientDatabase.class) {
                if (instance == null) {
                    instance = buildDatabase(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private static SimpleMosDiaryClientDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, SimpleMosDiaryClientDatabase.class, "SimpleMosDiaryClientDatabase.db").fallbackToDestructiveMigration().build();
    }
}