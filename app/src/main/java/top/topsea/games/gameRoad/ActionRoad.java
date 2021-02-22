package top.topsea.games.gameRoad;

import android.util.Log;

public class ActionRoad {
    private static int index;
    private static boolean first;

    public static int[][] operationRight(int[][] a, int x, int y, int zeroY) {
        int temp;
        for (int i = zeroY; i >= y + 1; i--) {
            Log.d("zxzxd", ".." + a[x][i]);
            temp = a[x][i];
            a[x][i] = a[x][i - 1];
            a[x][i - 1] = temp;
        }
        return a;
    }

    public static int[][] operationLeft(int[][] a, int x, int y, int zeroY) {
        int temp;
        for (int i = zeroY; i <= y - 1; i++) {
            Log.d("zxzxd", ".." + a[x][i]);
            temp = a[x][i];
            a[x][i] = a[x][i + 1];
            a[x][i + 1] = temp;
        }
        return a;
    }

    public static int[][] operationUp(int[][] a, int x, int y, int zeroX) {
        int temp;
        for (int i = zeroX; i <= x - 1; i++) {
            Log.d("zxzxd", ".." + a[x][i]);
            temp = a[i][y];
            a[i][y] = a[i + 1][y];
            a[i + 1][y] = temp;
        }
        return a;
    }

    public static int[][] operationDown(int[][] a, int x, int y, int zeroX) {
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
                return operationRight(a, x, y, zeroY);
            } else {
                return operationLeft(a, x, y, zeroY);
            }
        }

        if (y == zeroY) {
            if (x < zeroX) {
                return operationDown(a, x, y, zeroX);
            } else {
                return operationUp(a, x, y, zeroX);
            }
        }
        return a;
    }

    public static int[][] RandomPosition(int[][] a) {
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

        for (int i = 0; i < 5; i++) {
            int x = (int) (Math.random() * 4);
            int y = (int) (Math.random() * 4);
            operationRight(a, (zeroX + x) % 4, (zeroY + y) % 4, zeroY);
            operationLeft(a, (zeroX + x) % 4, (zeroY + y) % 4, zeroY);
            operationDown(a, (zeroX + x) % 4, (zeroY + y) % 4, zeroX);
            operationUp(a, (zeroX + x) % 4, (zeroY + y) % 4, zeroX);
            Log.d("///////////////////x", (zeroX + x) % 4 + "");
            Log.d("///////////////////y", (zeroY + y) % 4 + "");
        }

        return a;
    }
}

