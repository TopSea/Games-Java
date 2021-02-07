package top.topsea.games.game2048;

public class Action2048 {

    private static int index;
    private static boolean first;
    private static boolean sign;

    public static int[][] operationRight(int[][] a) {
        int[][] result = new int[a.length][a[0].length];

        for (int i = 0; i < a.length; i++) {
            index = a[0].length - 1;
            first = true;
            sign = true;
            for (int j = a[0].length - 1; j >= 0; j--) {
                if (a[i][j] != 0) {
                    if (first) {
                        result[i][index] = a[i][j];
                        first = false;
                        continue;
                    }
                    if (a[i][j] == result[i][index] && sign) {
                        result[i][index] *= 2;
                        sign = false;
                    } else {
                        index--;
                        result[i][index] = a[i][j];
                        sign = true;
                    }
                }
            }
        }
        return result;
    }

    public static int[][] operationLeft(int[][] a) {
        int[][] result = new int[a.length][a[0].length];

        for (int i = 0; i < a.length; i++) {
            index = 0;
            first = true;
            sign = true;
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] != 0) {
                    if (first) {
                        result[i][index] = a[i][j];
                        first = false;
                        continue;
                    }
                    if (a[i][j] == result[i][index] && sign) {
                        result[i][index] *= 2;
                        sign = false;
                    } else {
                        index++;
                        result[i][index] = a[i][j];
                        sign = true;
                    }
                }
            }
        }
        return result;
    }

    public static int[][] operationUp(int[][] a) {
        int[][] result = new int[a.length][a[0].length];

        for (int j = 0; j < a[0].length; j++) {
            index = 0;
            first = true;
            sign = true;
            for (int i = 0; i < a.length; i++) {
                if (a[i][j] != 0) {
                    if (first) {
                        result[index][j] = a[i][j];
                        first = false;
                        continue;
                    }
                    if (a[i][j] == result[index][j] && sign) {
                        result[index][j] *= 2;
                        sign = false;
                    } else {
                        index++;
                        result[index][j] = a[i][j];
                        sign = true;
                    }
                }
            }
        }
        return result;
    }

    public static int[][] operationDown(int[][] a) {
        int[][] result = new int[a.length][a[0].length];

        for (int j = 0; j < a[0].length; j++) {
            index = a.length - 1;
            first = true;
            sign = true;
            for (int i = a.length - 1; i >= 0; i--) {
                if (a[i][j] != 0) {
                    if (first) {
                        result[index][j] = a[i][j];
                        first = false;
                        continue;
                    }
                    if (a[i][j] == result[index][j] && sign) {
                        result[index][j] *= 2;
                        sign = false;
                    } else {
                        index--;
                        result[index][j] = a[i][j];
                        sign = true;
                    }
                }
            }
        }
        return result;
    }

}
