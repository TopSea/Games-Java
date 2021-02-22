package top.topsea.games.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;

import top.topsea.games.database.GamesDatabase;
import top.topsea.games.database.Score;
import top.topsea.games.database.ScoreDao;
import top.topsea.games.game2048.Action2048;
import top.topsea.games.game2048.Array2048;
import top.topsea.games.utils.FileOperation;
import top.topsea.games.MainActivity;
import top.topsea.games.R;

public class Main2048Fragment extends Fragment implements GestureDetector.OnGestureListener {

    private final int width = 4;
    private final int length = 4;
    private int bestScore;
    private final String fileName = "2048.array";
    private TextView[][] texts;
    private int[][] textViewID;
    private int[][] array2048;
    private GestureDetectorCompat mDetector;
    private int score = 0;
    TextView yourScore, best;
    private Context context, contextToast;
    private Toast toastSave, toastDone;

    //database
    private GamesDatabase database;
    private ScoreDao scoreDao;
    private Score scoreD;

    public Main2048Fragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_2048, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        database = GamesDatabase.getInstance(getContext());
        scoreDao = database.getScoreDao();
        scoreD = scoreDao.getScore("2048");

        context = getContext();
        contextToast = getContext();
        texts = new TextView[width][length];

        if (savedInstanceState != null){
            textViewID = FileOperation.StringToArray(savedInstanceState.get("textViewID").toString());
            array2048 = FileOperation.StringToArray(savedInstanceState.get("state").toString());
        }else {
            textViewID = new int[][]{
                    {R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4},
                    {R.id.textView5, R.id.textView6, R.id.textView7, R.id.textView8},
                    {R.id.textView9, R.id.textView10, R.id.textView11, R.id.textView12},
                    {R.id.textView13, R.id.textView14, R.id.textView15, R.id.textView16}
            };

            try {
                array2048 = FileOperation.StringToArray(FileOperation.readArray(context, fileName));
                bestScore = (int) scoreD.getBestRecord();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        score = Array2048.getScore(array2048);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                texts[i][j] = getActivity().findViewById(textViewID[i][j]);
            }
        }
        yourScore = getView().findViewById(R.id.now_score_road);
        best = getView().findViewById(R.id.best_2048);
        best.setOnLongClickListener(v -> {
            Print2048(Cheating());
            yourScore.setText("Score: " + Array2048.getScore(Cheating()));
            return true;
        });

        mDetector = new GestureDetectorCompat(getActivity(), this);

        //触摸监听
        MainActivity.MyOnTouchListener myOnTouchListener = ev -> mDetector.onTouchEvent(ev);
        ((MainActivity) requireActivity()).registerMyOnTouchListener(myOnTouchListener);

        Button save = getView().findViewById(R.id.button_save_2048);
        save.setOnClickListener(v -> {
            bestScore = Math.max(score, bestScore);
            scoreD.setBestRecord(bestScore);
            scoreDao.updateScore(scoreD);
            FileOperation.saveArray(context, array2048, fileName);
            toastSave.show();
            NavController navController = Navigation.findNavController(getView());
            navController.navigateUp();
        });

        Button reGame = getView().findViewById(R.id.button_regame_2048);
        reGame.setOnClickListener(v -> {
            bestScore = Math.max(score, bestScore);
            scoreD.setBestRecord(bestScore);
            scoreDao.updateScore(scoreD);
            score = 0;
            array2048 = Array2048.InitializeArray(width, length);
            FileOperation.saveArray(context, array2048, fileName);
            Print2048(array2048);
        });

        toastSave = Toast.makeText(contextToast, "保存成功！", Toast.LENGTH_SHORT);
        toastDone = Toast.makeText(contextToast, "游戏结束，得分: " + score, Toast.LENGTH_SHORT);
        //打印
        Print2048(array2048);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("textViewID", FileOperation.ArrayToString(textViewID));
        outState.putString("state", FileOperation.ArrayToString(array2048));
    }

    @Override
    public void onStop() {
        super.onStop();
        FileOperation.saveArray(context, array2048, fileName);
        bestScore = Math.max(score, bestScore);
        scoreD.setBestRecord(bestScore);
        scoreDao.updateScore(scoreD);
//        FileOperation.SaveBest(context, Math.max(score, bestScore) + "", 1);
        toastSave.show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @SuppressLint("SetTextI18n")
    private void Print2048(int[][] array) {
        int red;
        int green;
        int blue;
        int transparent;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (array[i][j] == 0){
                    texts[i][j].setBackgroundColor(Color.LTGRAY);
                    texts[i][j].setText("");
                }else{
                    texts[i][j].setText(array[i][j] + "");
                    //2的次数
                    int temp = (int)(Math.log(array[i][j]) / Math.log(2));
                    red = temp * 8 + 127;
                    green = 80 - temp * 5;
                    blue = 48 - temp * 3;
                    transparent = (array[i][j] == 0) ? 0 : red;
                    texts[i][j].setBackgroundColor(Color.argb(transparent, red, green, blue));
                }
            }
        }
        yourScore.setText("Score: " + score);
        best.setText("最佳纪录：" + bestScore);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        double moveX = e1.getRawX() - e2.getRawX();
        double moveY = e1.getRawY() - e2.getRawY();

        int[][] check = array2048;
        boolean isDone = false;
        if (Math.abs(moveX) > Math.abs(moveY)) {
            if (moveX < 0) {
                //向右
                array2048 = Action2048.operationRight(array2048);
            } else {
                //向左
                array2048 = Action2048.operationLeft(array2048);
            }
            isDone = Array2048.CountZeros(Action2048.operationDown(array2048)) == -1;
        } else {
            if (moveY < 0) {
                //向下
                array2048 = Action2048.operationDown(array2048);
            } else {
                //向上
                array2048 = Action2048.operationUp(array2048);
            }
            isDone = Array2048.CountZeros(Action2048.operationLeft(array2048)) == -1;
        }
        Log.d("++++++++++++", ""+isDone);
        if (isDone){
            FileOperation.SaveBest(context, Math.max(score, bestScore) + "", 1);
            toastDone.show();
        }else {
            if (!Arrays.deepEquals(check, array2048)) {
                array2048 = Array2048.RandomNum(array2048);
                score += 2;
                Print2048(array2048);
            }
        }
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    private int[][] Cheating(){
        int[][] cheat = new int[length][width];
        int num = 2;
        for (int i = 0; i < cheat.length; i++) {
            if(i == 1 || i == 3){
                for (int j = cheat[0].length - 1; j >= 0; j--) {
                    cheat[i][j] = num;
                    num *= 2;
                }
            }else {
                for (int j = 0; j < cheat[0].length; j++) {
                    cheat[i][j] = num;
                    num *= 2;
                }
            }
        }
        return cheat;
    }
}