 package top.topsea.games.gamesTetris;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ArrayTetris {

    private final int boardHeight = 30;
    private final int boardWidth = 16;
    private int gameBoard[][] = new int[boardHeight][boardWidth];
    private final Random random = new Random();
    private ArrayList<Blocks> pieceList = new ArrayList<Blocks>();
    private final int number_of_Pieces = 7;

    public ArrayTetris() {
        pieceList.add(new Blocks(random.nextInt(number_of_Pieces) + 1));
        pieceList.add(new Blocks(random.nextInt(number_of_Pieces) + 1));
    }

    /*
    returns Color of x,y gameBoard cell
     */

    public int codeToColor(int x, int y) {

        if (gameBoard[x][y] == 0) return Color.parseColor("#FFFF00");  // Yellow
        if (gameBoard[x][y] == 1) return Color.parseColor("#00FF00");
        ; // Square Green
        if (gameBoard[x][y] == 2) return Color.parseColor("#FF00FF");
        ; //  zpiece Magenta
        if (gameBoard[x][y] == 3) return Color.parseColor("#0000FF");
        ;  // ipiece Blue
        if (gameBoard[x][y] == 4) return Color.parseColor("#00FFFF");
        ;  // tpiece Cyan
        if (gameBoard[x][y] == 5) return Color.parseColor("#ffbf00");
        ;  // spiece Orange
        if (gameBoard[x][y] == 6) return Color.parseColor("#BEBEBE");
        ;  // jpiece gray
        if (gameBoard[x][y] == 7) return Color.parseColor("#FF0000");
        ; // lpiece Red

        return -1;
    }

    public void clearGameBoard() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                gameBoard[i][j] = 0;
            }
        }
    }

    public ArrayList<Blocks> getPieceList() {
        return pieceList;
    }

    public Blocks getCurrentPiece() {
        return pieceList.get(pieceList.size() - 2);
    }

    public Blocks getNextPiece() {
        return pieceList.get(pieceList.size() - 1);
    }

    private void placePiece(Blocks currentPiece) {
        gameBoard[currentPiece.x1][currentPiece.y1] = currentPiece.colorCode;
        gameBoard[currentPiece.x2][currentPiece.y2] = currentPiece.colorCode;
        gameBoard[currentPiece.x3][currentPiece.y3] = currentPiece.colorCode;
        gameBoard[currentPiece.x4][currentPiece.y4] = currentPiece.colorCode;
    }

    private void deletePiece(Blocks currentPiece) {
        gameBoard[currentPiece.x1][currentPiece.y1] = 0;
        gameBoard[currentPiece.x2][currentPiece.y2] = 0;
        gameBoard[currentPiece.x3][currentPiece.y3] = 0;
        gameBoard[currentPiece.x4][currentPiece.y4] = 0;
    }

    /*
    checks if Piece can move in direction x|y
    copy Piece and try to move it, return true
    if it can move
     */

    private boolean pieceCanMove(Blocks currentPiece, int x, int y) {
        int tmp = 0;
        /*
        copy piece coordinates
         */
        Point p1 = new Point(currentPiece.x1, currentPiece.y1);
        Point p2 = new Point(currentPiece.x2, currentPiece.y2);
        Point p3 = new Point(currentPiece.x3, currentPiece.y3);
        Point p4 = new Point(currentPiece.x4, currentPiece.y4);

        Point tmp1 = new Point(currentPiece.x1 + x, currentPiece.y1 + y);
        Point tmp2 = new Point(currentPiece.x2 + x, currentPiece.y2 + y);
        Point tmp3 = new Point(currentPiece.x3 + x, currentPiece.y3 + y);
        Point tmp4 = new Point(currentPiece.x4 + x, currentPiece.y4 + y);

        ArrayList<Point> tmpPieceCoordinates = new ArrayList<Point>();

        tmpPieceCoordinates.add(tmp1);
        tmpPieceCoordinates.add(tmp2);
        tmpPieceCoordinates.add(tmp3);
        tmpPieceCoordinates.add(tmp4);


        for (Point p : tmpPieceCoordinates) {

            if (p.x < boardHeight && p.y >= 0 && p.y < boardWidth && gameBoard[p.x][p.y] == 0) {
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

    private boolean pieceCanRotate(Blocks currentPiece) {
        int tmp = 0;
        ArrayList<Point> tmpPieceCoordinates = new ArrayList<Point>();

        Blocks tmpStein = new Blocks(currentPiece);

        Point p1 = new Point(currentPiece.x1, currentPiece.y1);
        Point p2 = new Point(currentPiece.x2, currentPiece.y2);
        Point p3 = new Point(currentPiece.x3, currentPiece.y3);
        Point p4 = new Point(currentPiece.x4, currentPiece.y4);

        tmpStein.turnPiece();

        Point tmp1 = new Point(tmpStein.x1, tmpStein.y1);
        Point tmp2 = new Point(tmpStein.x2, tmpStein.y2);
        Point tmp3 = new Point(tmpStein.x3, tmpStein.y3);
        Point tmp4 = new Point(tmpStein.x4, tmpStein.y4);

        tmpPieceCoordinates.add(tmp1);
        tmpPieceCoordinates.add(tmp2);
        tmpPieceCoordinates.add(tmp3);
        tmpPieceCoordinates.add(tmp4);

        for (Point p : tmpPieceCoordinates) {

            if (p.x < boardHeight && p.x >= 0 && p.y >= 0 && p.y < boardWidth && gameBoard[p.x][p.y] == 0) {
                tmp++;
            } else if (p.equals(p1) || p.equals(p2) || p.equals(p3) || p.equals(p4)) {
                tmp++;
            }
        }

        /* all four little squares are correct*/
        return tmp == 4;
    }

    private boolean canMoveLeft(Blocks currentPiece) {
        if (pieceCanMove(currentPiece, 0, -1)) {
            return true;
        }
        return false;
    }

    private boolean can_Move_Right(Blocks currentPiece) {
        if (pieceCanMove(currentPiece, 0, 1)) {
            return true;
        }
        return false;
    }

    public boolean canMoveDown(Blocks currentPiece) {
        if (pieceCanMove(currentPiece, 1, 0)) {
            return true;
        }
        return false;
    }


    private void movePiece(Blocks currentPiece, int x, int y) {
        deletePiece(currentPiece);
        currentPiece.move(x, y);
        placePiece(currentPiece);
    }

    public void moveRight(Blocks currentPiece) {
        if (can_Move_Right(currentPiece)) {
            movePiece(currentPiece, 0, 1);
        }
    }

    public void moveLeft(Blocks currentPiece) {
        if (canMoveLeft(currentPiece)) {
            movePiece(currentPiece, 0, -1);
        }
    }

    public void moveDown(Blocks currentPiece) {
        if (canMoveDown(currentPiece)) {
            movePiece(currentPiece, 1, 0);
        }
    }

    public void fastDrop(Blocks currentPiece) {
        deletePiece(currentPiece);

        while (canMoveDown(currentPiece)) {
            moveDown(currentPiece);
        }
        placePiece(currentPiece);
    }

    /*
    turn all pieces until square piece
     */

    public void rotatePiece(Blocks currentPiece) {

        if (pieceCanRotate(currentPiece) && currentPiece.colorCode != 1) {
            deletePiece(currentPiece);
            currentPiece.turnPiece();
            placePiece(currentPiece);
        }
        placePiece(currentPiece);
    }

    public int clearRows() {

        int deletedRowIndex;
        int deletedRows = 0;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        for (int i = 0; i < boardHeight; i++) {
            for (int j = boardWidth - 1; j >= 0; j--) {

                if (gameBoard[i][j] == 0) { // Row not full
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
            int[][] gameBoardCopy = new int[highestRow][boardWidth];

            for (int i = 0; i < gameBoardCopy.length; i++) {
                for (int j = 0; j < gameBoardCopy[1].length; j++) {
                    gameBoardCopy[i][j] = gameBoard[i][j];
                }
            }

            for (int i = 0; i < gameBoardCopy.length; i++) {
                for (int j = 0; j < gameBoardCopy[1].length; j++) {
                    gameBoard[i + deletedRows][j] = gameBoardCopy[i][j];
                }
            }
        }
        return deletedRows;
    }

    public void deleteRow(int r) {
        for (int i = 0; i < boardWidth; i++) {
            gameBoard[r][i] = 0;
        }
    }

    public boolean checkGameOver(Blocks spielStein) {
        if (!canMoveDown(spielStein) && spielStein.getMinXCoordinate(
                spielStein.x1, spielStein.x2, spielStein.x3, spielStein.x4) <= 1) {
            return true;
        }
        return false;
    }

    public int getBoardHeight() {
        return this.boardHeight;
    }

    public int getBoardWidth() {
        return this.boardWidth;
    }
}