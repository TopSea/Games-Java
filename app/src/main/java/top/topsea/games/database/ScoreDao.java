package top.topsea.games.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ScoreDao {
    @Query("SELECT * FROM score")
    List<Score> getAll();

    @Query("SELECT * FROM score WHERE game_name LIKE :gameName")
    Score getScore(String gameName);

    @Query("SELECT game_record FROM score WHERE game_name LIKE :gameName LIMIT 1")
    long getRecord(String gameName);

    @Query("SELECT best_record FROM score WHERE game_name LIKE :gameName LIMIT 1")
    long getBest(String gameName);

    @Insert
    void insertScore(Score... score);

    @Update
    void updateScore(Score score);

    @Delete
    void deleteScore(Score score);

}
