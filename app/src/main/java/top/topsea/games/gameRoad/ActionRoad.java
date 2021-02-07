package top.topsea.games.gameRoad;

import android.util.Log;

public class ActionRoad {
    private static int index;
    private static boolean first;

    public static int[][] operationRight(int[][] a, int x, int y, int zeroX, int zeroY) {
        int temp;
        for (int i = zeroY; i >= y + 1; i--) {
            Log.d("zxzxd", ".." + a[x][i]);
            temp = a[x][i];
            a[x][i] = a[x][i - 1];
            a[x][i - 1] = temp;
        }
        return a;
    }

    public static int[][] operationLeft(int[][] a, int x, int y, int zeroX, int zeroY) {
        int temp;
        for (int i = zeroY; i <= y - 1; i++) {
            Log.d("zxzxd", ".." + a[x][i]);
            temp = a[x][i];
            a[x][i] = a[x][i + 1];
            a[x][i + 1] = temp;
        }
        return a;
    }

    public static int[][] operationUp(int[][] a, int x, int y, int zeroX, int zeroY) {
        int temp;
        for (int i = zeroX; i <= x - 1; i++) {
            Log.d("zxzxd", ".." + a[x][i]);
            temp = a[i][y];
            a[i][y] = a[i + 1][y];
            a[i + 1][y] = temp;
        }
        return a;
    }

    public static int[][] operationDown(int[][] a, int x, int y, int zeroX, int zeroY) {
        int temp;
        for (int i = zeroX; i >= x + 1; i--) {
            Log.d("zxzxd", ".." + a[x][i]);
            temp = a[i][y];
            a[i][y] = a[i - 1][y];
            a[i - 1][y] = temp;
        }
        return a;
    }


    public static int[][] DetermineDirection(int[][] a, int x, int y) {
        int zeroX = 0;
        int zeroY = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                }
            }
        }

        if (x == zeroX) {
            if (y < zeroY) {
                return operationRight(a, x, y, zeroX, zeroY);
            } else {
                return operationLeft(a, x, y, zeroX, zeroY);
            }
        }

        if (y == zeroY) {
            if (x < zeroX) {
                return operationDown(a, x, y, zeroX, zeroY);
            } else {
                return operationUp(a, x, y, zeroX, zeroY);
            }
        }
        return a;
    }
}

