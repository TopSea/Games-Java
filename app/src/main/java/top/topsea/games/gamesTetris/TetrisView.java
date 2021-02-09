package top.topsea.games.gamesTetris;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import top.topsea.games.MainActivity;
import top.topsea.games.fragment.MainTetrisFragment;


public class TetrisView extends View {

    private MediaPlayer mediaPlayer;
    private ArrayTetris gameBoard;
    private MainActivity mainActivity;

    private Timer timer = new Timer();
    private Random random = new Random();
    private ArrayList<Blocks> pieceList;
    private NextBlockView nextPieceView;

    private Points points;
    private final int score = 10;
    private int timerPeriod = 250;
    private int level = 0;
    private boolean pause;
    private int blockSize;
    private Paint paint;

    public TetrisView(Context context) {
        super(context);
        blockSize = dp2Px();
        paint = new Paint();
        this.mainActivity = (MainActivity) context;
        this.gameBoard = ArrayTetris.getArrayTetris();
        this.nextPieceView = new NextBlockView(context);
        pieceList = gameBoard.getPieceList();
        points = new Points(context);
        gameLoop();
    }

    public TetrisView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        blockSize = dp2Px();
        paint = new Paint();
        this.mainActivity = (MainActivity) context;
        this.gameBoard = ArrayTetris.getArrayTetris();
        this.nextPieceView = new NextBlockView(context);
        pieceList = gameBoard.getPieceList();
        points = new Points(context);
        gameLoop();
    }

    public TetrisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        blockSize = dp2Px();
        paint = new Paint();
        this.mainActivity = (MainActivity) context;
        this.gameBoard = ArrayTetris.getArrayTetris();
        this.nextPieceView = new NextBlockView(context);
        pieceList = gameBoard.getPieceList();
        points = new Points(context);
        gameLoop();
    }

    public TetrisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        blockSize = dp2Px();
        paint = new Paint();
        this.mainActivity = (MainActivity) context;
        this.gameBoard = ArrayTetris.getArrayTetris();
        this.nextPieceView = new NextBlockView(context);
        pieceList = gameBoard.getPieceList();
        points = new Points(context);
        gameLoop();
    }

    public void gameLoop() {

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                mainActivity.runOnUiThread(new TimerTask() {

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {

                        if (!gameOver()/* && !mainActivity.getPause()*/) {

                            gameBoard.moveDown(gameBoard.getCurrentPiece());

                            if (!gameBoard.canMoveDown(gameBoard.getCurrentPiece())) {
                                int deletedRows = gameBoard.clearRows();
                                gameBoard.clearRows();
                                pieceList.remove(gameBoard.getCurrentPiece());
                                pieceList.add(new Blocks(random.nextInt(7) + 1));
                                nextPieceView.invalidate();

                                if (deletedRows > 0) {
                                    points.setCurrentPoints(points.getCurrentPoints() + deletedRows * score);
                                    int p = points.getCurrentPoints();
                                    points.setLevel();
                                    MainTetrisFragment.setScore();

//                                    currentPunkteTextView.setText("Points:" + " " + p);
//                                    currentLevelTextView.setText("Level" + " " + points.getLevel());

                                    if (points.getLevel() > points.loadHighscore()) {
                                        points.writeHighscore();
//                                        highscoreLevelTextView.setText("Highscore:" + " " + points.getLevel());
                                    }
                                }

                                if (points.getLevel() > level) {
                                    level++;
                                    timerPeriod = timerPeriod - (points.getLevel() * 20);
                                    timer.cancel();
                                    timer = new Timer();
                                    gameLoop();
                                }
                            }
                            invalidate();
                        }
                    }
                });
            }
        }, 0, timerPeriod);
    }

    public boolean gameOver() {

        if (gameBoard.checkGameOver(gameBoard.getCurrentPiece())) {
            timer.cancel();
            pieceList.clear();
            gameBoard.clearGameBoard();
//            mainActivity.setPause(true);
//            mediaPlayer.stop();
//            showGameOverScreen();
            return true;
        }
        return false;
    }

    public void resetGame() {
        timer.cancel();
        pieceList.clear();
        gameBoard.clearGameBoard();
//        mainActivity.setPause(true);
        mediaPlayer.stop();
        invalidate();
        Intent intent = new Intent(this.getContext(), MainActivity.class);
        getContext().startActivity(intent);
    }

//    public void showGameOverScreen() {
//        Intent intent = new Intent(this.getContext(), GameOverScreen.class);
//        getContext().startActivity(intent);
//    }

    /*
    change colorCode to spezific Color and paint on GAmeboard
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        RectF rel;
        super.onDraw(canvas);
        paint.setColor(Color.BLUE);
        for (int x = 0; x < 25; x ++) {
            for (int y = 0; y < 16; y ++) {
                int color = gameBoard.codeToColor(x, y);
                paint.setColor(color);
                rel = new RectF(y * blockSize, x * blockSize, y * blockSize + blockSize, x * blockSize + blockSize);
                canvas.drawRoundRect(rel, 8, 8, paint);
            }
        }
    }

    private int dp2Px(){
        return (int)(getContext().getResources().getDisplayMetrics().density * 20);
    }

    /*
    control falling pieces with buttons
     */

//    @Override
//    public void onClick(View v) {
//        if (!mainActivity.getPause()) {
//
//            switch (v.getId()) {
//                case R.id.rightButton:
//                    gameBoard.moveRight(gameBoard.getCurrentPiece());
//                    invalidate();
//                    break;
//                case R.id.downButton:
//                    gameBoard.fastDrop(gameBoard.getCurrentPiece());
//                    invalidate();
//                    break;
//                case R.id.leftButton:
//                    gameBoard.moveLeft(gameBoard.getCurrentPiece());
//                    invalidate();
//                    break;
//                case R.id.rotateButton:
//                    gameBoard.rotatePiece(gameBoard.getCurrentPiece());
//                    invalidate();
//                    break;
//            }
//        }
//    }

    public Timer getTimer() {
        return this.timer;
    }
}
