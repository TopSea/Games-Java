package top.topsea.games.gamesTetris;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import top.topsea.games.MainActivity;

import static top.topsea.games.gamesTetris.Blocks.getBlocks;

public class TetrisView extends View {

    private MediaPlayer mediaPlayer;
    private ArrayTetris arrayTetris;
    private MainActivity mainActivity;
    private NextBlockView next;
    private Timer timer = new Timer();
    private Random random = new Random();
    private ArrayList<Blocks> blocks;
    private final int score = 10;
    private int timerPeriod = 250;
    private int level = 0;
    private BlockList blockList;
    private boolean pause;
    private int blockSize;

    private final Paint paint = new Paint();


    public TetrisView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TetrisView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        this.setBackgroundColor(Color.WHITE);
        this.mainActivity = (MainActivity) context;
//        next = new NextBlockView();
        blockList = BlockList.getBlockInstance();
        blocks = blockList.getBlocks();
        next = new NextBlockView(context, blockList);
        blockSize = dp2Px();
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

                        if (!gameOver()) {

                            ArrayTetris.moveDown(blockList.getCurrentBlock());
                            Log.d("moveDown", blockList.getCurrentBlock().toString());
                            if (!ArrayTetris.canMoveDown(blockList.getCurrentBlock())) {
                                int deletedRows = ArrayTetris.clearRows();
                                ArrayTetris.clearRows();
                                blocks.remove(blockList.getCurrentBlock());
                                blocks.add(getBlocks(random.nextInt(7) + 1));
                                next.invalidate();
                                Log.d("block", blockList.getCurrentBlock().toString());
                            }
                            invalidate();
                        }
                    }
                });
            }
        }, 0, timerPeriod);
    }

    public boolean gameOver() {

        return false;
    }

    public void resetGame() {

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        RectF rel;
        super.onDraw(canvas);paint.setColor(Color.BLUE);
        for (int x = 0; x < 25; x ++) {
            for (int y = 0; y < 16; y ++) {
                int color = ArrayTetris.BlockColor(x, y);
                paint.setColor(color);
                rel = new RectF(y * blockSize, x * blockSize, y * blockSize + blockSize, x * blockSize + blockSize);
                canvas.drawRoundRect(rel, 8, 8, paint);
            }
        }
    }

    public int getHowManyBlocks(int screen, int size) {
        return screen / size;
    }

    private int dp2Px(){
        return (int)(getContext().getResources().getDisplayMetrics().density * 20);
    }
}
