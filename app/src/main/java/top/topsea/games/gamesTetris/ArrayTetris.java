 package top.topsea.games.gamesTetris;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ArrayTetris {

    private static final int height = 25;
    private static final int width = 16;
    private static int array[][] = new int[height][width];
    private final Random random = new Random();
    private final int number_of_Pieces = 7;

    public static int BlockColor(int x, int y) {

        if (array[x][y] == 0) return Color.parseColor("#FFFF00");
        ; // Yellow
        if (array[x][y] == 1) return Color.parseColor("#00FF00");
        ; // Square Green
        if (array[x][y] == 2) return Color.parseColor("#FF00FF");
        ; //  zpiece Magenta
        if (array[x][y] == 3) return Color.parseColor("#0000FF");
        ;  // ipiece Blue
        if (array[x][y] == 4) return Color.parseColor("#00FFFF");
        ;  // tpiece Cyan
        if (array[x][y] == 5) return Color.parseColor("#ffbf00");
        ;  // spiece Orange
        if (array[x][y] == 6) return Color.parseColor("#BEBEBE");
        ;  // jpiece gray
        if (array[x][y] == 7) return Color.parseColor("#FF0000");
        ; // lpiece Red

        return -1;
    }

    public static void clearGameBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[i][j] = 0;
            }
        }
    }

    private static void placePiece(Blocks currentPiece) {
        array[currentPiece.x1][currentPiece.y1] = currentPiece.color;
        array[currentPiece.x2][currentPiece.y2] = currentPiece.color;
        array[currentPiece.x3][currentPiece.y3] = currentPiece.color;
        array[currentPiece.x4][currentPiece.y4] = currentPiece.color;
    }

    private static void deletePiece(Blocks currentPiece) {
        array[currentPiece.x1][currentPiece.y1] = 0;
        array[currentPiece.x2][currentPiece.y2] = 0;
        array[currentPiece.x3][currentPiece.y3] = 0;
        array[currentPiece.x4][currentPiece.y4] = 0;
    }

    /*
    checks if Piece can move in direction x|y
    copy Piece and try to move it, return true
    if it can move
     */

    private static boolean pieceCanMove(Blocks block, int x, int y) {
        int tmp = 0;
        /*
        copy piece coordinates
         */
        Point p1 = new Point(block.x1, block.y1);
        Point p2 = new Point(block.x2, block.y2);
        Point p3 = new Point(block.x3, block.y3);
        Point p4 = new Point(block.x4, block.y4);

        Point tmp1 = new Point(block.x1 + x, block.y1 + y);
        Point tmp2 = new Point(block.x2 + x, block.y2 + y);
        Point tmp3 = new Point(block.x3 + x, block.y3 + y);
        Point tmp4 = new Point(block.x4 + x, block.y4 + y);

        ArrayList<Point> tmpPieceCoordinates = new ArrayList<Point>();

        tmpPieceCoordinates.add(tmp1);
        tmpPieceCoordinates.add(tmp2);
        tmpPieceCoordinates.add(tmp3);
        tmpPieceCoordinates.add(tmp4);


        for (Point p : tmpPieceCoordinates) {

            if (p.x < height && p.y >= 0 && p.y < width && array[p.x][p.y] == 0) {
                tmp++;
            } else if (p.equals(p1) || p.equals(p2) || p.equals(p3) || p.equals(p4)) {
                tmp++;
            }
        }

        return tmp == 4;
    }

     /*
     copy current Piece and check if it can rotate
     if true return true
      */

    private static boolean pieceCanRotate(Blocks block) {
        int tmp = 0;
        ArrayList<Point> tmpPieceCoordinates = new ArrayList<Point>();

        Blocks tmpStein;
        tmpStein = block;

        Point p1 = new Point(block.x1, block.y1);
        Point p2 = new Point(block.x2, block.y2);
        Point p3 = new Point(block.x3, block.y3);
        Point p4 = new Point(block.x4, block.y4);

        tmpStein.turnBlock();

        Point tmp1 = new Point(tmpStein.x1, tmpStein.y1);
        Point tmp2 = new Point(tmpStein.x2, tmpStein.y2);
        Point tmp3 = new Point(tmpStein.x3, tmpStein.y3);
        Point tmp4 = new Point(tmpStein.x4, tmpStein.y4);

        tmpPieceCoordinates.add(tmp1);
        tmpPieceCoordinates.add(tmp2);
        tmpPieceCoordinates.add(tmp3);
        tmpPieceCoordinates.add(tmp4);

        for (Point p : tmpPieceCoordinates) {

            if (p.x < height && p.x >= 0 && p.y >= 0 && p.y < width && array[p.x][p.y] == 0) {
                tmp++;
            } else if (p.equals(p1) || p.equals(p2) || p.equals(p3) || p.equals(p4)) {
                tmp++;
            }
        }

        /* all four little squares are correct*/
        return tmp == 4;
    }

    private static boolean canMoveLeft(Blocks currentPiece) {
        return pieceCanMove(currentPiece, 0, -1);
    }

    private static boolean canMoveRight(Blocks currentPiece) {
        return pieceCanMove(currentPiece, 0, 1);
    }

    public static boolean canMoveDown(Blocks currentPiece) {
        return pieceCanMove(currentPiece, 1, 0);
    }


    private static void movePiece(Blocks currentPiece, int x, int y) {
        deletePiece(currentPiece);
        currentPiece.move(x, y);
        placePiece(currentPiece);
    }

    public static void moveRight(Blocks currentPiece) {
        if (canMoveRight(currentPiece)) {
            movePiece(currentPiece, 0, 1);
        }
    }

    public static void moveLeft(Blocks currentPiece) {
        if (canMoveLeft(currentPiece)) {
            movePiece(currentPiece, 0, -1);
        }
    }

    public static void moveDown(Blocks currentPiece) {
        if (canMoveDown(currentPiece)) {
            movePiece(currentPiece, 1, 0);
        }
    }

    public static void fastDrop(Blocks currentPiece) {
        deletePiece(currentPiece);

        while (canMoveDown(currentPiece)) {
            moveDown(currentPiece);
        }
        placePiece(currentPiece);
    }

    /*
    turn all pieces until square piece
     */

    public static void rotateBlock(Blocks currentPiece) {

        if (pieceCanRotate(currentPiece) && currentPiece.color != 1) {
            deletePiece(currentPiece);
            currentPiece.turnBlock();
            placePiece(currentPiece);
        }
        placePiece(currentPiece);
    }

    public static int clearRows() {

        int deletedRowIndex;
        int deletedRows = 0;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        for (int i = 0; i < height; i++) {
            for (int j = width - 1; j >= 0; j--) {

                if (array[i][j] == 0) { // Row not full
                    break;
                }
                if (j == 0) {
                    deletedRowIndex = i;
                    arrayList.add(deletedRowIndex);
                    deletedRows++;
                    deleteRow(deletedRowIndex);
                }
            }
        }

        if (deletedRows >= 1) {
            int highestRow = Collections.min(arrayList); // highest Row which can be cleared
            int[][] gameBoardCopy = new int[highestRow][width];

            for (int i = 0; i < gameBoardCopy.length; i++) {
                for (int j = 0; j < gameBoardCopy[1].length; j++) {
                    gameBoardCopy[i][j] = array[i][j];
                }
            }

            for (int i = 0; i < gameBoardCopy.length; i++) {
                for (int j = 0; j < gameBoardCopy[1].length; j++) {
                    array[i + deletedRows][j] = gameBoardCopy[i][j];
                }
            }
        }
        return deletedRows;
    }

    public static void deleteRow(int r) {
        for (int i = 0; i < width; i++) {
            array[r][i] = 0;
        }
    }

    public boolean checkGameOver(Blocks spielStein) {
        if (!canMoveDown(spielStein) && spielStein.getMinXCoordinate(
                spielStein.x1, spielStein.x2, spielStein.x3, spielStein.x4) <= 1) {
            return true;
        }
        return false;
    }

}