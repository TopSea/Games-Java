package top.topsea.games.gamesTetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import top.topsea.games.R;

public class NextBlockView extends View {

//    private BlockList blockList;
//    private ArrayList<Blocks> blocks;
    private Paint paint;

    private final Bitmap squareBlock = BitmapFactory.decodeResource(getResources(), R.drawable.square);
    private final Bitmap tBlock = BitmapFactory.decodeResource(getResources(), R.drawable.tpiece);
    private final Bitmap zBlock = BitmapFactory.decodeResource(getResources(), R.drawable.zpiece);
    private final Bitmap sBlock = BitmapFactory.decodeResource(getResources(), R.drawable.spiece);
    private final Bitmap jBlock = BitmapFactory.decodeResource(getResources(), R.drawable.jpiece);
    private final Bitmap lBlock = BitmapFactory.decodeResource(getResources(), R.drawable.lpiece);
    private final Bitmap iBlock = BitmapFactory.decodeResource(getResources(), R.drawable.ipiece);

    public NextBlockView(Context context, BlockList blockList) {
        super(context);
//        this.blockList = blockList;
//        blocks = blockList.getBlocks();
//        paint = new Paint();
    }

    public NextBlockView(Context context, AttributeSet attributeSet) {
        super(context);
//        this.blockList = blockList;
//        blocks = blockList.getBlocks();
//        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
//        Log.d("+++++++++++++++++++", "" + blocks.toString());

//        if (blocksList.size() > 0) {
//            Blocks s = gameBoard.getNextBlock();

//            if (Blocks.color == 1) {
                canvas.drawBitmap(squareBlock, 0, 0, paint);
//            }

//            if (Blocks.color == 2) {
//                canvas.drawBitmap(zBlock, 0, 0, paint);
//            }
//
//            if (Blocks.color == 3) {
//                canvas.drawBitmap(iBlock, 0, 0, paint);
//            }
//
//            if (Blocks.color == 4) {
//                canvas.drawBitmap(tBlock, 0, 0, paint);
//            }
//
//            if (Blocks.color == 5) {
//                canvas.drawBitmap(sBlock, 0, 0, paint);
//            }
//
//            if (Blocks.color == 6) {
//                canvas.drawBitmap(jBlock, 0, 0, paint);
//            }
//
//            if (Blocks.color == 7) {
//                canvas.drawBitmap(lBlock, 0, 0, paint);
//            }

//        }
    }
}