package top.topsea.games.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Score {

    @PrimaryKey(autoGenerate = true)
    private int gameId;

    @ColumnInfo(name = "game_name")
    private String gameName;

    @ColumnInfo(name = "game_record")
    private long gameRecord;

    @ColumnInfo(name = "best_record")
    private long bestRecord;

    public Score(String gameName, long gameRecord, long bestRecord) {
        this.gameName = gameName;
        this.gameRecord = gameRecord;
        this.bestRecord = bestRecord;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public long getGameRecord() {
        return gameRecord;
    }

    public void setGameRecord(long gameRecord) {
        this.gameRecord = gameRecord;
    }

    public long getBestRecord() {
        return bestRecord;
    }

    public void setBestRecord(long bestRecord) {
        this.bestRecord = bestRecord;
    }
}
