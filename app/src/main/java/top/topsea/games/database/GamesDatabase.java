package top.topsea.games.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Score.class}, version = 1, exportSchema = false)
public abstract class GamesDatabase extends RoomDatabase {

    private static GamesDatabase instance;

    public static GamesDatabase getInstance(Context context){
        if (instance == null) {
            synchronized (GamesDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), GamesDatabase.class, "Games")
                            .allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }

    public abstract ScoreDao getScoreDao();

}
