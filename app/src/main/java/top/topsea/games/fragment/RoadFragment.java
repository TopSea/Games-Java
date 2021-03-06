package top.topsea.games.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;

import top.topsea.games.R;
import top.topsea.games.database.GamesDatabase;
import top.topsea.games.database.Score;
import top.topsea.games.database.ScoreDao;
import top.topsea.games.gameRoad.ActionRoad;
import top.topsea.games.gameRoad.ArrayRoad;
import top.topsea.games.utils.FileOperation;

import static top.topsea.games.utils.DateOperation.String2Long;
import static top.topsea.games.utils.DateOperation.String2LongDate;


public class RoadFragment extends Fragment {

    private final int width = 4;
    private final int length = 4;
    private final String fileName = "road.array";
    private TextView[][] texts;
    private int[][] textViewID;
    private int[][] arrayRoad;
    private Chronometer chronometerScore, chronometerBest;
    private long score;
    private long best;

    private boolean isPause = false;
    private String pauseTime = "";

    //database
    private GamesDatabase database;
    private ScoreDao scoreDao;
    private Score scoreD;

    public RoadFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_road, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        database = GamesDatabase.getInstance(getContext());
        scoreDao = database.getScoreDao();
        scoreD = scoreDao.getScore("Road");

        texts = new TextView[width][length];
        chronometerScore = getView().findViewById(R.id.now_score_road);
        chronometerBest = getView().findViewById(R.id.best_road);

        if (savedInstanceState != null) {
            textViewID = FileOperation.StringToArray(savedInstanceState.get("roadID").toString());
            arrayRoad = FileOperation.StringToArray(savedInstanceState.get("roadState").toString());
            score = Long.parseLong(savedInstanceState.get("SCORE").toString());
        } else {
            textViewID = new int[][]{
                    {R.id.road1, R.id.road2, R.id.road3, R.id.road4},
                    {R.id.road5, R.id.road6, R.id.road7, R.id.road8},
                    {R.id.road9, R.id.road10, R.id.road11, R.id.road12},
                    {R.id.road13, R.id.road14, R.id.road15, R.id.road16}
            };

            try {
                arrayRoad = FileOperation.StringToArray(FileOperation.readArray(getContext(), fileName));
                best = scoreD.getBestRecord();
                score = scoreD.getGameRecord();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        chronometerScore.setBase(score);
        chronometerBest.setBase(best);

        chronometerScore.start();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                texts[i][j] = getActivity().findViewById(textViewID[i][j]);
                int finalI = i;
                int finalJ = j;
                texts[i][j].setOnClickListener(v -> {
                    arrayRoad = ActionRoad.DetermineDirection(arrayRoad, finalI, finalJ);
                    PrintRoad();
                    if (ArrayRoad.isDone(arrayRoad)) {
                        chronometerScore.stop();
                        long score = String2Long(chronometerScore.getText().toString());
                        long best = Math.max(String2Long(chronometerBest.getText().toString()), score);
                        scoreD.setBestRecord(best);
                        chronometerBest.setBase(best);
                        scoreDao.updateScore(scoreD);
                        Toast toast = Toast.makeText(getContext(), "游戏结束，用时: " + chronometerScore.getText(), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        }

        Button save = getView().findViewById(R.id.button_save_road);
        save.setOnClickListener(v -> {
            if (isPause) {
                chronometerScore.setBase(String2Long(pauseTime));
                chronometerScore.start();
                save.setText(R.string.button_pause);
                isPause = false;
            } else {
                pauseTime = chronometerScore.getText().toString();
                save.setText(R.string.button_continue);
                chronometerScore.stop();
                isPause = true;
            }
        });

        Button reGame = getView().findViewById(R.id.button_regame_road);
        reGame.setOnClickListener(v -> {
            chronometerScore.setBase(SystemClock.elapsedRealtime());
            chronometerScore.start();
            arrayRoad = ArrayRoad.InitializeArray();
            PrintRoad();
        });

        //打印
        PrintRoad();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        long saveScore = chronometerScore.getBase();
        outState.putString("roadID", FileOperation.ArrayToString(textViewID));
        outState.putString("roadState", FileOperation.ArrayToString(arrayRoad));
        outState.putString("SCORE", saveScore + "");
    }

    @Override
    public void onStop() {
        FileOperation.saveArray(getContext(), arrayRoad, fileName);
        long score = String2Long(chronometerScore.getText().toString());
        scoreD.setGameRecord(score);
        scoreDao.updateScore(scoreD);
        pauseTime = chronometerScore.getText().toString();
        chronometerScore.stop();

        Toast toast = Toast.makeText(getContext(), "保存成功！", Toast.LENGTH_SHORT);
        toast.show();
        super.onStop();
    }

    @Override
    public void onResume() {

        chronometerScore.setBase(String2Long(pauseTime));
        chronometerScore.start();
        super.onResume();
    }

    @SuppressLint("SetTextI18n")
    private void PrintRoad() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arrayRoad[i][j] == 0) {
                    texts[i][j].setText("");
                    texts[i][j].setBackgroundColor(Color.WHITE);
                } else {
                    texts[i][j].setText(arrayRoad[i][j] + "");
                    texts[i][j].setBackgroundColor(Color.LTGRAY);
                }
            }
        }
    }

}