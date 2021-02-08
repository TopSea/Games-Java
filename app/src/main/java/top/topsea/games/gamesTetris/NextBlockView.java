package top.topsea.games.gamesTetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import top.topsea.games.R;

public class NextBlockView extends View {

    private ArrayTetris gameBoard;
    private ArrayList<Blocks> pieceList;

    private final Bitmap squarePiece = BitmapFactory.decodeResource(getResources(), R.drawable.square);
    private final Bitmap tPiece = BitmapFactory.decodeResource(getResources(), R.drawable.tpiece);
    private final Bitmap zPiece = BitmapFactory.decodeResource(getResources(), R.drawable.zpiece);
    private final Bitmap sPiece = BitmapFactory.decodeResource(getResources(), R.drawable.spiece);
    private final Bitmap jPiece = BitmapFactory.decodeResource(getResources(), R.drawable.jpiece);
    private final Bitmap lPiece = BitmapFactory.decodeResource(getResources(), R.drawable.lpiece);
    private final Bitmap iPiece = BitmapFactory.decodeResource(getResources(), R.drawable.ipiece);

    public NextBlockView(Context context) {
        super(context);
        this.gameBoard = ArrayTetris.getArrayTetris();
        pieceList = gameBoard.getPieceList();
    }

    public NextBlockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.gameBoard = ArrayTetris.getArrayTetris();
        pieceList = gameBoard.getPieceList();
    }

    public NextBlockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.gameBoard = ArrayTetris.getArrayTetris();
        pieceList = gameBoard.getPieceList();
    }

    public NextBlockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.gameBoard = ArrayTetris.getArrayTetris();
        pieceList = gameBoard.getPieceList();
    }

//    public synchronized static NextBlockView getNextBlockView(){
//
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();

        if (pieceList.size() > 0) {
            Blocks s = gameBoard.getNextPiece();

            if (s.colorCode == 1) {
                canvas.drawBitmap(squarePiece, 0, 0, p);
            }

            if (s.colorCode == 2) {
                canvas.drawBitmap(zPiece, 0, 0, p);
            }

            if (s.colorCode == 3) {
                canvas.drawBitmap(iPiece, 0, 0, p);
            }

            if (s.colorCode == 4) {
                canvas.drawBitmap(tPiece, 0, 0, p);
            }

            if (s.colorCode == 5) {
                canvas.drawBitmap(sPiece, 0, 0, p);
            }

            if (s.colorCode == 6) {
                canvas.drawBitmap(jPiece, 0, 0, p);
            }

            if (s.colorCode == 7) {
                canvas.drawBitmap(lPiece, 0, 0, p);
            }

        }
    }
}