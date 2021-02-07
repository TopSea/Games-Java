package top.topsea.games.utils;

import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

import top.topsea.games.database.Score;

public class DatabaseUtils {

    public static List<Score> InitData(){
        ArrayList<Score> scores = new ArrayList<>();
        //2048
        Score score2048 = new Score("2048", 0, 0);

        //数字华容道
        Score scoreRoad = new Score("Road", SystemClock.elapsedRealtime(), 0);

        //俄罗斯方块
        Score scoreTetris = new Score("Tetris", 0, 0);

        scores.add(score2048);
        scores.add(scoreRoad);
        scores.add(scoreTetris);

        return scores;
    }

}
