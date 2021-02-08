package top.topsea.games.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import top.topsea.games.MainActivity;
import top.topsea.games.R;
import top.topsea.games.game2048.Action2048;
import top.topsea.games.game2048.Array2048;
import top.topsea.games.gamesTetris.ArrayTetris;
import top.topsea.games.gamesTetris.BlockList;
import top.topsea.games.gamesTetris.Blocks;
import top.topsea.games.utils.FileOperation;


public class MainTetrisFragment extends Fragment implements GestureDetector.OnGestureListener {

    private Button regame, save;
    private TextView bestScore, nowScore;
    private GestureDetectorCompat mDetector;
    private ArrayTetris arrayTetris;
    private static ArrayList<Blocks> blockList = new ArrayList<Blocks>();

    public MainTetrisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        regame = getView().findViewById(R.id.button_tetris_regame);
        regame.setOnClickListener(v -> {

        });

        save = getView().findViewById(R.id.button_tetris_save);
        save.setOnClickListener(v -> {

        });

        bestScore = getView().findViewById(R.id.tetris_best);
        nowScore = getView().findViewById(R.id.tetris_now);

        arrayTetris = ArrayTetris.getArrayTetris();
        blockList = arrayTetris.getPieceList();

        mDetector = new GestureDetectorCompat(getActivity(), this);

        //触摸监听
        MainActivity.MyOnTouchListener myOnTouchListener = ev -> mDetector.onTouchEvent(ev);
        ((MainActivity) requireActivity()).registerMyOnTouchListener(myOnTouchListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tetris, container, false);
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
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        double moveX = e1.getRawX() - e2.getRawX();
        double moveY = e1.getRawY() - e2.getRawY();

        boolean isDone = false;
        if (Math.abs(moveX) > Math.abs(moveY)) {
            if (moveX < 0) {
                //向右
                arrayTetris.moveRight(arrayTetris.getCurrentPiece());
            } else {
                //向左
                arrayTetris.moveLeft(arrayTetris.getCurrentPiece());
            }
        } else {
            if (moveY < 0) {
                //向下
            } else {
                //向上
                arrayTetris.rotatePiece(arrayTetris.getCurrentPiece());
            }
        }
        return true;
    }
}